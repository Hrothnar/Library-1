package com.tk.neo.model.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tk.neo.model.entity.Person;
import com.tk.neo.model.service.interfaccia.PersonService;

@Component
public class PersonValidator implements Validator {
	private final PersonService personService;
	
	@Autowired
	public PersonValidator(PersonService personService) {
		this.personService = personService;
	}

	@Override
	public boolean supports(Class<?> klass) {
		return klass == Person.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
//		Person person = (Person) target;
//		if (personDAO.isExist(person)) {
//			errors.rejectValue("name", "", "This person already exists");
//		}
 	}

}
