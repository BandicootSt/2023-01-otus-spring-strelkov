package ru.otus.homework.strelkov.service.impl;

import ru.otus.homework.strelkov.dao.QuestionDao;
import ru.otus.homework.strelkov.service.QuestionService;

import java.util.stream.Collectors;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public void printQuestions() {
        questionDao.getQuestions()
            .forEach(q ->
                System.out.println(
                    q.getQuestion() +
                        "\n\t" +
                        q.getAnswerOptions()
                            .stream()
                            .map(answer -> answer.getOptionNumber() + ") " + answer.getOption())
                            .collect(Collectors.joining("\n\t"))
                )
            );
    }
}
