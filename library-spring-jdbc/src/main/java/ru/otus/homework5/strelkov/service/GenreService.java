package ru.otus.homework5.strelkov.service;

import ru.otus.homework5.strelkov.domain.Genre;

import java.util.List;

public interface GenreService {

    long addGenre(Genre genre);

    List<Genre> getAllGenres();

    Genre getGenreById(Long genreId);

    void deleteGenreById(Long genreId);
}
