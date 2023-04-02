package ru.otus.homework.strelkov.domain;

import lombok.NonNull;
import lombok.Value;

@Value
public class AppUser {

    @NonNull
    String login;

    @NonNull
    String password;

}
