package ru.otus.homework9.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework9.strelkov.dao.AuthorsRepository;
import ru.otus.homework9.strelkov.domain.Author;
import ru.otus.homework9.strelkov.dto.AddAuthorRequestDto;
import ru.otus.homework9.strelkov.exception.AuthorNotFoundException;
import ru.otus.homework9.strelkov.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorsRepository authorsRepository;

    @Override
    @Transactional
    public void addAuthor(AddAuthorRequestDto addAuthorRequestDto) {
        authorsRepository.save(new Author(
            addAuthorRequestDto.getFirstName(),
            addAuthorRequestDto.getLastName(),
            addAuthorRequestDto.getPatronymic()
        ));
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
