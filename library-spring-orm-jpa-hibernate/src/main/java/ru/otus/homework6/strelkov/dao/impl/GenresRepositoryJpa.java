package ru.otus.homework6.strelkov.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework6.strelkov.dao.GenresRepository;
import ru.otus.homework6.strelkov.domain.Genre;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenresRepositoryJpa implements GenresRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void save(Genre genre) {
        entityManager.persist(genre);
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public Genre findById(Long genreId) {
        return entityManager.find(Genre.class, genreId);
    }

    @Override
    public void delete(Long genreId) {
        Genre genre = findById(genreId);
        entityManager.remove(genre);
    }
}
