package ru.otus.homework.strelkov.service.impl;

import org.springframework.stereotype.Component;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.service.QuestionConverter;

import java.util.stream.Collectors;

@Component
public class QuestionConverterImpl implements QuestionConverter {

    @Override
    public String convertQuestion(Question question) {
        return question.getQuestion() +
            "\n\t" +
            question.getAnswerOptions()
                .stream()
                .map(answer -> answer.getOptionNum() + ") " + answer.getOption())
                .collect(Collectors.joining("\n\t"));
    }
}