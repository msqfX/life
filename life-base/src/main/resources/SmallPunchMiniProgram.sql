/*
 Navicat Premium Data Transfer

 Source Server         : LinuxService
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 106.52.87.114:3306
 Source Schema         : SmallPunchMiniProgram

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 12/07/2019 23:02:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '管理员信息id,主键，递增',
  `admin_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员登录名，唯一索引，15字符，字母、数字、下划线、破折号组成',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '加密后的密码串',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `admin_name`(`admin_name`) USING BTREE COMMENT '登录名，唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for diary_comment
-- ----------------------------
DROP TABLE IF EXISTS `diary_comment`;
CREATE TABLE `diary_comment`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `diary_id` int(11) UNSIGNED NOT NULL,
  `pid` int(11) NOT NULL DEFAULT 0,
  `reviewer_id` int(11) UNSIGNED NOT NULL,
  `respondent_id` int(11) UNSIGNED NOT NULL,
  `text_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sound_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `reviewer_id`(`reviewer_id`) USING BTREE,
  INDEX `respondent_id`(`respondent_id`) USING BTREE,
  INDEX `diary_comment_fk_diary_id`(`diary_id`) USING BTREE,
  CONSTRAINT `respondent_id` FOREIGN KEY (`respondent_id`) REFERENCES `user_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `reviewer_id` FOREIGN KEY (`reviewer_id`) REFERENCES `user_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for diary_like
-- ----------------------------
DROP TABLE IF EXISTS `diary_like`;
CREATE TABLE `diary_like`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '点赞记录id, 主键、自增、无符号',
  `diary_id` int(11) UNSIGNED NOT NULL COMMENT '所属日记记录id,外键、无符号',
  `liked_user_id` int(11) UNSIGNED NOT NULL COMMENT '被点赞用户id,外键、无符号',
  `admirer_id` int(11) UNSIGNED NOT NULL COMMENT '点赞用户id,外键、无符号',
  `create_time` datetime(0) NOT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `diary_like_fk_diary_id`(`diary_id`) USING BTREE,
  INDEX `liked_user_id`(`liked_user_id`) USING BTREE,
  INDEX `admirer_id`(`admirer_id`) USING BTREE,
  CONSTRAINT `admirer_id` FOREIGN KEY (`admirer_id`) REFERENCES `user_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `liked_user_id` FOREIGN KEY (`liked_user_id`) REFERENCES `user_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 125 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for diary_resource
-- ----------------------------
DROP TABLE IF EXISTS `diary_resource`;
CREATE TABLE `diary_resource`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '资源路径记录id,主键自增、无符号',
  `diary_id` int(11) UNSIGNED NOT NULL COMMENT '资源所属的打卡日记记录id,不能为空，打卡日记记录外键，无符号',
  `resource_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '资源存储相对路径,默认空字符串',
  `type` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '资源类型, 不能为空，默认为1--图片  2--音频 3--视频',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `diary_id`(`diary_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for new_punch_card_project
-- ----------------------------
DROP TABLE IF EXISTS `new_punch_card_project`;
CREATE TABLE `new_punch_card_project`  (
  `id` int(10) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for personal_page_visit_record
-- ----------------------------
DROP TABLE IF EXISTS `personal_page_visit_record`;
CREATE TABLE `personal_page_visit_record`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增，无符号',
  `visitor_id` int(11) UNSIGNED NOT NULL COMMENT '无符号，访问者用户id，外键',
  `visited_id` int(11) UNSIGNED NOT NULL COMMENT '无符号，被访问者用户id，外键',
  `create_time` datetime(0) NOT NULL COMMENT '第一次访问时间，不能为空，年-月-日 时:分',
  `update_time` datetime(0) NOT NULL COMMENT '最新访问时间，不能为空，年-月-日 时:分',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `visitor_id`(`visitor_id`) USING BTREE,
  INDEX `visited_id`(`visited_id`) USING BTREE,
  CONSTRAINT `visited_id` FOREIGN KEY (`visited_id`) REFERENCES `user_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `visitor_id` FOREIGN KEY (`visitor_id`) REFERENCES `user_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pro_intr_detail_info
-- ----------------------------
DROP TABLE IF EXISTS `pro_intr_detail_info`;
CREATE TABLE `pro_intr_detail_info`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '圈子简介详情信息标识id，主键、自增',
  `project_id` int(10) UNSIGNED NOT NULL COMMENT '所属圈子id，外键',
  `content` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'type为1存储文本、 2--存储图片url、3--音频文件url、4--视频文件url',
  `type` tinyint(3) NOT NULL COMMENT ' 简介内容类型，1--文字、2--图片，3--录音，4--视频',
  `order` tinyint(3) NOT NULL COMMENT '当前简介内容处于对应圈子简介的位置顺序',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `project_id`(`project_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 129 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for project_type_label
-- ----------------------------
DROP TABLE IF EXISTS `project_type_label`;
CREATE TABLE `project_type_label`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '圈子类型标签标识id',
  `parent_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父级分类id，默认为0--一级分类',
  `label_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型标签名，唯一索引，15字符内',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `label_name`(`label_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for punch_card_diary
-- ----------------------------
DROP TABLE IF EXISTS `punch_card_diary`;
CREATE TABLE `punch_card_diary`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '打卡日记记录编号,主键自增、无符号',
  `project_id` int(11) UNSIGNED NOT NULL COMMENT '所属圈子的圈子编号,外键、无符号、不能为空',
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '所属用户的用户编号,外键、无符号、不能为空',
  `text_content` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '打卡日记文本内容, 不能为空，默认为空字符串，限制512',
  `punch_card_time` datetime(0) NOT NULL COMMENT '打卡时间(年-月-日 时:分),不能为空',
  `punch_card_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '打卡地点名称，不能为空、默认为空字符串',
  `address_longitude` double(10, 6) NOT NULL DEFAULT 0.000000 COMMENT '打卡地点对应经度,不能为空,默认为 0',
  `address_latitude` double(10, 6) NOT NULL DEFAULT 0.000000 COMMENT '打卡地点对应纬度,不能为空,默认为 0',
  `visible_type` tinyint(3) NOT NULL DEFAULT 0 COMMENT '该条打卡日记的可见类型,默认0--公开 其他圈子成员可见  1--仅圈主可见',
  `curr_diary_punch_card_day_num` int(11) NOT NULL DEFAULT 0 COMMENT '当前日记的已坚持打卡天数(可不连续)，不能为空，默认为0 ',
  `have_sticky` tinyint(3) NOT NULL DEFAULT 0 COMMENT '是否置顶 ,默认为0--不置顶 1--置顶 ',
  `is_repair_diary` tinyint(3) NOT NULL DEFAULT 0 COMMENT '是否为补打卡日记,默认为0--非补打卡日记  1--补打卡日记',
  ` repair_punch_card_time` datetime(0) NOT NULL COMMENT '补打卡时间，(年-月-日 时:分)',
  `like_user_num` int(11) NOT NULL DEFAULT 0 COMMENT '点赞人数，默认为0',
  `comment_num` int(11) NOT NULL DEFAULT 0 COMMENT '评论数，默认为0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `project_id`(`project_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `project_id` FOREIGN KEY (`project_id`) REFERENCES `punch_card_project` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for punch_card_project
-- ----------------------------
DROP TABLE IF EXISTS `punch_card_project`;
CREATE TABLE `punch_card_project`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键、自增、圈子编号、唯一标识',
  `project_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '圈子名称',
  `privacy_type` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '圈子私密类型，0--公开（默认），1--私密',
  `open_audit` tinyint(3) NOT NULL DEFAULT 0 COMMENT '是否开启审核，0--不开启（默认），1--开启',
  `type_label` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '圈子类型标签，存储标签名字符串，以逗号隔离',
  `cover_img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'default_cover_img' COMMENT '圈子封面图，圈子创建时默认为小程序端内置的一张图片,标识符为default_cover_img',
  `creator_id` int(10) UNSIGNED NOT NULL COMMENT '外键，圈主的用户id',
  `creator_introduce` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '圈主简介，50字',
  `weixin_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '默认为空，长度为6-20，由字母、数字、下划线、减号组成，字母开头',
  `all_punch_card_num` int(11) NOT NULL DEFAULT 0 COMMENT '圈子总打卡数 默认0',
  `today_punch_card_num` int(11) NOT NULL DEFAULT 0 COMMENT '圈子今日打卡数 默认0',
  `attend_user_num` int(11) NOT NULL DEFAULT 1 COMMENT '默认为1 在创建的时候创建者已加入',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `creator_id`(`creator_id`) USING BTREE,
  CONSTRAINT `creator_id` FOREIGN KEY (`creator_id`) REFERENCES `user_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 793 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_recommend_bg_img
-- ----------------------------
DROP TABLE IF EXISTS `sys_recommend_bg_img`;
CREATE TABLE `sys_recommend_bg_img`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键、无符号',
  `bg_img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '系统提供的个人主页背景图地址，不为空',
  `is_recommend` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否被设置为系统推荐的背景图，0--未设置为推荐（默认） 1--已设置为推荐、不为空',
  `use_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '当前图片被设置为个人主页背景图人数，默认为0',
  `create_at` date NOT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_recommend_pro_cover_img
-- ----------------------------
DROP TABLE IF EXISTS `sys_recommend_pro_cover_img`;
CREATE TABLE `sys_recommend_pro_cover_img`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键、无符号',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '系统推荐的圈子封面图地址，不为空',
  `is_recommend` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否被设置为系统推荐的圈子封面背景图，0--未设置为推荐（默认） 1--已设置为推荐、不为空',
  `use_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '当前图片被设置为圈子封面图人数，默认为0',
  `create_at` date NOT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for unread_news_count
-- ----------------------------
DROP TABLE IF EXISTS `unread_news_count`;
CREATE TABLE `unread_news_count`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键、自增',
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '无符号，用户id，外键',
  `unread_news_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '未读消息类型 likeNews,commentNews',
  `unread_news_num` int(11) UNSIGNED NOT NULL COMMENT '该类型消息的未读条数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `unread_news_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `unread_news_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键、自增',
  `open_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信openid、唯一',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'default_avatar' COMMENT '用户头像地址、默认为default_avatar,即为小程序端内置的默认头像',
  `bg_img_id` int(10) UNSIGNED NOT NULL COMMENT '个人主页背景图，外键、系统背景图随机、不为空',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称、默认微信昵称不为空',
  `weixin_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信号，长度为6-20，字母开头、数字、下划线、减号组成，唯一、默认为空',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期、默认为空',
  `sex` tinyint(4) NOT NULL COMMENT '性别、默认为微信提供的信息 0-未知，1-男性，2-女性',
  `person_sign` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个性签名、默认值为空',
  `personal_page_visit_num` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '个人主页访问量，默认0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `openId`(`open_id`) USING BTREE,
  UNIQUE INDEX `weixinNum`(`weixin_num`) USING BTREE,
  INDEX `bgImgId`(`bg_img_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_project_record
-- ----------------------------
DROP TABLE IF EXISTS `user_project_record`;
CREATE TABLE `user_project_record`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '我参与或创建的圈子记录标识id，主键自增',
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '打卡用户id，外键',
  `project_id` int(11) UNSIGNED NOT NULL COMMENT '打卡圈子id，外键',
  `is_creator` tinyint(3) NOT NULL COMMENT ' 是否为圈主，0--不是圈子创建者（默认）1--圈子创建者',
  `attend_status` tinyint(3) NOT NULL DEFAULT 1 COMMENT '用户加入圈子的状态，1:参与中，2:待审核，3:已拒绝，4:已退出',
  `all_punch_card_num` int(11) NOT NULL DEFAULT 0 COMMENT '用户累计打卡次数，默认0',
  `attend_time` date NOT NULL COMMENT '加入或者创建圈子时间--年月日',
  `last_punch_card_time` datetime(0) NULL DEFAULT NULL COMMENT '用户上一次的打卡时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_user_id`(`user_id`) USING BTREE,
  INDEX `fk_project_id`(`project_id`) USING BTREE,
  CONSTRAINT `fk_project_id` FOREIGN KEY (`project_id`) REFERENCES `punch_card_project` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
