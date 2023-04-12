package ru.otus.homework.strelkov.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.homework.strelkov.service.LocaleHolder;

import java.util.Locale;

@Component
public class LocaleHolderImpl implements LocaleHolder {

    private final Locale locale;

    public LocaleHolderImpl(@Value("${locale}") Locale locale) {
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
