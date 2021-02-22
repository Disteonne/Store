CREATE TABLE users
(
    id        SERIAL PRIMARY KEY,
    surname   VARCHAR(30) NOT NULL,
    name      VARCHAR(30) NOT NULL,
    age       integer     NOT NULL,
    login     VARCHAR     NOT NULL,
    password  VARCHAR     NOT NULL,
    mail      VARCHAR     NOT NULL,
    address_id integer REFERENCES address (id)
);