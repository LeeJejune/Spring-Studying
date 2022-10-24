package com.demo.springtdd.book;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    BookService(BookRepository repository) {
        this.bookRepository = repository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void createBook(CreatedBookDto bookDto) {
        if (bookDto.isValid()){
            throw new IllegalArgumentException();
        }

        bookRepository.save(bookDto.toDomain());
    }
}
