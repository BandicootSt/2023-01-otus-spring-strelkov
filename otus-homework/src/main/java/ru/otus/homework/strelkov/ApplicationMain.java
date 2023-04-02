package ru.otus.homework.strelkov;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.homework.strelkov.service.StudentExamService;

@ComponentScan
public class ApplicationMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationMain.class);
        StudentExamService studentExamService = context.getBean(StudentExamService.class);
        studentExamService.examineStudent();
    }
}
