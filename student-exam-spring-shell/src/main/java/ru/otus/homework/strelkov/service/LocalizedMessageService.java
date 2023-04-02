package ru.otus.homework.strelkov.service;

public interface LocalizedMessageService {

    String getLocalizedMessage(String msgPropertyCode, Object[] args);

    String getLocalizedMessage(String msgPropertyCode);
}
