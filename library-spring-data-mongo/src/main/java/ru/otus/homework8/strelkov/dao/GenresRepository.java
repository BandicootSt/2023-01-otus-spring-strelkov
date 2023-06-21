package ru.otus.homework8.strelkov.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework8.strelkov.domain.Genre;

public interface GenresRepository extends MongoRepository<Genre, String> {
}
