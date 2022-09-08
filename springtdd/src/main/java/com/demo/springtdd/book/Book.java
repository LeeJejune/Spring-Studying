package com.demo.springtdd.book;

public class Book {
    public Book(String name, String isbn) {
        this.name = name;
        this.isbn = isbn;
    }

    private String name;
    private String isbn;

    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }
}
