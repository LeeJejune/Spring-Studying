package com.demo.springtdd.book;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BookServiceTest {

    BookService sut;
    BookRepository spyRepository;
    @BeforeEach
    void setup() {
        spyRepository = mock(BookRepository.class);
        sut = new BookService(spyRepository);
    }

    @Test
    void getBooks_returnsBookList() {
        given(spyRepository.findAll()).willReturn(
                List.of(new Book("clean code", "2"))
        );

        List<Book> result = sut.getBooks();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("clean code");
        assertThat(result.get(0).getIsbn()).isEqualTo("2");
    }

    @Test
    void createBook_throwsException_whenCreatedBookDtoIsbnIsEmpty() {
        CreatedBookDto dto = new CreatedBookDto("", "name");

        assertThatThrownBy(() ->  sut.createBook(dto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createBook_throwsException_whenCreatedBookDtoIsbnIsNull() {
        CreatedBookDto dto = new CreatedBookDto(null, "name");

        assertThatThrownBy(() ->  sut.createBook(dto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createBook_passesBookToRepository() {
        CreatedBookDto dto = new CreatedBookDto("isbn", "name");

        sut.createBook(dto);

        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        verify(spyRepository).save(captor.capture());
        assertThat(captor.getValue().getName()).isEqualTo("name");
        assertThat(captor.getValue().getIsbn()).isEqualTo("isbn");
    }
}