package ru.otus.homework9.strelkov.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.homework9.strelkov.domain.Author;
import ru.otus.homework9.strelkov.domain.Book;
import ru.otus.homework9.strelkov.domain.Genre;
import ru.otus.homework9.strelkov.dto.AddBookRequestDto;
import ru.otus.homework9.strelkov.exception.BookNotFoundException;
import ru.otus.homework9.strelkov.service.AuthorService;
import ru.otus.homework9.strelkov.service.BookService;
import ru.otus.homework9.strelkov.service.GenreService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BooksController.class)
class BooksControllerTest {

    private static final Author FIRST_AUTHOR = new Author(1L, "TestFirstName", "TestLastName", "TestPatronymic");
    private static final Author SECOND_AUTHOR = new Author(2L, "TestFirstName2", "TestLastName2", "TestPatronymic2");

    private static final Genre FIRST_GENRE = new Genre(1L, "TestGenre");
    private static final Genre SECOND_GENRE = new Genre(2L, "TestGenre2");

    private static final Book FIRST_BOOK = new Book(1L, "TestBook", FIRST_AUTHOR, FIRST_GENRE);
    private static final Book SECOND_BOOK = new Book(2L, "TestBook2", SECOND_AUTHOR, SECOND_GENRE);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Test
    void testGetAllBooks() throws Exception {
        given(bookService.getAllBooks()).willReturn(List.of(FIRST_BOOK, SECOND_BOOK));
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
            .andExpect(status().isOk())
            //language=html
            .andExpect(content().string(
                "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\"/>\n" +
                    "    <title>Books</title>\n" +
                    "    <style type=\"text/css\">\n" +
                    "        body {\n" +
                    "            padding: 50px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .books {\n" +
                    "            border: 1px solid steelblue;\n" +
                    "            width: 300px;\n" +
                    "            border-collapse: collapse;\n" +
                    "        }\n" +
                    "\n" +
                    "        .books tr td, th {\n" +
                    "            padding: 5px;\n" +
                    "            border: 1px solid steelblue;\n" +
                    "        }\n" +
                    "\n" +
                    "        .action {\n" +
                    "            padding: 5px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .books td:last-child, td:first-child {\n" +
                    "            width: 50px;\n" +
                    "        }\n" +
                    "\n" +
                    "\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<h3>Library</h3>\n" +
                    "\n" +
                    "<table class=\"books\">\n" +
                    "    <thead>\n" +
                    "    <tr>\n" +
                    "        <th>Name</th>\n" +
                    "        <th>Author</th>\n" +
                    "        <th>Genre</th>\n" +
                    "        <th>Action</th>\n" +
                    "    </tr>\n" +
                    "    </thead>\n" +
                    "    <tbody>\n" +
                    "    <tr>\n" +
                    "        <td>TestBook</td>\n" +
                    "        <td>TestFirstName</td>\n" +
                    "        <td>TestGenre</td>\n" +
                    "        <td>\n" +
                    "            <div class=\"action\">\n" +
                    "                <a href=\"/books/edit?id=1\">\n" +
                    "                    <button>Edit</button>\n" +
                    "                </a>\n" +
                    "            </div>\n" +
                    "            <div class=\"action\">\n" +
                    "                <a href=\"/comments?bookId=1\">\n" +
                    "                    <button>View comments</button>\n" +
                    "                </a>\n" +
                    "            </div>\n" +
                    "            <div class=\"action\">\n" +
                    "                <form id=\"delete-form\" action=\"/books/delete?id=1\" method=\"post\">\n" +
                    "                    <button type=\"submit\" class=\"link-button\">Delete</button>\n" +
                    "                </form>\n" +
                    "            </div>\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>TestBook2</td>\n" +
                    "        <td>TestFirstName2</td>\n" +
                    "        <td>TestGenre2</td>\n" +
                    "        <td>\n" +
                    "            <div class=\"action\">\n" +
                    "                <a href=\"/books/edit?id=2\">\n" +
                    "                    <button>Edit</button>\n" +
                    "                </a>\n" +
                    "            </div>\n" +
                    "            <div class=\"action\">\n" +
                    "                <a href=\"/comments?bookId=2\">\n" +
                    "                    <button>View comments</button>\n" +
                    "                </a>\n" +
                    "            </div>\n" +
                    "            <div class=\"action\">\n" +
                    "                <form id=\"delete-form\" action=\"/books/delete?id=2\" method=\"post\">\n" +
                    "                    <button type=\"submit\" class=\"link-button\">Delete</button>\n" +
                    "                </form>\n" +
                    "            </div>\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    </tbody>\n" +
                    "</table>\n" +
                    "<div class=\"action\">\n" +
                    "    <form id=\"add-form\" action=\"/books/add\" method=\"get\">\n" +
                    "        <button type=\"submit\" class=\"link-button\">Add new</button>\n" +
                    "    </form>\n" +
                    "</div>\n" +
                    "<div class=\"action\">\n" +
                    "    <form id=\"search-form\" action=\"/books/search\" method=\"get\">\n" +
                    "        <input placeholder=\"Enter book Name\" type=\"text\" name=\"name\"/>\n" +
                    "        <button type=\"submit\" class=\"link-button\">Search</button>\n" +
                    "    </form>\n" +
                    "</div>\n" +
                    "<div class=\"action\">\n" +
                    "    <a href=\"/books\">\n" +
                    "        <button>View all books</button>\n" +
                    "    </a>\n" +
                    "</div>\n" +
                    "<div class=\"action\">\n" +
                    "    <a href=\"/authors\">\n" +
                    "        <button>View all authors</button>\n" +
                    "    </a>\n" +
                    "</div>\n" +
                    "<div class=\"action\">\n" +
                    "    <a href=\"/genres\">\n" +
                    "        <button>View all genres</button>\n" +
                    "    </a>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>"
            ));
    }

    @Test
    void testGetBookByName() throws Exception {
        given(bookService.getBookByName(FIRST_BOOK.getName())).willReturn(FIRST_BOOK);
        mockMvc.perform(MockMvcRequestBuilders.get("/books/search?name=" + FIRST_BOOK.getName()))
            .andExpect(status().isOk())
            //language=html
            .andExpect(content().string(
                "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\"/>\n" +
                    "    <title>Books</title>\n" +
                    "    <style type=\"text/css\">\n" +
                    "        body {\n" +
                    "            padding: 50px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .books {\n" +
                    "            border: 1px solid steelblue;\n" +
                    "            width: 300px;\n" +
                    "            border-collapse: collapse;\n" +
                    "        }\n" +
                    "\n" +
                    "        .books tr td, th {\n" +
                    "            padding: 5px;\n" +
                    "            border: 1px solid steelblue;\n" +
                    "        }\n" +
                    "\n" +
                    "        .action {\n" +
                    "            padding: 5px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .books td:last-child, td:first-child {\n" +
                    "            width: 50px;\n" +
                    "        }\n" +
                    "\n" +
                    "\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<h3>Library</h3>\n" +
                    "\n" +
                    "<table class=\"books\">\n" +
                    "    <thead>\n" +
                    "    <tr>\n" +
                    "        <th>Name</th>\n" +
                    "        <th>Author</th>\n" +
                    "        <th>Genre</th>\n" +
                    "        <th>Action</th>\n" +
                    "    </tr>\n" +
                    "    </thead>\n" +
                    "    <tbody>\n" +
                    "    <tr>\n" +
                    "        <td>TestBook</td>\n" +
                    "        <td>TestFirstName</td>\n" +
                    "        <td>TestGenre</td>\n" +
                    "        <td>\n" +
                    "            <div class=\"action\">\n" +
                    "                <a href=\"/books/edit?id=1\">\n" +
                    "                    <button>Edit</button>\n" +
                    "                </a>\n" +
                    "            </div>\n" +
                    "            <div class=\"action\">\n" +
                    "                <a href=\"/comments?bookId=1\">\n" +
                    "                    <button>View comments</button>\n" +
                    "                </a>\n" +
                    "            </div>\n" +
                    "            <div class=\"action\">\n" +
                    "                <form id=\"delete-form\" action=\"/books/delete?id=1\" method=\"post\">\n" +
                    "                    <button type=\"submit\" class=\"link-button\">Delete</button>\n" +
                    "                </form>\n" +
                    "            </div>\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "    </tbody>\n" +
                    "</table>\n" +
                    "<div class=\"action\">\n" +
                    "    <form id=\"add-form\" action=\"/books/add\" method=\"get\">\n" +
                    "        <button type=\"submit\" class=\"link-button\">Add new</button>\n" +
                    "    </form>\n" +
                    "</div>\n" +
                    "<div class=\"action\">\n" +
                    "    <form id=\"search-form\" action=\"/books/search\" method=\"get\">\n" +
                    "        <input placeholder=\"Enter book Name\" type=\"text\" name=\"name\"/>\n" +
                    "        <button type=\"submit\" class=\"link-button\">Search</button>\n" +
                    "    </form>\n" +
                    "</div>\n" +
                    "<div class=\"action\">\n" +
                    "    <a href=\"/books\">\n" +
                    "        <button>View all books</button>\n" +
                    "    </a>\n" +
                    "</div>\n" +
                    "<div class=\"action\">\n" +
                    "    <a href=\"/authors\">\n" +
                    "        <button>View all authors</button>\n" +
                    "    </a>\n" +
                    "</div>\n" +
                    "<div class=\"action\">\n" +
                    "    <a href=\"/genres\">\n" +
                    "        <button>View all genres</button>\n" +
                    "    </a>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>"
            ));
    }

    @Test
    void testGetBookByName4xx() throws Exception {
        final String unknownBookName = "Unknown book name";
        given(bookService.getBookByName(unknownBookName)).willThrow(new BookNotFoundException("Not found book with name: " + unknownBookName));
        mockMvc.perform(MockMvcRequestBuilders.get("/books/search?name=" + unknownBookName))
            .andExpect(status().isBadRequest())
            //language=html
            .andExpect(content().string(
                "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\"/>\n" +
                    "    <title>Error</title>\n" +
                    "    <style type=\"text/css\">\n" +
                    "        body {\n" +
                    "            padding: 50px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .action {\n" +
                    "            padding: 5px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<h3>Error</h3>\n" +
                    "\n" +
                    "<div>Not found book with name: Unknown book name</div>\n" +
                    "<div class=\"action\">\n" +
                    "    <a href=\"/books\">\n" +
                    "        <button>Back to library</button>\n" +
                    "    </a>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>"
            ));
    }

    @Test
    void testEditBook() throws Exception {
        given(bookService.getBookById(FIRST_BOOK.getId())).willReturn(FIRST_BOOK);
        mockMvc.perform(MockMvcRequestBuilders.get("/books/edit?id=" + FIRST_BOOK.getId()))
            .andExpect(status().isOk())
            //language=html
            .andExpect(content().string(
                "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\"/>\n" +
                    "  <title>Edit book</title>\n" +
                    "  <style type=\"text/css\">\n" +
                    "        body {\n" +
                    "            padding: 50px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .books {\n" +
                    "            border: 1px solid steelblue;\n" +
                    "            width: 300px;\n" +
                    "            border-collapse: collapse;\n" +
                    "        }\n" +
                    "\n" +
                    "        .books tr td, th {\n" +
                    "            padding: 5px;\n" +
                    "            border: 1px solid steelblue;\n" +
                    "        }\n" +
                    "\n" +
                    "        .books td:last-child, td:first-child {\n" +
                    "            width: 50px;\n" +
                    "        }\n" +
                    "\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "  <form id=\"edit-form\" action=\"/books/edit?id=1\" method=\"post\">\n" +
                    "    <h3>Book Info:</h3>\n" +
                    "\n" +
                    "    <div class=\"row\">\n" +
                    "      <label for=\"book-name-input\">Name:</label>\n" +
                    "      <input id=\"book-name-input\" placeholder=\"Enter new book name\" name=\"name\" type=\"text\" value=\"TestBook\"/>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <div class=\"row\">\n" +
                    "      <button type=\"submit\">Save</button>\n" +
                    "      <a href=\"/books\"><button type=\"button\">Cancel</button></a>\n" +
                    "    </div>\n" +
                    "  </form>\n" +
                    "</body>\n" +
                    "</html>"
            ));

        final Book changedBook = new Book(
            FIRST_BOOK.getId(),
            "NewFirstBookName",
            FIRST_BOOK.getAuthor(),
            FIRST_BOOK.getGenre()
        );
        mockMvc.perform(
                MockMvcRequestBuilders.post("/books/edit")
                    .flashAttr("book", changedBook)
            )
            .andExpect(status().is3xxRedirection());
    }

    @Test
    void testAddBook() throws Exception {
        given(authorService.getAllAuthors()).willReturn(List.of(FIRST_AUTHOR, SECOND_AUTHOR));
        given(genreService.getAllGenres()).willReturn(List.of(FIRST_GENRE, SECOND_GENRE));
        mockMvc.perform(MockMvcRequestBuilders.get("/books/add"))
            .andExpect(status().isOk())
            //language=html
            .andExpect(content().string(
                "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\"/>\n" +
                    "  <title>Add book</title>\n" +
                    "  <style type=\"text/css\">\n" +
                    "        body {\n" +
                    "            padding: 50px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .books {\n" +
                    "            border: 1px solid steelblue;\n" +
                    "            width: 300px;\n" +
                    "            border-collapse: collapse;\n" +
                    "        }\n" +
                    "\n" +
                    "        .books tr td, th {\n" +
                    "            padding: 5px;\n" +
                    "            border: 1px solid steelblue;\n" +
                    "        }\n" +
                    "\n" +
                    "        .books td:last-child, td:first-child {\n" +
                    "            width: 50px;\n" +
                    "        }\n" +
                    "\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "  <form method=\"post\" action=\"/books/add\">\n" +
                    "    <h3>Book Info:</h3>\n" +
                    "\n" +
                    "    <input id=\"name\" placeholder=\"Enter book Name\" required type=\"text\" name=\"name\" value=\"\"/>\n" +
                    "\n" +
                    "    <select id=\"author\" required name=\"authorId\">\n" +
                    "      <option value=\"\" hidden>Select author</option>\n" +
                    "      <option value=\"1\">TestFirstName</option>\n" +
                    "      <option value=\"2\">TestFirstName2</option>\n" +
                    "    </select>\n" +
                    "\n" +
                    "    <select id=\"genre\" required name=\"genreId\">\n" +
                    "      <option value=\"\" hidden>Select genre</option>\n" +
                    "      <option value=\"1\">TestGenre</option>\n" +
                    "      <option value=\"2\">TestGenre2</option>\n" +
                    "    </select>\n" +
                    "\n" +
                    "    <div class=\"row\">\n" +
                    "      <button type=\"submit\">Save</button>\n" +
                    "      <a href=\"/books\"><button type=\"button\">Cancel</button></a>\n" +
                    "    </div>\n" +
                    "  </form>\n" +
                    "</body>\n" +
                    "</html>"
            ));

        final Book newBook = new Book(
            "NewBook",
            FIRST_AUTHOR,
            SECOND_GENRE
        );

        given(bookService.getAllBooks()).willReturn(List.of(FIRST_BOOK, SECOND_BOOK, newBook));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/books/add")
                    .flashAttr(
                        "addBookRequest",
                        new AddBookRequestDto(
                            newBook.getName(),
                            FIRST_AUTHOR.getId(),
                            FIRST_GENRE.getId()
                        )
                    )
            )
            .andExpect(status().is3xxRedirection());
    }

    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books/delete?id=" + FIRST_BOOK.getId()))
            .andExpect(status().is3xxRedirection());
    }
}