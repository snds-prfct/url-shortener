

CREATE SCHEMA url_shortener;

CREATE TABLE url_shortener.short_urls (
    short_url varchar PRIMARY KEY,
    long_url varchar NOT NULL,
    created_at timestamp NOT NULL DEFAULT NOW()
);
