package ru.otus.homework10.strelkov.service;

import ru.otus.homework10.strelkov.domain.Genre;
import ru.otus.homework10.strelkov.dto.AddGenreRequestDto;

import java.util.List;

public interface GenreService {

    void addGenre(AddGenreRequestDto addGenreRequestDto);

    List<Genre> getAllGenres();

    Genre getGenreById(Long genreId);

    void deleteGenreById(Long genreId);
}
