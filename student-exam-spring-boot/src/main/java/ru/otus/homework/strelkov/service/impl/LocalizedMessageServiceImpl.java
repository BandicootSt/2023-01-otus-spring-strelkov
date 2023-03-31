package ru.otus.homework.strelkov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework.strelkov.service.LocaleHolder;
import ru.otus.homework.strelkov.service.LocalizedMessageService;

@Service
@RequiredArgsConstructor
public class LocalizedMessageServiceImpl implements LocalizedMessageService {

    private final MessageSource messageSource;
    private final LocaleHolder localeHolder;

    @Override
    public String getLocalizedMessage(String msgPropertyCode, Object[] args) {
        return messageSource.getMessage(msgPropertyCode, args, localeHolder.getLocale());
    }

    @Override
    public String getLocalizedMessage(String msgPropertyCode) {
        return messageSource.getMessage(msgPropertyCode, null, localeHolder.getLocale());
    }
}
