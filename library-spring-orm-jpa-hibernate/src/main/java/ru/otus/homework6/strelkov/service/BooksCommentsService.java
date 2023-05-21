package ru.otus.homework6.strelkov.service;

import ru.otus.homework6.strelkov.domain.BookComment;
import ru.otus.homework6.strelkov.dto.AddBookCommentRequestDto;

import java.util.List;

public interface BooksCommentsService {

    void addComment(AddBookCommentRequestDto addCommentRequest);

    List<BookComment> getCommentsByBookId(Long bookId);

    BookComment getCommentById(Long commentId);

    void editCommentById(Long commentId, String newText);

    void deleteCommentById(Long commentId);
}
