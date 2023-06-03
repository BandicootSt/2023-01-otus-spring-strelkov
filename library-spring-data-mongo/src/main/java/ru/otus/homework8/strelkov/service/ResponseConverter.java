package ru.otus.homework8.strelkov.service;

public interface ResponseConverter {

    String fromSuccess();
    <T> String fromSuccess(T result);

    String fromError(String errMsg);
}
