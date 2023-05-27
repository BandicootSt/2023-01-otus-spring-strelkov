package ru.otus.homework6.strelkov.dao;

import ru.otus.homework6.strelkov.domain.Genre;

import java.util.List;

public interface GenresRepository {

    void save(Genre genre);

    List<Genre> findAll();

    Genre findById(Long genreId);

    void delete(Long genreId);
}
