CREATE TABLE supplier
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR NOT NULL,
    mail       VARCHAR NOT NULL,
    address_id integer REFERENCES address (id),
    CONSTRAINT constraint_name UNIQUE (name)
);