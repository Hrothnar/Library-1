package com.tk.neo.model.service.dao;

import java.util.List;

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

	public List<Book> getAllBooks(String page, String booksPerPage) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM books ORDER BY year_of_publishing ASC LIMIT :booksPerPage OFFSET (:page - 1) * :booksPerPage";
		return session.createNativeQuery(sql, Book.class)
				.setParameter("booksPerPage", convert(booksPerPage))
				.setParameter("page", convert(page))
				.getResultList();
	}

	public List<Book> getAllBooks() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT b FROM Book b ORDER BY b.yearOfPublishing ASC";
		return session.createQuery(hql, Book.class).getResultList();
	}

	public List<Book> findBooks(String search) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT b FROM Book b WHERE b.title LIKE :search";
		return session.createQuery(hql, Book.class)
				.setParameter("search", "%" + search + "%")
				.getResultList();
	}

	public void saveBook(BookDTO bookDTO) {
		Session session = sessionFactory.getCurrentSession();
		Book book = new Book();
		book.setAuthor(bookDTO.author);
		book.setTitle(bookDTO.title);
		book.setYearOfPublishing(bookDTO.yearOfPublishing);
		session.persist(book);
	}

	public Book getBook(long id) {
		Session session = sessionFactory.getCurrentSession();
		Book book = session.get(Book.class, id);
		if (book == null) {
			throw new RuntimeException("Such book does not exist");
		}
		return session.get(Book.class, id);
	}

	public void removeBook(long id) {
		Session session = sessionFactory.getCurrentSession();
		Book book = getBook(id);
		Person person = book.getPerson();
		person.removeBook(book);
		session.remove(book);
	}
	
	private int convert(String string) {
		try {
			return Integer.parseInt(string);
		} catch (Exception ex) {
			throw new RuntimeException("Impossible to convert this string into a number", ex);
		}
	}


}
