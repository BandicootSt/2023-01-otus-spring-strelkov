package ru.otus.homework.strelkov.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import ru.otus.homework.strelkov.domain.AnswerOption;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.service.impl.StudentExamServiceImpl;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StudentExamServiceImplTest extends TestConfig {

    private static final Question FIRST_QUESTION = new Question(
        "Which US president wrote his own Sherlock Holmes story?",
        Map.of(
            1, new AnswerOption("John F. Kennedy", false),
            2, new AnswerOption("Franklin Roosevelt", true),
            3, new AnswerOption("Ronald Reagan", false)
        )
    );

    private static final Question SECOND_QUESTION = new Question(
        "What duty was introduced in the 12th century in England in order to force men to go to war?",
        Map.of(
            1, new AnswerOption("Parasitism tax", false),
            2, new AnswerOption("Cowardice tax", true),
            3, new AnswerOption("No boots tax", false)
        )
    );

    private static final Question THIRD_QUESTION = new Question(
        "Where did the phrase “money smells like?” come from?",
        Map.of(
            1, new AnswerOption("Perfume bearer", false),
            2, new AnswerOption("Unwashed socks charge", false),
            3, new AnswerOption("Toilet tax", true)
        )
    );

    private static final Question FORTH_QUESTION = new Question(
        "The Oscar-winning Russian cartoon is…",
        Map.of(
            1, new AnswerOption("The Old Man and the Sea", true),
            2, new AnswerOption("Prostokvashino", false),
            3, new AnswerOption("Winnie the Pooh", false)
        )
    );

    private static final Question FIFTH_QUESTION = new Question(
        "What was the clothing equivalent of money in the Russian Empire?",
        Map.of(
            1, new AnswerOption("Fur skins", true),
            2, new AnswerOption("Cattle", false),
            3, new AnswerOption("Tobacco", false)
        )
    );

    private static final List<Question> TEST_QUESTIONS = asList(
        FIRST_QUESTION,
        SECOND_QUESTION,
        THIRD_QUESTION,
        FORTH_QUESTION,
        FIFTH_QUESTION
    );

    @Mock
    private IOService ioService;

    @Mock
    private QuestionService questionService;

    @Value("${exam.passing.score}")
    private int passingScore;

    @Captor
    private ArgumentCaptor<String> stringCaptor;


    private StudentExamService studentExamService;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        studentExamService = new StudentExamServiceImpl(
            ioService,
            questionService,
            passingScore
        );
    }

    @Test
    public void testExamineStudentSuccess() {

        when(ioService.inputString()).thenReturn("Ivanov", "Ivan");
        when(questionService.printAndReturnQuestions()).thenReturn(TEST_QUESTIONS);

        when(ioService.inputInt()).thenReturn(2, 2, 1, 1, 1);

        studentExamService.examineStudent();

        verify(ioService, times(4)).outputString(stringCaptor.capture());
        assertEquals("Student: Ivan Ivanov, Score: 4, Result: Success", stringCaptor.getValue());
    }

    @Test
    public void testExamineStudentFailed() {

        when(ioService.inputString()).thenReturn("Ivanov", "Ivan");
        when(questionService.printAndReturnQuestions()).thenReturn(TEST_QUESTIONS);

        when(ioService.inputInt()).thenReturn(3, 3, 2, 1, 1);

        studentExamService.examineStudent();

        verify(ioService, times(4)).outputString(stringCaptor.capture());
        assertEquals("Student: Ivan Ivanov, Score: 2, Result: Failed", stringCaptor.getValue());
    }

}