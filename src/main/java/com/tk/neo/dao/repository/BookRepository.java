package com.tk.neo.dao.repository;

import java.util.List;

import com.tk.neo.model.Book;

public interface BookRepository {
	List<Book> getAllByPersonId(long personId);
	List<Book> getAll();
	Book getById(long id);
	void save(Book book);
	void attachPerson(long id, long personId);
	void releaseBook(long id);
	void update(long id, Book book);
	void remove(long id);
	

}
