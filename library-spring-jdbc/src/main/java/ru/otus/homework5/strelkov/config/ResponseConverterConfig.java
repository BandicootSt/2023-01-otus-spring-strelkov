package ru.otus.homework5.strelkov.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework5.strelkov.service.ResponseConverter;
import ru.otus.homework5.strelkov.service.impl.ResponseConverterImpl;

@Configuration
public class ResponseConverterConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    @Bean
    public ResponseConverter responseConverter() {
        return new ResponseConverterImpl(objectMapper(), gson());
    }
}
