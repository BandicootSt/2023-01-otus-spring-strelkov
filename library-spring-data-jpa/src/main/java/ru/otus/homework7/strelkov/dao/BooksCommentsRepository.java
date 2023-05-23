package ru.otus.homework7.strelkov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.homework7.strelkov.domain.BookComment;

import java.util.List;

public interface BooksCommentsRepository extends JpaRepository<BookComment, Long> {

    List<BookComment> findByBookId(Long bookId);

    @Modifying
    @Query("update BookComment bc set bc.text = :text where bc.id = :id")
    void updateTextById(
        @Param("id") long commentId,
        @Param("text") String text
    );
}
