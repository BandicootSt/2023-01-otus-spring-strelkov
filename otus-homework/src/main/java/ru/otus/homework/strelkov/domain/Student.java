package ru.otus.homework.strelkov.domain;

import lombok.NonNull;
import lombok.Value;

@Value
public class Student {

    @NonNull
    String firstName;

    @NonNull
    String lastName;

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
}
