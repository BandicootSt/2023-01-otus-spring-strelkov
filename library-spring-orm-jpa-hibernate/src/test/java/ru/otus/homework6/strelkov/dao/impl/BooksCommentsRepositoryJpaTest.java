package ru.otus.homework6.strelkov.dao.impl;

import com.google.common.collect.ImmutableList;
import jakarta.persistence.TypedQuery;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework6.strelkov.dao.BooksCommentsRepository;
import ru.otus.homework6.strelkov.domain.Author;
import ru.otus.homework6.strelkov.domain.Book;
import ru.otus.homework6.strelkov.domain.BookComment;
import ru.otus.homework6.strelkov.domain.Genre;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(BooksCommentsRepositoryJpa.class)
class BooksCommentsRepositoryJpaTest {

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

    private static final BookComment FIRST_PREPARED_TEST_COMMENT = new BookComment(
        1L,
        "TestComment1",
        FIRST_PREPARED_TEST_BOOK
    );
    private static final BookComment SECOND_PREPARED_TEST_COMMENT = new BookComment(
        2L,
        "TestComment2",
        FIRST_PREPARED_TEST_BOOK
    );
    private static final BookComment THIRD_PREPARED_TEST_COMMENT = new BookComment(
        3L,
        "TestComment3",
        FIRST_PREPARED_TEST_BOOK
    );

    private static final List<BookComment> PREPARED_TEST_COMMENTS = ImmutableList.of(
        FIRST_PREPARED_TEST_COMMENT,
        SECOND_PREPARED_TEST_COMMENT,
        THIRD_PREPARED_TEST_COMMENT
    );

    @Autowired
    private BooksCommentsRepository commentsRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSave() {

        final String testCommentText = "some_test_comment";
        BookComment testBookComment = new BookComment(testCommentText, SECOND_PREPARED_TEST_BOOK);

        commentsRepo.save(testBookComment);

        TypedQuery<BookComment> query = entityManager.getEntityManager().createQuery(
            "select c from BookComment c where c.text = :text",
            BookComment.class
        );

        query.setParameter("text", testCommentText);

        assertEquals(testBookComment, query.getSingleResult());
    }

    @Test
    public void testFindCommentsByBookId() {
        assertTrue(commentsRepo.findByBookId(FIRST_PREPARED_TEST_BOOK.getId()).containsAll(PREPARED_TEST_COMMENTS));
    }

    @Test
    public void testFindById() {
        assertThat(commentsRepo.findById(FIRST_PREPARED_TEST_COMMENT.getId()), equalTo(FIRST_PREPARED_TEST_COMMENT));
    }

    @Test
    public void testUpdateCommentById() {
        commentsRepo.updateById(1L, "NewCommentText");
        assertThat(entityManager.find(BookComment.class, 1L).getText(), equalTo("NewCommentText"));
    }

    @Test
    public void testDelete() {
        commentsRepo.delete(THIRD_PREPARED_TEST_COMMENT.getId());
        assertThat(entityManager.find(BookComment.class,3L), Matchers.nullValue());
    }

}