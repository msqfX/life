#用户信息表
create table user(
id int primary key auto_increment,
open_id varchar(50) NOT NULL COMMENT '微信openid、唯一',
nick_name varchar(30),
avatar_url varchar(500),
gender tinyint(1) COMMENT '性别、默认为微信提供的信息 0-未知，1-男性，2-女性',
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
birthday date NULL DEFAULT NULL COMMENT '出生日期、默认为空',
sex tinyint(4) NOT NULL COMMENT '性别、默认为微信提供的信息 0-未知，1-男性，2-女性',
person_sign varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个性签名、默认值为空',
bg_img int(10) UNSIGNED NOT NULL COMMENT '个人主页背景图，外键、系统背景图随机、不为空',
personal_page_visit_num int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '个人主页访问量，默认0',
create_time timestamp  not NULL DEFAULT CURRENT_TIMESTAMP,
update_time timestamp  not NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)charset utf8 collate utf8_general_ci;

create unique index user_open_id_index on user(open_id);



CREATE TABLE `user_info`  (
                              `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键、自增',
                              `open_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信openid、唯一',
                              `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'default_avatar' COMMENT '用户头像地址、默认为default_avatar,即为小程序端内置的默认头像',

                              `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称、默认微信昵称不为空',
                              `weixin_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信号，长度为6-20，字母开头、数字、下划线、减号组成，唯一、默认为空',
                              `birthday` date NULL DEFAULT NULL COMMENT '出生日期、默认为空',
                              `sex` tinyint(4) NOT NULL COMMENT '性别、默认为微信提供的信息 0-未知，1-男性，2-女性',

                              `person_sign` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个性签名、默认值为空',
                              `bg_img` int(10) UNSIGNED NOT NULL COMMENT '个人主页背景图，外键、系统背景图随机、不为空',
                              `personal_page_visit_num` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '个人主页访问量，默认0',
                               create_time timestamp  not NULL DEFAULT CURRENT_TIMESTAMP,
                               update_time timestamp  not NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                                  PRIMARY KEY (`id`) USING BTREE,
                              UNIQUE INDEX `openId`(`open_id`) USING BTREE,
                              UNIQUE INDEX `weixinNum`(`weixin_num`) USING BTREE,
                              INDEX `bgImgId`(`bg_img_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;



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


drop table water;

#账户流水表
create table water(
id bigint primary key auto_increment,
user_id int not null,
amount decimal not null default 0,
account_id bigint not null ,
change_type tinyint not null ,
consumption_type tinyint not null,
remark varchar(50),
create_time timestamp  not NULL DEFAULT CURRENT_TIMESTAMP,
update_time timestamp  not NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)charset utf8 collate utf8_general_ci;

create unique index water_user_id_index on water(user_id);
create unique index water_create_time_index on water(create_time);
create unique index water_update_time_index on water(update_time);
