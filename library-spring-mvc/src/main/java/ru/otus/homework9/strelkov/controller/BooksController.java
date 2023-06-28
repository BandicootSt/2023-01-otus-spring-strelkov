package ru.otus.homework9.strelkov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework9.strelkov.domain.Book;
import ru.otus.homework9.strelkov.dto.AddBookRequestDto;
import ru.otus.homework9.strelkov.service.AuthorService;
import ru.otus.homework9.strelkov.service.BookService;
import ru.otus.homework9.strelkov.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping(path = {"", "/"})
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book_list";
    }

    @GetMapping("/search")
    public String getBookByName(@RequestParam("name") String bookName, Model model) {
        Book book = bookService.getBookByName(bookName);
        model.addAttribute("books", List.of(book));
        return "book_list";
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new AddBookRequestDto());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("genres", genreService.getAllGenres());
        return "book_add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("addBookRequest") AddBookRequestDto request) {
        bookService.addBook(request);
        return "redirect:/books/";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") Long bookId, Model model) {
        Book book = bookService.getBookById(bookId);
        model.addAttribute("book", book);
        return "book_edit";
    }

    @PostMapping("/edit")
    public String saveChanges(@ModelAttribute("book") Book book, Model model) {
        bookService.updateBookNameById(book.getName(), book.getId());
        model.addAttribute("book", book);
        return "redirect:/books/";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam("id") Long bookId) {
        bookService.deleteBookById(bookId);
        return "redirect:/books/";
    }
}
