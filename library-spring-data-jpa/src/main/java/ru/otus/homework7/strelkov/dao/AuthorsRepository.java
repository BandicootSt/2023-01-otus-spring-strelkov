package ru.otus.homework7.strelkov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework7.strelkov.domain.Author;

public interface AuthorsRepository extends JpaRepository<Author, Long> {
}
