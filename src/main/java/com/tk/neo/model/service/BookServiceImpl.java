package com.tk.neo.model.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tk.neo.model.dto.BookDTO;
import com.tk.neo.model.entity.Book;
import com.tk.neo.model.entity.Person;
import com.tk.neo.model.service.dao.BookDAO;
import com.tk.neo.model.service.dao.PersonDAO;
import com.tk.neo.model.service.interfaccia.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	private final BookDAO bookDAO;
	private final PersonDAO personDAO;
	
	@Autowired
	public BookServiceImpl(BookDAO bookDAO, PersonDAO personDAO) {
		this.bookDAO = bookDAO;
		this.personDAO = personDAO;
	}

	public List<BookDTO> getAllBooks(String page, String booksPerPage) {
		return page == null ? bookDAO.getAllBooks() : bookDAO.getAllBooks(page, booksPerPage);
	}

	@Override
	public List<BookDTO> findBooks(String search) {
		return bookDAO.findBooks(search);
	}

	@Override
	public void saveBook(BookDTO bookDTO) {
		bookDAO.saveBook(bookDTO);
	}

	@Override
	public BookDTO getBook(long id) {
		return bookDAO.getBook(id);
	}

	@Override
	public void attachBook(long id, long personId) {
		Book book = bookDAO.findBook(id);
		Person person = personDAO.findPerson(personId);
		book.setTakingTime(LocalDateTime.now());
		person.adBook(book);
	}

	@Override
	public void releaseBook(long id) {
		Book book = bookDAO.findBook(id);
		Person person = book.getPerson();
		book.setTakingTime(null);
		person.removeBook(book);
	}

	@Override
	public void updateBook(long id, BookDTO bookDTO) {
		Book book = bookDAO.findBook(id);
		book.setAuthor(bookDTO.author);
		book.setTitle(bookDTO.title);
		book.setYearOfPublishing(bookDTO.yearOfPublishing);
	}

	@Override
	public void removeBook(long id) {
		bookDAO.removeBook(id);
	}
	
	
}
