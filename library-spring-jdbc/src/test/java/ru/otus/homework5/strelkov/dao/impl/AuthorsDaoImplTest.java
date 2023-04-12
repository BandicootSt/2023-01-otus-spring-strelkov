package ru.otus.homework5.strelkov.dao.impl;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework5.strelkov.dao.AuthorsDao;
import ru.otus.homework5.strelkov.domain.Author;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

@JdbcTest
@Import(AuthorsDaoImpl.class)
class AuthorsDaoImplTest {

    private static final Author FIRST_PREPARED_TEST_AUTHOR =
        Author.builder()
            .id(1L)
            .firstName("TestFirstName1")
            .lastName("TestLastName1")
            .patronymic("TestPatronymic1")
            .build();

    private static final Author SECOND_PREPARED_TEST_AUTHOR =
        Author.builder()
            .id(2L)
            .firstName("TestFirstName2")
            .lastName("TestLastName2")
            .patronymic("TestPatronymic2")
            .build();

    private static final Author THIRD_PREPARED_TEST_AUTHOR =
        Author.builder()
            .id(3L)
            .firstName("TestFirstName3")
            .lastName("TestLastName3")
            .build();

    private static final List<Author> PREPARED_TEST_AUTHORS = ImmutableList.of(
        FIRST_PREPARED_TEST_AUTHOR,
        SECOND_PREPARED_TEST_AUTHOR,
        THIRD_PREPARED_TEST_AUTHOR
    );

    @Autowired
    private AuthorsDao authorsDao;

    @Test
    public void testAddAuthor() {

        Author testAuthor = Author.builder()
            .firstName("TestFirstName4")
            .lastName("TestLastName4")
            .patronymic("TestPatronymic4")
            .build();

        long addedAuthorId = authorsDao.addAuthor(testAuthor);

        assertThat(addedAuthorId, equalTo(4L));

        testAuthor = testAuthor
            .toBuilder()
            .id(addedAuthorId)
            .build();

        assertThat(authorsDao.getAuthorById(addedAuthorId), equalTo(testAuthor));
    }

    @Test
    public void testGetAllAuthors() {
        assertTrue(authorsDao.getAllAuthors().containsAll(PREPARED_TEST_AUTHORS));
    }

    @Test
    public void testGetAuthorById() {
        assertThat(authorsDao.getAuthorById(FIRST_PREPARED_TEST_AUTHOR.getId()), equalTo(FIRST_PREPARED_TEST_AUTHOR));
    }

    @Test
    public void testDeleteAuthor() {
        assertThat(authorsDao.getAllAuthors(), hasSize(3));
        authorsDao.deleteAuthorById(3L);
        assertThat(authorsDao.getAllAuthors(), hasSize(2));
    }

}