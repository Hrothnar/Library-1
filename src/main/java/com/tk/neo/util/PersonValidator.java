package com.tk.neo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tk.neo.dao.PersonDAO;
import com.tk.neo.model.Person;

@Component
public class PersonValidator implements Validator {
	private final PersonDAO personDAO;
	
	@Autowired
	public PersonValidator(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == Person.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Person person = (Person) target;
		if (personDAO.isExist(person)) {
			errors.rejectValue("name", "", "This person already exists");
		}
 	}

}
