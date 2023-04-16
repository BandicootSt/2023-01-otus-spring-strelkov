package ru.otus.homework.strelkov.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.homework.strelkov.service.StudentExamService;

@Component
public class StudentExamRunner implements CommandLineRunner {

    private final StudentExamService studentExamService;

    public StudentExamRunner(StudentExamService studentExamService) {
        this.studentExamService = studentExamService;
    }

    @Override
    public void run(String... args) {
        studentExamService.examineStudent();
    }
}
