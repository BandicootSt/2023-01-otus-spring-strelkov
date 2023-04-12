package ru.otus.homework5.strelkov.dao;

import ru.otus.homework5.strelkov.domain.Book;

import java.util.List;

public interface BooksDao {

    long addBook(Book book);

    List<Book> getAllBooks();

    Book getBookByName(String bookName);

    void updateBookNameById(String newBookName, Long bookId);

    void deleteBookById(Long bookId);
}
