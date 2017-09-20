/* 2.初始数据 */

-- password: 123
insert into admin (name, password, enabled) values ('root','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',true);

INSERT INTO `system_param` (`id`, `data_key`, `data_val`, `comment`, `sort_id`, `pid`) VALUES ('1', 'STR_SystemName', '无忧金融', '系统名称', '0', '0');
INSERT INTO `system_param` (`id`, `data_key`, `data_val`, `comment`, `sort_id`, `pid`) VALUES ('2', 'INT_BeforeBuyMins', '1', '开盘前多长时间内不可买票(分钟)', '0', '0');
INSERT INTO `system_param` (`id`, `data_key`, `data_val`, `comment`, `sort_id`, `pid`) VALUES ('3', 'INT_PlatformPercent', '20', '平台收益百分比(%)', '0', '0');
