package com.tk.neo.model.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "people")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty(message = "Name should not be empty")
	@Size(min = 2, max = 32, message = "Name should be 2-32 characters")
	@Column(name = "name", nullable = false, unique = false, table = "people", updatable = true, insertable = true, length = 64)
	private String name;
	@Column(name = "date_of_birth", nullable = false, unique = false, table = "people", updatable = true, insertable = true)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(fallbackPatterns = {"dd.MM.yyyy"} )
	private Date dateOfBirht; // Type "DATE" is used in PostgreSQL for keeping simple dates like following - (06.08.23)
	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "person", orphanRemoval = false, targetEntity = Book.class)
	private Set<Book> books = new HashSet<>();
	
	public Person() {
		
	}
	
	public void adBook(Book book) {
		this.books.add(book);
		book.setPerson(this);
	}
	
	public void removeBook(Book book) {
		this.books.remove(book);
		book.setPerson(null);
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

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	
}
