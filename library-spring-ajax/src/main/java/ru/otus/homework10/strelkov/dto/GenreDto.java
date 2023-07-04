package ru.otus.homework10.strelkov.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import ru.otus.homework10.strelkov.domain.Genre;

@Value
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class GenreDto {

    Long id;

    String name;

    public static GenreDto fromDomain(Genre genre) {
        return new GenreDto(
            genre.getId(),
            genre.getName()
        );
    }
}
