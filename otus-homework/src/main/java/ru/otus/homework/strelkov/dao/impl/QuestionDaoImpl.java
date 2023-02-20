package ru.otus.homework.strelkov.dao.impl;

import lombok.NonNull;
import ru.otus.homework.strelkov.dao.QuestionDao;
import ru.otus.homework.strelkov.domain.AnswerOption;
import ru.otus.homework.strelkov.domain.Question;
import ru.otus.homework.strelkov.service.exception.QuestionsFileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionDaoImpl implements QuestionDao {

    private static final int QUESTION_CSV_COL_INDEX = 0;
    private static final int CORRECT_ANSWER_CSV_COL_INDEX = 4;
    private static final String LINE_BREAK = "\n";
    private static final String SEMICOLON_SEPARATOR = ";";
    private final String questionsFilePath;

    public QuestionDaoImpl(String questionsFilePath) {
        this.questionsFilePath = questionsFilePath;
    }

    @Override
    @NonNull
    public List<Question> getQuestions() {

        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(questionsFilePath)) {

            if (is == null) {
                throw new QuestionsFileNotFoundException("Questions file not found, file path: " + questionsFilePath);
            }

            final String questionsFileEntry = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            return Arrays.stream(questionsFileEntry.split(LINE_BREAK))
                .map(q -> generateQuestionWithAnswerOptions(q.split(SEMICOLON_SEPARATOR)))
                .collect(Collectors.toList());
        } catch (IOException ex) {
            throw new RuntimeException("Error while trying to read questions file from resources, errMsg: " + ex.getMessage(), ex);
        }
    }

    @NonNull
    private Question generateQuestionWithAnswerOptions(String[] questionRecord) {
        List<AnswerOption> answerOptions = new ArrayList<>();
        int correctAnswerOptionNum = Integer.parseInt(questionRecord[CORRECT_ANSWER_CSV_COL_INDEX]);
        for (int i = 1; i < questionRecord.length - 1; i++) {
            answerOptions.add(new AnswerOption(i, questionRecord[i], i == correctAnswerOptionNum));
        }
        return new Question(questionRecord[QUESTION_CSV_COL_INDEX], answerOptions);
    }


}
