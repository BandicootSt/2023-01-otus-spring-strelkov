package ru.otus.homework5.strelkov.service.impl;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework5.strelkov.dao.BooksDao;
import ru.otus.homework5.strelkov.domain.Author;
import ru.otus.homework5.strelkov.domain.Book;
import ru.otus.homework5.strelkov.domain.Genre;
import ru.otus.homework5.strelkov.service.AuthorService;
import ru.otus.homework5.strelkov.service.BookService;
import ru.otus.homework5.strelkov.service.GenreService;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceImplTest {

    private static final Author FIRST_PREPARED_TEST_AUTHOR =
        Author.builder()
            .id(1L)
            .firstName("TestFirstName1")
            .lastName("TestLastName1")
            .patronymic("TestPatronymic1")
            .build();

    private static final Author SECOND_PREPARED_TEST_AUTHOR =
        Author.builder()
            .id(2L)
            .firstName("TestFirstName2")
            .lastName("TestLastName2")
            .patronymic("TestPatronymic2")
            .build();

    private static final Genre FIRST_PREPARED_TEST_GENRE = new Genre(1L, "TestGenre1");
    private static final Genre SECOND_PREPARED_TEST_GENRE = new Genre(2L, "TestGenre2");

    private static final Book FIRST_PREPARED_TEST_BOOK =
        new Book(1L, "TestBook1",  FIRST_PREPARED_TEST_AUTHOR, FIRST_PREPARED_TEST_GENRE);

    private static final Book SECOND_PREPARED_TEST_BOOK =
        new Book(2L, "TestBook2",  SECOND_PREPARED_TEST_AUTHOR, SECOND_PREPARED_TEST_GENRE);


    private static final List<Book> PREPARED_TESTS_BOOKS = ImmutableList.of(
        FIRST_PREPARED_TEST_BOOK,
        SECOND_PREPARED_TEST_BOOK
    );

    @MockBean
    private BooksDao booksDao;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    private BookService bookService;

    @BeforeEach
    public void beforeEach() {
        bookService = new BookServiceImpl(
            booksDao,
            authorService,
            genreService
        );
    }

    @Test
    public void testGetAllBooks() {
        when(booksDao.getAllBooks()).thenReturn(PREPARED_TESTS_BOOKS);
        assertTrue(bookService.getAllBooks().containsAll(PREPARED_TESTS_BOOKS));
    }

    @Test
    public void testGetBookByName() {
        when(booksDao.getBookByName(FIRST_PREPARED_TEST_BOOK.getName())).thenReturn(FIRST_PREPARED_TEST_BOOK);
        assertThat(bookService.getBookByName(FIRST_PREPARED_TEST_BOOK.getName()), equalTo(FIRST_PREPARED_TEST_BOOK));
    }

}