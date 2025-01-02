\connect auth-server-db

CREATE SCHEMA IF NOT EXISTS auth;
SET search_path TO auth;

create table auth.clients (
    require_proof_key boolean not null,
    client_id varchar(255) unique,
    client_name varchar(255) unique,
    client_secret varchar(255),
    grant_type varchar(255),
    id varchar(255) not null,
    redirect_uri varchar(255) not null,
    token_format varchar(255),
    primary key (id)
)

