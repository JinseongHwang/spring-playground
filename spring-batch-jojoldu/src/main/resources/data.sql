use spring_batch;

create table if not exists pay
(
    id           bigint not null auto_increment,
    amount       bigint,
    tx_name      varchar(255),
    tx_date_time datetime,
    primary key (id)
) engine = InnoDB;

create table if not exists pay2
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

create table if not exists teacher
(
    id           bigint not null auto_increment,
    name         varchar(255),
    subject      varchar(255),
    primary key (id)
) engine = InnoDB;

insert ignore into teacher (id, name, subject) values (1, '1선생님', '');
insert ignore into teacher (id, name, subject) values (2, '2선생님', '');
insert ignore into teacher (id, name, subject) values (3, '3선생님', '');
insert ignore into teacher (id, name, subject) values (4, '4선생님', '');
insert ignore into teacher (id, name, subject) values (5, '5선생님', '');
insert ignore into teacher (id, name, subject) values (6, '6선생님', '');
insert ignore into teacher (id, name, subject) values (7, '7선생님', '');
insert ignore into teacher (id, name, subject) values (8, '8선생님', '');
insert ignore into teacher (id, name, subject) values (9, '9선생님', '');
insert ignore into teacher (id, name, subject) values (10, '10선생님', '');

create table if not exists student
(
    id           bigint not null auto_increment,
    name         varchar(255),
    teacher_id   bigint,
    primary key (id)
) engine = InnoDB;

insert ignore into student (id, name, teacher_id) values (1, '1학생', 1);