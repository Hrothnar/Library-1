package com.tk.neo.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tk.neo.model.Book;

public class BookMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setId(rs.getLong("id"));
		book.setTitle(rs.getString("title"));
		book.setYear(rs.getInt("year"));
		book.setAuthor(rs.getString("author"));
		book.setPersonId(rs.getLong("user_id"));
		return book;
	}

}
