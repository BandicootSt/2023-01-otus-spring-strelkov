package ru.otus.homework9.strelkov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework9.strelkov.domain.BookComment;
import ru.otus.homework9.strelkov.dto.AddBookCommentRequestDto;
import ru.otus.homework9.strelkov.service.BooksCommentsService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/comments")
public class BookCommentsPagesController {

    private final BooksCommentsService commentsService;

    @GetMapping(path = {"", "/"})
    public String getBookComments(@RequestParam("bookId") Long bookId, Model model) {
        List<BookComment> comments = commentsService.getCommentsByBookId(bookId);
        model.addAttribute("comments", comments);
        model.addAttribute("bookId", bookId);
        return "comment_list";
    }

    @GetMapping("/add")
    public String addComment(@RequestParam("bookId") Long bookId, Model model) {
        AddBookCommentRequestDto requestDto = new AddBookCommentRequestDto();
        requestDto.setBookId(bookId);
        model.addAttribute("comment", requestDto);
        return "comment_add";
    }

    @PostMapping("/add")
    public String addComment(@ModelAttribute AddBookCommentRequestDto request) {
        commentsService.addComment(request);
        return "redirect:/comments?bookId=" + request.getBookId();
    }

    @GetMapping("/edit")
    public String editComment(@RequestParam("id") Long commentId, Model model) {
        BookComment comment = commentsService.getCommentById(commentId);
        model.addAttribute("comment", comment);
        return "comment_edit";
    }

    @PostMapping("/edit")
    public String saveChanges(@ModelAttribute("comment") BookComment comment) {
        commentsService.editCommentTextById(comment.getId(), comment.getText());
        return "redirect:/comments?bookId=" + comment.getBook().getId();
    }

    @PostMapping("/delete")
    public String deleteComment(@RequestParam("bookId") Long bookId, @RequestParam("id") Long commentId) {
        commentsService.deleteCommentById(commentId);
        return "redirect:/comments?bookId=" + bookId;
    }
}
