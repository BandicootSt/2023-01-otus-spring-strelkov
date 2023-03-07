package ru.otus.homework.strelkov.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.homework.strelkov.dao.QuestionDao;
import ru.otus.homework.strelkov.dao.impl.QuestionDaoImpl;

@PropertySource("classpath:application.properties")
@Configuration
public class QuestionDaoConfig {

    @Bean
    public QuestionDao questionDao(@Value("${questions.file.path}") String questionsFilePath) {
        return new QuestionDaoImpl(questionsFilePath);
    }
}
