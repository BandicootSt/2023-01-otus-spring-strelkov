package ru.otus.homework.strelkov.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.domain.StudentName;
import ru.otus.homework.strelkov.service.IOService;
import ru.otus.homework.strelkov.service.QuestionService;
import ru.otus.homework.strelkov.service.StudentExamService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentExamServiceImpl implements StudentExamService {

    private final IOService ioService;
    private final QuestionService questionService;
    private final int passingScore;

    public StudentExamServiceImpl(
        IOService ioService,
        QuestionService questionService,
        @Value("${exam.passing.score}") int passingScore
    ) {
        this.ioService = ioService;
        this.questionService = questionService;
        this.passingScore = passingScore;
    }

    @Override
    public void examineStudent() {
        final StudentName studentName = requestStudentName();
        ioService.outputString("Exam start! Choose one right answer for every question.");

        final List<Question> questions = questionService.printAndReturnQuestions();
        AtomicInteger score = new AtomicInteger();

        questions.forEach(q -> {
            if (q.getAnswerOptionByAnswerNum().get(ioService.inputInt()).isCorrect()) {
                score.getAndIncrement();
            }
        });

        if (score.get() >= passingScore) {
            ioService.outputString("Student: " + studentName + ", Score: " + score.get() + ", Result: Success");
        } else {
            ioService.outputString("Student: " + studentName + ", Score: " + score.get() + ", Result: Failed");
        }
    }

    @Override
    public StudentName requestStudentName() {
        ioService.outputString("Enter your first name");
        String firstName = ioService.inputString();
        ioService.outputString("Enter your last name");
        return new StudentName(firstName, ioService.inputString());
    }
}
