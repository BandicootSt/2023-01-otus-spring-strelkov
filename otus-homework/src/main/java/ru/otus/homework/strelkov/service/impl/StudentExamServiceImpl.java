package ru.otus.homework.strelkov.service.impl;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.domain.Student;
import ru.otus.homework.strelkov.service.IOService;
import ru.otus.homework.strelkov.service.QuestionService;
import ru.otus.homework.strelkov.service.StudentExamService;

import java.util.List;

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
        final Student student = requestStudentName();
        ioService.outputString("Exam start! Choose one right answer for every question.");

        final List<Question> questions = questionService.getQuestions();
        int resultScore = 0;

        for (Question question : questions) {
            questionService.printQuestion(question);

            if (questionService.getAnswerOption(question, ioService.inputInt()).isCorrect()) {
                resultScore ++;
            }
        }

        if (resultScore >= passingScore) {
            ioService.outputString("Student: " + student + ", Score: " + resultScore + ", Result: Success");
        } else {
            ioService.outputString("Student: " + student + ", Score: " + resultScore + ", Result: Failed");
        }
    }

    @NonNull
    private Student requestStudentName() {
        ioService.outputString("Enter your first name");
        String firstName = ioService.inputString();
        ioService.outputString("Enter your last name");
        return new Student(firstName, ioService.inputString());
    }
}
