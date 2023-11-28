create table IF NOT EXISTS feed_item
(
    creator      varchar(255) not null,
    title        varchar(255) not null,
    type         varchar(255) not null,
    description  varchar(4096),
    link         varchar(255),
    publish_date varchar(255),
    thumbnail    varchar(255),
    primary key (creator, title, type)
);

alter table feed_item
    owner to admin;