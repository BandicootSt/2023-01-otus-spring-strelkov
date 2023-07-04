package ru.otus.homework10.strelkov.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import ru.otus.homework10.strelkov.domain.Author;

@Value
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class AuthorDto {

    Long id;

    String firstName;

    String lastName;

    String patronymic;

    public static AuthorDto fromDomain(Author author) {
        return new AuthorDto(
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getPatronymic()
        );
    }
}
