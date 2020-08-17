SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS tbl_reply;
DROP TABLE IF EXISTS tbl_board;
DROP TABLE IF EXISTS tbl_message;
DROP TABLE IF EXISTS tbl_user;




/* Create Tables */

CREATE TABLE tbl_board
(
	bno int(11) NOT NULL AUTO_INCREMENT,
	title varchar(200) NOT NULL,
	content text,
	writer varchar(50) NOT NULL,
	regdate timestamp DEFAULT now() NOT NULL,
	viewcnt int(11) DEFAULT 0,
	PRIMARY KEY (bno)
);


CREATE TABLE tbl_message
(
	mid int(11) NOT NULL AUTO_INCREMENT,
	targetid varchar(50) NOT NULL,
	sender varchar(50) NOT NULL,
	message text NOT NULL,
	opendate timestamp,
	senddate timestamp DEFAULT now() NOT NULL,
	PRIMARY KEY (mid)
);


CREATE TABLE tbl_reply
(
	rno int NOT NULL AUTO_INCREMENT,
	replytext varchar(1000) NOT NULL,
	replyer varchar(50) NOT NULL,
	regdate timestamp DEFAULT now() NOT NULL,
	updatedate timestamp DEFAULT now() NOT NULL,
	bno int(11) NOT NULL,
	PRIMARY KEY (rno)
);


CREATE TABLE tbl_user
(
	uid varchar(50) NOT NULL,
	upw varchar(50) NOT NULL,
	uname varchar(100) NOT NULL,
	upoint int(11),
	PRIMARY KEY (uid)
);

create table tbl_attach
(
	fullName varchar(150) not null,
	bno int not null,
	regdate timestamp default now(),
	primary key(fullName)
);

alter table tbl_attach add constraint fk_board_attach
foreign key (bno) references tbl_board (bno);

/* Create Foreign Keys */

ALTER TABLE tbl_reply
	ADD FOREIGN KEY (bno)
	REFERENCES tbl_board (bno)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

alter table tbl_message add constraint fk_usertarget
foreign key (targetid) references tbl_user (uid);

alter table tbl_message add constraint fk_usersender
foreign key (sender) references tbl_user (uid);

alter table tbl_board add column replycnt int default 0;

insert into tbl_user(uid, upw, uname) values ('user00', 'user00', 'LEE');
insert into tbl_user(uid, upw, uname) values ('user01', 'user01', 'SON');
insert into tbl_user(uid, upw, uname) values ('user02', 'user02', 'KI');
insert into tbl_user(uid, upw, uname) values ('user03', 'user03', 'KWON');
insert into tbl_user(uid, upw, uname) values ('user10', 'user10', 'KIM');

select * from tbl_user;
select * from tbl_message;
select * from tbl_board;