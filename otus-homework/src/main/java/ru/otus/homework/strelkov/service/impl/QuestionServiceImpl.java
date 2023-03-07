package ru.otus.homework.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.strelkov.dao.QuestionDao;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.service.IOService;
import ru.otus.homework.strelkov.service.QuestionConverter;
import ru.otus.homework.strelkov.service.QuestionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;
    private final IOService ioService;
    private final QuestionConverter questionConverter;

    @Override
    public List<Question> printAndReturnQuestions() {
        List<Question> questions = questionDao.getQuestions();
        questions.forEach(q -> ioService.outputString(questionConverter.convertQuestion(q)));
        return questions;
    }
}
