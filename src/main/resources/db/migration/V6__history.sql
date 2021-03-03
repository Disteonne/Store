CREATE TABLE history
(
    id      SERIAL PRIMARY KEY,
    date    timestamp NOT NULL,
    info    jsonb                    NOT NULL,
    user_id integer REFERENCES users (id)
);
