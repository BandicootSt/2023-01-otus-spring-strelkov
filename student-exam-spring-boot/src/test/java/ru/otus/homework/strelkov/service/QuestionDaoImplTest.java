package ru.otus.homework.strelkov.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.homework.strelkov.config.AppProps;
import ru.otus.homework.strelkov.dao.QuestionDao;
import ru.otus.homework.strelkov.dao.impl.QuestionDaoImpl;
import ru.otus.homework.strelkov.domain.AnswerOption;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.service.exception.QuestionsFileNotFoundException;

import java.util.Locale;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class QuestionDaoImplTest {

    private static final String TEST_QUESTIONS_FILE_PATH = "test_questions.csv";
    private static final String NOT_EXISTED_QUESTION_FILE_PATH = "not_existed_questions_file.csv";

    @Mock
    private AppProps appProps;

    private QuestionDao questionDao;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        questionDao = new QuestionDaoImpl(appProps);
        when(appProps.getLocale()).thenReturn(Locale.ENGLISH);
    }

    @Test
    public void testGetQuestions() {
        when(appProps.getQuestionsFilePathEn()).thenReturn(TEST_QUESTIONS_FILE_PATH);
        assertEquals(
            asList(
                new Question(
                    "Which US president wrote his own Sherlock Holmes story?",
                    asList(
                        new AnswerOption(1,"John F. Kennedy", false),
                        new AnswerOption(2, "Franklin Roosevelt", true),
                        new AnswerOption(3, "Ronald Reagan", false)
                    )
                ),
                new Question(
                    "What duty was introduced in the 12th century in England in order to force men to go to war?",
                    asList(
                        new AnswerOption(1,"Parasitism tax", false),
                        new AnswerOption(2,"Cowardice tax", true),
                        new AnswerOption(3,"No boots tax", false)
                    )
                ),
                new Question(
                    "Where did the phrase “money smells like?” come from?",
                    asList(
                        new AnswerOption(1,"Perfume bearer", false),
                        new AnswerOption(2,"Unwashed socks charge", false),
                        new AnswerOption(3,"Toilet tax", true)
                    )
                ),
                new Question(
                    "The Oscar-winning Russian cartoon is…",
                    asList(
                        new AnswerOption(1,"The Old Man and the Sea", true),
                        new AnswerOption(2,"Prostokvashino", false),
                        new AnswerOption(3,"Winnie the Pooh", false)
                    )
                ),
                new Question(
                    "What was the clothing equivalent of money in the Russian Empire?",
                    asList(
                        new AnswerOption(1,"Fur skins", true),
                        new AnswerOption(2,"Cattle", false),
                        new AnswerOption(3,"Tobacco", false)
                    )
                )
            ),
            questionDao.getQuestions()
        );
    }

    @Test
    public void testGetQuestionsFileNotExist() {
        when(appProps.getQuestionsFilePathEn()).thenReturn(NOT_EXISTED_QUESTION_FILE_PATH);

        QuestionsFileNotFoundException ex = assertThrows(
            QuestionsFileNotFoundException.class,
            () -> questionDao.getQuestions()
        );

        assertEquals("Questions file not found, file path: " + NOT_EXISTED_QUESTION_FILE_PATH, ex.getMessage());
    }
}
