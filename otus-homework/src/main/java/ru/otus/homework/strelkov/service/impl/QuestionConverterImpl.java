package ru.otus.homework.strelkov.service.impl;

import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.service.QuestionConverter;

import java.util.stream.Collectors;

public class QuestionConverterImpl implements QuestionConverter {

    @Override
    public String convertQuestion(Question question) {
        return question.getQuestion() +
            "\n\t" +
            question.getAnswerOptions()
                .stream()
                .map(answer -> answer.getOptionNumber() + ") " + answer.getOption())
                .collect(Collectors.joining("\n\t"));
    }
}