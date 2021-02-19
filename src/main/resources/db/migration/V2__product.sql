CREATE TABLE product
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR     NOT NULL,
    type        VARCHAR(30) NOT NULL,
    price       real        not null,
    count       integer     NOT NULL,
    info        VARCHAR     NOT NULL,
    supplier_id integer REFERENCES supplier (id)
);