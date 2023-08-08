package com.tk.neo.model.service.interfaccia;

import java.util.List;

import com.tk.neo.model.dto.BookDTO;

public interface BookService {
	List<BookDTO> getAllBooks(String page, String booksPerPage);
	List<BookDTO> findBooks(String search);
	BookDTO getBook(long id);
	void saveBook(BookDTO bookDTO);
	void attachBook(long id, long personId);
	void releaseBook(long id);
	void updateBook(long id, BookDTO bookDTO);
	void removeBook(long id);
	

}
