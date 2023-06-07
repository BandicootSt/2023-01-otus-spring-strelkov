package ru.otus.homework8.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework8.strelkov.dao.AuthorsRepository;
import ru.otus.homework8.strelkov.domain.Author;
import ru.otus.homework8.strelkov.exception.AuthorNotFoundException;
import ru.otus.homework8.strelkov.service.AuthorService;
import ru.otus.homework8.strelkov.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorsRepository authorsRepository;
    private final BookService bookService;

    @Override
    @Transactional
    public void addAuthor(Author author) {
        authorsRepository.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthorById(String authorId) {
        return authorsRepository.findById(authorId)
            .orElseThrow(() -> new AuthorNotFoundException("Not found author with id: " + authorId));
    }

    @Override
    @Transactional
    public void deleteAuthorById(String authorId) {
        bookService.deleteAllBooksByAuthorId(authorId);
        authorsRepository.deleteById(authorId);
    }
}
