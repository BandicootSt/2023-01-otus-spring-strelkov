package ru.otus.homework8.strelkov.mongock.changelog;

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
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "strelkov", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "addAuthors", author = "strelkov", runAlways = true)
    public void addAuthors(AuthorsRepository repository) {
        repository.saveAll(
            asList(
                new Author("1", "DemoFirstName1", "DemoFirstName1", "DemoPatronymic1"),
                new Author("2","DemoFirstName2", "DemoFirstName2", "DemoPatronymic2"),
                new Author("3", "DemoFirstName1", "DemoFirstName2", null)
            )
        );
    }

    @ChangeSet(order = "003", id = "addGenres", author = "strelkov", runAlways = true)
    public void addGenres(GenresRepository repository) {
        repository.saveAll(
            asList(
                new Genre("1", "DemoGenre1"),
                new Genre("2", "DemoGenre2"),
                new Genre("3", "DemoGenre3")
            )
        );
    }

    @ChangeSet(order = "004", id = "addBooks", author = "strelkov", runAlways = true)
    public void addBooks(BooksRepository repository) {
        repository.saveAll(
            asList(
                Book.builder()
                    .id("1")
                    .name("DemoBook1")
                    .author(new Author("1", "DemoFirstName1", "DemoLastName1", "DemoPatronymic1"))
                    .genre(new Genre("1", "DemoGenre1"))
                    .build(),
                Book.builder()
                    .id("2")
                    .name("DemoBook2")
                    .author(new Author("2", "DemoFirstName2", "DemoLastName2", "DemoPatronymic2"))
                    .genre(new Genre("2", "DemoGenre2"))
                    .build()
            )
        );
    }

    @ChangeSet(order = "005", id = "addComments", author = "strelkov", runAlways = true)
    public void addBooksComments(BooksCommentsRepository repository) {
        repository.saveAll(
            asList(
                new BookComment("DemoComment1",
                    Book.builder()
                        .id("1")
                        .name("DemoBook1")
                        .author(new Author("1", "DemoFirstName1", "DemoLastName1", "DemoPatronymic1"))
                        .genre(new Genre("1", "DemoGenre1"))
                        .build()
                ),
                new BookComment("DemoComment2",
                    Book.builder()
                        .id("2")
                        .name("DemoBook2")
                        .author(new Author("2", "DemoFirstName2", "DemoLastName2", "DemoPatronymic2"))
                        .genre(new Genre("2", "DemoGenre2"))
                        .build()
                )
            )
        );
    }


}
