package ru.otus.homework8.strelkov.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.homework8.strelkov.dao.CustomBooksRepository;
import ru.otus.homework8.strelkov.domain.Book;

@RequiredArgsConstructor
public class CustomBooksRepositoryImpl implements CustomBooksRepository {

    private final MongoOperations mongoOperations;
    @Override
    public void updateNameById(String id, String name) {
        mongoOperations.updateFirst(
            Query.query(Criteria.where("id").is(id)),
            Update.update("name", name),
            Book.class
        );
    }
}
