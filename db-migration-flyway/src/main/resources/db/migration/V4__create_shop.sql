
drop table if exists tbl_shop;

create table tbl_shop(
     shop_id bigint auto_increment,
     name varchar(255),
     primary key (shop_id)
);