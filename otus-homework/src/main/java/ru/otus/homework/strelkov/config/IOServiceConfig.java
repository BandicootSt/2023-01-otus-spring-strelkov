package ru.otus.homework.strelkov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.strelkov.service.IOService;
import ru.otus.homework.strelkov.service.impl.IOServiceStreams;

@Configuration
public class IOServiceConfig {

    @Bean
    public IOService ioService() {
        return new IOServiceStreams(System.out, System.in);
    }
}
