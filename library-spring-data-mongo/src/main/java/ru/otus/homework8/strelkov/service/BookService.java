package ru.otus.homework8.strelkov.service;

import ru.otus.homework8.strelkov.domain.Book;
import ru.otus.homework8.strelkov.dto.AddBookRequestDto;

import java.util.List;

public interface BookService {

    void addBook(AddBookRequestDto addBookRequest);

    List<Book> getAllBooks();

    Book getBookByName(String bookName);

    Book getBookById(String bookId);

    void updateBookNameById(String newBookName, String bookId);

    void deleteBookById(String bookId);
}
