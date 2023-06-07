package ru.otus.homework8.strelkov.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework8.strelkov.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends MongoRepository<Book, String>, CustomBooksRepository {

    Optional<Book> findByName(String bookName);

    List<Book> findAllByAuthorId(String authorId);

    List<Book> findAllByGenreId(String genreId);
}
