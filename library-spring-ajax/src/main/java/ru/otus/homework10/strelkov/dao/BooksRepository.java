package ru.otus.homework10.strelkov.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework10.strelkov.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<Book, Long> {

    @Override
    @EntityGraph(value = "book-entity-graph")
    List<Book> findAll();

    @EntityGraph(value = "book-entity-graph")
    Optional<Book> findByName(String bookName);

    @Override
    @EntityGraph(value = "book-entity-graph")
    Optional<Book> findById(Long id);
}
