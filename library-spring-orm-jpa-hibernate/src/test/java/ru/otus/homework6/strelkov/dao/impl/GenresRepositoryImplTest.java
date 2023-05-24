package ru.otus.homework6.strelkov.dao.impl;

import com.google.common.collect.ImmutableList;
import jakarta.persistence.TypedQuery;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework6.strelkov.dao.GenresRepository;
import ru.otus.homework6.strelkov.domain.Genre;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(GenresRepositoryJpa.class)
class GenresRepositoryImplTest {

    private static final Genre FIRST_PREPARED_TEST_GENRE = new Genre(1L, "TestGenre1");
    private static final Genre SECOND_PREPARED_TEST_GENRE = new Genre(2L, "TestGenre2");
    private static final Genre THIRD_PREPARED_TEST_GENRE = new Genre(3L, "TestGenre3");

    private static final List<Genre> PREPARED_TEST_GENRES = ImmutableList.of(
        FIRST_PREPARED_TEST_GENRE,
        SECOND_PREPARED_TEST_GENRE,
        THIRD_PREPARED_TEST_GENRE
    );

    @Autowired
    private GenresRepository genresRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSave() {

        Genre testGenre = new Genre("TestGenre4");

        genresRepo.save(testGenre);

        TypedQuery<Genre> query = entityManager.getEntityManager().createQuery(
            "select g from Genre g where g.name = :name",
            Genre.class
        );

        query.setParameter("name", "TestGenre4");

        assertEquals(testGenre, query.getSingleResult());
    }

    @Test
    public void testFindAll() {
        assertTrue(genresRepo.findAll().containsAll(PREPARED_TEST_GENRES));
    }

    @Test
    public void testFindById() {
        assertThat(genresRepo.findById(FIRST_PREPARED_TEST_GENRE.getId()), equalTo(FIRST_PREPARED_TEST_GENRE));
    }

    @Test
    public void testDelete() {
        genresRepo.delete(3L);
        assertThat(entityManager.find(Genre.class,3L), Matchers.nullValue());
    }

}