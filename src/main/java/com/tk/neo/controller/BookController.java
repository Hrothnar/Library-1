package com.tk.neo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tk.neo.dao.BookDAO;
import com.tk.neo.dao.PersonDAO;
import com.tk.neo.model.Book;
import com.tk.neo.model.Person;

@Controller
@RequestMapping("/book")
public class BookController {
	private final BookDAO bookDAO;
	private final PersonDAO personDAO;

	@Autowired
	public BookController(BookDAO bookDAO, PersonDAO personDAO) {
		this.bookDAO = bookDAO;
		this.personDAO = personDAO;
	}

	@GetMapping("/all")
	public String showAll(Model model) {
		model.addAttribute("books", bookDAO.getAll());
		return "book/all";
	}

	@GetMapping("/create")
	public String create(@ModelAttribute("book") Book book) {
		return "book/create";
	}

	@PostMapping()
	public String saveCreation(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "book/create";
		}
		bookDAO.save(book);
		return "redirect:/person/menu";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") long id, Model model) {
		Book book = bookDAO.getById(id);
		Person person = personDAO.getById(book.getPersonId());
		model.addAttribute("book", book);
		model.addAttribute("person", person);
		model.addAttribute("people", personDAO.getAll());
		return "book/show";
	}

	@PostMapping("/{id}/attach")
	public String attachBook(@PathVariable("id") long id, @RequestParam("person_id") long personId) {
		bookDAO.attachPerson(id, personId);
		return "redirect:/book/all";
	}

	@GetMapping("/{id}/release")
	public String releaseBook(@PathVariable("id") long id) {
		bookDAO.releaseBook(id);
		return "redirect:/book/" + id;
	}

	@GetMapping("/{id}/update")
	public String updateBook(@PathVariable("id") long id, Model model) {
		model.addAttribute("book", bookDAO.getById(id));
		return "book/update";
	}

	@PostMapping("/{id}")
	public String saveUpdation(@PathVariable("id") long id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "book/update";
		}
		bookDAO.update(id, book);
		return "redirect:/book/" + id;
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") long id) {
		bookDAO.remove(id);
		return "redirect:/book/all";
	}

}
