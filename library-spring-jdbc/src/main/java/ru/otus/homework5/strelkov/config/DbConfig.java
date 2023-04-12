package ru.otus.homework5.strelkov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource ds) {
        return new NamedParameterJdbcTemplate(ds);
    }
}
