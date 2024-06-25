CREATE TABLE IF NOT EXISTS coin_repository.deactivated_token
(
    id         UUID PRIMARY KEY,
    keep_until timestamp NOT NULL
);
