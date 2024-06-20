CREATE TABLE IF NOT EXISTS coin_repository.user_role
(
    id         UUID        NOT NULL PRIMARY KEY,
    user_email VARCHAR(64) REFERENCES coin_repository."user" (email),
    role       VARCHAR(64) NOT NULL
    );
