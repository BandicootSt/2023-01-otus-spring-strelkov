package ru.otus.homework.strelkov.service;

import ru.otus.homework.strelkov.domain.AppUserLoginResult;

public interface AppUserLoginService {

    AppUserLoginResult login(String login, String password);
}
