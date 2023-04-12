package ru.otus.homework.strelkov.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.strelkov.domain.AnswerOption;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.service.QuestionsSupportService;
import ru.otus.homework.strelkov.service.exception.NoAnswerWithSuchNumException;

@Service
public class QuestionsSupportServiceImpl implements QuestionsSupportService {
    @Override
    public AnswerOption getAnswerOption(Question question, int optionNum) {
        return question.getAnswerOptions()
            .stream()
            .filter(ao -> ao.getOptionNum() == optionNum)
            .findFirst()
            .orElseThrow(() -> new NoAnswerWithSuchNumException("Answer with num " + optionNum + " doesn't exist"));
    }
}
