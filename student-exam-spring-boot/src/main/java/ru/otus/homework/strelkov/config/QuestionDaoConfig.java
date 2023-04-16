package ru.otus.homework.strelkov.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.strelkov.dao.QuestionDao;
import ru.otus.homework.strelkov.dao.impl.QuestionDaoImpl;
import ru.otus.homework.strelkov.service.LocaleHolder;

@Configuration
public class QuestionDaoConfig {

    @Autowired
    private LocaleHolder holder;

    @Autowired
    private AppProps props;

    @Bean
    public QuestionDao questionDao() {
        return new QuestionDaoImpl(props.getQuestionsFilePath().get(holder.getLocale().getLanguage()));
    }
}
