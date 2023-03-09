package ru.otus.homework.strelkov.service.exception;

public class NoAnswerWithSuchNumException extends RuntimeException {

    public NoAnswerWithSuchNumException(String msg) {
        super(msg);
    }
}
