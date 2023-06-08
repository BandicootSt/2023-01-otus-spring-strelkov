package ru.otus.homework9.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework9.strelkov.dao.BooksCommentsRepository;
import ru.otus.homework9.strelkov.dao.BooksRepository;
import ru.otus.homework9.strelkov.domain.BookComment;
import ru.otus.homework9.strelkov.dto.AddBookCommentRequestDto;
import ru.otus.homework9.strelkov.exception.BookCommentNotFoundException;
import ru.otus.homework9.strelkov.exception.BookNotFoundException;
import ru.otus.homework9.strelkov.service.BooksCommentsService;

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
    public List<BookComment> getCommentsByBookId(Long bookId) {
        return booksCommentsRepository.findByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public BookComment getCommentById(Long commentId) {
        return booksCommentsRepository.findById(commentId)
            .orElseThrow(() -> new BookCommentNotFoundException("Not found book comment with id: " + commentId));
    }

    @Override
    @Transactional
    public void editCommentTextById(Long commentId, String newText) {
        booksCommentsRepository.updateTextById(commentId, newText);
    }

    @Override
    @Transactional
    public void deleteCommentById(Long commentId) {
        booksCommentsRepository.deleteById(commentId);
    }
}
