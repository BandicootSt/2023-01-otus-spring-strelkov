package ru.otus.homework8.strelkov.mongock.test.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.homework8.strelkov.dao.AuthorsRepository;
import ru.otus.homework8.strelkov.dao.BooksCommentsRepository;
import ru.otus.homework8.strelkov.dao.BooksRepository;
import ru.otus.homework8.strelkov.dao.GenresRepository;
import ru.otus.homework8.strelkov.domain.Author;
import ru.otus.homework8.strelkov.domain.Book;
import ru.otus.homework8.strelkov.domain.BookComment;
import ru.otus.homework8.strelkov.domain.Genre;

import static java.util.Arrays.asList;

@ChangeLog
public class TestDatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "strelkov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "addAuthors", author = "strelkov", runAlways = true)
    public void addAuthors(AuthorsRepository repository) {
        repository.saveAll(
            asList(
                new Author("1", "TestFirstName1", "TestFirstName1", "TestPatronymic1"),
                new Author("2","TestFirstName2", "TestFirstName2", "TestPatronymic2"),
                new Author("3", "TestFirstName1", "TestFirstName2", null)
            )
        );
    }

    @ChangeSet(order = "003", id = "addGenres", author = "strelkov", runAlways = true)
    public void addGenres(GenresRepository repository) {
        repository.saveAll(
            asList(
                new Genre("1", "TestGenre1"),
                new Genre("2", "TestGenre2"),
                new Genre("3", "TestGenre3")
            )
        );
    }

    @ChangeSet(order = "004", id = "addBooks", author = "strelkov", runAlways = true)
    public void addBooks(BooksRepository repository) {
        repository.saveAll(
            asList(
                Book.builder()
                    .id("1")
                    .name("TestBook1")
                    .author(new Author("1", "TestFirstName1", "TestLastName1", "TestPatronymic1"))
                    .genre(new Genre("1", "TestGenre1"))
                    .build(),
                Book.builder()
                    .id("2")
                    .name("TestBook2")
                    .author(new Author("2", "TestFirstName2", "TestLastName2", "TestPatronymic2"))
                    .genre(new Genre("2", "TestGenre2"))
                    .build()
            )
        );
    }

    @ChangeSet(order = "005", id = "addComments", author = "strelkov", runAlways = true)
    public void addBooksComments(BooksCommentsRepository repository) {
        repository.saveAll(
            asList(
                new BookComment(
                    "1",
                    "TestComment1",
                    Book.builder()
                        .id("1")
                        .name("TestBook1")
                        .author(new Author("1", "TestFirstName1", "TestLastName1", "TestPatronymic1"))
                        .genre(new Genre("1", "TestGenre1"))
                        .build()
                ),
                new BookComment(
                    "2",
                    "TestComment2",
                    Book.builder()
                        .id("1")
                        .name("TestBook1")
                        .author(new Author("1", "TestFirstName1", "TestLastName1", "TestPatronymic1"))
                        .genre(new Genre("1", "TestGenre1"))
                        .build()
                ),
                new BookComment(
                    "3",
                    "TestComment3",
                    Book.builder()
                        .id("1")
                        .name("TestBook1")
                        .author(new Author("1", "TestFirstName1", "TestLastName1", "TestPatronymic1"))
                        .genre(new Genre("1", "TestGenre1"))
                        .build()
                )
            )
        );
    }


}
