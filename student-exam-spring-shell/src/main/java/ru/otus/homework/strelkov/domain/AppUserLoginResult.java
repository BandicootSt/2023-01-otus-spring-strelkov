package ru.otus.homework.strelkov.domain;

import lombok.Value;

@Value
public class AppUserLoginResult {

    AppUser user;

    String logResultMessage;
}
