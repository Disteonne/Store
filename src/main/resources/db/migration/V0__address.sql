CREATE TABLE address
(
    id       SERIAL PRIMARY KEY,
    country  VARCHAR(40) NOT NULL,
    city     VARCHAR(40) NOT NULL,
    street   VARCHAR(40) NOT NULL,
    building VARCHAR(10)
);