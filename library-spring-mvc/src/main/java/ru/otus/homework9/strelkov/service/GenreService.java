package ru.otus.homework9.strelkov.service;

import ru.otus.homework9.strelkov.domain.Genre;
import ru.otus.homework9.strelkov.dto.AddGenreRequestDto;

import java.util.List;

public interface GenreService {

    void addGenre(AddGenreRequestDto addGenreRequestDto);

    List<Genre> getAllGenres();

    Genre getGenreById(Long genreId);

    void deleteGenreById(Long genreId);
}
