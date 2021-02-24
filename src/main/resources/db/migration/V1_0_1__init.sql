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

create table "orders"
(
    id        bigserial   not null
        constraint order_pk
            primary key,
    number    integer     not null,
    product_id integer     not null,
    status    varchar(50) not null
);

alter table "orders"
    owner to sa;

create unique index order_id_uindex
    on "orders" (id);

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

insert into product (name, stock, remark) values('戴森吹风机', 1, '好');