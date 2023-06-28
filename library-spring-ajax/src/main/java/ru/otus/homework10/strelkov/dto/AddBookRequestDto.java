package ru.otus.homework10.strelkov.dto;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookRequestDto {

    @Nonnull
    private String name;

    @Nonnull
    private Long authorId;

    @Nonnull
    private Long genreId;
}
