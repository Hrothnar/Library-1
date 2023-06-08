package com.tk.neo.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
	private long id;
	@NotEmpty(message = "Name should not be empty")
	@Size(min = 2, max = 32, message = "Name should be 2-32 characters")
	private String name;
	@Min(value = 1900, message = "Check the entered date")
	@Max(value = 2015, message = "Check the entered date")
	private int birthYear;
	
	public Person(String name, int birthYear) {
		this.name = name;
		this.birthYear = birthYear;
	}
	
	public Person() {
		
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

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	

}
