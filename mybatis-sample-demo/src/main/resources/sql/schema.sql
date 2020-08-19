#mybatis 第一章
create database mybatis_sample_db default character set utf8 collate utf8_general_ci;

create table country(
id int not null auto_increment,
countryname varchar(255) null,
countrycode varchar(255) null,
primary key (id)
);

insert country (countryname,countrycode)
values ('中国','CN'),('美国','US'),('俄罗斯','RU'),
('英国','GB'),('法国','FR');

#mybatis 第二章
create table sys_user(
id bigint not null auto_increment comment '用户ID',
user_name varchar(50) comment '用户名',
user_password varchar(50) comment '密码',
user_email varchar(50) comment '邮箱',
user_info text comment '简介',
head_img blob comment '头像',
create_time datetime comment '创建时间',
primary key(id)
);
alter table sys_user comment '用户表';

create table sys_role(
id bigint not null auto_increment comment '角色ID',
role_name varchar(50) comment '角色名',
enabled int comment '有效标志',
create_by bigint comment '创建人',
create_time datetime comment '创建时间',
primary key (id)
);
alter table sys_role comment '角色表';

create table sys_privilege(
id bigint not null auto_increment comment '权限ID',
privilege_name varchar(50) comment '权限名称',
privilege_url varchar(200) comment '权限URL',
primary key(id)
);
alter table sys_privilege comment '权限表';

create table sys_user_role(
user_id bigint comment '用户ID',
role_id bigint comment '角色ID'
);
alter table sys_user_role comment '用户角色关联表';

create table sys_role_privilege(
role_id bigint comment '角色ID',
privilege_id bigint comment '权限ID'
);
alter table sys_role_privilege comment '角色关联表';

INSERT INTO sys_user VALUES (1,'admin','123456','admin@mybatis.tk','管理员',NULL,current_timestamp);
INSERT INTO sys_user VALUES (1001,'test','123456','test@mybatis.tk','测试用户',NULL,current_timestamp);

INSERT INTO sys_role VALUES (1,'管理员',1,1,current_timestamp);
INSERT INTO sys_role VALUES (2,'普通用户',1,1,current_timestamp);

INSERT INTO sys_user_role VALUES (1,1);
INSERT INTO sys_user_role VALUES (1,2);
INSERT INTO sys_user_role VALUES (1001,2);

INSERT INTO sys_privilege VALUES (1,'用户管理','/users');
INSERT INTO sys_privilege VALUES (2,'角色管理','/roles');
INSERT INTO sys_privilege VALUES (3,'系统日志','/logs');
INSERT INTO sys_privilege VALUES (4,'人员维护','/persons');
INSERT INTO sys_privilege VALUES (5,'单位维护','/companies');

INSERT INTO sys_role_privilege VALUES (1,1);
INSERT INTO sys_role_privilege VALUES (1,2);
INSERT INTO sys_role_privilege VALUES (1,3);
INSERT INTO sys_role_privilege VALUES (2,4);
INSERT INTO sys_role_privilege VALUES (2,5);

ALTER TABLE sys_user MODIFY COLUMN user_email VARCHAR ( 50 ) NULL DEFAULT 'test@mybatis.tk' COMMENT '邮箱' AFTER user_password;




