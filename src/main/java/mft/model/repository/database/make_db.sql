
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
    status  number(1)
);
create sequence customer_seq start with 1 increment by 1;

create table product_tbl
(
    id number primary key ,
    name   nvarchar2(50),
    brand nvarchar2(30),
    productSize nvarchar2(10),
    price number(10),
    description nvarchar2(100)
);
create sequence product_seq start with 1 increment by 1;

insert into product_tbl (id, name, brand, productSize, price, description)
values (product_seq.nextval,'macbook air MGN63','apple', '13.3 inch', 42789000, '256 G - M1 - MultiMedia');
insert into product_tbl (id, name, brand, productSize, price, description)
values (product_seq.nextval,'iphone13','apple', '6.1 inch',35000000,'128 G - 12MPix - IOS 15');
insert into product_tbl (id, name, brand, productSize, price, description)
values (product_seq.nextval,'bag-crossbody bag','zara','19*11*4cm',2070000,'polyester - cream - small - magnetic clasp');
insert into product_tbl (id, name, brand, productSize, price, description)
values (product_seq.nextval,'galaxy Z fold5','samsung','7.6',70499000,'512G - android 13 - 50MPix');
insert into product_tbl (id, name, brand, productSize, price, description)
values (product_seq.nextval,'shoes - air jordan 1retro high','nike','3.5-18 M',9000000,'men''s shoes jordan - yellow & white');
insert into product_tbl (id, name, brand, productSize, price, description)
values (product_seq.nextval,'galaxy watch6','samsung','44mm',9309800,'sports- silicon - circle');
insert into product_tbl (id, name, brand, productSize, price, description)
values (product_seq.nextval,'TV - 75X80K','sony','75 inch',73000000,'bluetooth - ultra HD - 4K ');
insert into product_tbl (id, name, brand, productSize, price, description)
values (product_seq.nextval,'magic mouse 2021 MK2E3ZM','apple','21*57mm',4050000,'bluetooth - accuracy range: 800 /1600');
insert into product_tbl (id, name, brand, productSize, price, description)
values (product_seq.nextval,'puffer anorak','zara','all sizes',4770000,'high collar with hood - long sleeves');
insert into product_tbl (id, name, brand, productSize, price, description)
values (product_seq.nextval,'airpod pro 2nd','apple','45mm',10049000,'bluetooth - resistant - 30 h');
insert into product_tbl (id, name, brand, productSize, price, description)
values (product_seq.nextval,'shoes - nike dunk low','nike','5-12 M',5750000,'women''s shoes- white and black');
insert into product_tbl (id, name, brand, productSize, price, description)
values (product_seq.nextval,'shoes - nike invincible 3','nike','5-12 M',9000000,'women''s shoes running - pink& blue& white');
insert into product_tbl (id, name, brand, productSize, price, description)
values (product_seq.nextval,'paper patch sweatshirt','zara','over size',2700000,'men - white - round neck and shory sleeves');
insert into product_tbl (id, name, brand, productSize, price, description)
values (product_seq.nextval,'shoes-nike dunk low retro','nike','6-13M',5750000,'men''s shoes life style-green&red&white');
insert into product_tbl (id, name, brand, productSize, price, description)
values (product_seq.nextval,'ساعت هوشمند Ultra 2','apple','49mm',40000000,'جنس بند سیلیکونی مناسب ورزش');
commit ;


create table orderDetails_tbl
(
    id number primary key ,
    customer_id references customer_tbl,
    products_id references product_tbl,
    quantity number(10),
    price number(20)
);
create sequence orderDetails_seq start with 1 increment by 1;

create table payment_tbl
(
    id number primary key ,
    customer_id references customer_tbl,
    totalcost  number(20),
    paymentdetails nvarchar2(50),
    type nvarchar2(20) not null ,
    order_type nvarchar2(10),
    paymentdate timestamp
);
create sequence payment_seq start with 1 increment by 1;

create table orders_tbl
(
    id number primary key ,
    customer_id references customer_tbl,
    amount number(20),
    discount number(20),
    order_type nvarchar2(10),
    orderdate timestamp
);
create sequence orders_seq start with 1 increment by 1;

create view orders_report as
select O.ID    as order_id,
       C.ID   as customer_id,
       C.NAME   as customer_name,
       C.FAMILY as customer_family,
       D.PRODUCTS_ID as products_id,
       O.AMOUNT as amount,
       O.DISCOUNT as discount,
       O.ORDERDATE
from orders_tbl O,
     customer_tbl C,
     orderDetails_tbl D
where O.customer_id = C.ID and
      D.customer_id = O.customer_id;


create view payment_report as
select P.ID    as payment_id,
       C.ID   as customer_id,
       C.NAME   as customer_name,
       C.FAMILY as customer_family,
       P.TOTALCOST as total_cost,
       P.TYPE as payment_type,
       P.PAYMENTDATE as payment_date
from payment_tbl P,
     customer_tbl C
where P.customer_id = C.ID;

create table log_tbl
(
    id number,
    class_name nvarchar2(50),
    log_type nvarchar2(50),
    data nvarchar2(50)
);
create sequence log_seq start with  1 increment by 1;

