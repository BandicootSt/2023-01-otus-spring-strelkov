package ru.otus.homework6.strelkov.service;

import ru.otus.homework6.strelkov.domain.Author;

import java.util.List;

public interface AuthorService {

    void addAuthor(Author author);

    List<Author> getAllAuthors();

    Author getAuthorById(Long authorId);

    void deleteAuthorById(Long authorId);
}
