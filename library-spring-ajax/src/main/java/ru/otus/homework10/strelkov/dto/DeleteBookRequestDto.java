package ru.otus.homework10.strelkov.dto;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBookRequestDto {

    @Nonnull
    private Long id;
}
