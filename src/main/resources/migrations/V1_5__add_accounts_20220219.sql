DROP SEQUENCE IF EXISTS account_id_seq;
DROP SEQUENCE IF EXISTS currency_id_seq;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS currencies;

CREATE TABLE currencies
(
    id INT PRIMARY KEY,
    uuid UUID NOT NULL DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL ,
    short_name VARCHAR(10) NOT NULL,
    exchange_rate DECIMAL NOT NULL

);

CREATE TABLE accounts
(
    id INT PRIMARY KEY,
    uuid UUID NOT NULL DEFAULT gen_random_uuid(),
    type VARCHAR(100) NOT NULL ,
    balance DECIMAL DEFAULT 0.0,
    currency_id INT REFERENCES currencies(id),
    owner_id INT REFERENCES currencies(id)

);

CREATE SEQUENCE currency_id_seq INCREMENT 1 START 1 OWNED BY users.id;
CREATE SEQUENCE account_id_seq INCREMENT 1 START 1 OWNED BY users.id;

