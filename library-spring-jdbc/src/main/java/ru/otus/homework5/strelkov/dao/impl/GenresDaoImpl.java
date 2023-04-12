package ru.otus.homework5.strelkov.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework5.strelkov.dao.GenresDao;
import ru.otus.homework5.strelkov.domain.Genre;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenresDaoImpl implements GenresDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long addGenre(Genre genre) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        //language=sql
        jdbc.update(
            "insert into genres (" +
                "   name" +
                ") values(" +
                "   :name " +
                ")",
            new MapSqlParameterSource("name", genre.getName()),
            keyHolder
        );

        return (long) keyHolder.getKey();
    }

    @Override
    public List<Genre> getAllGenres() {
        //language=sql
        return jdbc.query(
            " select" +
                "   g.id," +
                "   g.name," +
                " from genres g",
            new MapSqlParameterSource(),
            (rs, i) ->
                new Genre(
                    rs.getLong("id"),
                    rs.getString("name")
                )
        );
    }

    @Override
    public Genre getGenreById(Long genreId) {
        //language=sql
        return jdbc.queryForObject(
            " select" +
                "   g.name," +
                " from genres g" +
                " where g.id = :genre_id",
            new MapSqlParameterSource("genre_id", genreId),
            (rs, i) ->
                new Genre(
                    genreId,
                    rs.getString("name")
                )
        );
    }

    @Override
    public void deleteGenreById(Long genreId) {
        //language=sql
        jdbc.update(
            " delete from genres g" +
                " where g.id = :id",
            new MapSqlParameterSource("id", genreId)
        );
    }
}
