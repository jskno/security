\connect spring-sec-db

create table stdn.students (
    age integer not null,
    id bigint not null,
    email varchar(255),
    firstname varchar(255),
    lastname varchar(255),
    primary key (id)
);

create sequence stdn.students_seq start with 1 increment by 50;

create table sec.users (
    firstname varchar(255),
    lastname varchar(255),
    password varchar(255),
    u_id varchar(255) not null,
    username varchar(255),
    roles varchar(255) array,
    primary key (u_id)
);


