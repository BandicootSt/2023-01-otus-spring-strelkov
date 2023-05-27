package ru.otus.homework7.strelkov.service;

import ru.otus.homework7.strelkov.domain.BookComment;
import ru.otus.homework7.strelkov.dto.AddBookCommentRequestDto;

import java.util.List;

public interface BooksCommentsService {

    void addComment(AddBookCommentRequestDto addCommentRequest);

    List<BookComment> getCommentsByBookId(Long bookId);

    BookComment getCommentById(Long commentId);

    void editCommentTextById(Long commentId, String newText);

    void deleteCommentById(Long commentId);
}
