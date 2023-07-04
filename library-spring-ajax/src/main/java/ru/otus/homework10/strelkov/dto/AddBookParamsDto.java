package ru.otus.homework10.strelkov.dto;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookParamsDto {

    @Nonnull
    private List<AuthorDto> authors;

    @Nonnull
    private List<GenreDto> genres;
}
