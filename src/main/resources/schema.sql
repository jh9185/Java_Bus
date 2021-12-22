
CREATE TABLE tbl_login(
LoginId Long auto_increment,
id varchar (30) not null ,
password varchar (30) not null ,
primary key(LoginId)
);

CREATE TABLE tbl_board(
boardId Long auto_increment,
title varchar (30) not null ,
content varchar (30) not null ,
name varchar (30) not null ,
primary key(boardId)
);
