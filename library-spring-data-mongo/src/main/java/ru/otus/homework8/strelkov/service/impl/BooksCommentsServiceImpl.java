package ru.otus.homework8.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework8.strelkov.dao.BooksCommentsRepository;
import ru.otus.homework8.strelkov.dao.BooksRepository;
import ru.otus.homework8.strelkov.domain.Book;
import ru.otus.homework8.strelkov.domain.BookComment;
import ru.otus.homework8.strelkov.dto.AddBookCommentRequestDto;
import ru.otus.homework8.strelkov.exception.BookCommentNotFoundException;
import ru.otus.homework8.strelkov.exception.BookNotFoundException;
import ru.otus.homework8.strelkov.service.BooksCommentsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BooksCommentsServiceImpl implements BooksCommentsService {

    private final BooksCommentsRepository booksCommentsRepository;
    private final BooksRepository booksRepository;

    @Override
    @Transactional
    public void addComment(AddBookCommentRequestDto addCommentRequest) {
        booksCommentsRepository.save(new BookComment(
            addCommentRequest.getText(),
            booksRepository.findById(addCommentRequest.getBookId())
                .orElseThrow(() -> new BookNotFoundException("Not found book with id: " + addCommentRequest.getBookId()))
        ));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookComment> getCommentsByBookId(String bookId) {
        Book book = booksRepository.findById(bookId)
            .orElseThrow(() -> new BookNotFoundException("Not found book with id: " + bookId));
        List<BookComment> comments = booksCommentsRepository.findByBookId(bookId);
        comments.forEach(comment -> comment.setBook(book));
        return comments;
    }

    @Override
    @Transactional(readOnly = true)
    public BookComment getCommentById(String commentId) {
        return booksCommentsRepository.findById(commentId)
            .orElseThrow(() -> new BookCommentNotFoundException("Not found book comment with id: " + commentId));
    }

    @Override
    @Transactional
    public void editCommentTextById(String commentId, String newText) {
        booksCommentsRepository.updateTextById(commentId, newText);
    }

    @Override
    @Transactional
    public void deleteCommentById(String commentId) {
        booksCommentsRepository.deleteById(commentId);
    }

    @Override
    public void deleteAllCommentsByBook(Book book) {
        booksCommentsRepository.deleteAllByBook(book);
    }

    @Override
    public void deleteAllCommentsByBookAuthorId(String authorId) {
        booksCommentsRepository.deleteAllCommentsByBookAuthorId(authorId);
    }

    @Override
    public void deleteAllCommentsByBookGenreId(String genreId) {
        booksCommentsRepository.deleteAllCommentsByBookGenreId(genreId);
    }
}
