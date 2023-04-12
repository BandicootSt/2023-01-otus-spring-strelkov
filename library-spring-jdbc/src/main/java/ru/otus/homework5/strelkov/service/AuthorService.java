package ru.otus.homework5.strelkov.service;

import ru.otus.homework5.strelkov.domain.Author;

import java.util.List;

public interface AuthorService {

    long addAuthor(Author author);

    List<Author> getAllAuthors();

    Author getAuthorById(Long authorId);

    void deleteAuthorById(Long authorId);
}
