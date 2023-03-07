package ru.otus.homework.strelkov.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.strelkov.config.IOServiceConfig;
import ru.otus.homework.strelkov.config.QuestionDaoConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {IOServiceConfig.class, QuestionDaoConfig.class})
@ComponentScan(basePackages = "ru.otus.homework.strelkov")
@TestPropertySource({
    "classpath:application-test.properties",
})
abstract public class TestConfig {
}
