package ru.otus.homework.strelkov.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
@Data
public class AppProps {

    private Locale locale;
    private String questionsFilePathEn;
    private String questionsFilePathRu;
    private int examPassingScore;
}
