use spring_batch;

create table if not exists pay
(
    id           bigint not null auto_increment,
    amount       bigint,
    tx_name      varchar(255),
    tx_date_time datetime,
    primary key (id)
) engine = InnoDB;

insert ignore into pay (id, amount, tx_name, tx_date_time) VALUES (1, 1000, 'trade1', '2018-09-10 00:00:00');
insert ignore into pay (id, amount, tx_name, tx_date_time) VALUES (2, 2000, 'trade2', '2018-09-10 00:00:00');
insert ignore into pay (id, amount, tx_name, tx_date_time) VALUES (3, 3000, 'trade3', '2018-09-10 00:00:00');
insert ignore into pay (id, amount, tx_name, tx_date_time) VALUES (4, 4000, 'trade4', '2018-09-10 00:00:00');
