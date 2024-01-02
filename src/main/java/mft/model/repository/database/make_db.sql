
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
create sequence customer_seq start with 1 increment by 1;

create table product_tbl
(
    id number primary key ,
    name   nvarchar2(30),
    brand nvarchar2(30),
    productSize number(5),
    price number(10),
    description nvarchar2(50)
);
create sequence product_seq start with 1 increment by 1;

create table orderDetails_tbl
(
    id number primary key ,
    products_id references product_tbl,
    quantity number(10)
);
create sequence orderDetails_seq start with 1 increment by 1;

create table payment_tbl
(
    id number primary key ,
    totalcost  number(20),
    paymentdetails nvarchar2(50),
    paymentdate timestamp
);
create sequence payment_seq start with 1 increment by 1;

create table orders_tbl
(
    id number primary key ,
    customer_id  references customer_tbl,
    items_id references orderDetails_tbl,
    payment_id references payment_tbl,
    amount number(10),
    discount number(5),
    orderdate timestamp
);
create sequence orders_seq start with 1 increment by 1;

create view orders_report as
select C.ID   as customer_id,
       C.NAME   as customer_name,
       C.FAMILY as customer_family,
       OD.ID     as item_id,
       P.NAME  as product_name,
       P.price as product_price,
       OD.quantity as quantity,
       PA.totalcost  as totalcost,
       O.ID    as order_id,
       O.discount as discount,
       O.orderdate
from orders_tbl O,
     customer_tbl C,
     orderDetails_tbl OD,
     payment_tbl PA,
     product_tbl P
where OD.products_id = P.ID
  and O.customer_id = C.ID
  and O.items_id = OD.ID
  and O.payment_id = PA.id;

create table log_tbl
(
    id number,
    class_name nvarchar2(50),
    log_type nvarchar2(50),
    data nvarchar2(50)
);
create sequence log_seq start with  1 increment by 1;