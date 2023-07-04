package ru.otus.homework10.strelkov.dto;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditBookRequestDto {

    @Nonnull
    private Long id;

    @Nonnull
    private String name;
}
