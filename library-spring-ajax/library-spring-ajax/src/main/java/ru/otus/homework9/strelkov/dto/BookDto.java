package ru.otus.homework9.strelkov.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import ru.otus.homework9.strelkov.domain.Book;

@Value
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class BookDto {

    Long id;

    String name;

    AuthorDto author;

    GenreDto genre;

    public static BookDto fromDomain(Book book) {
        return new BookDto(
            book.getId(),
            book.getName(),
            new AuthorDto(
                book.getAuthor().getId(),
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName(),
                book.getAuthor().getPatronymic()
            ),
            new GenreDto(
                book.getGenre().getId(),
                book.getGenre().getName()
            )
        );
    }
}
