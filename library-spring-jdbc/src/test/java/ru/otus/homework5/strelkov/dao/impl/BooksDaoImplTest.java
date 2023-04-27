package ru.otus.homework5.strelkov.dao.impl;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework5.strelkov.dao.BooksDao;
import ru.otus.homework5.strelkov.domain.Author;
import ru.otus.homework5.strelkov.domain.Book;
import ru.otus.homework5.strelkov.domain.Genre;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@JdbcTest
@Import(BooksDaoImpl.class)
class BooksDaoImplTest {

    private static final Book FIRST_PREPARED_TEST_BOOK =
        new Book(
            1L,
            "TestBook1",
            Author.builder()
                .id(1L)
                .firstName("TestFirstName1")
                .lastName("TestLastName1")
                .patronymic("TestPatronymic1")
                .build(),
            new Genre(1L, "TestGenre1")
        );

    private static final Book SECOND_PREPARED_TEST_BOOK =
        new Book(
            2L,
            "TestBook2",
            Author.builder()
                .id(2L)
                .firstName("TestFirstName2")
                .lastName("TestLastName2")
                .patronymic("TestPatronymic2")
                .build(),
            new Genre(2L, "TestGenre2")
        );

    private static final List<Book> PREPARED_TESTS_BOOKS = ImmutableList.of(
        FIRST_PREPARED_TEST_BOOK,
        SECOND_PREPARED_TEST_BOOK
    );

    @Autowired
    private BooksDao booksDao;

    @Test
    public void testAddBook() {

        Book testBook = new Book(
            "TestBook3",
            Author.builder()
                .id(1L)
                .firstName("TestFirstName1")
                .lastName("TestLastName1")
                .patronymic("TestPatronymic1")
                .build(),
            new Genre(1L, "TestGenre1")
        );

        long addedBookId = booksDao.addBook(testBook);

        testBook.setId(addedBookId);

        assertThat(booksDao.getBookByName("TestBook3"), equalTo(testBook));
    }

    @Test
    public void testGetAllBooks() {
        assertTrue(booksDao.getAllBooks().containsAll(PREPARED_TESTS_BOOKS));
    }

    @Test
    public void testGetBookByName() {
        assertThat(booksDao.getBookByName(SECOND_PREPARED_TEST_BOOK.getName()), equalTo(SECOND_PREPARED_TEST_BOOK));
    }

    @Test
    public void testUpdateBook() {
        booksDao.updateBookNameById("NewTestBookName", 1L);
        assertThat(booksDao.getBookByName("NewTestBookName").getId(), equalTo(1L));
    }

    @Test
    public void testDeleteBook() {
        booksDao.deleteBookById(1L);
        assertFalse(booksDao.getAllBooks().contains(FIRST_PREPARED_TEST_BOOK));
    }

}