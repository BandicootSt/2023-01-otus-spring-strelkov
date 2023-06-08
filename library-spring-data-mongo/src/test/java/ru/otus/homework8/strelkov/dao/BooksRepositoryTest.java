package ru.otus.homework8.strelkov.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.homework8.strelkov.domain.Author;
import ru.otus.homework8.strelkov.domain.Book;
import ru.otus.homework8.strelkov.domain.Genre;
import ru.otus.homework8.strelkov.exception.BookNotFoundException;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
class BooksRepositoryTest {

    private static final Book FIRST_PREPARED_TEST_BOOK = Book.builder()
        .id("1")
        .name("TestBook1")
        .author(new Author("1", "TestFirstName1", "TestLastName1", "TestPatronymic1"))
        .genre(new Genre("1", "TestGenre1"))
        .build();

    private static final Book SECOND_PREPARED_TEST_BOOK = Book.builder()
        .id("2")
        .name("TestBook2")
        .author(new Author("2", "TestFirstName2", "TestLastName2", "TestPatronymic2"))
        .genre(new Genre("2", "TestGenre2"))
        .build();

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    public void testFindByName() {
        assertThat(
            booksRepository.findByName(FIRST_PREPARED_TEST_BOOK.getName())
                .orElseThrow(() -> new BookNotFoundException("Not found book by name: " + FIRST_PREPARED_TEST_BOOK.getName())),
            equalTo(FIRST_PREPARED_TEST_BOOK)
        );
    }

    @Test
    public void testUpdateBookNameById() {
        booksRepository.updateNameById("1", "NewTestBookName");
        assertThat(
            mongoOperations.findOne(
                    Query.query(Criteria.where("id").is("1")),
                    Book.class
                )
                .getName(),
            equalTo("NewTestBookName")
        );
    }

    @Test
    public void testDeleteAllByAuthorId() {
        booksRepository.deleteAllByAuthorId(FIRST_PREPARED_TEST_BOOK.getAuthor().getId());
        assertEquals(
            Collections.emptyList(),
            mongoOperations.find(
                Query.query(Criteria.where("author").is(FIRST_PREPARED_TEST_BOOK.getAuthor())),
                Book.class
            )
        );
    }
}