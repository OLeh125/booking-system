DROP SEQUENCE IF EXISTS user_id_seq;
DROP SEQUENCE IF EXISTS event_id_seq;
DROP SEQUENCE IF EXISTS order_id_seq;
DROP SEQUENCE IF EXISTS ticket_id_seq;
DROP TABLE IF EXISTS orders_tickets;
DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS users;


CREATE EXTENSION IF NOT EXISTS pgcrypto;-- to gen_random_uuid works

CREATE TABLE users
(
    id INT PRIMARY KEY,
    uuid UUID NOT NULL DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL ,
    surname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    status VARCHAR(20) DEFAULT 'ACTIVE'
);

CREATE SEQUENCE user_id_seq INCREMENT 1 START 1 OWNED BY users.id;

CREATE TABLE events(
                       id INT PRIMARY KEY,
                       uuid UUID NOT NULL DEFAULT gen_random_uuid(),
                       place VARCHAR(200) NOT NULL,
                       date TIMESTAMP NOT NULL,
                       description VARCHAR (500),
                       status VARCHAR (20) DEFAULT 'PLANNED',
                       tickets_number INT NOT NULL
);

CREATE SEQUENCE event_id_seq INCREMENT 1 START 1 OWNED BY events.id;

CREATE TABLE orders (
                        id INT PRIMARY KEY,
                        uuid UUID NOT NULL DEFAULT gen_random_uuid(),
                        created_at TIMESTAMP DEFAULT NOW(),
                        client_id INT REFERENCES users(id)
);

CREATE SEQUENCE order_id_seq INCREMENT 1 START 1 OWNED BY orders.id;

CREATE TABLE tickets
(
    id INT PRIMARY KEY,
    uuid UUID NOT NULL DEFAULT gen_random_uuid(),
    number INT NOT NULL,
    additional_inf VARCHAR(1000),
    status VARCHAR (20) DEFAULT 'AVAILABLE',
    seat VARCHAR (100),
    price DECIMAL NOT NULL,
    event_id INT REFERENCES events(id) ON DELETE CASCADE
);

CREATE SEQUENCE ticket_id_seq INCREMENT 1 START 1 OWNED BY tickets.id;

CREATE TABLE orders_tickets(
    order_id INT REFERENCES orders(id),
    tickets_id INT REFERENCES tickets(id) UNIQUE,
    UNIQUE (order_id, tickets_id)
)