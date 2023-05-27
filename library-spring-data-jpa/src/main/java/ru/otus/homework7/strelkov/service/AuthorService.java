package ru.otus.homework7.strelkov.service;

import ru.otus.homework7.strelkov.domain.Author;

import java.util.List;

public interface AuthorService {

    void addAuthor(Author author);

    List<Author> getAllAuthors();

    Author getAuthorById(Long authorId);

    void deleteAuthorById(Long authorId);
}
