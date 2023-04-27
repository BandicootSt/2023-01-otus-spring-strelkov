package ru.otus.homework5.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework5.strelkov.dao.GenresDao;
import ru.otus.homework5.strelkov.domain.Genre;
import ru.otus.homework5.strelkov.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenresDao genresDao;

    @Override
    public Genre addGenre(Genre genre) {
        return genresDao.addGenre(genre);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genresDao.getAllGenres();
    }

    @Override
    public Genre getGenreById(Long genreId) {
        return genresDao.getGenreById(genreId);
    }

    @Override
    public void deleteGenreById(Long genreId) {
        genresDao.deleteGenreById(genreId);
    }
}
