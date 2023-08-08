package com.tk.neo.model.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class PersonDTO {
	public long id;
	@NotEmpty(message = "Name should not be empty")
	@Size(min = 2, max = 32, message = "Name should be 2-32 characters")
	public String name;
	@DateTimeFormat(fallbackPatterns = { "dd.MM.yyyy" })
	public Date dateOfBirth;
	public Set<BookDTO> books;

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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Set<BookDTO> getBooks() {
		return books;
	}

	public void setBooks(Set<BookDTO> books) {
		this.books = books;
	}

}
