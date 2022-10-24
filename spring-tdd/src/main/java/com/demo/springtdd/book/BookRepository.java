package com.demo.springtdd.book;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository {
    List<Book> findAll();

    void save(Book book);
}