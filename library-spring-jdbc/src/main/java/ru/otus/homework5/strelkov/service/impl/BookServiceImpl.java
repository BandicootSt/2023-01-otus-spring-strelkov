package ru.otus.homework5.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework5.strelkov.dao.BooksDao;
import ru.otus.homework5.strelkov.domain.Book;
import ru.otus.homework5.strelkov.dto.AddBookRequestDto;
import ru.otus.homework5.strelkov.service.AuthorService;
import ru.otus.homework5.strelkov.service.BookService;
import ru.otus.homework5.strelkov.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BooksDao booksDao;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public long addBook(AddBookRequestDto addBookRequest) {
        return booksDao.addBook(
            Book.builder()
                .name(addBookRequest.getName())
                .author(authorService.getAuthorById(addBookRequest.getAuthorId()))
                .genre(genreService.getGenreById(addBookRequest.getGenreId()))
                .build()
        );
    }

    @Override
    public List<Book> getAllBooks() {
        return booksDao.getAllBooks();
    }

    @Override
    public Book getBookByName(String bookName) {
        return booksDao.getBookByName(bookName);
    }

    @Override
    public void updateBookNameById(String newBookName, Long bookId) {
        booksDao.updateBookNameById(newBookName, bookId);
    }

    @Override
    public void deleteBookById(Long bookId) {
        booksDao.deleteBookById(bookId);
    }
}
