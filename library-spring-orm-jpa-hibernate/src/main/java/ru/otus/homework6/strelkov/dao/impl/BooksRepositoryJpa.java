package ru.otus.homework6.strelkov.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework6.strelkov.dao.BooksRepository;
import ru.otus.homework6.strelkov.domain.Book;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BooksRepositoryJpa implements BooksRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void save(Book book) {
        entityManager.persist(book);
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b join fetch b.author join fetch b.genre", Book.class);
        return query.getResultList();
    }

    @Override
    public Book findByName(String bookName) {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b join fetch b.author join fetch b.genre where b.name = :name", Book.class);
        query.setParameter("name", bookName);
        return query.getSingleResult();
    }

    @Override
    public Book findById(Long bookId) {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b join fetch b.author join fetch b.genre where b.id = :id", Book.class);
        query.setParameter("id", bookId);
        return query.getSingleResult();
    }

    @Override
    public void updateBookNameById(Long bookId, String newBookName) {
        Query query = entityManager.createQuery("update Book b set b.name = :name where b.id = :id");
        query.setParameter("name", newBookName);
        query.setParameter("id", bookId);
        query.executeUpdate();
    }

    @Override
    public void delete(Long bookId) {
        Query query = entityManager.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", bookId);
        query.executeUpdate();
    }
}
