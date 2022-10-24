package com.demo.springtdd.book;


public class CreatedBookDto {
    private String name;
    private String isbn;

    public CreatedBookDto(String isbn, String name) {
        this.isbn = isbn;
        this.name = name;
    }

    public CreatedBookDto(){}

    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isValid() {
        return isbn == null || isbn.isEmpty();
    }

    public Book toDomain() {
        return new Book(name, isbn);
    }
}