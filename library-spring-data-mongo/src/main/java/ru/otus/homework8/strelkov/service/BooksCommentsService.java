package ru.otus.homework8.strelkov.service;

import ru.otus.homework8.strelkov.domain.BookComment;
import ru.otus.homework8.strelkov.dto.AddBookCommentRequestDto;

import java.util.List;

public interface BooksCommentsService {

    void addComment(AddBookCommentRequestDto addCommentRequest);

    List<BookComment> getCommentsByBookId(String bookId);

    BookComment getCommentById(String commentId);

    void editCommentTextById(String commentId, String newText);

    void deleteCommentById(String commentId);
}
