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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DataMongoTest
class BooksRepositoryTest {

    private static final Book PREPARED_TEST_BOOK = Book.builder()
        .id("1")
        .name("TestBook1")
        .author(new Author("1", "TestFirstName1", "TestLastName1", "TestPatronymic1"))
        .genre(new Genre("1", "TestGenre1"))
        .build();

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    public void testFindByName() {
        assertThat(
            booksRepository.findByName(PREPARED_TEST_BOOK.getName())
                .orElseThrow(() -> new BookNotFoundException("Not found book by name: " + PREPARED_TEST_BOOK.getName())),
            equalTo(PREPARED_TEST_BOOK)
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
}