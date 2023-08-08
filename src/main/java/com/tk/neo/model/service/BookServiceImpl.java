package com.tk.neo.model.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tk.neo.model.dto.BookDTO;
import com.tk.neo.model.entity.Book;
import com.tk.neo.model.entity.Person;
import com.tk.neo.model.mapper.BookMapper;
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

	@Override
	public List<BookDTO> getAllBooks(String page, String booksPerPage) {
		List<Book> books = page == null ? bookDAO.getAllBooks() : bookDAO.getAllBooks(page, booksPerPage);
		return books.stream()
				.map(BookMapper::toLazy)
				.collect(Collectors.toList());
	}

	@Override
	public List<BookDTO> findBooks(String element) {
		List<Book> books = bookDAO.findBooks(element);
		return books.stream()
				.map(BookMapper::toLazy)
				.collect(Collectors.toList());
	}

	@Override
	public void saveBook(BookDTO bookDTO) {
		bookDAO.saveBook(bookDTO);
	}

	@Override
	public BookDTO getBook(long id, boolean eager) {
		Book book = bookDAO.getBook(id);
		return eager ? BookMapper.toEager(book) : BookMapper.toLazy(book);
	}
	
	@Override
	public void attachBook(long id, long personId) {
		Book book = bookDAO.getBook(id);
		Person person = personDAO.getPerson(personId);
		book.setTakingTime(new Date());
		person.adBook(book);
	}

	@Override
	public void releaseBook(long id) {
		Book book = bookDAO.getBook(id);
		Person person = book.getPerson();
		book.setTakingTime(null);
		person.removeBook(book);
	}

	@Override
	public void updateBook(long id, BookDTO bookDTO) {
		Book book = bookDAO.getBook(id);
		book.setAuthor(bookDTO.author);
		book.setTitle(bookDTO.title);
		book.setYearOfPublishing(bookDTO.yearOfPublishing);
	}

	@Override
	public void removeBook(long id) {
		bookDAO.removeBook(id);
	}
	
	
}
