package ru.otus.homework8.strelkov.dao;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.homework8.strelkov.domain.Author;
import ru.otus.homework8.strelkov.domain.Book;
import ru.otus.homework8.strelkov.domain.BookComment;
import ru.otus.homework8.strelkov.domain.Genre;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
class BooksCommentsRepositoryTest {

    private static final Book PREPARED_TEST_BOOK =
        new Book(
            "1",
            "TestBook1",
            new Author("1", "TestFirstName1", "TestLastName1", "TestPatronymic1"),
            new Genre("1", "TestGenre1")
        );

    private static final List<BookComment> PREPARED_TEST_COMMENTS = ImmutableList.of(
        new BookComment(
            "1",
            "TestComment1",
            PREPARED_TEST_BOOK
        ),
        new BookComment(
            "2",
            "TestComment2",
            PREPARED_TEST_BOOK
        ),
        new BookComment(
            "3",
            "TestComment3",
            PREPARED_TEST_BOOK
        )
    );

    @Autowired
    private BooksCommentsRepository commentsRepo;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    public void testFindCommentsByBookId() {
        assertTrue(commentsRepo.findByBookId(PREPARED_TEST_BOOK.getId()).containsAll(PREPARED_TEST_COMMENTS));
    }

    @Test
    public void testUpdateCommentById() {
        commentsRepo.updateTextById("1", "NewCommentText");
        assertThat(
            mongoOperations.findOne(
                    Query.query(Criteria.where("id").is("1")),
                    BookComment.class
                )
                .getText(),
            equalTo("NewCommentText")
        );
    }

}