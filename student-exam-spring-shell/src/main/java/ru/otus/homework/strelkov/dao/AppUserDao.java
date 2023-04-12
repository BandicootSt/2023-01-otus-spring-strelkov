package ru.otus.homework.strelkov.dao;

import ru.otus.homework.strelkov.domain.AppUser;

import java.util.Optional;

public interface AppUserDao {

    void saveUser(AppUser user);

    Optional<AppUser> getUser(String login);
}
