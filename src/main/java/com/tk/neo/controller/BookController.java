package com.tk.neo.controller;

import java.util.List;

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

import com.tk.neo.model.dto.BookDTO;
import com.tk.neo.model.dto.PersonDTO;
import com.tk.neo.model.service.interfaccia.BookService;
import com.tk.neo.model.service.interfaccia.PersonService;

@Controller
@RequestMapping("/book")
public class BookController {
	private final BookService bookService;
	private final PersonService personService;
	
	private static final String BOOKS_PER_PAGE = "3";

	@Autowired
	public BookController(BookService bookService, PersonService personService) {
		this.bookService = bookService;
		this.personService = personService;
	}

	@GetMapping("/all")
	public String showAll(@RequestParam(name = "page", required = false) String page, Model model) {
		List<BookDTO> books = bookService.getAllBooks(page, BOOKS_PER_PAGE);
		model.addAttribute("books", books);
		return "book/all";
	}

	@GetMapping("/find")
	public String find() {
		return "book/find";
	}

	@PostMapping("/look")
	public String lookForBook(@RequestParam(name = "search") String search, Model model) {
		List<BookDTO> books = bookService.findBooks(search);
		model.addAttribute("books", books);
		return books.isEmpty() ? "book/not_found" : "book/all";
	}
	

	@GetMapping("/create")
	public String create(@ModelAttribute("book") BookDTO bookDTO) {
		return "book/create";
	}

	@PostMapping()
	public String saveCreation(@ModelAttribute("book") @Valid BookDTO bookDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "book/create";
		}
		bookService.saveBook(bookDTO);
		return "redirect:/";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") long id, Model model) {
		BookDTO bookDTO = bookService.getBook(id);
		List<PersonDTO> peopleDTO = personService.getAllPeople();
		model.addAttribute("book", bookDTO );
		model.addAttribute("person", bookDTO .personDTO);
		model.addAttribute("people", peopleDTO);
		return "book/show";
	}

	@PostMapping("/{id}/attach")
	public String attachBook(@PathVariable("id") long id, @RequestParam("personId") long personId) {
		bookService.attachBook(id, personId);
		return "redirect:/book/all";
	}
	

	@GetMapping("/{id}/release")
	public String releaseBook(@PathVariable("id") long id) {
		bookService.releaseBook(id);
		return "redirect:/book/" + id;
	}

	@GetMapping("/{id}/update")
	public String updateBook(@PathVariable("id") long id, Model model) {
		BookDTO bookDTO = bookService.getBook(id);
		model.addAttribute("book", bookDTO);
		return "book/update";
	}

	@PostMapping("/{id}")
	public String saveUpdation(@PathVariable("id") long id, @ModelAttribute("book") @Valid BookDTO bookDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "book/update";
		}
		bookService.updateBook(id, bookDTO);
		return "redirect:/book/" + id;
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") long id) {
		bookService.removeBook(id);
		return "redirect:/book/all";
	}

}
