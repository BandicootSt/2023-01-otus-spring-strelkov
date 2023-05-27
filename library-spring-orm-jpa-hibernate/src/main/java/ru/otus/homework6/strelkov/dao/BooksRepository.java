package ru.otus.homework6.strelkov.dao;

import ru.otus.homework6.strelkov.domain.Book;

import java.util.List;

public interface BooksRepository {

    void save(Book book);

    List<Book> findAll();

    Book findByName(String bookName);

    Book findById(Long bookId);

    void updateBookNameById(Long bookId, String newBookName);

    void delete(Long bookId);
}
