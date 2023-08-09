package com.tk.neo.model.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tk.neo.model.entity.Person;

@Component
public class PersonValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> klass) {
		return klass == Person.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasFieldErrors("dateOfBirth")) {
			errors.rejectValue("dateOfBirth", "dateFormat", "Enter the correct date format (dd.MM.yyyy)");
		}
	}

}
