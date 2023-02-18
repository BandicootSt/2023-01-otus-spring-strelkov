package ru.otus.homework.strelkov;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.strelkov.service.QuestionService;

public class ApplicationMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService questionService = context.getBean(QuestionService.class);
        questionService.printQuestions();
    }
}
