package ru.otus.homework5.strelkov.dto;

import jakarta.annotation.Nonnull;
import lombok.Value;

@Value
public class AddBookRequestDto {

    @Nonnull
    String name;

    @Nonnull
    Long authorId;

    @Nonnull
    Long genreId;
}
