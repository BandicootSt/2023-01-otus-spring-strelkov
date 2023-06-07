package ru.otus.homework8.strelkov.dao;

import ru.otus.homework8.strelkov.domain.Book;

import java.util.List;

public interface CustomBooksCommentsRepository  {

    void updateTextById(String commentId, String text);

    void deleteAllByBooks(List<Book> books);

    void deleteAllByBook(Book book);
}
