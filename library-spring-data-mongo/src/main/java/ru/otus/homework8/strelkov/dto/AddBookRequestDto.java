package ru.otus.homework8.strelkov.dto;

import jakarta.annotation.Nonnull;
import lombok.Value;

@Value
public class AddBookRequestDto {

    @Nonnull
    String name;

    @Nonnull
    String authorId;

    @Nonnull
    String genreId;
}
