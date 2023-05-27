package ru.otus.homework7.strelkov.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework7.strelkov.domain.Genre;

public interface GenresRepository extends JpaRepository<Genre, Long> {
}
