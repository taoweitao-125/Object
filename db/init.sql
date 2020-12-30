drop database if exists carsdata;
create database carsdata character set utf8mb4  ;
use carsdata;
drop table if exists driver;
create table driver(
    id int primary key auto_increment,
    username varchar (20) not null unique,
    password varchar (20) not null,
    name varchar(10) not null,
    age int not null ,
    sex varchar(4) not null ,
    id_card varchar(18) not null unique ,
    car_num varchar(12) not null unique
);

drop table if exists cars;
create table cars(
   id int primary key auto_increment,
   driver_id int,
   num varchar(15) not null unique ,
   color varchar(6) not null ,
   type varchar(8) not null ,
   capacity int not null ,
   message varchar(200),
   foreign key (driver_id) references driver(id)
);

insert into driver(username,password,name, age, sex, id_card, car_num) values ('wangxiaoer','123','王小二',18,'男','610423200212083215','121313565648');
insert into driver(username,password,name, age, sex, id_card, car_num) values ('wangerniu','123','王二牛',20,'男','610423200012303213','121313565899');
insert into driver(username,password,name, age, sex, id_card, car_num) values ('liutiedan','123','刘铁蛋',55,'男','610423196503083215','361556865648');
insert into driver(username,password,name, age, sex, id_card, car_num) values ('cuihua','123','翠花',26,'女','610423199412083214','256313565643');
insert into driver(username,password,name, age, sex, id_card, car_num) values ('qiuxaing','123','秋香',30,'女','610423199012086482','121313535698');


insert into cars(driver_id, num, color, type, capacity, message) values (1,'陕A 88888','红色','越野车',5,'即将年检');
insert into cars(driver_id, num, color, type, capacity, message) values (1,'陕D 66585','黑色','小轿车',4,null);
insert into cars(driver_id, num, color, type, capacity, message) values (3,'陕A 33632','蓝色','小轿车',4,null);
insert into cars(driver_id, num, color, type, capacity, message) values (4,'陕D 88923','白色','跑车',2,'新车上路');
insert into cars(driver_id, num, color, type, capacity, message) values (5,'陕A 82828','红色','小轿车',4,null);

select id, driver_id, num, color, type, capacity, message from cars where driver_id = ? ;