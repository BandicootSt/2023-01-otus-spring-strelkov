package ru.otus.homework.strelkov.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.homework.strelkov.dao.QuestionDao;
import ru.otus.homework.strelkov.domain.AnswerOption;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.service.impl.IOServiceStreams;
import ru.otus.homework.strelkov.service.impl.QuestionConverterImpl;
import ru.otus.homework.strelkov.service.impl.QuestionServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class QuestionServiceTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    private QuestionDao questionDao;

    private QuestionService questionService;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        questionService = new QuestionServiceImpl(
            questionDao,
            new IOServiceStreams(new PrintStream(outputStreamCaptor), System.in),
            new QuestionConverterImpl()
        );
    }

    @Test
    void testPrintQuestions() {
        when(questionDao.getQuestions())
            .thenReturn(
                asList(
                    new Question(
                        "How many squares are on the chessboard?",
                        asList(
                            new AnswerOption(1, "64", true),
                            new AnswerOption(2, "56", false),
                            new AnswerOption(3, "72", false)
                        )
                    ),
                    new Question(
                        "How many musicians are in the quintet?",
                        asList(
                            new AnswerOption(1, "7", false),
                            new AnswerOption(2, "4", false),
                            new AnswerOption(3, "5", true)
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
                \t3) 72
                How many musicians are in the quintet?
                \t1) 7
                \t2) 4
                \t3) 5
                """,
            outputStreamCaptor.toString()
        );
    }
}