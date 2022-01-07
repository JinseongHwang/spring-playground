
drop table if exists tbl_user;

create table tbl_user(
    user_id bigint auto_increment,
    name varchar(255),
    gpa double,
    primary key (user_id)
);