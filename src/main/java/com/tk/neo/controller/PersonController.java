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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tk.neo.dao.BookDAO;
import com.tk.neo.dao.PersonDAO;
import com.tk.neo.model.Person;


@Controller
@RequestMapping("/person")
public class PersonController {
	private final PersonDAO personDAO;
	private final BookDAO bookDAO;

	@Autowired
	public PersonController(PersonDAO personDAO, BookDAO bookDAO) {
		this.personDAO = personDAO;
		this.bookDAO = bookDAO;
	}
	
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	@GetMapping("/all")
	public String showAll(Model model) {
		model.addAttribute("people", personDAO.getAll());
		return "person/all";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") long id, Model model) {
		model.addAttribute("person", personDAO.getById(id));
		model.addAttribute("books", bookDAO.getAllByPersonId(id));
		return "person/show";
	}
	
	@GetMapping("/{id}/update")
	public String update(@PathVariable("id") long id, Model model) {
		model.addAttribute("person", personDAO.getById(id));
		return "person/update";
	}
	
	@GetMapping("/create")
	public String create(@ModelAttribute("person") Person person) {
		return "person/create";
	}
	
	@PostMapping()
	public String saveCreation(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "person/create";
		}
		personDAO.save(person);
		return "redirect:/person/menu";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") long id) {
		personDAO.remove(id);
		return "redirect:/person/menu";
	}
	
	@PutMapping("/{id}")
	public String saveUpdation(@PathVariable("id") long id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "person/update";
		}
		personDAO.update(id, person);
		return "redirect:/person/menu";
	}
	
}
