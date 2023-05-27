package ru.otus.homework7.strelkov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.homework7.strelkov.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<Book, Long> {

    @Override
    @Query("select b from Book b join fetch b.author join fetch b.genre")
    List<Book> findAll();

    @Query("select b from Book b join fetch b.author join fetch b.genre where b.name = :name")
    Optional<Book> findByName(@Param("name") String bookName);

    @Override
    @Query("select b from Book b join fetch b.author join fetch b.genre where b.id = :id")
    Optional<Book> findById(@Param("id") Long id);

    @Modifying
    @Query("update Book b set b.name = :name where b.id = :id")
    void updateNameById(
        @Param("id") long id,
        @Param("name") String name
    );
}
