-- 创建数据库: wyjf_dev
create database wyjf_dev character set utf8 collate utf8_general_ci;


drop table if exists user;

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
  uid                  bigint not null auto_increment comment '会员ID',
  phone                varchar(16) comment '电话号码',
  nickname             varchar(32) comment '昵称',
  balance              decimal(19,2) not null default 0 comment '余额',
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
  head_thunb           longblob comment '头像'
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


