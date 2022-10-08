--liquibase formatted sql
--changeset hotelbooking:202210081055

CREATE TABLE customers
(
    id            UUID PRIMARY KEY,
    first_name    VARCHAR(50) NOT NULL,
    last_name     VARCHAR(50) NOT NULL,
    personal_code VARCHAR(15) NOT NULL,
    email         VARCHAR(50) NOT NULL
);

CREATE TABLE rooms
(
    number VARCHAR(2) PRIMARY KEY,
    beds   NUMERIC       NOT NULL,
    price  DECIMAL(5, 2) NOT NULL

);

CREATE TABLE reservations
(
    id          UUID PRIMARY KEY,
    customer_id UUID REFERENCES customers (id),
    room        VARCHAR(2) REFERENCES rooms (number),
    open        DATE NOT NULL,
    close       DATE NOT NULL
);

