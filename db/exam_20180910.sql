/*
Navicat MariaDB Data Transfer

Source Server         : 本地服务
Source Server Version : 100211
Source Host           : localhost:3369
Source Database       : exam

Target Server Type    : MariaDB
Target Server Version : 100211
File Encoding         : 65001

Date: 2018-09-10 18:36:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exam_question_bank
-- ----------------------------
DROP TABLE IF EXISTS `exam_question_bank`;
CREATE TABLE `exam_question_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '题目',
  `answer` varchar(255) DEFAULT NULL COMMENT '答案',
  `true_answer` varchar(255) DEFAULT NULL COMMENT '正确答案',
  `jie_xi` varchar(500) DEFAULT '' COMMENT '解释',
  `note` varchar(500) DEFAULT '' COMMENT '说明',
  `question_bank_id` varchar(32) DEFAULT NULL COMMENT '题库ID',
  `question_bank_name` varchar(20) DEFAULT '' COMMENT '题库名称',
  `category_id` varchar(20) DEFAULT '' COMMENT '类别',
  `course_id` varchar(30) DEFAULT '' COMMENT '科目名称',
  `type` varchar(1) DEFAULT '' COMMENT '题目类型',
  `is_lock` int(11) unsigned zerofill DEFAULT NULL,
  `created_by` varchar(32) DEFAULT '' COMMENT '创建人',
  `created_date` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '创建时间',
  `updated_by` varchar(32) DEFAULT '' COMMENT '修改人',
  `updated_date` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1029 DEFAULT CHARSET=utf8 COMMENT='题库';

-- ----------------------------
-- Records of exam_question_bank
-- ----------------------------

-- ----------------------------
-- Table structure for exam_roll
-- ----------------------------
DROP TABLE IF EXISTS `exam_roll`;
CREATE TABLE `exam_roll` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roll_id` varchar(32) DEFAULT NULL COMMENT '卷',
  `question_bank_id` varchar(32) DEFAULT '' COMMENT '题库',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卷';

-- ----------------------------
-- Records of exam_roll
-- ----------------------------

-- ----------------------------
-- Table structure for public_login
-- ----------------------------
DROP TABLE IF EXISTS `public_login`;
CREATE TABLE `public_login` (
  `customer_id` varchar(32) NOT NULL DEFAULT '' COMMENT '客户ID',
  `access_id` varchar(32) NOT NULL DEFAULT '' COMMENT '访问账号',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `is_lock` tinyint(1) DEFAULT NULL COMMENT '是否锁定',
  PRIMARY KEY (`customer_id`,`access_id`),
  KEY `index_customerId` (`customer_id`) USING BTREE,
  KEY `index_pwd` (`password`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录表';

-- ----------------------------
-- Records of public_login
-- ----------------------------
INSERT INTO `public_login` VALUES ('*', 'superAdmin', 'sa123456', '0');
