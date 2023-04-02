package ru.otus.homework.strelkov.service;

import ru.otus.homework.strelkov.domain.AnswerOption;
import ru.otus.homework.strelkov.domain.Question;

public interface QuestionsSupportService {

    AnswerOption getAnswerOption(Question question, int optionNum);
}
