package ru.otus.homework9.strelkov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework9.strelkov.domain.Genre;
import ru.otus.homework9.strelkov.dto.AddGenreRequestDto;
import ru.otus.homework9.strelkov.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/genres")
public class GenresController {

    private final GenreService genreService;

    @GetMapping(path = {"", "/"})
    public String getAllGenres(Model model) {
        List<Genre> genres = genreService.getAllGenres();
        model.addAttribute("genres", genres);
        return "genre_list";
    }

    @GetMapping("/add")
    public String addGenre(Model model) {
        model.addAttribute("genre", new AddGenreRequestDto());
        return "genre_add";
    }

    @PostMapping("/add")
    public String addGenre(@ModelAttribute AddGenreRequestDto request) {
        genreService.addGenre(request);
        return "redirect:/genres/";
    }

    @PostMapping("/delete")
    public String deleteGenre(@RequestParam("id") Long genreId) {
        genreService.deleteGenreById(genreId);
        return "redirect:/genres/";
    }
}
