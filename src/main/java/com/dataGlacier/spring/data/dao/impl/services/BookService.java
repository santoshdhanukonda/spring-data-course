package com.dataGlacier.spring.data.dao.impl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataGlacier.spring.data.dao.entities.Book;
import com.dataGlacier.spring.data.dao.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	public void save(Book book) {
		this.bookRepository.save(book);
	}

	public List<Book> getAllBooks() {
		return this.bookRepository.findAll();
	}
}
