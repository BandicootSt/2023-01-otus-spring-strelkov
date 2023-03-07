package ru.otus.homework.strelkov.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import ru.otus.homework.strelkov.dao.QuestionDao;
import ru.otus.homework.strelkov.dao.impl.QuestionDaoImpl;
import ru.otus.homework.strelkov.domain.AnswerOption;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.service.exception.QuestionsFileNotFoundException;

import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuestionDaoImplTest extends TestConfig {

    private static final String NOT_EXISTED_QUESTION_FILE_PATH = "not_existed_questions_file.csv";

    @Value("${questions.file.path}")
    String questionsFilePath;

    private QuestionDao questionDao;

    @Test
    public void testGetQuestions() {
        questionDao = new QuestionDaoImpl(questionsFilePath);
        assertEquals(
            asList(
                new Question(
                    "Which US president wrote his own Sherlock Holmes story?",
                    Map.of(
                        1, new AnswerOption("John F. Kennedy", false),
                        2, new AnswerOption("Franklin Roosevelt", true),
                        3, new AnswerOption("Ronald Reagan", false)
                    )
                ),
                new Question(
                    "What duty was introduced in the 12th century in England in order to force men to go to war?",
                    Map.of(
                        1, new AnswerOption("Parasitism tax", false),
                        2, new AnswerOption("Cowardice tax", true),
                        3, new AnswerOption("No boots tax", false)
                    )
                ),
                new Question(
                    "Where did the phrase “money smells like?” come from?",
                    Map.of(
                        1, new AnswerOption("Perfume bearer", false),
                        2, new AnswerOption("Unwashed socks charge", false),
                        3, new AnswerOption("Toilet tax", true)
                    )
                ),
                new Question(
                    "The Oscar-winning Russian cartoon is…",
                    Map.of(
                        1, new AnswerOption("The Old Man and the Sea", true),
                        2, new AnswerOption("Prostokvashino", false),
                        3, new AnswerOption("Winnie the Pooh", false)
                    )
                ),
                new Question(
                    "What was the clothing equivalent of money in the Russian Empire?",
                    Map.of(
                        1, new AnswerOption("Fur skins", true),
                        2, new AnswerOption("Cattle", false),
                        3, new AnswerOption("Tobacco", false)
                    )
                )
            ),
            questionDao.getQuestions()
        );
    }

    @Test
    public void testGetQuestionsFileNotExist() {
        questionDao = new QuestionDaoImpl(NOT_EXISTED_QUESTION_FILE_PATH);

        QuestionsFileNotFoundException ex = assertThrows(
            QuestionsFileNotFoundException.class,
            () -> questionDao.getQuestions()
        );

        assertEquals("Questions file not found, file path: " + NOT_EXISTED_QUESTION_FILE_PATH, ex.getMessage());
    }
}
