package com.tk.neo.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
	private long id;
	@NotEmpty(message = "Title should not be empty")
	@Size(min = 2, max = 32, message = "Check the entered data")
	private String title;
	@NotEmpty(message = "Author should not be empty")
	@Size(min = 2, max = 32, message = "Check the entered data")
	private String author;
	@Max(value = 2023)
	private int year;
	private long personId;
	
	public Book(String title, String author, int year, long personId) {
		this.title = title;
		this.author = author;
		this.year = year;
		this.personId = personId;
	}

	public Book() {
		
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}
	
	
}
