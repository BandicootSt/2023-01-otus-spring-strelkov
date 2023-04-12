package ru.otus.homework.strelkov.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.strelkov.dao.AppUserDao;
import ru.otus.homework.strelkov.domain.AppUser;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AppUserDaoImpl implements AppUserDao {

    private final Map<String, AppUser> userByLogin;

    @Override
    public void saveUser(AppUser user) {
        userByLogin.put(user.getLogin(), user);
    }

    @Override
    public Optional<AppUser> getUser(String login) {
        return Optional.ofNullable(userByLogin.get(login));
    }
}
