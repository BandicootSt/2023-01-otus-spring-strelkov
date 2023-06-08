package ru.otus.homework8.strelkov.dao;

import ru.otus.homework8.strelkov.domain.Book;

public interface CustomBooksCommentsRepository  {

    void updateTextById(String commentId, String text);

    void deleteAllByBook(Book book);
}
