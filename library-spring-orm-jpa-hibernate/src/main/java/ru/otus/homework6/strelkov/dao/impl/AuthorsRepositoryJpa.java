package ru.otus.homework6.strelkov.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework6.strelkov.dao.AuthorsRepository;
import ru.otus.homework6.strelkov.domain.Author;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorsRepositoryJpa implements AuthorsRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void save(Author author) {
        entityManager.persist(author);
    }

    @Transactional(readOnly = true)
    public List<Author> findAll() {
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public Author findById(Long authorId) {
        return entityManager.find(Author.class, authorId);
    }

    @Override
    public void delete(Long authorId) {
        Author author = findById(authorId);
        entityManager.remove(author);
    }

}
