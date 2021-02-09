CREATE TABLE product
(
    id          SERIAL PRIMARY KEY,
    type        VARCHAR(30) NOT NULL,
    supplier_id integer REFERENCES supplier (id),
    info        VARCHAR     NOT NULL

);