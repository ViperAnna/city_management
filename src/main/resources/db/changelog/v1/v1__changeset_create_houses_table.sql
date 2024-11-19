--liquibase formatted sql
--changeset Klimovich:create_houses_table splitStatements:true endDelimiter:;

-- DROP TABLE IF EXISTS houses;
-- DROP TYPE IF EXISTS address;
--
-- CREATE TYPE address AS
-- (
--     street          VARCHAR(30),
--     number_of_house BIGINT,
--     zip_code        BIGINT
-- );
--
-- CREATE TABLE IF NOT EXISTS houses
-- (
--     id      BIGINT PRIMARY KEY,
--     address address NOT NULL
-- );

DROP TABLE IF EXISTS houses;

CREATE TABLE IF NOT EXISTS houses
(
    id      BIGSERIAL PRIMARY KEY,
    street          VARCHAR(30),
    number_of_house INTEGER,
    zip_code        BIGSERIAL
);