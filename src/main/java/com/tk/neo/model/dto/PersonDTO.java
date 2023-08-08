package com.tk.neo.model.dto;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.tk.neo.model.entity.Book;
import com.tk.neo.model.entity.Person;

public class PersonDTO {
	public long id;
	@NotEmpty(message = "Name should not be empty")
	@Size(min = 2, max = 32, message = "Name should be 2-32 characters")
	public String name;
	public Date dateOfBirht;
	public Set<BookDTO> books;

	public static PersonDTO toDto(Person person) {
		if (person != null) {
			PersonDTO personDTO = new PersonDTO();
			personDTO.id = person.getId();
			personDTO.name = person.getName();
			personDTO.dateOfBirht = person.getDateOfBirht();
			personDTO.books = toSetOfDto(person.getBooks());
			return personDTO;
		}
		return null;
	}

	public static Person toEntity(PersonDTO personDTO) {
		Person person = new Person();
		person.setName(personDTO.name);
		person.setDateOfBirht(personDTO.dateOfBirht);
		return person;
	}

	private static Set<BookDTO> toSetOfDto(Set<Book> books) {
		return books.stream().map(BookDTO::toDto).collect(Collectors.toSet());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirht() {
		return dateOfBirht;
	}

	public void setDateOfBirht(Date dateOfBirht) {
		this.dateOfBirht = dateOfBirht;
	}

	public Set<BookDTO> getBooks() {
		return books;
	}

	public void setBooks(Set<BookDTO> books) {
		this.books = books;
	}

}
