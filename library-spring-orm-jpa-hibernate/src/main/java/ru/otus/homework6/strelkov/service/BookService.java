package ru.otus.homework6.strelkov.service;

import ru.otus.homework6.strelkov.domain.Book;
import ru.otus.homework6.strelkov.dto.AddBookRequestDto;

import java.util.List;

public interface BookService {

    void addBook(AddBookRequestDto addBookRequest);

    List<Book> getAllBooks();

    Book getBookByName(String bookName);

    Book getBookById(Long bookId);

    void updateBookNameById(String newBookName, Long bookId);

    void deleteBookById(Long bookId);
}
