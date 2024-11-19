--liquibase formatted sql
--changeset Klimovich:create-cars-table splitStatements:true endDelimiter:;

DROP TABLE IF EXISTS cars;

CREATE TABLE cars
(
    id    BIGSERIAL PRIMARY KEY,
    brand VARCHAR(30) NOT NULL
);

