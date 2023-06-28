package ru.otus.homework10.strelkov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework10.strelkov.domain.Author;

public interface AuthorsRepository extends JpaRepository<Author, Long> {
}
