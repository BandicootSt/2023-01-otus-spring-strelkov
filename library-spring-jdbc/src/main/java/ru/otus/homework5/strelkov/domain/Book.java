package ru.otus.homework5.strelkov.domain;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@EqualsAndHashCode
public class Book {

    @Nullable
    Long id;

    @Nonnull
    String name;

    @Nonnull
    Author author;

    @Nonnull
    Genre genre;
}
