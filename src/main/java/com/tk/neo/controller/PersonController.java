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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tk.neo.model.dto.PersonDTO;
import com.tk.neo.model.service.interfaccia.PersonService;
import com.tk.neo.model.util.PersonValidator;


@Controller
@RequestMapping("/person")
public class PersonController {
	private final PersonService personService;
	private final PersonValidator personValidator;

	@Autowired
	public PersonController(PersonService personService, PersonValidator personValidator) {
		this.personService = personService;
		this.personValidator = personValidator;
	}
	
	@GetMapping("/create")
	public String create(@ModelAttribute("person") PersonDTO personDTO) {
		return "person/create";
	}
	
	@GetMapping("/all")
	public String showAll(Model model) {
		List<PersonDTO> peopleDTO = personService.getAllPeople();
		model.addAttribute("people", peopleDTO);
		return "person/all";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") long id, Model model) {
		PersonDTO personDTO = personService.getPerson(id);
		model.addAttribute("person", personDTO);
		model.addAttribute("books", personDTO.books);
		return "person/show";
	}
	
	@GetMapping("/{id}/update")
	public String update(@PathVariable("id") long id, Model model) {
		PersonDTO personDTO = personService.getPerson(id);
		model.addAttribute("person", personDTO);
		return "person/update";
	}
		
	@PostMapping()
	public String saveCreation(@ModelAttribute("person") @Valid PersonDTO personDTO, BindingResult bindingResult) {
//		personValidator.validate(person, bindingResult);
		if (bindingResult.hasErrors()) {
			return "person/create";
		}
		personService.savePerson(personDTO);
		return "redirect:/";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") long id) {
		personService.removePerson(id);
		return "redirect:/";
	}
	
	@PutMapping("/{id}")
	public String saveUpdation(@PathVariable("id") long id, @ModelAttribute("person") @Valid PersonDTO personDTO, BindingResult bindingResult) {
//		personValidator.validate(person, bindingResult);
		if (bindingResult.hasErrors()) {
			return "person/update";
		}
		personService.updatePerson(id, personDTO);
		return "redirect:/";
	}
	
}
