package com.tk.neo.model.mapper;

import com.tk.neo.model.dto.BookDTO;
import com.tk.neo.model.dto.PersonDTO;
import com.tk.neo.model.entity.Book;

public class BookMapper {
	
	public static BookDTO toLazy(Book book) {
		BookDTO bookDTO = new BookDTO();
		bookDTO.id = book.getId();
		bookDTO.title = book.getTitle();
		bookDTO.author = book.getAuthor();
		bookDTO.yearOfPublishing = book.getYearOfPublishing();
		bookDTO.takingTime = book.getTakingTime();
		return bookDTO;
	}
	
	public static BookDTO toEager(Book book) {
		BookDTO bookDTO = new BookDTO();
		PersonDTO personDTO = PersonMapper.toLazy(book.getPerson());
		bookDTO.id = book.getId();
		bookDTO.title = book.getTitle();
		bookDTO.author = book.getAuthor();
		bookDTO.yearOfPublishing = book.getYearOfPublishing();
		bookDTO.takingTime = book.getTakingTime();
		bookDTO.personDTO = personDTO;
		return bookDTO;
	}
	
}
