package ru.otus.homework6.strelkov.dao;

import ru.otus.homework6.strelkov.domain.Author;

import java.util.List;

public interface AuthorsRepository {

    void save(Author author);

    List<Author> findAll();

    Author findById(Long authorId);

    void delete(Long authorId);
}
