package ru.otus.homework5.strelkov.domain;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@EqualsAndHashCode
public class Author {

    @Nullable
    Long id;

    @Nonnull
    String firstName;

    @Nonnull
    String lastName;

    @Nullable
    String patronymic;
}
