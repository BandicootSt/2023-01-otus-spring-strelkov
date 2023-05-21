package ru.otus.homework6.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework6.strelkov.dao.BooksCommentsRepository;
import ru.otus.homework6.strelkov.domain.BookComment;
import ru.otus.homework6.strelkov.dto.AddBookCommentRequestDto;
import ru.otus.homework6.strelkov.service.BookService;
import ru.otus.homework6.strelkov.service.BooksCommentsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BooksCommentsServiceImpl implements BooksCommentsService {

    private final BooksCommentsRepository booksCommentsRepository;
    private final BookService bookService;

    @Override
    @Transactional
    public void addComment(AddBookCommentRequestDto addCommentRequest) {
        booksCommentsRepository.save(new BookComment(
            addCommentRequest.getText(),
            bookService.getBookById(addCommentRequest.getBookId())
        ));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookComment> getCommentsByBookId(Long bookId) {
        return booksCommentsRepository.findCommentsByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public BookComment getCommentById(Long bookId) {
        return booksCommentsRepository.findById(bookId);
    }

    @Override
    @Transactional
    public void editCommentById(Long commentId, String newText) {
        booksCommentsRepository.updateCommentById(commentId, newText);
    }

    @Override
    @Transactional
    public void deleteCommentById(Long commentId) {
        booksCommentsRepository.delete(commentId);
    }
}
