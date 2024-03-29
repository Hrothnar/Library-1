package com.tk.neo.model.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BookDTO {
	public long id;
	@NotEmpty(message = "Title should not be empty")
	@Size(min = 2, max = 32, message = "Check the entered data")
	public String title;
	@NotEmpty(message = "Author should not be empty")
	@Size(min = 2, max = 32, message = "Check the entered data")
	public String author;
	@Max(value = 2023)
	public int yearOfPublishing;
	public Date takingTime; 
	public PersonDTO personDTO;
	
	public boolean isExpired() {
		return takingTime.after(new Date()); //TODO
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

	public int getYearOfPublishing() {
		return yearOfPublishing;
	}

	public void setYearOfPublishing(int yearOfPublishing) {
		this.yearOfPublishing = yearOfPublishing;
	}

	public Date getTakingTime() {
		return takingTime;
	}

	public void setTakingTime(Date takingTime) {
		this.takingTime = takingTime;
	}

	public PersonDTO getPersonDTO() {
		return personDTO;
	}

	public void setPersonDTO(PersonDTO personDTO) {
		this.personDTO = personDTO;
	}
	
	
}
