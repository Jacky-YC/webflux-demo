create table stu
(
    id   bigserial not null
        constraint stu_pk
            primary key,
    age  integer   not null,
    name varchar   not null
);

alter table stu
    owner to sa;

create unique index stu_id_uindex
    on stu (id);

create table "order"
(
    id        bigserial   not null
        constraint order_pk
            primary key,
    number    integer     not null,
    productId integer     not null,
    status    varchar(50) not null
);

alter table "order"
    owner to sa;

create unique index order_id_uindex
    on "order" (id);

create table product
(
    id     bigserial    not null
        constraint product_pk
            primary key,
    name   varchar(50)  not null,
    stock  integer      not null,
    remark varchar(100) not null
);

alter table product
    owner to sa;

create unique index product_id_uindex
    on product (id);
