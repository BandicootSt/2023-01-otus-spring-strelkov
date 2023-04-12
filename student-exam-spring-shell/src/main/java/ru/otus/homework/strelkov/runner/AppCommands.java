package ru.otus.homework.strelkov.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.strelkov.domain.AppUser;
import ru.otus.homework.strelkov.domain.AppUserLoginResult;
import ru.otus.homework.strelkov.service.AppUserLoginService;
import ru.otus.homework.strelkov.service.AppUserRegistrationService;
import ru.otus.homework.strelkov.service.LocalizedMessageService;
import ru.otus.homework.strelkov.service.StudentExamService;

@ShellComponent
@RequiredArgsConstructor
public class AppCommands {

    private final AppUserRegistrationService userRegistrationService;
    private final AppUserLoginService userLoginService;
    private final StudentExamService studentExamService;
    private final LocalizedMessageService messageService;

    private AppUser user;

    @ShellMethod(value = "Registration command",  key = {"r", "reg"})
    public String registerUser(@ShellOption("l") String login, @ShellOption("p") String password) {
        userRegistrationService.registerUser(login, password);
        return messageService.getLocalizedMessage("user.reg.success");
    }

    @ShellMethod(value = "Login command",  key = {"l", "login"})
    public String login(@ShellOption("l") String login, @ShellOption("p") String password) {
        AppUserLoginResult logResult = userLoginService.login(login, password);
        user = logResult.getUser();
        return logResult.getLogResultMessage();
    }

    @ShellMethod(value = "Logout command",  key = "lout")
    public String logout() {
        if (user == null) {
            return messageService.getLocalizedMessage("user.logout.useless");
        }
        user = null;
        return messageService.getLocalizedMessage("user.logout.success");
    }

    @ShellMethod(value = "Start exam",  key = "start")
    @ShellMethodAvailability("isUserLoggedIn")
    public void startExam() {
        studentExamService.examineStudent();
    }

    private Availability isUserLoggedIn() {
        return user == null
            ? Availability.unavailable(messageService.getLocalizedMessage("user.must.be.logged.in"))
            : Availability.available();
    }
}
