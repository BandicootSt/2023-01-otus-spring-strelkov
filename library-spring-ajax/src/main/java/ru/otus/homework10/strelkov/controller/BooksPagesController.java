package ru.otus.homework10.strelkov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework10.strelkov.domain.Book;
import ru.otus.homework10.strelkov.service.AuthorService;
import ru.otus.homework10.strelkov.service.BookService;
import ru.otus.homework10.strelkov.service.GenreService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class BooksPagesController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping(path = {"", "/"})
    public String getAllBooks() {
        return "book_list";
    }

    @GetMapping("/add")
    public String addBook() {
        return "book_add";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") Long bookId, Model model) {
        Book book = bookService.getBookById(bookId);
        model.addAttribute("book", book);
        return "book_edit";
    }
}
