package ru.otus.homework8.strelkov.service;

import ru.otus.homework8.strelkov.domain.Author;

import java.util.List;

public interface AuthorService {

    void addAuthor(Author author);

    List<Author> getAllAuthors();

    Author getAuthorById(String authorId);

    void deleteAuthorById(String authorId);
}
