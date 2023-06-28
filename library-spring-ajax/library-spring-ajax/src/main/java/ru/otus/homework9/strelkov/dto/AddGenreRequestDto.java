package ru.otus.homework9.strelkov.dto;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddGenreRequestDto {

    @Nonnull
    private String name;
}
