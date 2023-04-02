package ru.otus.homework.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.strelkov.dao.AppUserDao;
import ru.otus.homework.strelkov.domain.AppUserLoginResult;
import ru.otus.homework.strelkov.service.AppUserLoginService;
import ru.otus.homework.strelkov.service.LocalizedMessageService;

@Service
@RequiredArgsConstructor
public class AppUserLoginServiceImpl implements AppUserLoginService {

    private final AppUserDao userDao;
    private final LocalizedMessageService messageService;

    @Override
    public AppUserLoginResult login(String login, String password) {
        return userDao.getUser(login)
            .map(user -> user.getPassword().equals(password)
                ? new AppUserLoginResult(user, messageService.getLocalizedMessage("user.login.success", new Object[]{login}))
                : new AppUserLoginResult(null, messageService.getLocalizedMessage("user.login.wrong.pass"))
            )
            .orElse(new AppUserLoginResult(null, messageService.getLocalizedMessage("user.login.wrong.log.pass")));
    }
}
