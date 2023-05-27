package ru.otus.homework7.strelkov.exception;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }
}
