package com.dataGlacier.spring.data.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataGlacier.spring.data.dao.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
