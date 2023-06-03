package ru.otus.homework8.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework8.strelkov.dao.BooksRepository;
import ru.otus.homework8.strelkov.domain.Book;
import ru.otus.homework8.strelkov.dto.AddBookRequestDto;
import ru.otus.homework8.strelkov.exception.BookNotFoundException;
import ru.otus.homework8.strelkov.service.AuthorService;
import ru.otus.homework8.strelkov.service.BookService;
import ru.otus.homework8.strelkov.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BooksRepository booksRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    @Transactional
    public void addBook(AddBookRequestDto addBookRequest) {
        booksRepository.save(
            Book.builder()
                .name(addBookRequest.getName())
                .author(authorService.getAuthorById(addBookRequest.getAuthorId()))
                .genre(genreService.getGenreById(addBookRequest.getGenreId()))
                .build()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookByName(String bookName) {
        return booksRepository.findByName(bookName)
            .orElseThrow(() -> new BookNotFoundException("Not found book with name: " + bookName));
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(String bookId) {
        return booksRepository.findById(bookId)
            .orElseThrow(() -> new BookNotFoundException("Not found book with id: " + bookId));
    }

    @Override
    @Transactional
    public void updateBookNameById(String newBookName, String bookId) {
        booksRepository.updateNameById(bookId, newBookName);
    }

    @Override
    @Transactional
    public void deleteBookById(String bookId) {
        booksRepository.deleteById(bookId);
    }
}
