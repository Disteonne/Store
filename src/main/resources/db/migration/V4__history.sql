CREATE TABLE history
(
    id   SERIAL PRIMARY KEY,
    date timestamp with time zone NOT NULL,
    info VARCHAR NOT NULL
);
