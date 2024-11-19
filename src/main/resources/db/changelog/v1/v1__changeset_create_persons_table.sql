--liquibase formatted sql
--changeset Klimovich:create_persons_table splitStatements:true endDelimiter:;

-- DROP TYPE IF EXISTS gender CASCADE;
DROP TABLE IF EXISTS persons;

-- CREATE TYPE gender AS ENUM ('male', 'female');

-- CREATE TABLE IF NOT EXISTS persons
-- (
--     id      BIGINT PRIMARY KEY,
--     name    VARCHAR(30) NOT NULL,
--     surname VARCHAR(30) NOT NULL,
--     gender  gender
-- );

CREATE TABLE IF NOT EXISTS persons
(
    id      BIGINT PRIMARY KEY,
    name    VARCHAR(30) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    gender  VARCHAR(30)
);

