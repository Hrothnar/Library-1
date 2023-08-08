package com.tk.neo.model.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.tk.neo.model.dto.BookDTO;
import com.tk.neo.model.dto.PersonDTO;
import com.tk.neo.model.entity.Book;
import com.tk.neo.model.entity.Person;

public class PersonMapper {
	
	public static PersonDTO toLazy(Person person) {
		if (person == null) {
			return null;
		}
		PersonDTO personDTO = new PersonDTO();
		personDTO.id = person.getId();
		personDTO.name = person.getName();
		personDTO.dateOfBirth = person.getDateOfBirth();
		return personDTO;
	}
	
	public static PersonDTO toEager(Person person) {
		PersonDTO personDTO = new PersonDTO();
		Set<BookDTO> booksDTO = toLazySet(person.getBooks());
		personDTO.id = person.getId();
		personDTO.name = person.getName();
		personDTO.dateOfBirth = person.getDateOfBirth();
		personDTO.books = booksDTO;
		return personDTO;
	}

	private static Set<BookDTO> toLazySet(Set<Book> books) {
		return books.stream()
				.map(BookMapper::toLazy)
				.collect(Collectors.toSet());
	}

}
