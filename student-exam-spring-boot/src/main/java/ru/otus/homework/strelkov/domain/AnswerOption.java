package ru.otus.homework.strelkov.domain;

import lombok.NonNull;
import lombok.Value;

@Value
public class AnswerOption {

    int optionNum;

    @NonNull
    String option;

    boolean correct;
}
