package ru.otus.homework9.strelkov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework9.strelkov.domain.Author;

public interface AuthorsRepository extends JpaRepository<Author, Long> {
}
