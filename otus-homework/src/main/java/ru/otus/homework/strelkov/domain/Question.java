package ru.otus.homework.strelkov.domain;

import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
public class Question {

    @NonNull
    String question;

    @NonNull
    List<AnswerOption> answerOptions;
}
