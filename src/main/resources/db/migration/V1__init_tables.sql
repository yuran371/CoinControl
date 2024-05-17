CREATE SCHEMA coin_repository;
SET SEARCH_PATH = "coin_repository";
CREATE TABLE IF NOT EXISTS "user"
(
    id          UUID               NOT NULL PRIMARY KEY,
    email       VARCHAR(64) UNIQUE NOT NULL,
    password    VARCHAR(64) UNIQUE NOT NULL,
    avatar_path VARCHAR(128)       NOT NULL
);

CREATE TABLE IF NOT EXISTS wallet
(
    id      UUID           NOT NULL PRIMARY KEY,
    user_id UUID REFERENCES "user" (id),
    name    VARCHAR(64)    NOT NULL,
    balance DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS transaction
(
    id               UUID           NOT NULL PRIMARY KEY,
    wallet_id        UUID REFERENCES wallet (id),
    amount           DECIMAL(10, 2) NOT NULL,
    date             TIMESTAMPTZ    NOT NULL,
    transaction_type VARCHAR(16)    NOT NULL,
    category         VARCHAR(128)   NOT NULL
);
