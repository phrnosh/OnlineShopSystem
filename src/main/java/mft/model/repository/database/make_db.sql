
create table user_tbl
(
    id       number primary key,
    username nvarchar2(20) UNIQUE ,
    password nvarchar2(20),
    status   number(1)
);
create sequence user_seq start with 1 increment by 1;

create table admin_tbl
(
    id     number primary key,
    user_id references user_tbl,
    name   nvarchar2(30),
    email nvarchar2(30)
);
create sequence admin_seq start with 1 increment by 1;

create table customer_tbl
(
    id     number primary key,
    user_id references user_tbl,
    name   nvarchar2(30),
    family nvarchar2(30),
    address nvarchar2(100),
    phoneNumber nchar(15) unique,
    email nvarchar2(50)
);
create sequence customer_seq start with 1 increment by 1;
