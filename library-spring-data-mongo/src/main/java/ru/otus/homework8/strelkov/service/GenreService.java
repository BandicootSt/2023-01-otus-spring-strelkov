package ru.otus.homework8.strelkov.service;

import ru.otus.homework8.strelkov.domain.Genre;

import java.util.List;

public interface GenreService {

    void addGenre(Genre genre);

    List<Genre> getAllGenres();

    Genre getGenreById(String genreId);

    void deleteGenreById(String genreId);
}
