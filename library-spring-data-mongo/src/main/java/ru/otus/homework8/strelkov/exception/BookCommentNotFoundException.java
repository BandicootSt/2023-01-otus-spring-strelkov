package ru.otus.homework8.strelkov.exception;

public class BookCommentNotFoundException extends RuntimeException {

    public BookCommentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookCommentNotFoundException(String message) {
        super(message);
    }
}
