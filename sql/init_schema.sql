/* 1.初始数据库结构 */

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
  token                varchar(64) comment '用户token',
  token_time           datetime    comment 'token有效期'
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



drop table if exists draw_result;

/*==============================================================*/
/* Table: draw_result                                           */
/*==============================================================*/
create table draw_result
(
  did                  bigint unsigned not null comment '盘口ID',
  start_price          decimal(19,2) not null comment '开始指数',
  end_price            decimal(19,2) not null comment '结束指数',
  result               int not null comment '1涨,0跌',
  platform_percent     decimal(19,2) not null comment '平台收益(百分比)',
  platform_amount      decimal(19,2) not null comment '平台收益金额',
  prize_amount         decimal(19,2) not null comment '用户剩余奖池(中奖方总额 - 平台收益金额)',
  process_time         datetime not null comment '结账时间',
  primary key (did)
) ENGINE=InnoDB;

alter table draw_result comment '盘口结果表';




drop table if exists ticket;

/*==============================================================*/
/* Table: ticket                                                */
/*==============================================================*/
create table ticket
(
  tid                  bigint not null auto_increment comment '自增ID',
  sid                  varchar(18) not null default '' comment '票流水号(15位时间戳+3位随机数)',
  uid                  bigint not null comment '会员ID',
  did                  bigint not null comment '盘口ID',
  direction            int not null default 0 comment '方向（涨1、跌0）',
  amount               decimal(19,2) not null default 0 comment '金额',
  real_amount          decimal(19,2) not null default 0 comment '真实金额(有可能有虚拟数据)',
  buy_time             datetime not null comment '购买时间',
  status               int not null default 0 comment '状态(未结账0,已结账1)',
  primary key (tid)
) ENGINE=InnoDB;

alter table ticket comment '票';



drop table if exists ticket_result_win;

/*==============================================================*/
/* Table: ticket_result_win                                     */
/*==============================================================*/
create table ticket_result_win
(
  tid                  bigint  not null comment '票ID',
  did                  bigint unsigned not null comment '盘口ID',
  win_percent          decimal(19,6) not null comment '中奖百分比(该票金额/赢方总额)',
  win_amount           decimal(19,2) not null comment '中奖金额',
  primary key (tid)
) ENGINE=InnoDB;

alter table ticket_result_win comment '票结算表(只含中奖票)';






drop index idx_log_balance_uid_type on log_balance;

drop table if exists log_balance;

/*==============================================================*/
/* Table: log_balance                                           */
/*==============================================================*/
create table log_balance
(
  lid                  serial not null,
  uid                  bigint not null comment '用户ID',
  amount               decimal(19,2) not null comment '变动金额',
  type                 int not null comment '0云投记录，1充值记录，2提款记录',
  tag                  int comment '子类型标识(1-5下注盘口类型, 6返现, 7中奖)',
  ref_id               bigint comment '引用记录ID',
  log_time             datetime not null comment '操作日志时间',
  primary key (lid)
) ENGINE=InnoDB;

alter table log_balance comment '用户余额日志表';

/*==============================================================*/
/* Index: idx_log_balance_uid_type                              */
/*==============================================================*/
create index idx_log_balance_uid_type on log_balance
(
  uid,
  type
);



/*==============================================================*/
/* Table: ticket        充值订单表                               */
/*==============================================================*/
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `order_number`    varchar(32) DEFAULT NULL COMMENT '订单编号',
  `order_status`    int(1) DEFAULT 0 COMMENT '订单状态0未支付，1已支付',
  `uid`             bigint(20) DEFAULT NULL COMMENT '会员ID',
  `order_moeny`     decimal(19,2) DEFAULT NULL COMMENT '支付金额',
  `order_type`      int(1) DEFAULT 0 COMMENT '订单类型0微信，1支付宝',
  `create_time`     datetime DEFAULT NULL COMMENT '创建时间',
  `pay_time`        datetime DEFAULT NULL COMMENT '支付时间',
  `remark`          varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: withdraw        提现申请表                               */
/*==============================================================*/
DROP TABLE IF EXISTS `withdraw`;
CREATE TABLE `withdraw` (
  `id`                  bigint(20) NOT NULL AUTO_INCREMENT,
  `uid`                 bigint(20) NOT NULL COMMENT '用户ID',
  `bcid`                bigint(20) DEFAULT NULL COMMENT '用户ID',
  `money`               decimal(19,2) DEFAULT NULL COMMENT '提现金额',
  `create_time`         datetime DEFAULT NULL COMMENT '提交时间',
  `handing_time`        datetime DEFAULT NULL COMMENT '处理时间',
  `status`              int(1) DEFAULT NULL COMMENT '状态（0未审核，1审核成功，2审核失败）',
  `remark`              varchar(256) DEFAULT '' COMMENT '审核不成功备注',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

