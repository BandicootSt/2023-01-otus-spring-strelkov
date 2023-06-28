package ru.otus.homework10.strelkov.service;

import ru.otus.homework10.strelkov.domain.Author;
import ru.otus.homework10.strelkov.dto.AddAuthorRequestDto;

import java.util.List;

public interface AuthorService {

    void addAuthor(AddAuthorRequestDto authorRequestDto);

    List<Author> getAllAuthors();

    Author getAuthorById(Long authorId);

    void deleteAuthorById(Long authorId);
}
