package com.tk.neo.dao;

import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tk.neo.dao.repository.BookRepository;
import com.tk.neo.model.Book;
import com.tk.neo.util.AppConnection;

@Component
public class BookDAO implements BookRepository {
	private final AppConnection appConnection;
	
	@Autowired
	public BookDAO(AppConnection appConnection) {
		this.appConnection = appConnection;
	}

	public List<Book> getAllByPersonId(long personId) {
		String sql = "SELECT * FROM books WHERE person_id = ?;";
		List<Book> books = new LinkedList<>();
		try (PreparedStatement statement = appConnection.getConnection().prepareStatement(sql)) {
			statement.setLong(1, personId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getLong("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setYear(rs.getInt("year"));
				book.setPersonId(rs.getLong("person_id"));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	public List<Book> getAll() {
		String sql = "SELECT * FROM books;";
		List<Book> books = new LinkedList<>();
		try (PreparedStatement statement = appConnection.getConnection().prepareStatement(sql)) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getLong("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setYear(rs.getInt("year"));
				book.setPersonId(rs.getLong("person_id"));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	public void save(Book book) {
		String sql = "INSERT INTO books (title, author, year) VALUES (?, ?, ?);";
		try (PreparedStatement statement = appConnection.getConnection().prepareStatement(sql)) {
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setInt(3, book.getYear());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Book getById(long id) {
		String sql = "SELECT * FROM books WHERE id = ?;";
		Book book = new Book();
		try (PreparedStatement statement = appConnection.getConnection().prepareStatement(sql)) {
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			rs.next();
			book.setId(rs.getLong("id"));
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("author"));
			book.setYear(rs.getInt("year"));
			book.setPersonId(rs.getLong("person_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	public void attachPerson(long id, long personId) {
		String sql = "UPDATE books SET person_id = ? WHERE id = ?;";
		try (PreparedStatement statement = appConnection.getConnection().prepareStatement(sql)) {
			statement.setLong(1, personId);
			statement.setLong(2, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void releaseBook(long id) {
		String sql = "UPDATE books SET person_id = ? WHERE id = ?;";
		try (PreparedStatement statement = appConnection.getConnection().prepareStatement(sql)) {
			statement.setNull(1, 0);
			statement.setLong(2, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(long id, Book book) {
		String sql = "UPDATE books SET title = ?, author = ?, year = ? WHERE id = ?;";
		try (PreparedStatement statement = appConnection.getConnection().prepareStatement(sql)) {
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setInt(3, book.getYear());
			statement.setLong(4, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(long id) {
		String sql = "DELETE FROM books WHERE id = ?;";
		try (PreparedStatement statement = appConnection.getConnection().prepareStatement(sql)) {
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
