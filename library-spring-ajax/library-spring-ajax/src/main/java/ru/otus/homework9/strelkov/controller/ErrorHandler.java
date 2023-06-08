package ru.otus.homework9.strelkov.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.otus.homework9.strelkov.dto.ErrorDto;
import ru.otus.homework9.strelkov.exception.BookNotFoundException;

import java.io.IOException;

@ControllerAdvice
@RequiredArgsConstructor
public class ErrorHandler {

    private final ObjectMapper mapper;

    @ExceptionHandler
    public void handleBookNotFoundException(
        HttpServletResponse response,
        BookNotFoundException ex
    ) throws IOException {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        mapper.writeValue(response.getOutputStream(), new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler
    public void handleException(
        HttpServletResponse response,
        Exception ex
    ) throws IOException {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        mapper.writeValue(response.getOutputStream(), new ErrorDto(ex.getMessage()));
    }
}
