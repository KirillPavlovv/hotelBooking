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

CREATE TABLE room_types
(
    id           NUMERIC PRIMARY KEY,
    description  VARCHAR(500),
    picture_name VARCHAR(30),
    price        DECIMAL(5, 2) NOT NULL
);

CREATE TABLE rooms
(
    number NUMERIC PRIMARY KEY,
    beds   NUMERIC REFERENCES room_types (id)
);

CREATE TABLE reservations
(
    id          UUID PRIMARY KEY,
    customer_id UUID REFERENCES customers (id),
    room        NUMERIC REFERENCES rooms (number),
    open        DATE NOT NULL,
    close       DATE NOT NULL
);


--changeset hotelbooking:202210081513

INSERT INTO customers (id, first_name, last_name, personal_code, email)
VALUES ('58fce113-5110-437c-b548-0750b01836f4', 'Kirill', 'Pavlov', '38808102124', 'kirillp1988@gmail.com');
INSERT INTO customers (id, first_name, last_name, personal_code, email)
VALUES ('4a92df85-a32d-44a1-bea0-e308bf13e2d8', 'Ergo', 'Tamm', '38608102124', 'ergotamm@gmail.com');
INSERT INTO customers (id, first_name, last_name, personal_code, email)
VALUES ('5e536974-54be-45fa-845f-33f38be7023a', 'Erki', 'Rei', '38809102124', 'erki@gmail.com');
INSERT INTO customers (id, first_name, last_name, personal_code, email)
VALUES ('b680108f-988d-4bcd-b2e5-33d91b84d0c0', 'Anna', 'Rebane', '47808102124', 'anna@gmail.com');

INSERT INTO room_types (id, description, picture_name, price) VALUES (1, 'One-bed room, shower, breakfast', '1.jpg', 35.00);
INSERT INTO room_types (id, description, picture_name, price) VALUES (2, 'Two-beds room, shower, breakfast', '2.jpg', 50.00);
INSERT INTO room_types (id, description, picture_name, price) VALUES (3, 'Three-beds room, Jakuzzi, breakfast', '3.jpg', 100.00);

INSERT INTO rooms (number, beds) VALUES (1, 1);
INSERT INTO rooms (number, beds) VALUES (2, 1);
INSERT INTO rooms (number, beds) VALUES (3, 1);
INSERT INTO rooms (number, beds) VALUES (4, 1);
INSERT INTO rooms (number, beds) VALUES (5, 1);
INSERT INTO rooms (number, beds) VALUES (6, 1);
INSERT INTO rooms (number, beds) VALUES (7, 1);
INSERT INTO rooms (number, beds) VALUES (8, 1);
INSERT INTO rooms (number, beds) VALUES (9, 1);
INSERT INTO rooms (number, beds) VALUES (10, 1);
INSERT INTO rooms (number, beds) VALUES (11, 2);
INSERT INTO rooms (number, beds) VALUES (12, 2);
INSERT INTO rooms (number, beds) VALUES (13, 2);
INSERT INTO rooms (number, beds) VALUES (14, 2);
INSERT INTO rooms (number, beds) VALUES (15, 2);
INSERT INTO rooms (number, beds) VALUES (16, 2);
INSERT INTO rooms (number, beds) VALUES (17, 2);
INSERT INTO rooms (number, beds) VALUES (18, 3);
INSERT INTO rooms (number, beds) VALUES (19, 3);
INSERT INTO rooms (number, beds) VALUES (20, 3);

INSERT INTO reservations (id, customer_id, room, open, close)
VALUES ('28d1111a-e42c-418f-9253-3c509839ce3c', '58fce113-5110-437c-b548-0750b01836f4', 1, '10-08-2022', '11-10-2022');
INSERT INTO reservations (id, customer_id, room, open, close)
VALUES ('563b6b99-a251-427b-924b-3f661efa7643', 'b680108f-988d-4bcd-b2e5-33d91b84d0c0', 19, '10-20-2022', '10-30-2022');
INSERT INTO reservations (id, customer_id, room, open, close)
VALUES ('bd8761ee-f9b2-4479-a413-a40ba29e2de0', '5e536974-54be-45fa-845f-33f38be7023a', 11, '11-17-2022', '10-31-2022');


