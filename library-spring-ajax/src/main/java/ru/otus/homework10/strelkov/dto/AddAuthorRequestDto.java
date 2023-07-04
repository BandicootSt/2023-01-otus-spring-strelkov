package ru.otus.homework10.strelkov.dto;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAuthorRequestDto {

    @Nonnull
    private String firstName;

    @Nonnull
    private String lastName;

    @Nullable
    private String patronymic;
}
