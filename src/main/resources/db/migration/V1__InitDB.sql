alter table _verifications drop foreign key FK_verify_temp_user;
drop table if exists _verifications;
drop table if exists hibernate_sequence;
drop table if exists temp_users_table;
drop table if exists users;

create table _verifications
(
    id      bigint not null,
    code    varchar(255),
    user_id bigint,
    primary key (id)
) engine = InnoDB;

create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB;

insert into hibernate_sequence
values (1);

create table temp_users_table
(
    id       bigint not null,
    email    varchar(255),
    login    varchar(255),
    password varchar(255),
    primary key (id)
) engine = InnoDB;

create table users
(
    id        varchar(255) not null,
    confirmed bit          not null,
    email     varchar(255),
    lastname  varchar(255),
    login     varchar(255),
    name      varchar(255),
    password  varchar(255),
    role      varchar(255),
    primary key (id)
) engine = InnoDB;

alter table _verifications
    add constraint UK_verify_code unique (code);

alter table users
    add constraint UK_user_email unique (email);

alter table _verifications
    add constraint FK_verify_temp_user foreign key (user_id) references temp_users_table (id);