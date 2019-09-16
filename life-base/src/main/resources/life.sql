/*
SQLyog Professional v12.08 (64 bit)
MySQL - 5.7.27 : Database - life
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`life` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `life`;

/*Table structure for table `admin_info` */

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

/*Data for the table `admin_info` */

/*Table structure for table `diary_comment` */

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
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

/*Data for the table `diary_comment` */

insert  into `diary_comment`(`id`,`diary_id`,`pid`,`reviewer_id`,`respondent_id`,`text_comment`,`sound_comment`,`create_time`,`update_time`) values (3,23,0,8,8,'测试哦','','2019-09-14 11:58:39','2019-09-14 11:58:39'),(4,23,0,8,8,'考虑考虑','','2019-09-14 11:58:39','2019-09-14 11:58:39'),(5,1,0,8,1,'测试一下','','2019-09-14 11:58:39','2019-09-14 11:58:39'),(7,12,0,1,1,'啦all','','2019-09-14 11:58:39','2019-09-14 11:58:39'),(11,1,5,1,8,'is阿覅发','','2019-09-14 11:58:39','2019-09-14 11:58:39'),(12,1,5,1,8,'看看','','2019-09-14 11:58:39','2019-09-14 11:58:39'),(13,25,0,1,1,'太累了','','2019-09-14 11:58:39','2019-09-14 11:58:39'),(17,26,0,8,1,'了了了了了了了','','2019-09-14 11:58:39','2019-09-14 11:58:39'),(18,26,0,1,8,'呵呵呵呵','','2019-09-14 11:58:39','2019-09-14 11:58:39'),(22,26,0,1,8,'啦啦啦','','2019-09-14 11:58:39','2019-09-14 11:58:39'),(30,24,0,8,8,'测试','','2019-09-14 11:58:39','2019-09-14 11:58:39'),(31,24,0,1,8,'啦咯啦咯啦咯','','2019-09-14 11:58:39','2019-09-14 11:58:39'),(32,46,0,8,1,'我是第一个评论者','','2019-09-14 11:58:39','2019-09-14 11:58:39'),(33,46,32,1,8,'么么哒','','2019-09-14 11:58:39','2019-09-14 11:58:39'),(38,17,0,1,8,'有点意思啊','','2019-09-14 12:00:30','2019-09-14 12:00:30'),(39,17,0,1,8,'兄弟们6不6','','2019-09-14 12:00:31','2019-09-14 12:00:31'),(40,18,0,1,8,'评论测试，返回首页自动更新','','2019-09-14 12:00:31','2019-09-14 12:00:31'),(41,24,30,1,8,'好评！','','2019-09-14 12:00:31','2019-09-14 12:00:31'),(47,24,30,8,1,'谢谢！','','2019-09-14 12:00:31','2019-09-14 12:00:31'),(52,24,30,1,8,'哈哈理解','','2019-09-14 12:00:31','2019-09-14 12:00:31'),(53,59,0,1,8,'生而为人且善良','','2019-09-14 12:00:31','2019-09-14 12:00:31'),(54,61,0,1,8,'不锻炼就是这个样子','','2019-09-14 12:00:31','2019-09-14 12:00:31'),(55,22,0,1,1,'加油＾０＾~','','2019-09-14 12:00:31','2019-09-14 12:00:31'),(60,68,0,1,8,'upupup~','','2019-09-14 12:00:31','2019-09-14 12:00:31'),(61,67,0,8,1,'晚安啦','','2019-09-14 12:00:31','2019-09-14 12:00:31'),(67,24,30,1,8,'是的梵蒂冈所多付付付付是大法官深度覆盖撒旦法','','2019-09-14 12:00:31','2019-09-14 12:00:31'),(68,21,0,1,8,'加油丫','','2019-09-14 12:00:31','2019-09-14 12:00:31'),(69,77,0,1,1,'兔兔','','2019-09-14 12:00:31','2019-09-14 12:00:31');

/*Table structure for table `diary_like` */

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
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

/*Data for the table `diary_like` */

insert  into `diary_like`(`id`,`diary_id`,`liked_user_id`,`admirer_id`,`create_time`,`update_time`) values (10,23,8,8,'2019-09-14 12:02:13','2019-09-14 12:02:13'),(25,1,1,1,'2019-09-14 12:02:13','2019-09-14 12:02:13'),(27,2,1,1,'2019-09-14 12:02:13','2019-09-14 12:02:13'),(28,1,1,8,'2019-09-14 12:02:13','2019-09-14 12:02:13'),(31,5,1,8,'2019-09-14 12:02:13','2019-09-14 12:02:13'),(32,4,1,8,'2019-09-14 12:02:13','2019-09-14 12:02:13'),(33,3,1,8,'2019-09-14 12:02:13','2019-09-14 12:02:13'),(60,12,1,1,'2019-09-14 12:02:13','2019-09-14 12:02:13'),(71,19,8,8,'2019-09-14 12:02:13','2019-09-14 12:02:13'),(76,5,1,1,'2019-09-14 12:02:13','2019-09-14 12:02:13'),(84,18,8,1,'2019-09-14 12:02:13','2019-09-14 12:02:13'),(93,19,8,1,'2019-09-14 12:02:13','2019-09-14 12:02:13'),(104,24,8,1,'2019-09-14 12:02:13','2019-09-14 12:02:13'),(105,17,8,1,'2019-09-14 12:02:13','2019-09-14 12:02:13'),(106,42,1,8,'2019-09-14 12:02:13','2019-09-14 12:02:13'),(110,41,1,8,'2019-09-14 12:02:13','2019-09-14 12:02:13');

/*Table structure for table `diary_resource` */

DROP TABLE IF EXISTS `diary_resource`;

CREATE TABLE `diary_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '资源路径记录id,主键自增、无符号',
  `diary_id` int(11) unsigned NOT NULL COMMENT '资源所属的打卡日记记录id,不能为空，打卡日记记录外键，无符号',
  `resource_url` varchar(255) NOT NULL DEFAULT '' COMMENT '资源存储相对路径,默认空字符串',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '资源类型, 不能为空，默认为1--图片  2--音频 3--视频',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `diary_id` (`diary_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

/*Data for the table `diary_resource` */

insert  into `diary_resource`(`id`,`diary_id`,`resource_url`,`type`,`create_time`) values (1,1,'public/image_upload/diaryResource/713/1/20181021/4cde436e247041472bd5c84bf6172a1c.jpg',1,'2019-09-14 12:06:48'),(2,1,'public/image_upload/diaryResource/713/1/20181021/e9c8497533260f631d75fc188070f8ce.jpg',1,'2019-09-14 12:06:48'),(3,2,'public/image_upload/diaryResource/713/2/20181021/02982df7c83dbb0fe82e9c956abef887.png',1,'2019-09-14 12:06:48'),(6,4,'public/image_upload/diaryResource/713/4/20181021/9aa26fe29a08b31f44ead60e23144a76.png',1,'2019-09-14 12:06:48'),(7,5,'public/image_upload/diaryResource/713/5/20181021/6abe9292f94d34bd9fc42cb5cf5ffb4c.png',1,'2019-09-14 12:06:48'),(13,8,'public/image_upload/diaryResource/713/8/20181021/c95530e4a07e5df7435ea4d460f55006.jpg',1,'2019-09-14 12:06:48'),(14,8,'public/image_upload/diaryResource/713/8/20181021/82426f3caee41eba762baaafdfd0940d.jpg',1,'2019-09-14 12:06:48'),(15,8,'public/image_upload/diaryResource/713/8/20181021/97115303ddbc7e6040a2e588d343067a.jpg',1,'2019-09-14 12:06:48'),(16,8,'public/image_upload/diaryResource/713/8/20181021/388fc9408224c719225473909a0c7f1d.jpg',1,'2019-09-14 12:06:48'),(17,8,'public/image_upload/diaryResource/713/8/20181021/d8f63b07e478742e2a91030b4510056c.jpg',1,'2019-09-14 12:06:48'),(18,8,'public/image_upload/diaryResource/713/8/20181021/9615b281230e8fe7362e5d73befb3060.jpg',1,'2019-09-14 12:06:48'),(19,8,'public/image_upload/diaryResource/713/8/20181021/166f02ef3f1a66ce1e6916fa873bf9a3.jpg',1,'2019-09-14 12:06:48'),(20,8,'public/image_upload/diaryResource/713/8/20181021/ddc19292e56719b9d060a80d48d56d03.jpg',1,'2019-09-14 12:06:48'),(21,8,'public/image_upload/diaryResource/713/8/20181021/2a65204894dcacfd9ff857807f41136a.jpg',1,'2019-09-14 12:06:48'),(22,9,'public/image_upload/diaryResource/720/9/20181022/6ae43cf8d3c5cc3e32922194e920aa11.jpg',1,'2019-09-14 12:06:49'),(23,9,'public/image_upload/diaryResource/720/9/20181022/c34b416e1759f5dc4878389d4ceed5a6.jpg',1,'2019-09-14 12:06:49'),(24,11,'public/image_upload/diaryResource/720/11/20181022/265d3165bff1ca19c36090a6d4ccab94.jpg',1,'2019-09-14 12:06:49'),(25,12,'public/image_upload/diaryResource/720/12/20181022/cd4ce9a94cbd2c78ff0dc5aa1b42ed01.jpg',1,'2019-09-14 12:06:49'),(26,13,'public/image_upload/diaryResource/738/13/20181022/467f78254387727f1c88250b8ea8bece.jpg',1,'2019-09-14 12:06:49'),(27,13,'public/image_upload/diaryResource/738/13/20181022/877670473d34bf446cd468c634663de7.jpg',1,'2019-09-14 12:06:49'),(28,13,'public/image_upload/diaryResource/738/13/20181022/d8f1adc6efacfa44a1cf68d99125885c.jpg',1,'2019-09-14 12:06:49'),(29,13,'public/image_upload/diaryResource/738/13/20181022/fbd98216f1ce549885274460325aad70.jpg',1,'2019-09-14 12:06:49'),(30,14,'public/image_upload/diaryResource/738/14/20181022/dd7cdce1bc8a845da4421992a247e835.jpg',1,'2019-09-14 12:06:49'),(31,14,'public/image_upload/diaryResource/738/14/20181022/2698d611cf20baa8f2056e2c7fb5359e.jpg',1,'2019-09-14 12:06:49'),(32,15,'public/image_upload/diaryResource/758/15/20181023/6749c176fe96aca04bdb13fa115b6630.png',1,'2019-09-14 12:06:49'),(33,15,'public/image_upload/diaryResource/758/15/20181023/2030b95190e2c56321a92c090bdf7641.png',1,'2019-09-14 12:06:49'),(34,15,'public/image_upload/diaryResource/758/15/20181023/76d7cc85612b98671adfd8316c487b45.png',1,'2019-09-14 12:06:49'),(35,16,'public/image_upload/diaryResource/758/16/20181023/eb07d737f4cc86a241cf9e8d028fc5f2.png',1,'2019-09-14 12:06:49'),(36,16,'public/image_upload/diaryResource/758/16/20181023/15adfa534625ecce23771d0ab5d67367.png',1,'2019-09-14 12:06:49'),(37,16,'public/image_upload/diaryResource/758/16/20181023/81542356af58b65ed8b303ca45a2e598.png',1,'2019-09-14 12:06:49'),(38,16,'public/image_upload/diaryResource/758/16/20181023/83b5751c81a0408ed534812594f86e9f.png',1,'2019-09-14 12:06:49'),(39,16,'public/image_upload/diaryResource/758/16/20181023/357a27ceba47a2088567874491882ff0.png',1,'2019-09-14 12:06:49'),(40,16,'public/image_upload/diaryResource/758/16/20181023/225f6757456f6fd96e05215d9c2b0efb.png',1,'2019-09-14 12:06:49'),(41,16,'public/image_upload/diaryResource/758/16/20181023/1a1d292150ca11d3e90d4caaa248e0f5.png',1,'2019-09-14 12:06:49'),(42,16,'public/image_upload/diaryResource/758/16/20181023/e2cec4aebf5f033028eff9a63ad58801.png',1,'2019-09-14 12:06:49'),(43,16,'public/image_upload/diaryResource/758/16/20181023/212ade8f12c1058c20bd9fac9e4f906c.png',1,'2019-09-14 12:06:49'),(44,18,'public/image_upload/diaryResource/758/18/20181023/ed3abbbf810262898945dc63263c0a99.png',1,'2019-09-14 12:06:49'),(45,18,'public/image_upload/diaryResource/758/18/20181023/3f81ebf48da5c27962382895533e0bfc.png',1,'2019-09-14 12:06:49'),(46,18,'public/image_upload/diaryResource/758/18/20181023/a04d689420790ee49c8a20a81f735742.png',1,'2019-09-14 12:06:49'),(47,18,'public/image_upload/diaryResource/758/18/20181023/e154319ee7a7e34ea380f8f3a71425e4.png',1,'2019-09-14 12:06:49'),(48,18,'public/image_upload/diaryResource/758/18/20181023/4749ca7323d3ab54b5f4a1a92b31aedb.png',1,'2019-09-14 12:06:49'),(49,18,'public/image_upload/diaryResource/758/18/20181023/166715c269dea49b5dba2fad55871c9e.png',1,'2019-09-14 12:06:49'),(50,20,'public/image_upload/diaryResource/738/20/20181023/738ba13295a995b95558e9aada58b469.png',1,'2019-09-14 12:06:49'),(51,22,'public/image_upload/diaryResource/720/22/20181024/4ffca5f80dc3d2972a8ab8af32a46d83.png',1,'2019-09-14 12:06:49'),(52,23,'public/image_upload/diaryResource/717/23/20181024/0025a3f9fbe5b5c0f6bc7864569e1e66.png',1,'2019-09-14 12:06:49'),(53,23,'public/image_upload/diaryResource/717/23/20181024/adb9c266c911e2b362956a1986c55f22.png',1,'2019-09-14 12:06:49'),(54,23,'public/image_upload/diaryResource/717/23/20181024/1e4d9a0491e35356db6c6ca0163d1ecf.png',1,'2019-09-14 12:06:49'),(55,23,'public/image_upload/diaryResource/717/23/20181024/a70887b48883b889f3b32856971d57c2.png',1,'2019-09-14 12:06:49'),(56,23,'public/image_upload/diaryResource/717/23/20181024/2e90977a24303a9a9d67bd1b84a025f3.png',1,'2019-09-14 12:06:49'),(57,24,'public/image_upload/diaryResource/720/24/20181024/cdc638f663b0fac0274552876d2c3db5.png',1,'2019-09-14 12:06:49'),(58,25,'public/image_upload/diaryResource/763/25/20181105/04816ec28993fb04af29b5b20e6db17c.jpg',1,'2019-09-14 12:06:49'),(59,26,'public/image_upload/diaryResource/720/26/20181111/1e7ad599d3ff7aeb100c0cf65eab869f.png',1,'2019-09-14 12:06:49'),(60,26,'public/image_upload/diaryResource/720/26/20181111/c4f27d6407d46fad8f1d9032f8fc75f7.png',1,'2019-09-14 12:06:49'),(82,41,'public/image_upload/diaryResource/720/41/20190425/c1ef5460bec4a7323e2d334de6ba2779.jpg',1,'2019-09-14 12:06:49'),(83,42,'public/image_upload/diaryResource/720/42/20190425/720bb9ad81c02c1e627eebf0bc7d8f27.jpg',1,'2019-09-14 12:06:49'),(84,42,'public/audio_upload/diaryResource/720/42/20190425/3eec3c91ff7caa455b8024f0eb0f086e.mp3',2,'2019-09-14 12:06:49'),(86,44,'public/image_upload/diaryResource/713/44/20190425/d669c94234355bc7c8d5740181c9aa93.jpg',1,'2019-09-14 12:06:49'),(87,44,'public/audio_upload/diaryResource/713/44/20190425/cce6a4a704e0305ea4682ee8106dfdb2.mp3',2,'2019-09-14 12:06:49'),(88,45,'public/image_upload/diaryResource/713/45/20190425/a8d1d591ced213e491f167d758c8aa3d.jpg',1,'2019-09-14 12:06:49'),(89,45,'public/image_upload/diaryResource/713/45/20190425/67ef41d11362226ddfa29a50ef7fc039.jpg',1,'2019-09-14 12:06:49'),(90,45,'public/audio_upload/diaryResource/713/45/20190425/38bee0ca5ec4ed8dd35544ac457972e4.mp3',2,'2019-09-14 12:06:49'),(91,45,'public/image_upload/diaryResource/713/45/20190425/51f7886a35d9b2200c8fcb1b49abfafa.jpg',1,'2019-09-14 12:06:49'),(92,45,'public/image_upload/diaryResource/713/45/20190425/e616089fabaf91c2bce19358eee21a84.jpg',1,'2019-09-14 12:06:49'),(93,45,'public/image_upload/diaryResource/713/45/20190425/325db4f30be4c6d4621ad0c798cf8f0d.jpg',1,'2019-09-14 12:06:49'),(94,45,'public/image_upload/diaryResource/713/45/20190425/3bc86d258cc76a5db05fabd5dfba64b3.jpg',1,'2019-09-14 12:06:49'),(95,46,'public/image_upload/diaryResource/713/46/20190425/56c7f62742f58d8ca77c75fa7a8626fb.jpg',1,'2019-09-14 12:06:49'),(96,46,'public/audio_upload/diaryResource/713/46/20190425/19adc12254de89652c1df366a797adcc.mp3',2,'2019-09-14 12:06:49'),(99,57,'public/image_upload/diaryResource/781/57/20190514/824194839ffbcb9f24c26e36aa05db82.jpg',1,'2019-09-14 12:06:49'),(100,57,'public/image_upload/diaryResource/781/57/20190514/c8adfaad329cc6df2754a9d46ea66959.jpg',1,'2019-09-14 12:06:49'),(101,57,'public/audio_upload/diaryResource/781/57/20190514/91da3d08bf03e21a2a8f4b021c0ceeeb.mp3',2,'2019-09-14 12:06:49'),(102,58,'public/image_upload/diaryResource/782/58/20190514/afb943f225dbea14a5205ce7c47ee6cd.jpg',1,'2019-09-14 12:06:49'),(105,61,'public/image_upload/diaryResource/720/61/20190516/ff880c1c0be32b298d9a6f140a8f23d6.jpg',1,'2019-09-14 12:06:49'),(106,61,'public/image_upload/diaryResource/720/61/20190516/ba79bdd2bab606abe74551616cc39738.jpg',1,'2019-09-14 12:06:50'),(107,63,'public/image_upload/diaryResource/786/63/20190516/625ccf29f082c524b6dad1cd6bf61dcc.jpg',1,'2019-09-14 12:06:50'),(111,65,'public/image_upload/diaryResource/713/65/20190527/428eb4e8526b46035571768344aad398.gif',1,'2019-09-14 12:06:50'),(112,66,'public/image_upload/diaryResource/782/66/20190528/ee4128c21df778bb53daa1d3dae7140f.jpg',1,'2019-09-14 12:06:50'),(113,67,'public/image_upload/diaryResource/782/67/20190528/22af0f7370db5d0a872bb9b1b65beae0.gif',1,'2019-09-14 12:06:50'),(114,68,'public/image_upload/diaryResource/786/68/20190528/215094d16a397d3560789866b969b5ea.jpg',1,'2019-09-14 12:06:50'),(115,70,'public/image_upload/diaryResource/743/70/20190601/c51f9ba1c58bac10b84e7e6f7cad4529.gif',1,'2019-09-14 12:06:50'),(117,78,'public/image_upload/diaryResource/778/78/20190601/707c2d04fcd8e33407c4b21d68c7dabc.jpg',1,'2019-09-14 12:06:50'),(118,79,'public/audio_upload/diaryResource/790/79/20190620/b72db14803e4db75093841775e163208.mp3',2,'2019-09-14 12:06:50'),(119,79,'public/image_upload/diaryResource/790/79/20190620/e255b0cb74bf531510e43b987056b4e3.jpg',1,'2019-09-14 12:06:50'),(120,83,'public/image_upload/diaryResource/798/83/20190807/e2bd8f19a63281d818a088011c812cfa.png',1,'2019-09-14 12:06:50'),(121,83,'public/image_upload/diaryResource/798/83/20190807/ba25f382eca704837a332aaa591985a0.png',1,'2019-09-14 12:06:50'),(122,85,'public/image_upload/diaryResource/798/85/20190807/4dabfd090b37b860c9e1a166e021f765.png',1,'2019-09-14 12:06:50'),(123,85,'public/image_upload/diaryResource/798/85/20190807/6a9245a0bd4476fe7e884705fec69fe1.png',1,'2019-09-14 12:06:50'),(124,86,'public/image_upload/diaryResource/798/86/20190808/5ac66f2fc44803836359044fb1966c4a.jpg',1,'2019-09-14 12:06:50'),(125,86,'public/image_upload/diaryResource/798/86/20190808/ad7c4108dca21ba0c9fb9d978d6a57ee.jpg',1,'2019-09-14 12:06:50'),(128,88,'public/image_upload/diaryResource/798/88/20190809/cff9ae104a663b5d794ee2845334935a.jpg',1,'2019-09-14 12:06:50'),(129,88,'public/image_upload/diaryResource/798/88/20190809/107e5e76564b63e4c55b9f2c2d5530e3.jpg',1,'2019-09-14 12:06:50'),(130,89,'public/image_upload/diaryResource/798/89/20190810/6d48ed0f9dda3b479a22077695c80a10.png',1,'2019-09-14 12:06:50'),(131,89,'public/image_upload/diaryResource/798/89/20190810/4006e462968aee2cad736c72d976174c.png',1,'2019-09-14 12:06:50'),(132,90,'public/image_upload/diaryResource/798/90/20190811/af61864749e7b43db3cf4976e4f1fdf8.jpg',1,'2019-09-14 12:06:50'),(133,90,'public/image_upload/diaryResource/798/90/20190811/9ff4d8ce8eac3e3ca914aeee5534508b.jpg',1,'2019-09-14 12:06:50'),(134,91,'public/image_upload/diaryResource/798/91/20190812/51f3c54671419712ccb320ae1fdbb5bf.jpg',1,'2019-09-14 12:06:50'),(135,91,'public/image_upload/diaryResource/798/91/20190812/dfea49dcfeddfcced55d265a87130bac.jpg',1,'2019-09-14 12:06:50'),(136,92,'public/image_upload/diaryResource/798/92/20190813/044e450ed840fd747743aa7b158bf47e.png',1,'2019-09-14 12:06:50'),(137,92,'public/image_upload/diaryResource/798/92/20190813/9afa8413a5042214069934574825d2d4.png',1,'2019-09-14 12:06:50'),(138,93,'public/image_upload/diaryResource/798/93/20190814/0c0158a689db7fdc3d14434c9d3956dd.jpg',1,'2019-09-14 12:06:50'),(139,93,'public/image_upload/diaryResource/798/93/20190814/074a96bce4c41d4cc36c5bca7c3f9618.jpg',1,'2019-09-14 12:06:50');

/*Table structure for table `new_punch_card_project` */

DROP TABLE IF EXISTS `new_punch_card_project`;

CREATE TABLE `new_punch_card_project` (
  `id` int(10) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

/*Data for the table `new_punch_card_project` */

/*Table structure for table `personal_page_visit_record` */

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

/*Data for the table `personal_page_visit_record` */

insert  into `personal_page_visit_record`(`id`,`visitor_id`,`visited_id`,`create_time`,`update_time`) values (1,1,8,'2019-05-10 12:33:00','2019-07-28 19:05:00'),(2,8,1,'2019-05-10 12:36:00','2019-05-15 16:19:00');

/*Table structure for table `pro_intr_detail_info` */

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
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

/*Data for the table `pro_intr_detail_info` */

insert  into `pro_intr_detail_info`(`id`,`project_id`,`content`,`type`,`sort`,`create_time`,`update_time`) values (93,720,'健康才是我们最大的财富，所以一定要好好锻炼身体！每天坚持跑上几圈，go go go!!!',1,0,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(94,720,'public/image_upload/projectIntrImages/720/20181015/aefcd7eedbe041f8bda42339a839b692.png',2,1,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(95,720,'public/image_upload/projectIntrImages/720/20181015/3c09197ca8023b0caf03497956ffaddd.png',2,2,'2019-09-14 12:13:37','2019-09-14 12:13:37'),(100,737,'public/image_upload/projectIntrImages/737/20181015/ae9d79880a49fc00889bcfbf3ba4b120.jpg',2,2,'2019-09-14 12:13:37','2019-09-14 12:13:37'),(101,737,'public/image_upload/projectIntrImages/737/20181015/1875357ca2ba20e23115bf6821fedc98.jpg',2,1,'2019-09-14 12:13:37','2019-09-14 12:13:37'),(103,758,'有效测试打卡圈子数据，16真香…',1,0,'2019-09-14 12:12:10','2019-09-14 12:12:10'),(104,758,'了了了了了了了了了了了六级学习英语老师的人',1,2,'2019-09-14 12:12:17','2019-09-14 12:12:17'),(105,758,'public/image_upload/projectIntrImages/758/20181017/12c3388544d2e06784174c09fbd87890.jpg',2,3,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(106,758,'public/image_upload/projectIntrImages/758/20181017/7d3ec975630a7649def6d6ea3564c559.jpg',2,1,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(109,713,'哈哈哈哈哈哈呵呵呵呵呵呵呵呵呵呵呵呵呵嗯哼哼或或呵呵呵呵呵呵呵呵呵呵呵呵安徽省地方还啦啦啦啦啦啦啦啦绿啦绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿绿按实际覅哦啊发阿his发哈斯法是杀手法师话费阿莎法hi爱搜发送复活赛欧',1,0,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(111,713,'public/image_upload/projectIntrImages/713/20181019/0f61e99e8a69cd77942e372dfd2483de.jpg',2,2,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(112,713,'public/image_upload/projectIntrImages/713/20181019/c2cfd0d9a254d80f48bf4602db52a013.jpg',2,1,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(113,719,'bbbbb',1,0,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(114,719,'bbbbb',1,2,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(115,719,'public/image_upload/projectIntrImages/719/20181020/ae876c519addcbf5ef6d53c2fc12fb0e.png',2,1,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(116,763,'dsfesfe',1,1,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(117,763,'public/image_upload/projectIntrImages/763/20181105/aaee89412f433a317533cba3dfc44181.jpg',2,0,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(118,780,'public/image_upload/projectIntrImages/780/20190514/f5d9da4b62453df0e149eff9fa2789ae.jpg',2,0,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(119,781,'阿明的健身及饮食打卡。欢迎加入，一起鼓励!一起加油!!',1,0,'2019-09-14 12:11:58','2019-09-14 12:11:58'),(120,781,'public/image_upload/projectIntrImages/781/20190514/b60fbccb0665b3ee92242030b28d1c20.jpg',2,1,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(121,781,'public/image_upload/projectIntrImages/781/20190514/9966257a0b693972f985c91fda06a691.jpg',2,2,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(122,782,'夜里，无病呻吟，只能不断单曲循环。',1,0,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(123,782,'public/image_upload/projectIntrImages/782/20190514/030642520057c9dd409725a12e17ee54.jpg',2,1,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(124,783,'excel的学习，是日积月累的学习，学好基本功，是便捷简单的快乐。\n学习参考网:exceL学习交流中心、秋叶excel、exceL精英培训、excel不加班。',1,0,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(125,783,'public/image_upload/projectIntrImages/783/20190516/97f59a3f2fbe9826625809e414c6811c.jpg',2,1,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(126,786,'一起学习Vue~',1,0,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(127,786,'public/image_upload/projectIntrImages/786/20190516/4c0c58b1d6a1e70822088470396f1a61.jpg',2,1,'2019-09-14 12:11:33','2019-09-14 12:11:33'),(128,791,'哈哈哈哈\n',1,0,'2019-09-14 12:11:33','2019-09-14 12:11:33');

/*Table structure for table `project_type_label` */

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

/*Data for the table `project_type_label` */

insert  into `project_type_label`(`id`,`parent_id`,`label_name`,`create_time`,`update_time`) values (1,0,'外语','2019-09-14 12:15:07','2019-09-14 12:15:07'),(2,0,'阅读','2019-09-14 12:15:07','2019-09-14 12:15:07'),(3,0,'运动','2019-09-14 12:15:07','2019-09-14 12:15:07'),(4,0,'艺术兴趣','2019-09-14 12:15:07','2019-09-14 12:15:07'),(5,0,'亲子幼教','2019-09-14 12:15:07','2019-09-14 12:15:07'),(6,0,'教育','2019-09-14 12:15:07','2019-09-14 12:15:07'),(7,0,'职场','2019-09-14 12:15:07','2019-09-14 12:15:07'),(8,0,'健康生活','2019-09-14 12:15:07','2019-09-14 12:15:07'),(9,0,'美妆','2019-09-14 12:15:07','2019-09-14 12:15:07'),(10,1,'英语','2019-09-14 12:15:07','2019-09-14 12:15:07'),(11,1,'日语','2019-09-14 12:15:07','2019-09-14 12:15:07'),(12,1,'托福','2019-09-14 12:15:07','2019-09-14 12:15:07'),(13,1,'雅思','2019-09-14 12:15:07','2019-09-14 12:15:07'),(14,1,'韩语','2019-09-14 12:15:07','2019-09-14 12:15:07'),(15,2,'读书','2019-09-14 12:15:07','2019-09-14 12:15:07'),(16,2,'朗读','2019-09-14 12:15:07','2019-09-14 12:15:07'),(17,3,'健身','2019-09-14 12:15:07','2019-09-14 12:15:07'),(18,3,'跑步','2019-09-14 12:15:07','2019-09-14 12:15:07'),(19,3,'减脂','2019-09-14 12:15:07','2019-09-14 12:15:07'),(20,3,'瑜伽','2019-09-14 12:15:07','2019-09-14 12:15:07'),(21,4,'绘画','2019-09-14 12:15:07','2019-09-14 12:15:07'),(22,4,'书法','2019-09-14 12:15:07','2019-09-14 12:15:07'),(23,4,'声乐','2019-09-14 12:15:07','2019-09-14 12:15:07'),(24,4,'摄影','2019-09-14 12:15:07','2019-09-14 12:15:07'),(25,4,'手工','2019-09-14 12:15:07','2019-09-14 12:15:07'),(26,5,'孕产','2019-09-14 12:15:07','2019-09-14 12:15:07'),(27,5,'育儿','2019-09-14 12:15:07','2019-09-14 12:15:07'),(28,5,'steam','2019-09-14 12:15:07','2019-09-14 12:15:07'),(29,6,'小学','2019-09-14 12:15:07','2019-09-14 12:15:07'),(31,6,'初中','2019-09-14 12:15:07','2019-09-14 12:15:07'),(32,6,'高中','2019-09-14 12:15:07','2019-09-14 12:15:07'),(33,6,'公务员','2019-09-14 12:15:07','2019-09-14 12:15:07'),(34,6,'考研','2019-09-14 12:15:07','2019-09-14 12:15:07'),(35,7,'求职','2019-09-14 12:15:07','2019-09-14 12:15:07'),(36,7,'办公培训','2019-09-14 12:15:07','2019-09-14 12:15:07'),(37,8,'早起早睡','2019-09-14 12:15:07','2019-09-14 12:15:07'),(38,8,'养生','2019-09-14 12:15:07','2019-09-14 12:15:07'),(39,8,'心理学','2019-09-14 12:15:07','2019-09-14 12:15:07'),(40,9,'护肤','2019-09-14 12:15:07','2019-09-14 12:15:07'),(41,9,'化妆','2019-09-14 12:15:07','2019-09-14 12:15:07');

/*Table structure for table `punch_card_diary` */

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
  ` repair_punch_card_time` datetime NOT NULL COMMENT '补打卡时间，(年-月-日 时:分)',
  `like_user_num` int(11) NOT NULL DEFAULT '0' COMMENT '点赞人数，默认为0',
  `comment_num` int(11) NOT NULL DEFAULT '0' COMMENT '评论数，默认为0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `project_id` (`project_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

/*Data for the table `punch_card_diary` */

insert  into `punch_card_diary`(`id`,`project_id`,`user_id`,`text_content`,`punch_card_time`,`punch_card_address`,`address_longitude`,`address_latitude`,`visible_type`,`curr_diary_punch_card_day_num`,`have_sticky`,`is_repair_diary`,` repair_punch_card_time`,`like_user_num`,`comment_num`,`create_time`,`update_time`) values (1,713,1,'这是这个小程序的第一条打卡日记哈哈哈哈哈哈哈哈哈哈,upupup!!!','2018-10-21 12:34:00','五邑大学-玫瑰园宿舍',113.085850,22.594860,0,1,1,0,'2018-10-22 19:06:02',2,3,'2019-09-14 12:18:25','2019-09-14 12:18:25'),(2,713,1,'','2018-10-21 16:35:00','',0.000000,0.000000,1,1,0,0,'2018-10-22 19:05:00',1,0,'2019-09-14 12:16:18','2019-09-14 12:16:18'),(4,713,1,'','2018-10-21 22:20:00','',0.000000,0.000000,0,1,0,0,'2018-10-22 19:06:08',1,0,'2019-09-14 12:16:18','2019-09-14 12:16:18'),(5,713,1,'','2018-10-21 22:21:00','',0.000000,0.000000,0,1,0,0,'2018-10-22 19:06:11',2,0,'2019-09-14 12:16:18','2019-09-14 12:16:18'),(8,713,1,'','2018-10-21 22:41:00','',0.000000,0.000000,0,1,0,0,'2018-10-22 19:06:23',0,0,'2019-09-14 12:16:18','2019-09-14 12:16:18'),(9,720,8,'成员隐私类型日记测试','2018-10-22 11:41:00','五邑大学-玫瑰园宿舍',113.085850,22.594860,1,1,0,0,'2018-10-22 19:06:26',0,0,'2019-09-14 12:16:18','2019-09-14 12:16:18'),(10,720,8,'昨夜天平长安 当天上星河转 我命已定盘','2018-10-22 11:41:00','五邑大学',113.085410,22.593868,0,1,1,0,'2018-10-22 19:06:30',0,0,'2019-09-14 12:16:18','2019-09-14 12:16:18'),(11,720,1,'圈主的隐私类型日记测试考虑考虑','2018-10-22 11:46:00','五邑大学-玫瑰园宿舍',113.085850,22.594860,1,1,0,0,'2018-10-22 19:06:33',0,0,'2019-09-14 12:16:18','2019-09-14 12:16:18'),(12,720,1,'早上好，喵喵喵。。。','2018-10-22 11:47:00','东湖公园',113.087900,22.591830,0,1,1,0,'2018-10-22 19:06:36',1,1,'2019-09-14 12:16:18','2019-09-14 12:16:18'),(13,738,8,'很烦，继续背单词！','2018-10-22 13:36:00','五邑大学-玫瑰园宿舍',113.085850,22.594860,1,1,0,0,'2018-10-22 19:06:39',0,0,'2019-09-14 12:16:18','2019-09-14 12:16:18'),(14,738,8,'很烦，不背了。。','2018-10-22 13:37:00','五邑大学-南主楼',113.083770,22.594130,0,1,0,0,'2018-10-22 19:06:42',0,0,'2019-09-14 12:16:18','2019-09-14 12:16:18'),(15,758,8,'莫意义。。。','2018-10-23 13:31:00','五邑大学',113.083990,22.594610,0,1,0,0,'2019-09-14 12:19:37',0,0,'2019-09-14 12:19:37','2019-09-14 12:19:37'),(16,758,8,'打卡+1！','2018-10-23 13:32:00','双龙广场大酒店',113.077070,22.599550,0,1,0,0,'2019-09-14 12:20:44',0,0,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(17,758,8,'打卡+2！','2018-10-23 14:17:00','',0.000000,0.000000,0,1,0,0,'2019-09-14 12:20:44',1,2,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(18,758,8,'','2018-10-23 14:21:00','北郊蓬江区人民政府(建设二路东)',113.078450,22.595140,0,1,0,0,'2019-09-14 12:20:44',2,1,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(19,758,8,'我曾被遥远的梦逼着我仰望星空...','2018-10-23 14:24:00','五邑大学',113.085188,22.595814,0,1,0,0,'2019-09-14 12:20:44',2,0,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(20,738,8,'','2018-10-23 14:33:00','',0.000000,0.000000,0,2,0,0,'2019-09-14 12:20:44',0,0,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(21,738,8,'还是要坚持的，咸鱼也要有梦想，万一实现了呢','2018-10-23 14:40:00','',0.000000,0.000000,0,2,0,0,'2019-09-14 12:20:44',0,1,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(22,720,1,'东湖公园跑步打卡！','2018-10-24 14:44:00','',0.000000,0.000000,0,2,0,0,'2019-09-14 12:20:44',1,1,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(23,717,8,'','2018-10-24 15:56:00','五邑大学',113.083990,22.594610,0,1,0,0,'2019-09-14 12:20:44',1,2,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(24,720,8,'对应我而言，跑步最大的快乐就是在最后的时刻体验那种濒临极限的感觉。','2018-10-24 21:35:00','北郊蓬江区人民政府(建设二路东)',113.078450,22.595140,0,2,0,0,'2019-09-14 12:20:44',1,6,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(26,720,1,'东湖公园跑步打卡','2018-11-11 16:49:00','五邑大学',113.081883,22.596210,0,3,0,0,'2019-09-14 12:20:44',0,3,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(41,720,1,'考虑考虑','2019-04-25 06:54:00','',0.000000,0.000000,0,4,0,0,'2019-09-14 12:20:44',1,0,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(42,720,1,'','2019-04-25 06:55:00','',0.000000,0.000000,0,4,0,0,'2019-09-14 12:20:44',1,0,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(44,713,1,'最高评分的一次！！！','2019-04-25 06:57:00','',0.000000,0.000000,0,2,0,0,'2019-09-14 12:20:44',0,0,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(45,713,1,'不同的行为之间也具有着位置共同性，比如，有些行为的作用位置都是在应用执行前，有些行为则是在模板输出之后，我们把这些行为发生作用的位置称之为钩子。 \n当应用程序运行到这个钩子的时候则会被拦截下来,统一执行绑定到该钩子中的相关行为，这类似于AOP编程中的切面概念，给某个钩子绑定相关行为就成了一种类AOP编程的思想','2019-04-25 12:09:00','',0.000000,0.000000,0,2,0,0,'2019-09-14 12:20:44',0,0,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(46,713,1,'每天失眠的时候就这样一遍一遍的看，就想象着自己在国际空间站，往下看着这个美丽的星球，那瞬间心便安定了许多。','2019-04-25 12:14:00','',0.000000,0.000000,0,2,0,0,'2019-09-14 12:20:44',1,2,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(57,781,1,'罗浮山之旅，难忘的回忆！','2019-05-14 09:35:00','',0.000000,0.000000,0,1,0,0,'2019-09-14 12:20:44',0,0,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(58,782,8,'\n我曾被无数的冷风吹透我胸口，\n我曾被遥远的梦逼着我仰望星空，\n我曾被无数的嘲讽让我放弃我的音乐梦，我曾被无数的黄土 淹没我的澎湃汹涌，\n我曾想要我的歌声 无尽沉沦的感动，\n我曾把他们当成我风雨过后那一道彩虹，我曾把堕落的原因 都丢给时间，\n我曾把机会就扔在我眼前，\n我曾把完整的镜子打碎 夜晚的枕头都是眼泪，\n我多想让过去重来 再给我一次机会，我想说过去的时间 我谁都不为，\n除了空谈 也就是 事事非非','2019-05-14 09:48:00','五邑大学-玫瑰园宿舍',113.085850,22.594860,0,1,0,0,'2019-09-14 12:20:44',0,0,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(59,782,8,'生而为人且善良 长亦成芒，近我者本赤 后刁我者无望，妒心人皆有之，怕容不下沧海灵诗，世间万物缓而长，愿他们永无止，苦苦念求写于宣纸，纵使岁月无情无意奔驰，有求于苍天 必有出头之日，懒症发作就别讲光明之时，苦尽甘来定不负生而善之，苍茫落幕 心系远方','2019-05-16 01:04:00','五邑大学玫瑰园宿舍(江门市蓬江区通济路)',113.085520,22.594131,0,2,0,0,'2019-09-14 12:20:44',1,1,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(61,720,8,'体测，废了','2019-05-16 11:45:00','五邑大学-体育馆',113.084950,22.598550,0,3,0,0,'2019-09-14 12:20:44',1,1,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(63,786,1,'阅读Vue文档打卡！！！','2019-05-16 21:53:00','',0.000000,0.000000,0,1,0,0,'2019-09-14 12:20:44',0,0,'2019-09-14 12:20:44','2019-09-14 12:20:44'),(65,713,1,'…','2019-05-27 22:07:00','',0.000000,0.000000,0,3,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(66,782,1,'会在何处见到你，莫非前尘已注定，\n飞过时空的距离，却囿于刀剑光影，\n三月春花渐次醒，迢迢年华谁老去，\n是劫是缘随我心，除了你万敌不侵，\n当恩怨各一半，我怎么圈揽，\n看灯笼血红染，寻仇已太晚，\n月下门童喟叹，昨夜太平长安，\n当天上星河转，我命已定盘，\n待绝笔墨痕干，宿敌已来犯，\n我借你的孤单，今生恐怕难还，\n缠扰孤岛的雪雨，飘飘洒洒谁来停，\n摘取一颗海上星，陪我终夜不孤寂，\n灵柩长埋深谷底，没有永远的秘密，\n染指江湖结悲局，无人逃得过宿命。','2019-05-28 01:16:00','五邑大学-玫瑰园宿舍',113.085850,22.594860,0,1,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(67,782,1,'晚安，好梦。。。','2019-05-28 01:17:00','五邑大学-玫瑰园宿舍',113.085850,22.594860,0,1,0,0,'2019-09-14 12:20:45',1,1,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(68,786,8,'文档看完了｡◕‿◕｡','2019-05-28 22:54:00','五邑大学-玫瑰园宿舍',113.085850,22.594860,0,1,0,0,'2019-09-14 12:20:45',1,1,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(69,786,1,'深入学习','2019-06-01 16:14:00','',0.000000,0.000000,0,2,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(70,743,8,'个人私密圈子，只能通过圈主邀请进入','2019-06-01 18:31:00','',0.000000,0.000000,0,1,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(71,787,1,'打卡测试','2019-06-01 18:42:00','',0.000000,0.000000,0,1,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(77,789,1,'测试一下','2019-06-01 19:16:00','',0.000000,0.000000,0,1,0,0,'2019-09-14 12:20:45',0,1,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(78,778,1,'到不了的地方…','2019-06-01 19:17:00','五邑大学',113.085510,22.594131,0,1,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(79,790,1,'','2019-06-20 15:36:00','中粮商务公园',113.923740,22.576580,0,1,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(80,713,1,'ssss','2019-08-07 20:48:00','',0.000000,0.000000,0,4,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(82,713,1,'','2019-08-06 21:06:00','',0.000000,0.000000,0,4,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(83,798,1,'第一天…','2019-08-06 21:10:00','棠下(广州市天河区)',113.381165,23.126880,0,1,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(85,798,1,'第二天','2019-08-07 23:25:00','棠下(广州市天河区)',113.381600,23.126797,0,2,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(86,798,1,'第三天…','2019-08-08 23:18:00','棠下(广州市天河区)',113.381325,23.126883,0,3,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(88,798,1,'第四天','2019-08-09 22:25:00','棠下(广州市天河区)',113.381584,23.126886,0,4,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(89,798,1,'第五天…','2019-08-10 22:29:00','棠下(广州市天河区)',113.381600,23.126781,0,5,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(90,798,1,'第六天…','2019-08-11 19:51:00','棠下(广州市天河区)',113.381640,23.126890,0,6,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(91,798,1,'第七天…','2019-08-12 23:36:00','棠下(广州市天河区)',113.381180,23.126886,0,7,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(92,798,1,'第八天…','2019-08-13 23:10:00','棠下(广州市天河区)',113.381620,23.126814,0,8,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45'),(93,798,1,'第九天…','2019-08-14 23:42:00','棠下(广州市天河区)',113.381615,23.126986,0,9,0,0,'2019-09-14 12:20:45',0,0,'2019-09-14 12:20:45','2019-09-14 12:20:45');

/*Table structure for table `punch_card_project` */

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
) ENGINE=InnoDB AUTO_INCREMENT=814 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

/*Data for the table `punch_card_project` */

insert  into `punch_card_project`(`id`,`project_name`,`privacy_type`,`open_audit`,`type_label`,`cover_img_url`,`creator_id`,`creator_introduce`,`weixin_num`,`all_punch_card_num`,`today_punch_card_num`,`attend_user_num`,`create_time`,`update_time`) values (713,'Armin的个人自习室',0,0,'阅读-读书','default_cover_img',1,'啦啦啦啦啦啦啦啦绿啦啦啦啦啦啦啦啦啦啦啦啦绿绿绿绿','sdhio48926',11,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(717,'个人护肤打卡',1,0,'美妆-护肤','default_cover_img',8,'','',1,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(720,'每天坚持跑步',0,0,'运动-跑步','public/image_upload/project_cover_img/sys_recommend/20181001/0a52b6b544e9fbb052bc112a5fb2a7b5.png',1,'hello，我是MYXuu','',10,0,2,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(737,'英语六级学习打卡',0,0,'外语-英语','public/image_upload/project_cover_img/sys_recommend/20181001/dbf1e4d5bc17e158420feecb14a37b48.png',1,'英语打卡走起啦啦啦啦啦啦啦啦啦啦啦考虑考虑啦','',0,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(738,'英语学习打卡',0,0,'外语-英语','public/image_upload/project_cover_img/sys_recommend/20181001/3fddb548f4998a2717e293c0d9594364.png',8,'','',4,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(743,'私密类型圈子测试',1,0,'外语-韩语,外语-雅思','default_cover_img',8,'','',1,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(758,'雅思考证',0,0,'外语-雅思,外语-英语','public/image_upload/project_cover_img/sys_recommend/20181001/0a52b6b544e9fbb052bc112a5fb2a7b5.png',8,'','',5,0,2,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(777,'早起早睡',0,0,'健康生活-早起早睡,健康生活-养生','public/image_upload/project_cover_img/custom/777/20190507/4d188daab0ce2cacfb7deec48deb98d0.jpg',1,'','',0,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(778,'星辰大海',0,0,'艺术兴趣-摄影','public/image_upload/project_cover_img/custom/778/20190507/0c2dd40a83b3fb02991815fa4e39ca07.jpg',1,'','',1,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(779,'实习打卡',0,0,'职场-求职','public/image_upload/project_cover_img/sys_recommend/20181001/4fc0dda90ada63fdd1ebf712054b07c0.png',1,'','',0,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(780,'防晒打卡圈子',0,0,'美妆-护肤','public/image_upload/project_cover_img/sys_recommend/20181001/4fc0dda90ada63fdd1ebf712054b07c0.png',1,'','',0,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(781,'阿明的健身计划',0,0,'运动-健身','public/image_upload/project_cover_img/sys_recommend/20181001/520d70c0a777ec055df58c3fed943b37.png',1,'加油＾０＾~，一起健身增重！！！','qq1977492949',1,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(782,'每日单曲循环',0,0,'艺术兴趣-声乐','public/image_upload/project_cover_img/custom/782/20190514/abd41c220e511d4d4b99f1420f6a7e7c.png',1,'我多想让过去重来 再给我一次机会，\n我想说过去的时间 我谁都不为','',4,0,2,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(783,'办公技能学习',0,0,'职场-办公培训','public/image_upload/project_cover_img/sys_recommend/20181001/520d70c0a777ec055df58c3fed943b37.png',1,'Armin','qq1977492949',0,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(786,'前端框架学习',0,0,'阅读-读书','public/image_upload/project_cover_img/sys_recommend/20181001/0a52b6b544e9fbb052bc112a5fb2a7b5.png',8,'hello,我是MYXuu','',3,0,2,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(787,'测试专用圈子',1,0,'外语-韩语,外语-雅思,外语-托福,外语-英语','public/image_upload/project_cover_img/sys_recommend/20181001/dbf1e4d5bc17e158420feecb14a37b48.png',1,'','',1,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(788,'测试专用圈子二',1,0,'教育-考研','default_cover_img',1,'','',0,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(789,'增量索引测试',0,0,'阅读-读书','public/image_upload/project_cover_img/sys_recommend/20181001/3fddb548f4998a2717e293c0d9594364.png',1,'','',1,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(790,'哭泣',0,0,'外语-雅思,外语-托福','default_cover_img',1,'','',1,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(791,'啊啊',1,0,'外语-韩语','default_cover_img',1,'','',0,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(792,'经历了',0,0,'外语-韩语,外语-雅思','default_cover_img',1,'','',0,0,1,'2019-09-14 12:21:56','2019-09-14 12:21:56'),(793,'读书总结',0,0,'阅读-读书','default_cover_img',1,'','',0,0,1,'2019-09-14 12:21:57','2019-09-14 12:21:57'),(794,'音乐',0,0,'艺术兴趣-声乐','default_cover_img',1,'','',0,0,1,'2019-09-14 12:21:57','2019-09-14 12:21:57'),(795,'技术博客',0,0,'阅读-读书','default_cover_img',1,'','',0,0,1,'2019-09-14 12:21:57','2019-09-14 12:21:57'),(796,'新增加',0,0,'阅读-读书','default_cover_img',1,'','',0,0,1,'2019-09-14 12:21:57','2019-09-14 12:21:57'),(797,'海边风景',0,0,'运动-跑步','default_cover_img',1,'','',0,0,1,'2019-09-14 12:21:57','2019-09-14 12:21:57'),(798,'校内四级考试复习',0,0,'外语-英语','public/image_upload/project_cover_img/sys_recommend/20181001/520d70c0a777ec055df58c3fed943b37.png',1,'','',9,0,2,'2019-09-14 12:21:57','2019-09-14 12:21:57'),(799,'测试圈子哦',0,0,'阅读-读书','default_cover_img',1,'','',0,0,1,'2019-09-15 19:39:46','2019-09-15 19:39:46'),(800,'测试圈子哦',0,0,'阅读-读书','default_cover_img',1,'','',0,0,1,'2019-09-15 19:54:26','2019-09-15 19:54:26'),(801,'顺风使帆',0,0,'阅读-读书','default_cover_img',1,'','',0,0,1,'2019-09-15 19:54:56','2019-09-15 21:38:34'),(802,'测试圈子哦',0,0,'阅读-读书','default_cover_img',1,'','',0,0,1,'2019-09-15 19:55:01','2019-09-15 19:55:01'),(803,'测试圈子一',0,0,'阅读-读书','default_cover_img',1,'','',0,0,1,'2019-09-15 20:12:31','2019-09-15 20:12:31'),(804,'待摊费用',0,0,'阅读-读书','default_cover_img',1,'','',0,0,1,'2019-09-15 20:15:26','2019-09-15 20:15:26'),(805,'的身份是',0,0,'阅读-读书','default_cover_img',1,'sfesf','sfdsfesfe',0,0,1,'2019-09-15 20:18:55','2019-09-15 20:23:13'),(806,'说分手',0,0,'阅读-读书','default_cover_img',1,'','',0,0,1,'2019-09-15 20:46:21','2019-09-15 20:46:21'),(807,'我测试',0,0,'运动-健身','default_cover_img',1,'','',0,0,1,'2019-09-15 20:47:53','2019-09-15 20:47:53'),(808,'的双丰收',0,0,'阅读-读书','default_cover_img',1,'','',0,0,1,'2019-09-15 21:16:19','2019-09-15 21:16:19'),(809,'是非得失',0,0,'阅读-读书','default_cover_img',1,'','',0,0,1,'2019-09-15 21:19:12','2019-09-15 21:19:12'),(810,'顺风使帆12',0,0,'阅读-读书','default_cover_img',1,'','',0,0,1,'2019-09-15 21:22:41','2019-09-15 21:39:19'),(811,'测试啊是否',0,0,'教育-初中','default_cover_img',1,'','',0,0,1,'2019-09-15 22:10:52','2019-09-15 22:18:04'),(812,'说分手',0,0,'阅读-读书','default_cover_img',1,'','',0,0,1,'2019-09-15 22:32:32','2019-09-15 22:32:32'),(813,'顺风使帆',0,0,'阅读-朗读','default_cover_img',1,'','',0,0,1,'2019-09-15 22:45:43','2019-09-15 22:45:43');

/*Table structure for table `sys_recommend_bg_img` */

DROP TABLE IF EXISTS `sys_recommend_bg_img`;

CREATE TABLE `sys_recommend_bg_img` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键、无符号',
  `bg_img_url` varchar(255) NOT NULL COMMENT '系统提供的个人主页背景图地址，不为空',
  `is_recommend` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否被设置为系统推荐的背景图，0--未设置为推荐（默认） 1--已设置为推荐、不为空',
  `use_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '当前图片被设置为个人主页背景图人数，默认为0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

/*Data for the table `sys_recommend_bg_img` */

insert  into `sys_recommend_bg_img`(`id`,`bg_img_url`,`is_recommend`,`use_num`,`create_time`,`update_time`) values (38,'../../image_upload/bg_image/system_recommend/20180916/d0ac5dca2ab76d00e27ebac1b8e2f1d4.png',1,0,'2018-09-16 00:00:00','2019-09-14 12:22:42'),(39,'../../image_upload/bg_image/system_recommend/20190621/074e48cc69118b4ceb542418c3515ed9.gif',1,0,'2019-06-21 00:00:00','2019-09-14 12:22:42');

/*Table structure for table `sys_recommend_pro_cover_img` */

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

/*Data for the table `sys_recommend_pro_cover_img` */

insert  into `sys_recommend_pro_cover_img`(`id`,`img_url`,`is_recommend`,`use_num`,`create_time`,`update_time`) values (2,'public/image_upload/project_cover_img/sys_recommend/20181001/254abcef77c16e09ee8b4ee7e817b550.png',1,2,'2019-09-14 12:23:53','2019-09-14 12:23:53'),(4,'public/image_upload/project_cover_img/sys_recommend/20181001/d9bab9f91d61f791aee6189a8df9d227.png',1,0,'2019-09-14 12:23:53','2019-09-14 12:23:53'),(5,'public/image_upload/project_cover_img/sys_recommend/20181001/d9635c6f91583f2b736be98d465bf2a3.png',0,0,'2019-09-14 12:23:53','2019-09-14 12:23:53'),(6,'public/image_upload/project_cover_img/sys_recommend/20181001/0ff55bfee64fb2111bf9de161c583d17.png',0,0,'2019-09-14 12:23:53','2019-09-14 12:23:53'),(7,'public/image_upload/project_cover_img/sys_recommend/20181001/dbf1e4d5bc17e158420feecb14a37b48.png',1,1,'2019-09-14 12:23:53','2019-09-14 12:23:53'),(8,'public/image_upload/project_cover_img/sys_recommend/20181001/ca35259f0f8f09d8a00203e8346f2d83.png',0,0,'2019-09-14 12:23:53','2019-09-14 12:23:53'),(9,'public/image_upload/project_cover_img/sys_recommend/20181001/ff4fd362017c3e150b7189209e72e080.png',0,0,'2019-09-14 12:23:53','2019-09-14 12:23:53'),(10,'public/image_upload/project_cover_img/sys_recommend/20181001/0a52b6b544e9fbb052bc112a5fb2a7b5.png',1,1,'2019-09-14 12:23:53','2019-09-14 12:23:53'),(11,'public/image_upload/project_cover_img/sys_recommend/20181001/f49bc61b288f144e54e25d5fa7a09ed3.png',0,0,'2019-09-14 12:23:53','2019-09-14 12:23:53'),(12,'public/image_upload/project_cover_img/sys_recommend/20181001/3fddb548f4998a2717e293c0d9594364.png',1,1,'2019-09-14 12:23:53','2019-09-14 12:23:53'),(13,'public/image_upload/project_cover_img/sys_recommend/20181001/861dc275dab246c8c1b44d3469cbfa6a.png',0,0,'2019-09-14 12:23:53','2019-09-14 12:23:53'),(14,'public/image_upload/project_cover_img/sys_recommend/20181001/4fc0dda90ada63fdd1ebf712054b07c0.png',1,2,'2019-09-14 12:23:54','2019-09-14 12:23:54'),(15,'public/image_upload/project_cover_img/sys_recommend/20181001/435e424e802d49c0ecbdfb6fdf2b0b56.png',0,0,'2019-09-14 12:23:54','2019-09-14 12:23:54'),(16,'public/image_upload/project_cover_img/sys_recommend/20181001/ad1099a2f54ae323838d26b40f3c556d.png',0,0,'2019-09-14 12:23:54','2019-09-14 12:23:54'),(17,'public/image_upload/project_cover_img/sys_recommend/20181001/520d70c0a777ec055df58c3fed943b37.png',1,3,'2019-09-14 12:23:54','2019-09-14 12:23:54'),(18,'public/image_upload/project_cover_img/sys_recommend/20181001/8d2cf55352324f703246476599a3c7dc.png',1,0,'2019-09-14 12:23:54','2019-09-14 12:23:54'),(19,'public/image_upload/project_cover_img/sys_recommend/20181001/9723dce0c7ca7144458c207c05aea803.png',0,0,'2019-09-14 12:23:54','2019-09-14 12:23:54'),(20,'public/image_upload/project_cover_img/sys_recommend/20181001/995c67b82c610fbd6c7a8ce4c17a6811.png',0,0,'2019-09-14 12:23:54','2019-09-14 12:23:54');

/*Table structure for table `unread_news_count` */

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

/*Data for the table `unread_news_count` */

insert  into `unread_news_count`(`id`,`user_id`,`unread_news_type`,`unread_news_num`,`create_time`,`update_time`) values (1,8,'likeNews',2,'2019-09-14 12:24:28','2019-09-14 12:24:28'),(2,1,'likeNews',0,'2019-09-14 12:24:28','2019-09-14 12:24:28'),(3,8,'commentNews',0,'2019-09-14 12:24:28','2019-09-14 12:24:28'),(4,1,'commentNews',0,'2019-09-14 12:24:28','2019-09-14 14:01:28');

/*Table structure for table `user` */

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
  `model` varchar(20) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

/*Data for the table `user` */

insert  into `user`(`id`,`open_id`,`nick_name`,`avatar_url`,`gender`,`country`,`province`,`city`,`language`,`pass_word`,`user_name`,`channel`,`brand`,`model`,`wx_language`,`system`,`platform`,`birthday`,`person_sign`,`bg_img`,`personal_page_visit_num`,`create_time`,`update_time`) values (1,'onCAd5Pn1Q55MiSDpAi_NwFIa7WE','陌上花开','https://wx.qlogo.cn/mmopen/vi_32/gP6xl5yc7Ljv5Wcw7Mnh2Dj7AVsrHJyYDXAMtefyL64mia3Hzic3N2ia40bMVvvxfOnsPpVCkjicic3sIjzOq4iatMRg/132',1,NULL,NULL,NULL,NULL,NULL,NULL,'wxmp',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,'2019-09-14 11:49:52','2019-09-14 12:29:52'),(8,'efsfesfesfsef','sfesf','https://wx.qlogo.cn/mmopen/vi_32/kUVg3UkWH6V3Wt3v8Wlfk9egzziajOKibQbic7Sr55L350jibaxCyl5W5uqrYM8jsibY7J4xxTticeibzg2LGAia2yMomA/132',1,NULL,NULL,NULL,NULL,NULL,NULL,'wxmp',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,'2019-09-14 11:49:52','2019-09-14 11:49:52'),(9,'0','陌上花开','https://wx.qlogo.cn/mmopen/vi_32/gP6xl5yc7Ljv5Wcw7Mnh2Dj7AVsrHJyYDXAMtefyL64mia3Hzic3N2ia40bMVvvxfOnsPpVCkjicic3sIjzOq4iatMRg/132',1,NULL,NULL,NULL,NULL,NULL,NULL,'wxmp',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,'2019-09-14 21:02:52','2019-09-14 21:02:52');

/*Table structure for table `user_project_record` */

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
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_user_id` (`user_id`) USING BTREE,
  KEY `fk_project_id` (`project_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

/*Data for the table `user_project_record` */

insert  into `user_project_record`(`id`,`user_id`,`project_id`,`is_creator`,`attend_status`,`all_punch_card_num`,`attend_time`,`last_punch_card_time`,`create_time`,`update_time`) values (1,1,713,1,1,11,'2018-10-12','2019-08-07 21:06:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(5,8,717,1,1,1,'2018-10-13','2018-10-24 15:56:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(8,1,720,1,1,6,'2018-10-14','2019-04-25 06:55:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(21,8,720,0,1,4,'2018-10-15','2019-05-16 11:45:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(28,1,737,1,1,0,'2018-10-15','2019-09-14 12:27:56','2019-09-14 12:27:56','2019-09-14 12:27:56'),(29,8,738,1,1,4,'2018-10-16','2018-10-23 14:40:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(49,8,758,1,1,5,'2018-10-17','2018-10-23 14:24:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(59,1,758,0,1,0,'2018-11-05','2019-09-14 12:27:56','2019-09-14 12:27:56','2019-09-14 12:27:56'),(62,1,777,1,1,0,'2019-05-07','2019-09-14 12:27:56','2019-09-14 12:27:56','2019-09-14 12:27:56'),(63,1,778,1,1,1,'2019-05-07','2019-06-01 19:17:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(64,1,779,1,1,0,'2019-05-07','2019-09-14 12:27:56','2019-09-14 12:27:56','2019-09-14 12:27:56'),(67,1,780,1,1,0,'2019-05-14','2019-09-14 12:27:56','2019-09-14 12:27:56','2019-09-14 12:27:56'),(68,1,781,1,1,1,'2019-05-14','2019-05-14 09:35:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(69,1,782,1,1,2,'2019-05-14','2019-05-28 01:17:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(70,8,782,0,1,2,'2019-05-14','2019-05-16 01:04:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(71,1,783,1,1,0,'2019-05-16','2019-09-14 12:27:56','2019-09-14 12:27:56','2019-09-14 12:27:56'),(74,8,786,1,1,1,'2019-05-16','2019-05-28 22:54:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(75,1,786,0,1,2,'2019-05-16','2019-06-01 16:14:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(76,1,787,1,1,1,'2019-05-19','2019-06-01 19:03:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(77,1,788,1,1,0,'2019-05-28','2019-06-01 19:06:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(78,1,789,1,1,1,'2019-05-29','2019-06-01 19:16:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(79,1,790,1,1,1,'2019-06-20','2019-06-20 15:36:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(80,1,791,1,1,0,'2019-06-21','2019-09-14 12:27:56','2019-09-14 12:27:56','2019-09-14 12:27:56'),(81,1,792,1,1,0,'2019-06-21','2019-09-14 12:27:56','2019-09-14 12:27:56','2019-09-14 12:27:56'),(82,1,793,1,1,0,'2019-07-28','2019-09-14 12:27:56','2019-09-14 12:27:56','2019-09-14 12:27:56'),(83,1,794,1,1,0,'2019-07-28','2019-09-14 12:27:56','2019-09-14 12:27:56','2019-09-14 12:27:56'),(84,1,795,1,1,0,'2019-07-28','2019-09-14 12:27:56','2019-09-14 12:27:56','2019-09-14 12:27:56'),(85,1,796,1,1,0,'2019-07-29','2019-09-14 12:27:56','2019-09-14 12:27:56','2019-09-14 12:27:56'),(86,1,797,1,1,0,'2019-07-29','2019-09-14 12:27:56','2019-09-14 12:27:56','2019-09-14 12:27:56'),(87,1,798,1,1,9,'2019-08-06','2019-08-14 23:42:00','2019-09-14 12:27:56','2019-09-14 12:27:56'),(88,8,798,0,1,0,'2019-08-10','2019-09-14 12:27:56','2019-09-14 12:27:56','2019-09-14 12:27:56');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
