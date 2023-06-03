package ru.otus.homework8.strelkov.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework8.strelkov.domain.BookComment;

import java.util.List;

public interface BooksCommentsRepository extends MongoRepository<BookComment, String>, CustomBooksCommentsRepository {

    List<BookComment> findByBookId(String bookId);
}
