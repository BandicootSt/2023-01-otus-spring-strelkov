package ru.otus.homework6.strelkov.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
    public List<BookComment> findByBookId(Long bookId) {
        TypedQuery<BookComment> query = entityManager.createQuery(
            "select c from BookComment c where c.book.id = :book_id",
            BookComment.class
        );
        query.setParameter("book_id", bookId);
        return query.getResultList();
    }

    @Override
    public BookComment findById(Long commentId) {
        TypedQuery<BookComment> query = entityManager.createQuery(
            "select c from BookComment c join fetch c.book where c.id = :id",
            BookComment.class
        );
        query.setParameter("id", commentId);
        return query.getSingleResult();
    }

    @Override
    public void updateById(Long commentId, String newText) {
        BookComment comment = findById(commentId);
        comment.setText(newText);
        entityManager.merge(comment);
    }

    @Override
    public void delete(Long commentId) {
        entityManager.remove(findById(commentId));
    }
}
