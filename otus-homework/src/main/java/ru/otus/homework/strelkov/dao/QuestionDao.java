package ru.otus.homework.strelkov.dao;

import lombok.NonNull;
import ru.otus.homework.strelkov.domain.Question;

import java.util.List;

public interface QuestionDao {

    @NonNull
    List<Question> getQuestions();
}
