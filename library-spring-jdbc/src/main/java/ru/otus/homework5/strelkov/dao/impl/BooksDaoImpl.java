package ru.otus.homework5.strelkov.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework5.strelkov.dao.BooksDao;
import ru.otus.homework5.strelkov.domain.Author;
import ru.otus.homework5.strelkov.domain.Book;
import ru.otus.homework5.strelkov.domain.Genre;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BooksDaoImpl implements BooksDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long addBook(Book book) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        //language=sql
        jdbc.update(
            "insert into books (" +
                "   name, " +
                "   author_id, " +
                "   genre_id" +
                ") values(" +
                "   :name, " +
                "   :author_id, " +
                "   :genre_id" +
                ")",
            new MapSqlParameterSource()
                .addValue("name", book.getName())
                .addValue("author_id", book.getAuthor().getId())
                .addValue("genre_id", book.getGenre().getId()),
            keyHolder
        );

        return (long) keyHolder.getKey();
    }

    @Override
    public void updateBookNameById(String newBookName, Long bookId) {
        //language=sql
        jdbc.update(
            " update books b set" +
                "   b.name = :name" +
                " where" +
                "   b.id = :id",
            new MapSqlParameterSource()
                .addValue("name", newBookName)
                .addValue("id", bookId)
        );
    }

    @Override
    public List<Book> getAllBooks() {
        //language=sql
        return jdbc.query(
            " select" +
                "   b.id as book_id," +
                "   b.name as book_name," +
                "   b.author_id," +
                "   b.genre_id," +
                "   a.first_name," +
                "   a.last_name," +
                "   a.patronymic," +
                "   g.name as genre_name," +
                " from books b" +
                " join authors a on b.author_id = a.id" +
                " join genres g on b.genre_id = g.id",
            new MapSqlParameterSource(),
            (rs, i) ->
                Book.builder()
                    .id(rs.getLong("book_id"))
                    .name(rs.getString("book_name"))
                    .author(
                        Author.builder()
                            .id(rs.getLong("author_id"))
                            .firstName(rs.getString("first_name"))
                            .lastName(rs.getString("last_name"))
                            .patronymic(rs.getString("patronymic"))
                            .build()
                    )
                    .genre(
                        new Genre(
                            rs.getLong("genre_id"),
                            rs.getString("genre_name")
                        )
                    )
                    .build()
        );
    }

    @Override
    public Book getBookByName(String bookName) {
        //language=sql
        return jdbc.queryForObject(
            " select" +
                "   b.id as book_id," +
                "   b.name as book_name," +
                "   b.author_id," +
                "   b.genre_id," +
                "   a.first_name," +
                "   a.last_name," +
                "   a.patronymic," +
                "   g.name as genre_name," +
                " from books b" +
                " join authors a on b.author_id = a.id" +
                " join genres g on b.genre_id = g.id" +
                " where " +
                "   b.name = :book_name",
            new MapSqlParameterSource("book_name", bookName),
            (rs, i) ->
                Book.builder()
                    .id(rs.getLong("book_id"))
                    .name(rs.getString("book_name"))
                    .author(
                        Author.builder()
                            .id(rs.getLong("author_id"))
                            .firstName(rs.getString("first_name"))
                            .lastName(rs.getString("last_name"))
                            .patronymic(rs.getString("patronymic"))
                            .build()
                    )
                    .genre(
                        new Genre(
                            rs.getLong("genre_id"),
                            rs.getString("genre_name")
                        )
                    )
                    .build()
        );
    }

    @Override
    public void deleteBookById(Long bookId) {
        //language=sql
        jdbc.update(
            " delete from books b" +
                " where b.id = :id",
            new MapSqlParameterSource("id", bookId)
        );
    }
}
