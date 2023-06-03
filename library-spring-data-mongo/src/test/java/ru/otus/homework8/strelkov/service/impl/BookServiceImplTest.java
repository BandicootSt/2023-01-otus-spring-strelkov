package ru.otus.homework8.strelkov.service.impl;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework8.strelkov.dao.BooksRepository;
import ru.otus.homework8.strelkov.domain.Author;
import ru.otus.homework8.strelkov.domain.Book;
import ru.otus.homework8.strelkov.domain.Genre;
import ru.otus.homework8.strelkov.service.AuthorService;
import ru.otus.homework8.strelkov.service.BookService;
import ru.otus.homework8.strelkov.service.GenreService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceImplTest {

    private static final Author FIRST_PREPARED_TEST_AUTHOR =
        new Author("1", "TestFirstName1", "TestLastName1", "TestPatronymic1");

    private static final Author SECOND_PREPARED_TEST_AUTHOR =
        new Author("2", "TestFirstName2", "TestLastName2", "TestPatronymic2");

    private static final Genre FIRST_PREPARED_TEST_GENRE = new Genre("1", "TestGenre1");
    private static final Genre SECOND_PREPARED_TEST_GENRE = new Genre("2", "TestGenre2");

    private static final Book FIRST_PREPARED_TEST_BOOK =
        new Book("1", "TestBook1",  FIRST_PREPARED_TEST_AUTHOR, FIRST_PREPARED_TEST_GENRE);

    private static final Book SECOND_PREPARED_TEST_BOOK =
        new Book("2", "TestBook2",  SECOND_PREPARED_TEST_AUTHOR, SECOND_PREPARED_TEST_GENRE);


    private static final List<Book> PREPARED_TESTS_BOOKS = ImmutableList.of(
        FIRST_PREPARED_TEST_BOOK,
        SECOND_PREPARED_TEST_BOOK
    );

    @MockBean
    private BooksRepository booksRepository;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    private BookService bookService;

    @BeforeEach
    public void beforeEach() {
        bookService = new BookServiceImpl(
            booksRepository,
            authorService,
            genreService
        );
    }

    @Test
    public void testGetAllBooks() {
        when(booksRepository.findAll()).thenReturn(PREPARED_TESTS_BOOKS);
        assertTrue(bookService.getAllBooks().containsAll(PREPARED_TESTS_BOOKS));
    }

    @Test
    public void testGetBookByName() {
        when(booksRepository.findByName(FIRST_PREPARED_TEST_BOOK.getName())).thenReturn(Optional.of(FIRST_PREPARED_TEST_BOOK));
        assertThat(bookService.getBookByName(FIRST_PREPARED_TEST_BOOK.getName()), equalTo(FIRST_PREPARED_TEST_BOOK));
    }

}