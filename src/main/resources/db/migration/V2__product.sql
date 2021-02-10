CREATE TABLE product
(
    id           SERIAL PRIMARY KEY,
    name_product VARCHAR     NOT NULL,
    type         VARCHAR(30) NOT NULL,
    price        real        not null,
    count        integer     NOT NULL,
    supplier_id  integer REFERENCES supplier (id),
    info         VARCHAR     NOT NULL

);