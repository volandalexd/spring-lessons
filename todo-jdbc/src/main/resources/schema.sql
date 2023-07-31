DROP TABLE IF EXISTS todo;
CREATE TABLE todo
(
    id varchar(36) not NULL primary key,
    description varchar(256) not NULL,
    created timestamp,
    modified timestamp,
    completed boolean
);