package ru.otus.homework7.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework7.strelkov.dao.AuthorsRepository;
import ru.otus.homework7.strelkov.domain.Author;
import ru.otus.homework7.strelkov.exception.AuthorNotFoundException;
import ru.otus.homework7.strelkov.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorsRepository authorsRepository;

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
    public Author getAuthorById(Long authorId) {
        return authorsRepository.findById(authorId)
            .orElseThrow(() -> new AuthorNotFoundException("Not found author with id: " + authorId));
    }

    @Override
    @Transactional
    public void deleteAuthorById(Long authorId) {
        authorsRepository.deleteById(authorId);
    }
}
