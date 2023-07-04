package ru.otus.homework10.strelkov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework10.strelkov.domain.Author;
import ru.otus.homework10.strelkov.dto.AddAuthorRequestDto;
import ru.otus.homework10.strelkov.service.AuthorService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/authors")
public class AuthorsPagesController {

    private final AuthorService authorService;

    @GetMapping(path = {"", "/"})
    public String getAllAuthors(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "author_list";
    }

    @GetMapping("/add")
    public String addAuthor(Model model) {
        model.addAttribute("author", new AddAuthorRequestDto());
        return "author_add";
    }

    @PostMapping("/add")
    public String addAuthor(@ModelAttribute AddAuthorRequestDto request) {
        authorService.addAuthor(request);
        return "redirect:/authors/";
    }

    @PostMapping("/delete")
    public String deleteGenre(@RequestParam("id") Long authorId) {
        authorService.deleteAuthorById(authorId);
        return "redirect:/authors/";
    }
}
