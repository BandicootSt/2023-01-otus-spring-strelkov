package ru.otus.homework.strelkov.dao.impl;

import lombok.NonNull;
import ru.otus.homework.strelkov.dao.QuestionDao;
import ru.otus.homework.strelkov.domain.AnswerOption;
import ru.otus.homework.strelkov.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionDaoImpl implements QuestionDao {

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
                throw new RuntimeException("File not found, file path: " + questionsFilePath);
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
        for (int i = 1; i < questionRecord.length; i++) {
            answerOptions.add(new AnswerOption(i, questionRecord[i]));
        }
        return new Question(questionRecord[0], answerOptions);
    }


}
