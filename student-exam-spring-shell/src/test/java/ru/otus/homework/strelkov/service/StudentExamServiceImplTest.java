package ru.otus.homework.strelkov.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework.strelkov.domain.AnswerOption;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.domain.Student;
import ru.otus.homework.strelkov.service.impl.StudentExamServiceImpl;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentExamServiceImplTest {

    private static final int EXAM_PASSING_SCORE = 3;

    private static final Student TEST_STUDENT = new Student("Ivan", "Ivanov");
    private static final String TEST_STUDENT_NAME = "Ivanov Ivan";
    private static final String EXAM_START_MESSAGE = "Exam start! Choose one right answer for every question";

    private static final Question FIRST_QUESTION = new Question(
        "Which US president wrote his own Sherlock Holmes story?",
        asList(
            new AnswerOption(1, "John F. Kennedy", false),
            new AnswerOption(2, "Franklin Roosevelt", true),
            new AnswerOption(3, "Ronald Reagan", false)
        )
    );

    private static final Question SECOND_QUESTION = new Question(
        "What duty was introduced in the 12th century in England in order to force men to go to war?",
        asList(
            new AnswerOption(1, "Parasitism tax", false),
            new AnswerOption(2, "Cowardice tax", true),
            new AnswerOption(3, "No boots tax", false)
        )
    );

    private static final Question THIRD_QUESTION = new Question(
        "Where did the phrase “money smells like?” come from?",
        asList(
            new AnswerOption(1, "Perfume bearer", false),
            new AnswerOption(2, "Unwashed socks charge", false),
            new AnswerOption(3, "Toilet tax", true)
        )
    );

    private static final Question FORTH_QUESTION = new Question(
        "The Oscar-winning Russian cartoon is…",
        asList(
            new AnswerOption(1, "The Old Man and the Sea", true),
            new AnswerOption(2, "Prostokvashino", false),
            new AnswerOption(3, "Winnie the Pooh", false)
        )
    );

    private static final Question FIFTH_QUESTION = new Question(
        "What was the clothing equivalent of money in the Russian Empire?",
        asList(
            new AnswerOption(1, "Fur skins", true),
            new AnswerOption(2, "Cattle", false),
            new AnswerOption(3, "Tobacco", false)
        )
    );

    private static final List<Question> TEST_QUESTIONS = asList(
        FIRST_QUESTION,
        SECOND_QUESTION,
        THIRD_QUESTION,
        FORTH_QUESTION,
        FIFTH_QUESTION
    );

    @MockBean
    private IOService ioService;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private QuestionsPrintService questionsPrintService;

    @MockBean
    private QuestionsSupportService questionsSupportService;

    @MockBean
    private StudentService studentService;

    @MockBean
    private LocalizedMessageService messageService;


    private StudentExamService studentExamService;

    @BeforeEach
    public void beforeEach() {

        studentExamService = new StudentExamServiceImpl(
            ioService,
            questionService,
            questionsPrintService,
            questionsSupportService,
            studentService,
            messageService,
            EXAM_PASSING_SCORE
        );

        when(studentService.requestStudentName()).thenReturn(TEST_STUDENT);
        when(studentService.getStudentNameAsString(eq(TEST_STUDENT))).thenReturn(TEST_STUDENT_NAME);
        when(messageService.getLocalizedMessage(eq("exam.start"))).thenReturn(EXAM_START_MESSAGE);
    }

    @Test
    public void testExamineStudentSuccess() {

        when(questionService.getQuestions()).thenReturn(TEST_QUESTIONS);

        when(ioService.inputInt()).thenReturn(2, 2, 1, 1, 1);

        when(questionsSupportService.getAnswerOption(eq(FIRST_QUESTION), eq(2)))
            .thenReturn(FIRST_QUESTION.getAnswerOptions().get(1));
        when(questionsSupportService.getAnswerOption(eq(SECOND_QUESTION), eq(2)))
            .thenReturn(SECOND_QUESTION.getAnswerOptions().get(1));
        when(questionsSupportService.getAnswerOption(eq(THIRD_QUESTION), eq(1)))
            .thenReturn(THIRD_QUESTION.getAnswerOptions().get(0));
        when(questionsSupportService.getAnswerOption(eq(FORTH_QUESTION), eq(1)))
            .thenReturn(FORTH_QUESTION.getAnswerOptions().get(0));
        when(questionsSupportService.getAnswerOption(eq(FIFTH_QUESTION), eq(1)))
            .thenReturn(FIFTH_QUESTION.getAnswerOptions().get(0));

        studentExamService.examineStudent();

        verify(ioService, times(1)).outputString(eq(EXAM_START_MESSAGE));
        verify(messageService, times(1))
            .getLocalizedMessage(eq("exam.result.success"), eq(new Object[]{TEST_STUDENT_NAME, 4}));
    }

    @Test
    public void testExamineStudentFailed() {

        when(questionService.getQuestions()).thenReturn(TEST_QUESTIONS);

        when(ioService.inputInt()).thenReturn(3, 3, 2, 1, 1);

        when(questionsSupportService.getAnswerOption(eq(FIRST_QUESTION), eq(3)))
            .thenReturn(FIRST_QUESTION.getAnswerOptions().get(2));
        when(questionsSupportService.getAnswerOption(eq(SECOND_QUESTION), eq(3)))
            .thenReturn(SECOND_QUESTION.getAnswerOptions().get(2));
        when(questionsSupportService.getAnswerOption(eq(THIRD_QUESTION), eq(2)))
            .thenReturn(THIRD_QUESTION.getAnswerOptions().get(1));
        when(questionsSupportService.getAnswerOption(eq(FORTH_QUESTION), eq(1)))
            .thenReturn(FORTH_QUESTION.getAnswerOptions().get(0));
        when(questionsSupportService.getAnswerOption(eq(FIFTH_QUESTION), eq(1)))
            .thenReturn(FIFTH_QUESTION.getAnswerOptions().get(0));

        studentExamService.examineStudent();

        verify(ioService, times(1)).outputString(eq(EXAM_START_MESSAGE));
        verify(messageService, times(1))
            .getLocalizedMessage(eq("exam.result.failed"), eq(new Object[]{TEST_STUDENT_NAME, 2}));
    }
}