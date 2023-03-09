package ru.otus.homework.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework.strelkov.config.AppProps;
import ru.otus.homework.strelkov.service.MessageService;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageSource messageSource;
    private final AppProps props;

    @Override
    public String getStudentRequestFirstNameMessage() {
        return messageSource.getMessage("student.request.first.name", null, props.getLocale());
    }

    @Override
    public String getStudentRequestLastNameMessage() {
        return messageSource.getMessage("student.request.last.name", null, props.getLocale());
    }

    @Override
    public String getExamStartMessage() {
        return messageSource.getMessage("exam.start", null, props.getLocale());
    }

    @Override
    public String getExamResultSuccessMessage(String studentName, int score) {
        return messageSource.getMessage(
            "exam.result.success",
            new String[] {studentName, String.valueOf(score)},
            props.getLocale()
        );
    }

    @Override
    public String getExamResultFailedMessage(String studentName, int score) {
        return messageSource.getMessage(
            "exam.result.failed",
            new String[] {studentName, String.valueOf(score)},
            props.getLocale()
        );
    }

}
