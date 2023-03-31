package ru.otus.homework.strelkov.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.domain.Student;
import ru.otus.homework.strelkov.service.IOService;
import ru.otus.homework.strelkov.service.LocalizedMessageService;
import ru.otus.homework.strelkov.service.QuestionService;
import ru.otus.homework.strelkov.service.QuestionsPrintService;
import ru.otus.homework.strelkov.service.QuestionsSupportService;
import ru.otus.homework.strelkov.service.StudentExamService;
import ru.otus.homework.strelkov.service.StudentService;

import java.util.List;

@Service
public class StudentExamServiceImpl implements StudentExamService {

    private final IOService ioService;
    private final QuestionService questionService;
    private final QuestionsPrintService questionsPrintService;
    private final QuestionsSupportService questionsSupportService;
    private final StudentService studentService;
    private final LocalizedMessageService messageService;
    private final Integer examPassingScore;

    public StudentExamServiceImpl(
        IOService ioService,
        QuestionService questionService,
        QuestionsPrintService questionsPrintService,
        QuestionsSupportService questionsSupportService,
        StudentService studentService,
        LocalizedMessageService messageService,
        @Value("${exam.passing.score}") Integer examPassingScore
    ) {
        this.ioService = ioService;
        this.questionService = questionService;
        this.questionsPrintService = questionsPrintService;
        this.questionsSupportService = questionsSupportService;
        this.studentService = studentService;
        this.messageService = messageService;
        this.examPassingScore = examPassingScore;
    }

    @Override
    public void examineStudent() {
        final Student student = studentService.requestStudentName();

        ioService.outputString(messageService.getLocalizedMessage("exam.start"));

        final List<Question> questions = questionService.getQuestions();
        int resultScore = 0;

        for (Question question : questions) {
            questionsPrintService.printQuestion(question);

            if (questionsSupportService.getAnswerOption(question, ioService.inputInt()).isCorrect()) {
                resultScore ++;
            }
        }

        final String studentName = studentService.getStudentNameAsString(student);

        if (resultScore >= examPassingScore) {
            ioService.outputString(
                messageService.getLocalizedMessage(
                    "exam.result.success",
                    new Object[]{studentName, resultScore}
                )
            );
        } else {
            ioService.outputString(
                messageService.getLocalizedMessage(
                    "exam.result.failed",
                    new Object[]{studentName, resultScore}
                )
            );
        }
    }
}
