package ru.otus.homework.strelkov.domain;

import lombok.NonNull;
import lombok.Value;

@Value
public class AnswerOption {

    @NonNull
    Integer optionNumber;

    @NonNull
    String option;
}
