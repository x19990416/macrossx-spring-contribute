/*
Navicat MySQL Data Transfer

Source Server         : rds6a1w2l8bn42e9549yo.mysql.rds.aliyuncs.com
Source Server Version : 50629
Source Host           : rds6a1w2l8bn42e9549yo.mysql.rds.aliyuncs.com:3306
Source Database       : kindergarden

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2016-11-22 14:36:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `file`
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `refid` bigint(20) DEFAULT NULL COMMENT '关联id',
  `name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `type` varchar(20) NOT NULL COMMENT '文件类型',
  `file` longblob NOT NULL COMMENT '文件实体',
  `create_user` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of file
-- ----------------------------
