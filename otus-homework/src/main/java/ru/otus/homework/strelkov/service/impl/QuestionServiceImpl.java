package ru.otus.homework.strelkov.service.impl;

import ru.otus.homework.strelkov.dao.QuestionDao;
import ru.otus.homework.strelkov.service.IOService;
import ru.otus.homework.strelkov.service.QuestionConverter;
import ru.otus.homework.strelkov.service.QuestionService;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;
    private final IOService ioService;
    private final QuestionConverter questionConverter;

    public QuestionServiceImpl(
        QuestionDao questionDao,
        IOService ioService,
        QuestionConverter questionConverter
    ) {
        this.questionDao = questionDao;
        this.ioService = ioService;
        this.questionConverter = questionConverter;
    }

    @Override
    public void printQuestions() {
        questionDao.getQuestions().forEach(q -> ioService.outputString(questionConverter.convertQuestion(q)));
    }
}
