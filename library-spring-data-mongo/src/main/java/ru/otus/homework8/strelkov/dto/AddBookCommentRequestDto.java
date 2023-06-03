package ru.otus.homework8.strelkov.dto;

import jakarta.annotation.Nonnull;
import lombok.Value;

@Value
public class AddBookCommentRequestDto {

    @Nonnull
    String text;

    @Nonnull
    String bookId;
}
