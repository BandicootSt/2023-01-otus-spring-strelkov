create table authors (
    id bigint auto_increment,
    first_name varchar2(100) not null,
    last_name varchar2(100) not null,
    patronymic varchar2(100),
    primary key (id),
    constraint authors_unique_name unique (first_name, last_name, patronymic)
);

create table genres (
    id bigint auto_increment,
    name varchar2(100) not null,
    primary key (id),
    constraint genres_unique_name unique (name)
);

create table books (
    id bigint auto_increment,
    name varchar2(100) not null,
    author_id number not null,
    genre_id number not null,
    primary key (id),
    foreign key (author_id) references authors (id),
    foreign key (genre_id) references genres (id),
    constraint books_unique_name unique (name)
);