package ru.otus.homework8.strelkov.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework8.strelkov.domain.Author;

public interface AuthorsRepository extends MongoRepository<Author, String> {
}
