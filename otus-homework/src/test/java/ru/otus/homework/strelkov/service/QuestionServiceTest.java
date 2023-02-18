package ru.otus.homework.strelkov.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.homework.strelkov.dao.QuestionDao;
import ru.otus.homework.strelkov.domain.AnswerOption;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.service.impl.QuestionServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class QuestionServiceTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private QuestionService questionService;

    @Mock
    private QuestionDao questionDao;


    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outputStreamCaptor));
        questionService = new QuestionServiceImpl(questionDao);
    }

    @Test
    void testPrintQuestions() {
        when(questionDao.getQuestions())
            .thenReturn(
                asList(
                    new Question(
                        "How many squares are on the chessboard?",
                        asList(
                            new AnswerOption(1, "64"),
                            new AnswerOption(2, "56"),
                            new AnswerOption(1, "72")
                        )
                    ),
                    new Question(
                        "How many squares are on the chessboard?",
                        asList(
                            new AnswerOption(1, "64"),
                            new AnswerOption(2, "56"),
                            new AnswerOption(1, "72")
                        )
                    )
                )
            );

        questionService.printQuestions();
        assertEquals(
            """
                How many squares are on the chessboard?
                \t1) 64
                \t2) 56
                \t1) 72
                How many squares are on the chessboard?
                \t1) 64
                \t2) 56
                \t1) 72
                """,
            outputStreamCaptor.toString()
        );
    }
}