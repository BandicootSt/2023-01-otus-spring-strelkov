package ru.otus.homework8.strelkov.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.homework8.strelkov.dao.CustomBooksCommentsRepository;
import ru.otus.homework8.strelkov.domain.Book;
import ru.otus.homework8.strelkov.domain.BookComment;

import java.util.List;

@RequiredArgsConstructor
public class CustomBooksCommentsRepositoryImpl implements CustomBooksCommentsRepository {

    private final MongoOperations mongoOperations;

    @Override
    public void updateTextById(String commentId, String text) {
        mongoOperations.updateFirst(
            Query.query(Criteria.where("id").is(commentId)),
            Update.update("text", text),
            BookComment.class
        );
    }

    @Override
    public void deleteAllByBooks(List<Book> books) {
        mongoOperations.findAllAndRemove(
            Query.query(Criteria.where("book").in(books)),
            BookComment.class
        );
    }

    @Override
    public void deleteAllByBook(Book book) {
        mongoOperations.findAllAndRemove(
            Query.query(Criteria.where("book").is(book)),
            BookComment.class
        );
    }
}
