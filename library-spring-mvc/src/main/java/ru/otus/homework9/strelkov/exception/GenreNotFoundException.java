package ru.otus.homework9.strelkov.exception;

public class GenreNotFoundException extends RuntimeException {

    public GenreNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenreNotFoundException(String message) {
        super(message);
    }
}
