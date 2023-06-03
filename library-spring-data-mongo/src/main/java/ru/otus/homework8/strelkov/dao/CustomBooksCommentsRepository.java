package ru.otus.homework8.strelkov.dao;

public interface CustomBooksCommentsRepository  {

    void updateTextById(String commentId, String text);
}
