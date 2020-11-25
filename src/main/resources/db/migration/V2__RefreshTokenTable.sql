drop table if exists refresh_jwt_tokens;

create table refresh_jwt_tokens(
    id      bigint not null,
    jws     varchar(255),
    user_id varchar(255),
    primary key (id)
) engine = InnoDB;

alter table refresh_jwt_tokens add constraint FK6yo4u46yx9h6o1fblkc26lxn foreign key (user_id) references users (id);