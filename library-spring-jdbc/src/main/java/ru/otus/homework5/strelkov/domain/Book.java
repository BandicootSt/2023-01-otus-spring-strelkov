package ru.otus.homework5.strelkov.domain;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    @Nullable
    private Long id;

    @Nonnull
    private final String name;

    @Nonnull
    private final Author author;

    @Nonnull
    private final Genre genre;

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}
