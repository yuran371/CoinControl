CREATE TABLE IF NOT EXISTS "deactivated_token"
(
    id         SERIAL    NOT NULL PRIMARY KEY,
    keep_until timestamp NOT NULL
);
