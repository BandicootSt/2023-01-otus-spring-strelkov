package ru.otus.homework.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.strelkov.dao.AppUserDao;
import ru.otus.homework.strelkov.domain.AppUser;
import ru.otus.homework.strelkov.service.AppUserRegistrationService;

@Service
@RequiredArgsConstructor
public class AppUserRegistrationServiceImpl implements AppUserRegistrationService {

    private final AppUserDao userDao;

    @Override
    public void registerUser(String login, String password) {
        userDao.saveUser(new AppUser(login, password));
    }
}
