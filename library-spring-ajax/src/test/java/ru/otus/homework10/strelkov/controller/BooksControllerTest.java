package ru.otus.homework10.strelkov.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.homework10.strelkov.domain.Author;
import ru.otus.homework10.strelkov.domain.Book;
import ru.otus.homework10.strelkov.domain.Genre;
import ru.otus.homework10.strelkov.exception.BookNotFoundException;
import ru.otus.homework10.strelkov.service.AuthorService;
import ru.otus.homework10.strelkov.service.BookService;
import ru.otus.homework10.strelkov.service.GenreService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BooksController.class)
class BooksControllerTest {

    private static final Author FIRST_AUTHOR = new Author(1L, "TestFirstName", "TestLastName", "TestPatronymic");
    private static final Author SECOND_AUTHOR = new Author(2L, "TestFirstName2", "TestLastName2", "TestPatronymic2");

    private static final Genre FIRST_GENRE = new Genre(1L, "TestGenre");
    private static final Genre SECOND_GENRE = new Genre(2L, "TestGenre2");

    private static final Book FIRST_BOOK = new Book(1L, "TestBook", FIRST_AUTHOR, FIRST_GENRE);
    private static final Book SECOND_BOOK = new Book(2L, "TestBook2", SECOND_AUTHOR, SECOND_GENRE);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Test
    void testGetAllBooks() throws Exception {
        given(bookService.getAllBooks()).willReturn(List.of(FIRST_BOOK, SECOND_BOOK));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
            .andExpect(status().isOk())
            //language=json
            .andExpect(content().json(
                """
                    [
                      {
                        "id": 1,
                        "name": "TestBook",
                        "author": {
                          "id": 1,
                          "firstName": "TestFirstName",
                          "lastName": "TestLastName",
                          "patronymic": "TestPatronymic"
                        },
                        "genre": {
                          "id": 1,
                          "name": "TestGenre"
                        }
                      },
                      {
                        "id": 2,
                        "name": "TestBook2",
                        "author": {
                          "id": 2,
                          "firstName": "TestFirstName2",
                          "lastName": "TestLastName2",
                          "patronymic": "TestPatronymic2"
                        },
                        "genre": {
                          "id": 2,
                          "name": "TestGenre2"
                        }
                      }
                    ]"""
            ));
    }

    @Test
    void testGetBookByName() throws Exception {
        given(bookService.getBookByName(FIRST_BOOK.getName())).willReturn(FIRST_BOOK);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/book?name=" + FIRST_BOOK.getName()))
            .andExpect(status().isOk())
            //language=json
            .andExpect(content().json(
                """
                    {
                      "id": 1,
                      "name": "TestBook",
                      "author": {
                        "id": 1,
                        "firstName": "TestFirstName",
                        "lastName": "TestLastName",
                        "patronymic": "TestPatronymic"
                      },
                      "genre": {
                        "id": 1,
                        "name": "TestGenre"
                      }
                    }
                    """
            ));
    }

    @Test
    void testGetBookByName4xx() throws Exception {
        final String unknownBookName = "Unknown book name";
        given(bookService.getBookByName(unknownBookName)).willThrow(new BookNotFoundException("Not found book with name: " + unknownBookName));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/book?name=" + unknownBookName))
            .andExpect(status().isNotFound())
            //language=json
            .andExpect(content().json(
                """
                    {
                      "errMsg": "Not found book with name: Unknown book name"
                    }
                    """
            ));
    }

    @Test
    void testEditBook() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders.put("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                //language=json
                .content(
                    "{\n" +
                    "  \"id\": " + FIRST_BOOK.getId() + ",\n" +
                    "  \"name\": \"NewFirstBookName\"\n" +
                        "}"
                )
            )
            .andExpect(status().isOk());
    }

    @Test
    void testAddBook() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                //language=json
                .content(
                    "{\n" +
                        "  \"name\": \"NewBook\",\n" +
                        "  \"authorId\": \"" + FIRST_AUTHOR.getId() +"\",\n" +
                        "  \"genreId\": \"" + FIRST_GENRE.getId() +"\"\n" +
                        "}"
                )
            )
            .andExpect(status().isOk());
    }

    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                    .delete("/api/books")
                    .contentType(MediaType.APPLICATION_JSON)
                    //language=json
                    .content(
                        "{\n" +
                            "  \"id\": \"" + FIRST_BOOK.getId() +"\"\n" +
                            "}"
                    )
            )
            .andExpect(status().isOk());
    }
}