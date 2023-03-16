package ru.otus.homework.strelkov.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.domain.Student;
import ru.otus.homework.strelkov.service.IOService;
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
    private final int passingScore;

    public StudentExamServiceImpl(
        IOService ioService,
        QuestionService questionService,
        QuestionsPrintService questionsPrintService,
        QuestionsSupportService questionsSupportService,
        StudentService studentService,
        @Value("${exam.passing.score}") int passingScore
    ) {
        this.ioService = ioService;
        this.questionService = questionService;
        this.questionsPrintService = questionsPrintService;
        this.questionsSupportService = questionsSupportService;
        this.studentService = studentService;
        this.passingScore = passingScore;
    }

    @Override
    public void examineStudent() {
        final Student student = studentService.requestStudentName();
        ioService.outputString("Exam start! Choose one right answer for every question.");

        final List<Question> questions = questionService.getQuestions();
        int resultScore = 0;

        for (Question question : questions) {
            questionsPrintService.printQuestion(question);

            if (questionsSupportService.getAnswerOption(question, ioService.inputInt()).isCorrect()) {
                resultScore ++;
            }
        }

        final String studentName = studentService.getStudentNameAsString(student);

        if (resultScore >= passingScore) {
            ioService.outputString("Student: " + studentName + ", Score: " + resultScore + ", Result: Success");
        } else {
            ioService.outputString("Student: " + studentName + ", Score: " + resultScore + ", Result: Failed");
        }
    }
}
