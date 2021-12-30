
CREATE TABLE tbl_busrouteinfo(
number Long auto_increment,
busRouteId Long not null default 0,
busRouteNm Long not null default 0,
corpNm varchar (30) not null default 0,
edStationNm varchar (30) not null default 0,
firstBusTm Long not null default 0,
firstLowTm Long not null default 0,
lastBusTm Long not null default 0,
lastBusYn Long not null default 0,
lastLowTm Long not null default 0,
length Long not null default 0,
routeType Long not null default 0,
stStationNm varchar (30) not null default 0,
term Long not null default 0,
primary  key(busRouteId)
);

CREATE TABLE tbl_member(
membernumber Long auto_increment,
id varchar (30) not null ,
password varchar (30) not null ,
primary key(membernumber)
);

CREATE TABLE tbl_board(
boardId Long auto_increment,
title varchar (30) not null ,
content varchar (30) not null ,
name varchar (30) not null ,
read varchar (30) not null ,
regdate timestamp default now(),
updatedate timestamp default now(),
primary key(boardId)
);
