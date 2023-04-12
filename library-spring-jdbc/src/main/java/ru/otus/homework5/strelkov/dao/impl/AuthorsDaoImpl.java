package ru.otus.homework5.strelkov.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework5.strelkov.dao.AuthorsDao;
import ru.otus.homework5.strelkov.domain.Author;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorsDaoImpl implements AuthorsDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long addAuthor(Author author) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        //language=sql
        jdbc.update(
            "insert into authors (" +
                "   first_name, " +
                "   last_name, " +
                "   patronymic" +
                ") values(" +
                "   :first_name, " +
                "   :last_name, " +
                "   :patronymic" +
                ")",
            new MapSqlParameterSource()
                .addValue("first_name", author.getFirstName())
                .addValue("last_name", author.getLastName())
                .addValue("patronymic", author.getPatronymic()),
            keyHolder
        );

        return (long) keyHolder.getKey();
    }

    @Override
    public List<Author> getAllAuthors() {
        //language=sql
        return jdbc.query(
            " select" +
                "   a.id," +
                "   a.first_name," +
                "   a.last_name," +
                "   a.patronymic" +
                " from authors a",
            new MapSqlParameterSource(),
            (rs, i) ->
                Author.builder()
                    .id(rs.getLong("id"))
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .patronymic(rs.getString("patronymic"))
                    .build()
        );
    }

    @Override
    public Author getAuthorById(Long authorId) {
        //language=sql
        return jdbc.queryForObject(
            " select" +
                "   a.id," +
                "   a.first_name," +
                "   a.last_name," +
                "   a.patronymic" +
                " from authors a" +
                " where a.id = :id",
            new MapSqlParameterSource("id", authorId),
            (rs, i) ->
                Author.builder()
                    .id(rs.getLong("id"))
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .patronymic(rs.getString("patronymic"))
                    .build()
        );
    }

    @Override
    public void deleteAuthorById(Long authorId) {
        //language=sql
        jdbc.update(
            " delete from authors a" +
                " where a.id = :id",
            new MapSqlParameterSource("id", authorId)
        );
    }
}
