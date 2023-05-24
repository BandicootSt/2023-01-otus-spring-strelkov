package ru.otus.homework6.strelkov.dao;

import ru.otus.homework6.strelkov.domain.BookComment;

import java.util.List;

public interface BooksCommentsRepository {

    void save(BookComment bookComment);

    List<BookComment> findByBookId(Long bookId);

    BookComment findById(Long commentId);

    void updateById(Long commentId, String newText);

    void delete(Long commentId);
}
