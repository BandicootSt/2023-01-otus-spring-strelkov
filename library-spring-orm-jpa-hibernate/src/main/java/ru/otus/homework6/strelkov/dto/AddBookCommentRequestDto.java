package ru.otus.homework6.strelkov.dto;

import jakarta.annotation.Nonnull;
import lombok.Value;

@Value
public class AddBookCommentRequestDto {

    @Nonnull
    String text;

    @Nonnull
    Long bookId;
}
