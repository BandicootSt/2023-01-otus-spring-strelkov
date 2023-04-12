package ru.otus.homework5.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework5.strelkov.dao.AuthorsDao;
import ru.otus.homework5.strelkov.domain.Author;
import ru.otus.homework5.strelkov.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorsDao authorsDao;

    @Override
    public long addAuthor(Author author) {
        return authorsDao.addAuthor(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorsDao.getAllAuthors();
    }

    @Override
    public Author getAuthorById(Long authorId) {
        return authorsDao.getAuthorById(authorId);
    }

    @Override
    public void deleteAuthorById(Long authorId) {
        authorsDao.deleteAuthorById(authorId);
    }
}
