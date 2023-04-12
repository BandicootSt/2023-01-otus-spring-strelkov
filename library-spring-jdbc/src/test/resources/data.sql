insert into authors (first_name, last_name, patronymic) values ('TestFirstName1', 'TestLastName1', 'TestPatronymic1');
insert into authors (first_name, last_name, patronymic) values ('TestFirstName2', 'TestLastName2', 'TestPatronymic2');
insert into authors (first_name, last_name) values ('TestFirstName3', 'TestLastName3');

insert into genres (name) values('TestGenre1');
insert into genres (name) values('TestGenre2');
insert into genres (name) values('TestGenre3');

insert into books (name, author_id, genre_id) values('TestBook1', 1, 1);
insert into books (name, author_id, genre_id) values('TestBook2', 2, 2);