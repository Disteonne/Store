CREATE TABLE users_role
(
    id SERIAL PRIMARY KEY,
    user_id integer NOT NULL  references users (id),
    name VARCHAR(10)

);