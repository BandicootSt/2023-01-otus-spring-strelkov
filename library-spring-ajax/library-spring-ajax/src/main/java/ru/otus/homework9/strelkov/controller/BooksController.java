package ru.otus.homework9.strelkov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework9.strelkov.dto.AddBookParamsDto;
import ru.otus.homework9.strelkov.dto.AddBookRequestDto;
import ru.otus.homework9.strelkov.dto.AuthorDto;
import ru.otus.homework9.strelkov.dto.BookDto;
import ru.otus.homework9.strelkov.dto.DeleteBookRequestDto;
import ru.otus.homework9.strelkov.dto.EditBookRequestDto;
import ru.otus.homework9.strelkov.dto.GenreDto;
import ru.otus.homework9.strelkov.service.AuthorService;
import ru.otus.homework9.strelkov.service.BookService;
import ru.otus.homework9.strelkov.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping(path = {"", "/"})
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks().stream().map(BookDto::fromDomain).toList();
    }

    @GetMapping("/book")
    public BookDto getBookByName(@RequestParam("name") String name) {
        return BookDto.fromDomain(bookService.getBookByName(name));
    }

    @GetMapping("/params")
    public AddBookParamsDto getPossibleParamsForAdd() {
        return new AddBookParamsDto(
            authorService.getAllAuthors().stream().map(AuthorDto::fromDomain).toList(),
            genreService.getAllGenres().stream().map(GenreDto::fromDomain).toList()
        );
    }

    @PostMapping(path = {"", "/"})
    public void addBook(@RequestBody AddBookRequestDto request) {
        bookService.addBook(request);
    }

    @PutMapping(path = {"", "/"})
    public void editBook(@RequestBody EditBookRequestDto request) {
        bookService.updateBookNameById(request.getName(), request.getId());
    }

    @DeleteMapping(path = {"", "/"})
    public void deleteBook(@RequestBody DeleteBookRequestDto deleteRequest) {
        bookService.deleteBookById(deleteRequest.getId());
    }
}
