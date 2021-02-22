CREATE TABLE history
(
    id      SERIAL PRIMARY KEY,
    date    timestamp with time zone NOT NULL,
    info    jsonb                    NOT NULL,
    user_id integer REFERENCES users (id)
);