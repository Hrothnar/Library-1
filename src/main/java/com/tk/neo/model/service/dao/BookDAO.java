package com.tk.neo.model.service.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tk.neo.model.dto.BookDTO;
import com.tk.neo.model.entity.Book;
import com.tk.neo.model.entity.Person;

@Component
public class BookDAO {
	private final SessionFactory sessionFactory;

	@Autowired
	public BookDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<BookDTO> getAllBooks(String page, String booksPerPage) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM books ORDER BY year_of_publishing ASC LIMIT :booksPerPage OFFSET (:page - 1) * :booksPerPage";
		List<Book> books = session.createNativeQuery(sql, Book.class)
						.setParameter("booksPerPage", convert(page))
						.setParameter("page", convert(booksPerPage))
						.getResultList();
		return books.stream()
				.map(BookDTO::toDto)
				.collect(Collectors.toList());
	}

	public List<BookDTO> getAllBooks() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT b FROM Book b ORDER BY b.yearOfPublishing ASC";
		List<Book> books = session.createQuery(hql, Book.class).getResultList();
		return books.stream()
				.map(BookDTO::toDto)
				.collect(Collectors.toList());
	}

	public List<BookDTO> findBooks(String search) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT b FROM Book b WHERE b.title LIKE :search";
		List<Book> books = session.createQuery(hql, Book.class)
						.setParameter("search", "%" + search + "%")
						.getResultList();
		return books.stream()
				.map(BookDTO::toDto)
				.collect(Collectors.toList());
	}

	private int convert(String string) {
		try {
			return Integer.parseInt(string);
		} catch (Exception ex) {
			throw new RuntimeException("Impossible to convert this string into a number", ex);
		}
	}
	
	public Book findBook(long id) {
		Session session = sessionFactory.getCurrentSession();
		Book book = session.get(Book.class, id);
		if (book == null) {
			throw new RuntimeException("Such book does not exist");
		}
		return book;
	}

	public void saveBook(BookDTO bookDTO) {
		Session session = sessionFactory.getCurrentSession();
		Book book = BookDTO.toEntity(bookDTO);
		session.persist(book);
	}

	public BookDTO getBook(long id) {
		Book book = findBook(id);
		return BookDTO.toDto(book);
	}

	public void removeBook(long id) {
		Session session = sessionFactory.getCurrentSession();
		Book book = findBook(id);
		Person person = book.getPerson();
		person.removeBook(book);
		session.remove(book);
	}


}
