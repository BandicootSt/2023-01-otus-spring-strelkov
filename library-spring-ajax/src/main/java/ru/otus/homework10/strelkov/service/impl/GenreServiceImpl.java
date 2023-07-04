package ru.otus.homework10.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework10.strelkov.dao.GenresRepository;
import ru.otus.homework10.strelkov.domain.Genre;
import ru.otus.homework10.strelkov.dto.AddGenreRequestDto;
import ru.otus.homework10.strelkov.exception.GenreNotFoundException;
import ru.otus.homework10.strelkov.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenresRepository genresRepository;

    @Override
    @Transactional
    public void addGenre(AddGenreRequestDto addGenreRequestDto) {
        genresRepository.save(new Genre(addGenreRequestDto.getName()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAllGenres() {
        return genresRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getGenreById(Long genreId) {
        return genresRepository.findById(genreId)
            .orElseThrow(() -> new GenreNotFoundException("Not found genre with id: " + genreId));
    }

    @Override
    @Transactional
    public void deleteGenreById(Long genreId) {
        genresRepository.deleteById(genreId);
    }
}
