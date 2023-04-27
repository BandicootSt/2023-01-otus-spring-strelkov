package ru.otus.homework5.strelkov.dao;

import ru.otus.homework5.strelkov.domain.Author;

import java.util.List;

public interface AuthorsDao {

    Author addAuthor(Author author);

    List<Author> getAllAuthors();

    Author getAuthorById(Long authorId);

    void deleteAuthorById(Long authorId);
}
