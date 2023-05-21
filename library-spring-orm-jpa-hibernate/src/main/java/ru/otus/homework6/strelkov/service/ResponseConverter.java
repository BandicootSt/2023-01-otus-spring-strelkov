package ru.otus.homework6.strelkov.service;

public interface ResponseConverter {

    String fromSuccess();
    <T> String fromSuccess(T result);

    String fromError(String errMsg);
}
