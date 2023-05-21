package ru.otus.homework6.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework6.strelkov.dao.GenresRepository;
import ru.otus.homework6.strelkov.domain.Genre;
import ru.otus.homework6.strelkov.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenresRepository genresRepository;

    @Override
    @Transactional
    public void addGenre(Genre genre) {
        genresRepository.save(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAllGenres() {
        return genresRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getGenreById(Long genreId) {
        return genresRepository.findById(genreId);
    }

    @Override
    @Transactional
    public void deleteGenreById(Long genreId) {
        genresRepository.delete(genreId);
    }
}
