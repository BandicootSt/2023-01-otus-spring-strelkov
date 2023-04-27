package ru.otus.homework5.strelkov.dao;

import ru.otus.homework5.strelkov.domain.Genre;

import java.util.List;

public interface GenresDao {

    Genre addGenre(Genre genre);

    List<Genre> getAllGenres();

    Genre getGenreById(Long genreId);

    void deleteGenreById(Long genreId);
}
