package ru.otus.homework.strelkov.domain;

import lombok.NonNull;
import lombok.Value;

import java.util.Map;

@Value
public class Question {

    @NonNull
    String question;

    @NonNull
    Map<Integer, AnswerOption> answerOptionByAnswerNum;
}
