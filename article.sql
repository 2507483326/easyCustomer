/*
Navicat MariaDB Data Transfer

Source Server         : local
Source Server Version : 100122
Source Host           : localhost:3306
Source Database       : article

Target Server Type    : MariaDB
Target Server Version : 100122
File Encoding         : 65001

Date: 2017-04-30 23:20:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻ID 主键',
  `title` varchar(64) NOT NULL COMMENT '新闻标题',
  `content` text NOT NULL COMMENT '新闻内容',
  `categoryId` int(11) NOT NULL COMMENT '类别ID',
  `state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0 为普通，1为加精，2为置顶',
  `createTime` datetime NOT NULL COMMENT '新闻创建时间',
  `creatorId` int(11) NOT NULL DEFAULT '0' COMMENT '创建人 默认为0管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for article_message
-- ----------------------------
DROP TABLE IF EXISTS `article_message`;
CREATE TABLE `article_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻信息ID 主键',
  `articleId` int(11) NOT NULL COMMENT '新闻ID',
  `admire` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数量 默认为0',
  `competitive` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否加精 默认为否',
  `sticky` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否置顶 默认为否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_message
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别ID 主键',
  `name` varchar(64) NOT NULL COMMENT '类别名称',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0 此类别启用 1此类别禁用',
  `createTime` date NOT NULL COMMENT '创建时间',
  `creatorId` int(11) NOT NULL DEFAULT '0' COMMENT '创建人的编号 默认为0管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for rule
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `path` text,
  `rule` text,
  `sort` int(11) DEFAULT NULL,
  `createId` bigint(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `remarks` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rule
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(128) DEFAULT NULL,
  `mobile` varchar(16) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `nickName` varchar(32) DEFAULT NULL,
  `avatar` char(32) DEFAULT NULL,
  `password` char(64) DEFAULT NULL,
  `salt` char(6) DEFAULT NULL COMMENT '用户支付密码的盐',
  `state` tinyint(4) DEFAULT NULL,
  `regTime` datetime DEFAULT NULL COMMENT ' 用户注册时间',
  `regIp` char(16) DEFAULT NULL,
  `regProvince` varchar(16) DEFAULT NULL COMMENT '用户注册省份',
  `regCity` varchar(16) DEFAULT NULL COMMENT '用户注册城市',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10000', 'admin', '17752850085', '27447985@qq.com', 'admin', 'default.jpg', '8e52c55f3389d90c54d1e19671ebe0af0c58bec8f52de266c2da01df38d0a0d9', null, '0', null, null, null, null);
INSERT INTO `user` VALUES ('10002', '2507483326@qq.com', null, '2507483326@qq.com', 'Epat', 'default.jpg', '788490e36ea7a3d102fcf9927cae3270', '123456', null, null, null, null, null);
