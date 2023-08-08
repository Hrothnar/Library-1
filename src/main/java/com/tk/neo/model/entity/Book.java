package com.tk.neo.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty(message = "Title should not be empty")
	@Size(min = 2, max = 32, message = "Check the entered data")
	@Column(name = "title", nullable = false, unique = true, table = "books", updatable = true, insertable = true, length = 128)
	private String title;
	@NotEmpty(message = "Author should not be empty")
	@Size(min = 2, max = 32, message = "Check the entered data")
	@Column(name = "author", nullable = false, unique = false, table = "books", updatable = true, insertable = true, length = 64)
	private String author;
	@Max(value = 2023)
	@Column(name = "year_of_publishing", nullable = true, unique = false, table = "books", updatable = true, insertable = true)
	private int yearOfPublishing;
	@Column(name = "taking_time", nullable = true, unique = false, table = "books", updatable = true, insertable = true)
	@Temporal(TemporalType.TIMESTAMP) 
	private Date takingTime; // Type "TIMESTAMP" is used in PostgreSQL for keeping seconds amount passed since January 1st 2000 
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY, targetEntity = Person.class)
	@JoinColumn(name = "person_id", referencedColumnName = "id", nullable = true, insertable = true, unique = false, table = "books", updatable = true)
	private Person person;
	
	public Book() {
		
	}
		
	public long getId() {
		return id;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
