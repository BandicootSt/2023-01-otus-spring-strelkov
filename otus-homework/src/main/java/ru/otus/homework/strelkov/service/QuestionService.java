package ru.otus.homework.strelkov.service;

import ru.otus.homework.strelkov.domain.AnswerOption;
import ru.otus.homework.strelkov.domain.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestions();

    void printQuestion(Question question);

    AnswerOption getAnswerOption(Question question, int optionNum);
}
