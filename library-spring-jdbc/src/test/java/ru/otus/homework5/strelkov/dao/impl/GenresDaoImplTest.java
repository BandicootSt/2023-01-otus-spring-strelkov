package ru.otus.homework5.strelkov.dao.impl;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework5.strelkov.dao.GenresDao;
import ru.otus.homework5.strelkov.domain.Genre;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

@JdbcTest
@Import(GenresDaoImpl.class)
class GenresDaoImplTest {

    private static final Genre FIRST_PREPARED_TEST_GENRE = new Genre(1L, "TestGenre1");
    private static final Genre SECOND_PREPARED_TEST_GENRE = new Genre(2L, "TestGenre2");
    private static final Genre THIRD_PREPARED_TEST_GENRE = new Genre(3L, "TestGenre3");

    private static final List<Genre> PREPARED_TEST_GENRES = ImmutableList.of(
        FIRST_PREPARED_TEST_GENRE,
        SECOND_PREPARED_TEST_GENRE,
        THIRD_PREPARED_TEST_GENRE
    );

    @Autowired
    private GenresDao genresDao;

    @Test
    public void testAddAGenre() {

        long addedGenreId = genresDao.addGenre(new Genre("TestGenre4"));

        assertThat(addedGenreId, equalTo(4L));

        Genre testGenre = new Genre(4L, "TestGenre4");

        assertThat(genresDao.getGenreById(addedGenreId), equalTo(testGenre));
    }

    @Test
    public void testGetAllGenres() {
        assertTrue(genresDao.getAllGenres().containsAll(PREPARED_TEST_GENRES));
    }

    @Test
    public void testGetGenreById() {
        assertThat(genresDao.getGenreById(FIRST_PREPARED_TEST_GENRE.getId()), equalTo(FIRST_PREPARED_TEST_GENRE));
    }

    @Test
    public void testDeleteGenre() {
        assertThat(genresDao.getAllGenres(), hasSize(3));
        genresDao.deleteGenreById(3L);
        assertThat(genresDao.getAllGenres(), hasSize(2));
    }

}