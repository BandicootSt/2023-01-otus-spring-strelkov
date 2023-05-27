package ru.otus.homework6.strelkov.dao.impl;

import com.google.common.collect.ImmutableList;
import jakarta.persistence.TypedQuery;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(BooksRepositoryJpa.class)
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
    public void testSave() {

        Book testBook = Book.builder()
            .name("TestBook3")
            .author(new Author(1L, "TestFirstName1", "TestLastName1", "TestPatronymic1"))
            .genre(new Genre(1L, "TestGenre1"))
            .build();

        booksRepository.save(testBook);

        TypedQuery<Book> query = entityManager.getEntityManager().createQuery(
            "select b from Book b where b.name = :name",
            Book.class
        );

        query.setParameter("name", "TestBook3");

        assertEquals(testBook, query.getSingleResult());
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
        booksRepository.delete(2L);
        assertThat(entityManager.find(Book.class, 2L), Matchers.nullValue());
    }
}