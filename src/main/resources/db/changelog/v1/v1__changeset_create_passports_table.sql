--liquibase formatted sql
--changeset Klimovich:create_cars_table splitStatements:true endDelimiter:;

DROP TABLE IF EXISTS passports;

CREATE TABLE IF NOT EXISTS passports
(
    id               BIGINT PRIMARY KEY,
    number_of_passport BIGINT NOT NULL UNIQUE
);

