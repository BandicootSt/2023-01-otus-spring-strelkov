package ru.otus.homework.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.strelkov.dao.QuestionDao;
import ru.otus.homework.strelkov.domain.AnswerOption;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.service.IOService;
import ru.otus.homework.strelkov.service.QuestionConverter;
import ru.otus.homework.strelkov.service.QuestionService;
import ru.otus.homework.strelkov.service.exception.NoAnswerWithSuchNumException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;
    private final IOService ioService;
    private final QuestionConverter questionConverter;

    @Override
    public List<Question> getQuestions() {
        return questionDao.getQuestions();
    }

    @Override
    public void printQuestion(Question question) {
        ioService.outputString(questionConverter.convertQuestion(question));
    }

    @Override
    public AnswerOption getAnswerOption(Question question, int optionNum) {
        return question.getAnswerOptions()
            .stream()
            .filter(ao -> ao.getOptionNum() == optionNum)
            .findFirst()
            .orElseThrow(() -> new NoAnswerWithSuchNumException("Answer with num " + optionNum + " doesn't exist"));
    }
}
