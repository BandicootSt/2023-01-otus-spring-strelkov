package ru.otus.homework7.strelkov.dao;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework7.strelkov.domain.Author;
import ru.otus.homework7.strelkov.domain.Book;
import ru.otus.homework7.strelkov.domain.Genre;
import ru.otus.homework7.strelkov.exception.BookNotFoundException;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class BooksRepositoryJpaTest {

    private static final Book FIRST_PREPARED_TEST_BOOK = Book.builder()
        .id(1L)
        .name("TestBook1")
        .author(new Author(1L, "TestFirstName1", "TestLastName1", "TestPatronymic1"))
        .genre(new Genre(1L, "TestGenre1"))
        .build();

    private static final Book SECOND_PREPARED_TEST_BOOK = Book.builder()
        .id(2L)
        .name("TestBook2")
        .author(new Author(2L, "TestFirstName2", "TestLastName2", "TestPatronymic2"))
        .genre(new Genre(2L, "TestGenre2"))
        .build();


    private static final List<Book> PREPARED_TESTS_BOOKS = ImmutableList.of(
        FIRST_PREPARED_TEST_BOOK,
        SECOND_PREPARED_TEST_BOOK
    );

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindByName() {
        assertThat(
            booksRepository.findByName(FIRST_PREPARED_TEST_BOOK.getName())
                .orElseThrow(() -> new BookNotFoundException("Not found book by name: " + FIRST_PREPARED_TEST_BOOK.getName())),
            equalTo(FIRST_PREPARED_TEST_BOOK)
        );
    }

    @Test
    public void testFindById() {
        assertThat(
            booksRepository.findById(FIRST_PREPARED_TEST_BOOK.getId())
                .orElseThrow(() -> new BookNotFoundException("Not found book by id: " + FIRST_PREPARED_TEST_BOOK.getId())),
            equalTo(FIRST_PREPARED_TEST_BOOK)
        );
    }

    @Test
    public void testUpdateBookNameById() {
        booksRepository.updateNameById(1L, "NewTestBookName");
        assertThat(entityManager.find(Book.class, 1L).getName(), equalTo("NewTestBookName"));
    }

    @Test
    public void testFindAll() {
        assertTrue(booksRepository.findAll().containsAll(PREPARED_TESTS_BOOKS));
    }
}