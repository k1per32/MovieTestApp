-- liquibase formatted sql
-- changeset yuriy:1
CREATE SCHEMA if not exists my_db;
CREATE TABLE if not exists users
(
    id_users serial,
    email    varchar unique,
    username varchar unique,
    name     varchar,

    PRIMARY KEY (id_users)
);
-- changeset yuriy:2
CREATE TABLE if not exists movies
(
    id_movies serial ,
    name varchar,
    url  varchar,
    PRIMARY KEY (id_movies)
);
-- changeset yuriy:3
CREATE TABLE if not exists favourites
(
    id serial,
    id_users_fav integer,
    id_movies_fav integer,
    PRIMARY KEY (id),
    FOREIGN KEY (id_users_fav) REFERENCES users,
    FOREIGN KEY (id_movies_fav) REFERENCES movies
);

