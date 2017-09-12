-- 创建数据库: wyjf_dev
create database wyjf_dev character set utf8 collate utf8_general_ci;

use wyjf_dev;

-- SECURITY: ADMIN ACCOUNT
DROP TABLE IF EXISTS admin;
CREATE TABLE admin (
  aid SERIAL,
  name VARCHAR(255) NOT NULL,
  password VARCHAR(255 ) NOT NULL,
  enabled BOOL DEFAULT true,
  primary key (AID)
) ENGINE=InnoDB;

insert into admin (name, password, enabled) values ('root','123',true);

drop table if exists user;

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
  uid                  bigint not null auto_increment comment '会员ID',
  phone                varchar(32) comment '电话号码',
  nickname             varchar(32) comment '昵称',
  balance              decimal(19,2) not null comment '余额',
  gender               char(1) comment '性别',
  password_login       varchar(64) comment '登陆密码',
  password_trade       varchar(64) comment '提现密码',
  primary key (uid)
) ENGINE=InnoDB;

alter table user comment '会员';



drop table if exists user_info;

/*==============================================================*/
/* Table: user_info                                             */
/*==============================================================*/
create table user_info
(
  uid                  bigint,
  head_thumb           longblob comment '头像'
);

alter table user_info comment '会员信息';



drop table if exists log_verifycode;

/*==============================================================*/
/* Table: log_verifycode                                        */
/*==============================================================*/
create table log_verifycode
(
  id                   bigint not null,
  phone                varchar(32),
  verifycode           varchar(16),
  create_time          datetime,
  status               bit,
  primary key (id)
) ENGINE=InnoDB;

alter table log_verifycode comment '验证码记录';


drop index idx_draw_day_seq on draw;

drop table if exists draw;

/*==============================================================*/
/* Table: draw                                                  */
/*==============================================================*/
create table draw
(
  did                  serial not null comment '盘口ID',
  draw_day             date comment '盘口日期',
  draw_seq             int comment '当天序号(1-5)',
  start_date           datetime not null comment '开始日期时间',
  end_date             datetime not null comment '结束时间',
  amount_up            decimal(19,2) not null default 0 comment '投涨总额',
  amount_down          decimal(19,2) not null default 0 comment '投跌总额',
  status               int not null default 0 comment '状态(0未结账，1已结账)',
  primary key (did)
) ENGINE=InnoDB;

alter table draw comment '盘口';

/*==============================================================*/
/* Index: idx_draw_day_seq                                      */
/*==============================================================*/
create unique index idx_draw_day_seq on draw
(
  draw_day,
  draw_seq
);



drop table if exists ticket;

/*==============================================================*/
/* Table: ticket                                                */
/*==============================================================*/
create table ticket
(
  tid                  bigint not null auto_increment comment '自增ID',
  sid                  char(18) not null default '' comment '票ID(15位时间戳+3位随机数)',
  uid                  bigint not null comment '会员ID',
  did                  char(9) not null comment '盘口ID',
  direction            int not null default 0 comment '方向（涨1、跌0）',
  amount               decimal(19,2) not null default 0 comment '金额',
  real_amount          decimal(19,2) not null default 0 comment '真实金额(有可能有虚拟数据)',
  status               int not null default 0 comment '状态(未结账0,已结账1)',
  primary key (tid)
) ENGINE=InnoDB;

alter table ticket comment '票';



