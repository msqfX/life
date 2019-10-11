/*
 Navicat MySQL Data Transfer

 Source Server         : ali-mysql-life
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : rm-2ze78zkoz01w306w6wo.mysql.rds.aliyuncs.com
 Source Database       : life

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : utf-8

 Date: 10/11/2019 18:36:46 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `admin_info`
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '管理员信息id,主键，递增',
  `admin_name` varchar(15) NOT NULL COMMENT '管理员登录名，唯一索引，15字符，字母、数字、下划线、破折号组成',
  `password` varchar(128) NOT NULL COMMENT '加密后的密码串',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `admin_name` (`admin_name`) USING BTREE COMMENT '登录名，唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `diary_comment`
-- ----------------------------
DROP TABLE IF EXISTS `diary_comment`;
CREATE TABLE `diary_comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `diary_id` int(11) unsigned NOT NULL,
  `pid` int(11) NOT NULL DEFAULT '0',
  `reviewer_id` int(11) unsigned NOT NULL,
  `respondent_id` int(11) unsigned NOT NULL,
  `text_comment` varchar(500) NOT NULL,
  `sound_comment` varchar(255) NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `reviewer_id` (`reviewer_id`) USING BTREE,
  KEY `respondent_id` (`respondent_id`) USING BTREE,
  KEY `diary_comment_fk_diary_id` (`diary_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records of `diary_comment`
-- ----------------------------
BEGIN;
INSERT INTO `diary_comment` VALUES ('117', '240', '0', '100000', '100000', '坚持就是胜利', '', '2019-10-07 17:34:42', '2019-10-08 11:20:20'), ('118', '240', '0', '100000', '100000', 'he', '', '2019-10-10 23:31:54', '2019-10-10 23:31:54'), ('119', '241', '0', '100000', '100000', '第二天吗', '', '2019-10-11 16:44:09', '2019-10-11 16:44:09'), ('120', '240', '0', '100000', '100000', '哈哈哈', '', '2019-10-11 16:44:37', '2019-10-11 16:44:37');
COMMIT;

-- ----------------------------
--  Table structure for `diary_like`
-- ----------------------------
DROP TABLE IF EXISTS `diary_like`;
CREATE TABLE `diary_like` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '点赞记录id, 主键、自增、无符号',
  `diary_id` int(11) unsigned NOT NULL COMMENT '所属日记记录id,外键、无符号',
  `liked_user_id` int(11) unsigned NOT NULL COMMENT '被点赞用户id,外键、无符号',
  `admirer_id` int(11) unsigned NOT NULL COMMENT '点赞用户id,外键、无符号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `diary_like_fk_diary_id` (`diary_id`) USING BTREE,
  KEY `liked_user_id` (`liked_user_id`) USING BTREE,
  KEY `admirer_id` (`admirer_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records of `diary_like`
-- ----------------------------
BEGIN;
INSERT INTO `diary_like` VALUES ('163', '241', '100000', '100000', '2019-10-09 22:41:02', '2019-10-09 22:41:02'), ('165', '240', '100000', '100000', '2019-10-10 23:57:21', '2019-10-10 23:57:21');
COMMIT;

-- ----------------------------
--  Table structure for `diary_resource`
-- ----------------------------
DROP TABLE IF EXISTS `diary_resource`;
CREATE TABLE `diary_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '资源路径记录id,主键自增、无符号',
  `diary_id` int(11) unsigned NOT NULL COMMENT '资源所属的打卡日记记录id,不能为空，打卡日记记录外键，无符号',
  `resource_url` varchar(255) NOT NULL DEFAULT '' COMMENT '资源存储相对路径,默认空字符串',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '资源类型, 不能为空，默认为1--图片  2--音频 3--视频',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `diary_id` (`diary_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `new_punch_card_project`
-- ----------------------------
DROP TABLE IF EXISTS `new_punch_card_project`;
CREATE TABLE `new_punch_card_project` (
  `id` int(10) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `personal_page_visit_record`
-- ----------------------------
DROP TABLE IF EXISTS `personal_page_visit_record`;
CREATE TABLE `personal_page_visit_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键自增，无符号',
  `visitor_id` int(11) unsigned NOT NULL COMMENT '无符号，访问者用户id，外键',
  `visited_id` int(11) unsigned NOT NULL COMMENT '无符号，被访问者用户id，外键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `visitor_id` (`visitor_id`) USING BTREE,
  KEY `visited_id` (`visited_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `pro_intr_detail_info`
-- ----------------------------
DROP TABLE IF EXISTS `pro_intr_detail_info`;
CREATE TABLE `pro_intr_detail_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '圈子简介详情信息标识id，主键、自增',
  `project_id` int(10) unsigned NOT NULL COMMENT '所属圈子id，外键',
  `content` varchar(512) NOT NULL COMMENT 'type为1存储文本、 2--存储图片url、3--音频文件url、4--视频文件url',
  `type` tinyint(1) NOT NULL COMMENT ' 简介内容类型，1--文字、2--图片，3--录音，4--视频',
  `sort` tinyint(1) NOT NULL COMMENT '当前简介内容处于对应圈子简介的位置顺序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `project_id` (`project_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records of `pro_intr_detail_info`
-- ----------------------------
BEGIN;
INSERT INTO `pro_intr_detail_info` VALUES ('183', '861', '早睡早起', '1', '0', '2019-10-07 17:33:13', '2019-10-07 17:33:13'), ('184', '861', 'http://upload.dliony.com/8d05bbd98e62471d8f6a4ab7b382729c', '2', '1', '2019-10-07 17:33:15', '2019-10-07 17:33:15'), ('185', '862', '一起阅读吧', '1', '0', '2019-10-11 16:45:32', '2019-10-11 16:45:32'), ('186', '862', 'http://upload.dliony.com/b64bebeec3c04e40914964206f334543', '2', '1', '2019-10-11 16:45:32', '2019-10-11 16:45:32');
COMMIT;

-- ----------------------------
--  Table structure for `project_type_label`
-- ----------------------------
DROP TABLE IF EXISTS `project_type_label`;
CREATE TABLE `project_type_label` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '圈子类型标签标识id',
  `parent_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '父级分类id，默认为0--一级分类',
  `label_name` varchar(15) NOT NULL COMMENT '类型标签名，唯一索引，15字符内',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `label_name` (`label_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records of `project_type_label`
-- ----------------------------
BEGIN;
INSERT INTO `project_type_label` VALUES ('1', '0', '外语', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('2', '0', '阅读', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('3', '0', '运动', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('4', '0', '艺术兴趣', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('5', '0', '亲子幼教', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('6', '0', '教育', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('7', '0', '职场', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('8', '0', '健康生活', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('9', '0', '美妆', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('10', '1', '英语', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('11', '1', '日语', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('12', '1', '托福', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('13', '1', '雅思', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('14', '1', '韩语', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('15', '2', '读书', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('16', '2', '朗读', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('17', '3', '健身', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('18', '3', '跑步', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('19', '3', '减脂', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('20', '3', '瑜伽', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('21', '4', '绘画', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('22', '4', '书法', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('23', '4', '声乐', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('24', '4', '摄影', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('25', '4', '手工', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('26', '5', '孕产', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('27', '5', '育儿', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('28', '5', 'steam', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('29', '6', '小学', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('31', '6', '初中', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('32', '6', '高中', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('33', '6', '公务员', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('34', '6', '考研', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('35', '7', '求职', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('36', '7', '办公培训', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('37', '8', '早起早睡', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('38', '8', '养生', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('39', '8', '心理学', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('40', '9', '护肤', '2019-09-14 12:15:07', '2019-09-14 12:15:07'), ('41', '9', '化妆', '2019-09-14 12:15:07', '2019-09-14 12:15:07');
COMMIT;

-- ----------------------------
--  Table structure for `punch_card_diary`
-- ----------------------------
DROP TABLE IF EXISTS `punch_card_diary`;
CREATE TABLE `punch_card_diary` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '打卡日记记录编号,主键自增、无符号',
  `project_id` int(11) unsigned NOT NULL COMMENT '所属圈子的圈子编号,外键、无符号、不能为空',
  `user_id` int(11) unsigned NOT NULL COMMENT '所属用户的用户编号,外键、无符号、不能为空',
  `text_content` varchar(512) NOT NULL DEFAULT '' COMMENT '打卡日记文本内容, 不能为空，默认为空字符串，限制512',
  `punch_card_time` datetime NOT NULL COMMENT '打卡时间(年-月-日 时:分),不能为空',
  `punch_card_address` varchar(255) NOT NULL COMMENT '打卡地点名称，不能为空、默认为空字符串',
  `address_longitude` double(10,6) NOT NULL DEFAULT '0.000000' COMMENT '打卡地点对应经度,不能为空,默认为 0',
  `address_latitude` double(10,6) NOT NULL DEFAULT '0.000000' COMMENT '打卡地点对应纬度,不能为空,默认为 0',
  `visible_type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '该条打卡日记的可见类型,默认0--公开 其他圈子成员可见  1--仅圈主可见',
  `curr_diary_punch_card_day_num` int(11) NOT NULL DEFAULT '0' COMMENT '当前日记的已坚持打卡天数(可不连续)，不能为空，默认为0 ',
  `have_sticky` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否置顶 ,默认为0--不置顶 1--置顶 ',
  `is_repair_diary` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否为补打卡日记,默认为0--非补打卡日记  1--补打卡日记',
  `repair_punch_card_time` datetime NOT NULL COMMENT '补打卡时间，(年-月-日 时:分)',
  `like_user_num` int(11) NOT NULL DEFAULT '0' COMMENT '点赞人数，默认为0',
  `comment_num` int(11) NOT NULL DEFAULT '0' COMMENT '评论数，默认为0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `project_id` (`project_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=243 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records of `punch_card_diary`
-- ----------------------------
BEGIN;
INSERT INTO `punch_card_diary` VALUES ('240', '861', '100000', '打卡第一天，嘿嘿', '2019-10-07 17:34:19', '玉江佳园', '116.582553', '39.800338', '0', '1', '0', '0', '2019-10-07 17:34:19', '1', '3', '2019-10-07 17:34:18', '2019-10-11 16:44:37'), ('241', '861', '100000', '打卡第二天', '2019-10-09 22:00:03', '', '0.000000', '0.000000', '0', '2', '0', '0', '2019-10-09 22:00:03', '1', '1', '2019-10-09 22:00:02', '2019-10-11 16:44:09'), ('242', '862', '100000', '哈哈', '2019-10-11 16:45:48', '', '0.000000', '0.000000', '0', '1', '0', '0', '2019-10-11 16:45:48', '0', '0', '2019-10-11 16:45:47', '2019-10-11 16:45:47');
COMMIT;

-- ----------------------------
--  Table structure for `punch_card_project`
-- ----------------------------
DROP TABLE IF EXISTS `punch_card_project`;
CREATE TABLE `punch_card_project` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键、自增、圈子编号、唯一标识',
  `project_name` varchar(15) NOT NULL COMMENT '圈子名称',
  `privacy_type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '圈子私密类型，0--公开（默认），1--私密',
  `open_audit` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否开启审核，0--不开启（默认），1--开启',
  `type_label` varchar(64) NOT NULL COMMENT '圈子类型标签，存储标签名字符串，以逗号隔离',
  `cover_img_url` varchar(255) NOT NULL DEFAULT 'default_cover_img' COMMENT '圈子封面图，圈子创建时默认为小程序端内置的一张图片,标识符为default_cover_img',
  `creator_id` int(10) unsigned NOT NULL COMMENT '外键，圈主的用户id',
  `creator_introduce` varchar(50) DEFAULT '' COMMENT '圈主简介，50字',
  `weixin_num` varchar(20) DEFAULT '' COMMENT '默认为空，长度为6-20，由字母、数字、下划线、减号组成，字母开头',
  `all_punch_card_num` int(11) NOT NULL DEFAULT '0' COMMENT '圈子总打卡数 默认0',
  `today_punch_card_num` int(11) NOT NULL DEFAULT '0' COMMENT '圈子今日打卡数 默认0',
  `attend_user_num` int(11) NOT NULL DEFAULT '1' COMMENT '默认为1 在创建的时候创建者已加入',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `creator_id` (`creator_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=863 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records of `punch_card_project`
-- ----------------------------
BEGIN;
INSERT INTO `punch_card_project` VALUES ('861', '早睡早起', '0', '0', '运动-健身,健康生活-早起早睡', 'default_cover_img', '100000', '还没想好呢', '', '2', '0', '2', '2019-10-07 17:32:11', '2019-10-10 00:00:00'), ('862', '读书', '0', '0', '阅读-读书', 'http://upload.dliony.com/Jietu20191007-013811.jpg', '100000', '', '', '1', '1', '1', '2019-10-11 16:45:00', '2019-10-11 16:45:47');
COMMIT;

-- ----------------------------
--  Table structure for `sys_recommend_bg_img`
-- ----------------------------
DROP TABLE IF EXISTS `sys_recommend_bg_img`;
CREATE TABLE `sys_recommend_bg_img` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键、无符号',
  `bg_img_url` varchar(255) NOT NULL COMMENT '系统提供的个人主页背景图地址，不为空',
  `is_recommend` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否被设置为系统推荐的背景图，0--未设置为推荐（默认） 1--已设置为推荐、不为空',
  `use_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '当前图片被设置为个人主页背景图人数，默认为0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records of `sys_recommend_bg_img`
-- ----------------------------
BEGIN;
INSERT INTO `sys_recommend_bg_img` VALUES ('38', 'http://upload.dliony.com/Jietu20191007-013811.jpg', '1', '0', '2018-09-16 00:00:00', '2019-10-07 01:44:27'), ('39', 'http://upload.dliony.com/2a083fec0fee4ba1b55adb0885b53237', '1', '0', '2019-06-21 00:00:00', '2019-10-07 01:44:21'), ('40', 'http://upload.dliony.com/254abcef77c16e09ee8b4ee7e817b550.png', '1', '0', '2019-10-03 15:33:30', '2019-10-03 15:33:56'), ('41', 'http://upload.dliony.com/d9635c6f91583f2b736be98d465bf2a3.png', '1', '0', '2019-10-03 15:34:12', '2019-10-03 15:34:12'), ('42', 'http://upload.dliony.com/d9bab9f91d61f791aee6189a8df9d227.png', '1', '0', '2019-10-03 15:34:29', '2019-10-07 01:43:59'), ('43', 'http://upload.dliony.com/dbf1e4d5bc17e158420feecb14a37b48.png', '1', '0', '2019-10-03 15:34:48', '2019-10-07 01:43:50');
COMMIT;

-- ----------------------------
--  Table structure for `sys_recommend_pro_cover_img`
-- ----------------------------
DROP TABLE IF EXISTS `sys_recommend_pro_cover_img`;
CREATE TABLE `sys_recommend_pro_cover_img` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键、无符号',
  `img_url` varchar(255) NOT NULL COMMENT '系统推荐的圈子封面图地址，不为空',
  `is_recommend` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否被设置为系统推荐的圈子封面背景图，0--未设置为推荐（默认） 1--已设置为推荐、不为空',
  `use_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '当前图片被设置为圈子封面图人数，默认为0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records of `sys_recommend_pro_cover_img`
-- ----------------------------
BEGIN;
INSERT INTO `sys_recommend_pro_cover_img` VALUES ('2', 'http://upload.dliony.com/Jietu20191007-013811.jpg', '1', '2', '2019-09-14 12:23:53', '2019-10-07 01:42:19'), ('4', 'http://upload.dliony.com/Jietu20191007-013903.jpg', '1', '0', '2019-09-14 12:23:53', '2019-10-07 01:40:40'), ('7', 'http://upload.dliony.com/254abcef77c16e09ee8b4ee7e817b550.png', '1', '1', '2019-09-14 12:23:53', '2019-10-07 01:42:29'), ('10', 'http://upload.dliony.com/2a083fec0fee4ba1b55adb0885b53237', '1', '1', '2019-09-14 12:23:53', '2019-10-07 01:42:42'), ('12', 'http://upload.dliony.com/3642a8e0e2c63f550c10584868c747b1.jpg', '1', '1', '2019-09-14 12:23:53', '2019-10-07 01:43:02'), ('14', 'http://upload.dliony.com/d9635c6f91583f2b736be98d465bf2a3.png', '1', '2', '2019-09-14 12:23:54', '2019-10-03 16:37:43'), ('17', 'http://upload.dliony.com/dbf1e4d5bc17e158420feecb14a37b48.png', '1', '3', '2019-09-14 12:23:54', '2019-10-03 16:37:39'), ('18', 'http://upload.dliony.com/d9bab9f91d61f791aee6189a8df9d227.png', '1', '0', '2019-09-14 12:23:54', '2019-10-03 16:37:18');
COMMIT;

-- ----------------------------
--  Table structure for `unread_news_count`
-- ----------------------------
DROP TABLE IF EXISTS `unread_news_count`;
CREATE TABLE `unread_news_count` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键、自增',
  `user_id` int(11) unsigned NOT NULL COMMENT '无符号，用户id，外键',
  `unread_news_type` varchar(32) NOT NULL COMMENT '未读消息类型 likeNews,commentNews',
  `unread_news_num` int(11) unsigned NOT NULL COMMENT '该类型消息的未读条数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `unread_news_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(50) NOT NULL COMMENT '微信openid、唯一',
  `nick_name` varchar(30) DEFAULT NULL,
  `avatar_url` varchar(500) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别、默认为微信提供的信息 0-未知，1-男性，2-女性',
  `country` varchar(30) DEFAULT NULL,
  `province` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `language` varchar(10) DEFAULT NULL,
  `pass_word` varchar(50) DEFAULT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `channel` varchar(10) DEFAULT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `wx_language` varchar(20) DEFAULT NULL,
  `system` varchar(20) DEFAULT NULL,
  `platform` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL COMMENT '出生日期、默认为空',
  `person_sign` varchar(20) DEFAULT NULL COMMENT '个性签名、默认值为空',
  `bg_img` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '个人主页背景图，外键、系统背景图随机、不为空',
  `personal_page_visit_num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '个人主页访问量，默认0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_open_id_index` (`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100002 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('100000', 'onCAd5Pn1Q55MiSDpAi_NwFIa7WE', '陌上花开', 'https://wx.qlogo.cn/mmopen/vi_32/gP6xl5yc7Ljv5Wcw7Mnh2Dj7AVsrHJyYDXAMtefyL64mia3Hzic3N2ia40bMVvvxfOnJ4WXWBWicDMMgTziaYPKoGKQ/132', '1', '中国', '陕西', '西安', 'zh_CN', null, null, 'wxmp', 'devtools', 'Nexus 5', 'zh', 'Android 5.0', 'devtools', null, null, '0', '0', '2019-10-08 11:18:59', '2019-10-11 17:37:47'), ('100001', 'onCAd5MVerIa6Vdhm7jtw5Fq-ST4', 'E=mc²', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIdrUo42RQHQDuVo4OwjaYvdF2PAPtCnzm6qibcq3iaVTKShbG1qcibYIWnib5wR5X3Tz8IxgODF3ic5ew/132', '2', '法国', '巴黎', '', 'zh_CN', null, null, 'wxmp', 'HUAWEI', 'LYA-AL00', 'zh_CN', 'Android 9', 'android', null, null, '0', '0', '2019-10-10 08:33:03', '2019-10-10 08:33:03');
COMMIT;

-- ----------------------------
--  Table structure for `user_project_record`
-- ----------------------------
DROP TABLE IF EXISTS `user_project_record`;
CREATE TABLE `user_project_record` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '我参与或创建的圈子记录标识id，主键自增',
  `user_id` int(11) unsigned NOT NULL COMMENT '打卡用户id，外键',
  `project_id` int(11) unsigned NOT NULL COMMENT '打卡圈子id，外键',
  `is_creator` tinyint(3) NOT NULL COMMENT ' 是否为圈主，0--不是圈子创建者（默认）1--圈子创建者',
  `attend_status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '用户加入圈子的状态，1:参与中，2:待审核，3:已拒绝，4:已退出',
  `all_punch_card_num` int(11) NOT NULL DEFAULT '0' COMMENT '用户累计打卡次数，默认0',
  `attend_time` date NOT NULL COMMENT '加入或者创建圈子时间--年月日',
  `last_punch_card_time` datetime DEFAULT NULL COMMENT '用户上一次的打卡时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `all_punch_card_day_num` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_user_id` (`user_id`) USING BTREE,
  KEY `fk_project_id` (`project_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records of `user_project_record`
-- ----------------------------
BEGIN;
INSERT INTO `user_project_record` VALUES ('121', '100000', '861', '1', '1', '2', '2019-10-07', '2019-10-09 22:00:03', '2019-10-07 17:32:11', '2019-10-09 22:00:02', '2'), ('123', '100000', '862', '1', '1', '1', '2019-10-11', '2019-10-11 16:45:48', '2019-10-11 16:45:00', '2019-10-11 16:45:47', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;




笔记
create table note(
id int primary key auto_increment,
user_id int,
content text,
create_time timestamp  not NULL DEFAULT CURRENT_TIMESTAMP,
update_time timestusableAmountamp  not NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)charset utf8 collate utf8_general_ci;

create unique index note_user_id_index on note(user_id);


#以下表暂时未用到

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
