package ru.otus.homework7.strelkov.service;

import ru.otus.homework7.strelkov.domain.Genre;

import java.util.List;

public interface GenreService {

    void addGenre(Genre genre);

    List<Genre> getAllGenres();

    Genre getGenreById(Long genreId);

    void deleteGenreById(Long genreId);
}
