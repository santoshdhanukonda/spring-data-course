package com.dataGlacier.spring.data.application;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dataGlacier.spring.data.config.DataGlacierAppConfig;
import com.dataGlacier.spring.data.dao.entities.Book;
import com.dataGlacier.spring.data.dao.impl.services.BookService;

public class SpringDataUsingJavaConfig {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext springDataContext 
				= new AnnotationConfigApplicationContext(DataGlacierAppConfig.class)) 
		{
			BookService bookDaoService = springDataContext.getBean(BookService.class);

			Book springBook = new Book();
			springBook.setAuthor("Santosh kumar");
			springBook.setPrice(new BigDecimal(1000));
			springBook.setTitle("Spring Date persitance");
			springBook.setPublishDate(new Date());

			bookDaoService.save(springBook);
			List<Book> libraryBooks = bookDaoService.getAllBooks();

			for (Book book : libraryBooks) {
				System.out.println("Book persisted with***" + book);
			}
		}
	}
}
