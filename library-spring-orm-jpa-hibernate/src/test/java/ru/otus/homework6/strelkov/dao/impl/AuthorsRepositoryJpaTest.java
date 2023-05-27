package ru.otus.homework6.strelkov.dao.impl;

import com.google.common.collect.ImmutableList;
import jakarta.persistence.TypedQuery;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework6.strelkov.dao.AuthorsRepository;
import ru.otus.homework6.strelkov.domain.Author;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(AuthorsRepositoryJpa.class)
class AuthorsRepositoryJpaTest {

    private static final Author FIRST_PREPARED_TEST_AUTHOR =
        new Author(1L, "TestFirstName1", "TestLastName1", "TestPatronymic1");

    private static final Author SECOND_PREPARED_TEST_AUTHOR =
        new Author(2L, "TestFirstName2", "TestLastName2", "TestPatronymic2");

    private static final Author THIRD_PREPARED_TEST_AUTHOR =
        new Author(3L, "TestFirstName3", "TestLastName3", null);

    private static final List<Author> PREPARED_TEST_AUTHORS = ImmutableList.of(
        FIRST_PREPARED_TEST_AUTHOR,
        SECOND_PREPARED_TEST_AUTHOR,
        THIRD_PREPARED_TEST_AUTHOR
    );

    @Autowired
    private AuthorsRepository authorsRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSave() {

        Author testAuthor = new Author("TestFirstName4", "TestLastName4", "TestPatronymic4");

        authorsRepo.save(testAuthor);

        TypedQuery<Author> query = entityManager.getEntityManager().createQuery(
            "select a from Author a where a.firstName = :firstName",
            Author.class
        );

        query.setParameter("firstName", "TestFirstName4");

        Author result = query.getSingleResult();

        assertEquals(testAuthor, result);
    }

    @Test
    public void testFindAll() {
        assertTrue(authorsRepo.findAll().containsAll(PREPARED_TEST_AUTHORS));
    }

    @Test
    public void testFindById() {
        assertThat(authorsRepo.findById(FIRST_PREPARED_TEST_AUTHOR.getId()), equalTo(FIRST_PREPARED_TEST_AUTHOR));
    }

    @Test
    public void testDelete() {
        authorsRepo.delete(3L);
        assertThat(entityManager.find(Author.class,3L), Matchers.nullValue());
    }

}