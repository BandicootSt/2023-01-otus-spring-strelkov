package ru.otus.homework5.strelkov.runner;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework5.strelkov.domain.Author;
import ru.otus.homework5.strelkov.domain.Genre;
import ru.otus.homework5.strelkov.dto.AddBookRequestDto;
import ru.otus.homework5.strelkov.service.AuthorService;
import ru.otus.homework5.strelkov.service.BookService;
import ru.otus.homework5.strelkov.service.GenreService;
import ru.otus.homework5.strelkov.service.ResponseConverter;

import java.util.function.Supplier;

@ShellComponent
@RequiredArgsConstructor
public class AppCommands {

    private final ResponseConverter responseConverter;
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @ShellMethod(value = "Add book", key = "bAdd")
    public String addBook(
        @ShellOption("name") String name,
        @ShellOption("a_id") Long authorId,
        @ShellOption("g_id") Long genreId
    ) {
        return withResultToStringConversion(() ->
            bookService.addBook(
                AddBookRequestDto.builder()
                    .name(name)
                    .authorId(authorId)
                    .genreId(genreId)
                    .build()
            )
        );
    }

    @ShellMethod(value = "Get all books", key = {"bGetAll", "bga"})
    public String getAllBooks() {
        return withResultToStringConversion(bookService::getAllBooks);
    }

    @ShellMethod(value = "Get book by name", key = {"bGet", "bg"})
    public String getBookByName(
        @ShellOption("name") String name
    ) {
        return withResultToStringConversion(() -> bookService.getBookByName(name));
    }

    @ShellMethod(value = "Update book name", key = {"bUpdName", "bun"})
    public String updateBookName(
        @ShellOption("newName") String newName,
        @ShellOption("id") Long bookId
    ) {
        return withResultToStringConversion(() -> bookService.updateBookNameById(newName, bookId));
    }

    @ShellMethod(value = "Delete book", key = {"bDel", "bd"})
    public String deleteBook(
        @ShellOption("id") Long bookId
    ) {
        return withResultToStringConversion(() -> bookService.deleteBookById(bookId));
    }

    @ShellMethod(value = "Add author", key = "aAdd")
    public String addAuthor(
        @ShellOption("fn") String firstName,
        @ShellOption("ln") String lastName,
        @ShellOption("p") String patronymic
    ) {
        return withResultToStringConversion(() ->
            authorService.addAuthor(
                Author.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .patronymic(patronymic)
                    .build()
            )
        );
    }

    @ShellMethod(value = "Get all authors", key = {"aGetAll", "aga"})
    public String getAllAuthors() {
        return withResultToStringConversion(authorService::getAllAuthors);
    }

    @ShellMethod(value = "Get author by id", key = {"aGet", "ag"})
    public String getAuthorById(
        @ShellOption("id") Long authorId
    ) {
        return withResultToStringConversion(() -> authorService.getAuthorById(authorId));
    }

    @ShellMethod(value = "Delete author", key = {"aDel", "ad"})
    public String deleteAuthor(
        @ShellOption("id") Long authorId
    ) {
        return withResultToStringConversion(() -> authorService.deleteAuthorById(authorId));
    }

    @ShellMethod(value = "Add genre", key = "gAdd")
    public String addGenre(
        @ShellOption("name") String genreName
    ) {
        return withResultToStringConversion(() -> genreService.addGenre(new Genre(genreName)));
    }

    @ShellMethod(value = "Get all genres", key = {"gGetAll", "gga"})
    public String getAllGenres() {
        return withResultToStringConversion(genreService::getAllGenres);
    }

    @ShellMethod(value = "Get genre by id", key = {"gGet", "gg"})
    public String getGenreById(
        @ShellOption("id") Long genreId
    ) {
        return withResultToStringConversion(() -> genreService.getGenreById(genreId));
    }

    @ShellMethod(value = "Delete genre", key = {"gDel", "gd"})
    public String deleteGenre(
        @ShellOption("id") Long genreId
    ) {
        return withResultToStringConversion(() -> genreService.deleteGenreById(genreId));
    }

    @Nonnull
    private <T> String withResultToStringConversion(Supplier<T> s) {
        try {
            return responseConverter.fromSuccess(s.get());
        } catch (Exception ex) {
            return responseConverter.fromError(ex.getMessage());
        }
    }

    @Nonnull
    private String withResultToStringConversion(Runnable r) {
        try {
            r.run();
            return responseConverter.fromSuccess();
        } catch (Exception ex) {
            return responseConverter.fromError(ex.getMessage());
        }
    }
}
