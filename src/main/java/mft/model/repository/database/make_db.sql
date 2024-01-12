
create table customer_tbl
(
    id     number primary key,
    name   nvarchar2(30),
    family nvarchar2(30),
    username nvarchar2(20) UNIQUE ,
    password nvarchar2(20),
    address nvarchar2(100),
    phoneNumber nchar(15) UNIQUE,
    email nvarchar2(50),
    status   number(1)
);
create sequence customer_seq start with 2 increment by 1;

create table product_tbl
(
    id number primary key ,
    name   nvarchar2(30),
    brand nvarchar2(30),
    productSize nvarchar2(10),
    price number(10),
    description nvarchar2(50)
);
create sequence product_seq start with 1 increment by 1;

create table orderDetails_tbl
(
    id number primary key ,
    products_name references product_tbl,
    quantity number(10),
    price number(20),
    invoice_id references orders_tbl
);
create sequence orderDetails_seq start with 1 increment by 1;

create table payment_tbl
(
    id number primary key ,
    totalcost  number(20),
    paymentdetails nvarchar2(50),
    type nvarchar2(20),
    paymentdate timestamp
);
create sequence payment_seq start with 1 increment by 1;

create table orders_tbl
(
    id number primary key ,
    customer_id  references customer_tbl,
    payment_id references payment_tbl,
    amount number(20),
    discount number(20),
    orderdate timestamp
);
create sequence orders_seq start with 1 increment by 1;

create view orders_report as
select O.ID    as order_id,
       C.ID   as customer_id,
       C.NAME   as customer_name,
       C.FAMILY as customer_family,
       PA.totalcost  as totalcost,
       O.discount as discount,
       O.orderdate
from orders_tbl O,
     customer_tbl C,
     payment_tbl PA
where O.customer_id = C.ID
  and O.payment_id = PA.id;

create table log_tbl
(
    id number,
    class_name nvarchar2(50),
    log_type nvarchar2(50),
    data nvarchar2(50)
);
create sequence log_seq start with  1 increment by 1;

