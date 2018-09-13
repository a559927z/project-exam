/*
Navicat MariaDB Data Transfer

Source Server         : localhost
Source Server Version : 100211
Source Host           : localhost:3369
Source Database       : exam

Target Server Type    : MariaDB
Target Server Version : 100211
File Encoding         : 65001

Date: 2018-09-14 00:49:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exam_question_bank
-- ----------------------------
DROP TABLE IF EXISTS `exam_question_bank`;
CREATE TABLE `exam_question_bank` (
  `question_id` varchar(32) NOT NULL COMMENT '题目ID',
  `title` varchar(255) DEFAULT NULL COMMENT '题目',
  `answer` varchar(500) DEFAULT '' COMMENT '答案',
  `true_answer` varchar(500) DEFAULT '' COMMENT '正确答案',
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
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='题库';

-- ----------------------------
-- Table structure for exam_question_bank_score
-- ----------------------------
DROP TABLE IF EXISTS `exam_question_bank_score`;
CREATE TABLE `exam_question_bank_score` (
  `question_bank_id` varchar(32) NOT NULL COMMENT '题库ID',
  `question_bank_type` varchar(1) NOT NULL DEFAULT '' COMMENT '题型（1：单选，2多选，3是非）',
  `score` double(3,1) DEFAULT NULL COMMENT '题目分数',
  PRIMARY KEY (`question_bank_id`,`question_bank_type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for exam_question_bank_true_answer
-- ----------------------------
DROP TABLE IF EXISTS `exam_question_bank_true_answer`;
CREATE TABLE `exam_question_bank_true_answer` (
  `question_id` varchar(32) NOT NULL COMMENT '题目ID',
  `true_answer_id` varchar(32) NOT NULL DEFAULT '' COMMENT '答案ID',
  `true_answer` varchar(500) NOT NULL DEFAULT '' COMMENT '答案',
  `question_bank_id` varchar(32) NOT NULL DEFAULT '' COMMENT '题库ID',
  `sort` int(1) NOT NULL COMMENT '排序',
  PRIMARY KEY (`question_id`,`true_answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for public_config
-- ----------------------------
DROP TABLE IF EXISTS `public_config`;
CREATE TABLE `public_config` (
  `config_id` char(32) NOT NULL,
  `customer_id` varchar(32) DEFAULT NULL,
  `config_key` varchar(50) DEFAULT NULL,
  `config_val` varchar(50) DEFAULT NULL,
  `function_id` varchar(32) DEFAULT NULL,
  `note` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
