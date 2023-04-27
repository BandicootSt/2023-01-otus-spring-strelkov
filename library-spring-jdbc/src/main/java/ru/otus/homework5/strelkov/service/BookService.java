package ru.otus.homework5.strelkov.service;

import ru.otus.homework5.strelkov.domain.Book;
import ru.otus.homework5.strelkov.dto.AddBookRequestDto;

import java.util.List;

public interface BookService {

    long addBook(AddBookRequestDto addBookRequest);

    List<Book> getAllBooks();

    Book getBookByName(String bookName);

    void updateBookNameById(String newBookName, Long bookId);

    void deleteBookById(Long bookId);
}
