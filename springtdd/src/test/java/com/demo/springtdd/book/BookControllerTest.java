package com.demo.springtdd.book;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookControllerTest {
    MockMvc mockMvc;
    BookService spyBookService;

    @BeforeEach
    void setup() {
        spyBookService = mock(BookService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new BookController(spyBookService))
                .build();
    }

    @Test
    void getBooks_returnsOkHttpStatus() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk());
    }

    @Test
    void getBooks_returnsBookList() throws Exception {
        given(spyBookService.getBooks()).willReturn(
                List.of(new Book("clean code", "2"))
        );

        mockMvc.perform(get("/books"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", equalTo("clean code")))
                .andExpect(jsonPath("$[0].isbn", equalTo("2")))
        ;
    }

    @Test
    void createBook_returnsCreatedHttpStatus() throws Exception {
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    void createBook_passesCreatedBookDtoToService() throws Exception {
        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"new book\",\"isbn\":\"3\"}")
        );

        ArgumentCaptor<CreatedBookDto> captor = ArgumentCaptor.forClass(CreatedBookDto.class);
        verify(spyBookService).createBook(captor.capture());
        assertThat(captor.getValue().getName()).isEqualTo("new book");
        assertThat(captor.getValue().getIsbn()).isEqualTo("3");
    }
}