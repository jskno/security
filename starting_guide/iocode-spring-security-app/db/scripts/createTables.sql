\connect spring-sec-db

create table stdn.students (
    age integer not null,
    id bigint not null,
    email varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    primary key (id)
)

create table sec.users (
    firstname varchar(255),
    lastname varchar(255),
    password varchar(255),
    u_id varchar(255) not null,
    username varchar(255),
    roles varchar(255) array,
    primary key (u_id)
)

