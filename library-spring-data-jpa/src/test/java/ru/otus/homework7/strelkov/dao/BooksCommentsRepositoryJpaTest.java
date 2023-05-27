package ru.otus.homework7.strelkov.dao;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework7.strelkov.domain.Author;
import ru.otus.homework7.strelkov.domain.Book;
import ru.otus.homework7.strelkov.domain.BookComment;
import ru.otus.homework7.strelkov.domain.Genre;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class BooksCommentsRepositoryJpaTest {

    private static final Book PREPARED_TEST_BOOK =
        new Book(
            1L,
            "TestBook1",
            new Author(1L, "TestFirstName1", "TestLastName1", "TestPatronymic1"),
            new Genre(1L, "TestGenre1")
        );

    private static final List<BookComment> PREPARED_TEST_COMMENTS = ImmutableList.of(
        new BookComment(
            1L,
            "TestComment1",
            PREPARED_TEST_BOOK
        ),
        new BookComment(
            2L,
            "TestComment2",
            PREPARED_TEST_BOOK
        ),
        new BookComment(
            3L,
            "TestComment3",
            PREPARED_TEST_BOOK
        )
    );

    @Autowired
    private BooksCommentsRepository commentsRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindCommentsByBookId() {
        assertTrue(commentsRepo.findByBookId(PREPARED_TEST_BOOK.getId()).containsAll(PREPARED_TEST_COMMENTS));
    }

    @Test
    public void testUpdateCommentById() {
        commentsRepo.updateTextById(1L, "NewCommentText");
        assertThat(entityManager.find(BookComment.class, 1L).getText(), equalTo("NewCommentText"));
    }

}