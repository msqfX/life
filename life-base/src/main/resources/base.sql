#用户信息表
create table user(
id int primary key auto_increment,
open_id varchar(50),
nick_name varchar(30),
avatar_url varchar(500),
gender tinyint(1),
country varchar(30),
province varchar(20),
city varchar(20),
language varchar(10),
pass_word varchar(50),
user_name varchar(30),
channel varchar(10),
brand varchar(20),
model varchar(20),
wx_language varchar(20),
system varchar(20),
platform varchar(20),
create_time timestamp  not NULL DEFAULT CURRENT_TIMESTAMP,
update_time timestamp  not NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)charset utf8 collate utf8_general_ci;

create unique index user_open_id_index on user(open_id);


#笔记
create table note(
id int primary key auto_increment,
user_id int,
content text,
create_time timestamp  not NULL DEFAULT CURRENT_TIMESTAMP,
update_time timestusableAmountamp  not NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)charset utf8 collate utf8_general_ci;

create unique index note_user_id_index on note(user_id);


#账户表
create table account(
id int primary key auto_increment,
user_id int not null ,
name varchar(20),
amount decimal default 0,
userd_amount decimal default 0,
usable_amount decimal default 0,
billing_day timestamp,
repayment_date timestamp ,
account_type tinyint not null ,
card_num varchar(15),
remark varchar(100),
changes decimal default 0,
create_time timestamp  not NULL DEFAULT CURRENT_TIMESTAMP,
update_time timestamp  not NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)charset utf8 collate utf8_general_ci;

create unique index account_user_id_index on account(user_id);
create unique index account_create_time_index on account(create_time);
create unique index account_update_time_index on account(update_time);

#流水表
CREATE TABLE `water` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `amount` decimal(10,0) DEFAULT '0',
  `account_type` tinyint(4) NOT NULL,
  `change_type` tinyint(4) NOT NULL,
  `consumption_type` tinyint(4) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `water_user_id_index` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create unique index water_user_id_index on account(user_id);
create unique index water_create_time_index on account(create_time);
create unique index water_update_time_index on account(update_time);


#账户流水表
create table water(
id int primary key auto_increment,
user_id int not null,
change decimal not null default 0,
account_type tinyint not null ,
change_type tinyint not null ,
consumption_type tinyint not null,
create_time timestamp  not NULL DEFAULT CURRENT_TIMESTAMP,
update_time timestamp  not NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)charset utf8 collate utf8_general_ci;

create unique index water_user_id_index on water(user_id);

