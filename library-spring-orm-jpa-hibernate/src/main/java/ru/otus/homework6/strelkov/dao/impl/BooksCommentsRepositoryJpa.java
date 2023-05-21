package ru.otus.homework6.strelkov.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework6.strelkov.dao.BooksCommentsRepository;
import ru.otus.homework6.strelkov.domain.BookComment;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BooksCommentsRepositoryJpa implements BooksCommentsRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void save(BookComment bookComment) {
        entityManager.persist(bookComment);
    }

    @Override
    public List<BookComment> findCommentsByBookId(Long bookId) {
        TypedQuery<BookComment> query = entityManager.createQuery(
            "select c from BookComment c join fetch c.book where c.book.id = :book_id",
            BookComment.class
        );
        query.setParameter("book_id", bookId);
        return query.getResultList();
    }

    @Override
    public BookComment findById(Long commentId) {
        return entityManager.find(BookComment.class, commentId);
    }

    @Override
    public void updateCommentById(Long commentId, String newText) {
        Query query = entityManager.createQuery("update BookComment c set c.text = :text where c.id = :id");
        query.setParameter("text", newText);
        query.setParameter("id", commentId);
        query.executeUpdate();
    }

    @Override
    public void delete(Long commentId) {
        Query query = entityManager.createQuery("delete from BookComment c where c.id = :id");
        query.setParameter("id", commentId);
        query.executeUpdate();
    }
}
