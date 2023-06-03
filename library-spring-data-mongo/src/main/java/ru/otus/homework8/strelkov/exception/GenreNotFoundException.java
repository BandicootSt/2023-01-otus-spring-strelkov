package ru.otus.homework8.strelkov.exception;

public class GenreNotFoundException extends RuntimeException {

    public GenreNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenreNotFoundException(String message) {
        super(message);
    }
}
