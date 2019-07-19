
create table user(
id int primary key auto_increment,
age int,
pass_word varchar
user_name varchar
);

create table note(
id int primary key auto_increment,
user_id int,
content text,
create_time timestamp  not NULL DEFAULT CURRENT_TIMESTAMP,
update_time timestamp  not NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


