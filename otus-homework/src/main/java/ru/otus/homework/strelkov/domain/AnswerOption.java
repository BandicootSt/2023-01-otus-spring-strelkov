package ru.otus.homework.strelkov.domain;

import lombok.NonNull;
import lombok.Value;

@Value
public class AnswerOption {

    @NonNull
    String option;

    boolean correct;
}
