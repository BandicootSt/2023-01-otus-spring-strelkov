package ru.otus.homework.strelkov.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.service.QuestionConverter;

import java.util.stream.Collectors;

@Service
public class QuestionConverterImpl implements QuestionConverter {

    @Override
    public String convertQuestion(Question question) {
        return question.getQuestion() +
            "\n\t" +
            question.getAnswerOptionByAnswerNum().entrySet()
                .stream()
                .map(answer -> answer.getKey() + ") " + answer.getValue().getOption())
                .collect(Collectors.joining("\n\t"));
    }
}