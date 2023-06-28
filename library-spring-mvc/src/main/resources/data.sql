insert into authors (first_name, last_name, patronymic) values ('DemoFirstName1', 'DemoLastName1', 'DemoPatronymic1');
insert into authors (first_name, last_name, patronymic) values ('DemoFirstName2', 'DemoLastName2', 'DemoPatronymic2');
insert into authors (first_name, last_name) values ('DemoFirstName3', 'DemoLastName3');

insert into genres (name) values('DemoGenre1');
insert into genres (name) values('DemoGenre2');
insert into genres (name) values('DemoGenre3');

insert into books (name, author_id, genre_id) values('DemoBook1', 1, 1);
insert into books (name, author_id, genre_id) values('DemoBook2', 2, 2);

insert into book_comments(text, book_id) values('DemoComment1', 1);
insert into book_comments(text, book_id) values('DemoComment2', 1);
insert into book_comments(text, book_id) values('DemoComment3', 1);