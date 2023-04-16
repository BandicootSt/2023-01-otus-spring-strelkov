package ru.otus.homework.strelkov.service;

import ru.otus.homework.strelkov.domain.Question;

public interface QuestionConverter {

    String convertQuestion(Question question);
}