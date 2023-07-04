package ru.otus.homework10.strelkov.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.homework10.strelkov.domain.BookComment;

import java.util.List;
import java.util.Optional;

public interface BooksCommentsRepository extends JpaRepository<BookComment, Long> {

    @Modifying
    @Query("update BookComment bc set bc.text = :text where bc.id = :id")
    void updateTextById(
        @Param("id") long commentId,
        @Param("text") String text
    );

    List<BookComment> findByBookId(Long bookId);

    @Override
    @EntityGraph(value = "book-comment-entity-graph")
    Optional<BookComment> findById(Long id);
}
