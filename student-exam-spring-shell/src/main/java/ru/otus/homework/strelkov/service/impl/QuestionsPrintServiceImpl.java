package ru.otus.homework.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.service.IOService;
import ru.otus.homework.strelkov.service.QuestionConverter;
import ru.otus.homework.strelkov.service.QuestionsPrintService;

@Service
@RequiredArgsConstructor
public class QuestionsPrintServiceImpl implements QuestionsPrintService {

    private final IOService ioService;
    private final QuestionConverter questionConverter;

    @Override
    public void printQuestion(Question question) {
        ioService.outputString(questionConverter.convertQuestion(question));
    }
}
