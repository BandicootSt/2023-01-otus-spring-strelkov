package ru.otus.homework6.strelkov.dao.impl;

import com.google.common.collect.ImmutableList;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework6.strelkov.dao.BooksRepository;
import ru.otus.homework6.strelkov.domain.Author;
import ru.otus.homework6.strelkov.domain.Book;
import ru.otus.homework6.strelkov.domain.Genre;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(BooksRepositoryJpa.class)
class BooksRepositoryJpaTest {

    private static final Book FIRST_PREPARED_TEST_BOOK =
        new Book(
            1L,
            "TestBook1",
            new Author(1L, "TestFirstName1", "TestLastName1", "TestPatronymic1"),
            new Genre(1L, "TestGenre1")
        );

    private static final Book SECOND_PREPARED_TEST_BOOK =
        new Book(
            2L,
            "TestBook2",
            new Author(2L, "TestFirstName2", "TestLastName2", "TestPatronymic2"),
            new Genre(2L, "TestGenre2")
        );

    private static final List<Book> PREPARED_TESTS_BOOKS = ImmutableList.of(
        FIRST_PREPARED_TEST_BOOK,
        SECOND_PREPARED_TEST_BOOK
    );

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSave() {

        Book testBook = new Book(
            "TestBook3",
            new Author(1L, "TestFirstName1", "TestLastName1", "TestPatronymic1"),
            new Genre(1L, "TestGenre1")
        );

        booksRepository.save(testBook);

        assertTrue(booksRepository.findAll().stream().anyMatch(b -> b.getName().equals("TestBook3")));
    }

    @Test
    public void testFindAll() {
        assertTrue(booksRepository.findAll().containsAll(PREPARED_TESTS_BOOKS));
    }

    @Test
    public void testFindById() {
        assertThat(booksRepository.findById(FIRST_PREPARED_TEST_BOOK.getId()), equalTo(FIRST_PREPARED_TEST_BOOK));
    }

    @Test
    public void testFindByName() {
        assertThat(booksRepository.findByName(FIRST_PREPARED_TEST_BOOK.getName()), equalTo(FIRST_PREPARED_TEST_BOOK));
    }

    @Test
    public void testUpdateBookNameById() {
        booksRepository.updateBookNameById(1L, "NewTestBookName");
        assertThat(entityManager.find(Book.class, 1L).getName(), equalTo("NewTestBookName"));
    }

    @Test
    public void testDelete() {
        booksRepository.delete(3L);
        assertThat(entityManager.find(Book.class,3L), Matchers.nullValue());
    }
}