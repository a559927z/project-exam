/*
Navicat MariaDB Data Transfer

Source Server         : 本地服务
Source Server Version : 100211
Source Host           : localhost:3369
Source Database       : exam

Target Server Type    : MariaDB
Target Server Version : 100211
File Encoding         : 65001

Date: 2018-10-16 19:34:17
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
  `type` varchar(1) DEFAULT '' COMMENT '题型（1：单选，2多选，3是非）',
  `is_lock` int(11) unsigned zerofill DEFAULT NULL,
  `created_by` varchar(32) DEFAULT '' COMMENT '创建人',
  `created_date` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '创建时间',
  `updated_by` varchar(32) DEFAULT '' COMMENT '修改人',
  `updated_date` datetime DEFAULT NULL ON UPDATE current_timestamp() COMMENT '修改时间',
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='题库';

-- ----------------------------
-- Records of exam_question_bank
-- ----------------------------
INSERT INTO `exam_question_bank` VALUES ('501337147517698048', '合格境内机构投资者（QDII）开展境外证券投资业务时，关于基金资产托管的表述，以下正确的是（）。', '☑QDII境外托管人在履行职责过程中，因本身过错、疏忽等原因而导致经济财产受损的，托管人应当承担相应责任☐QDII的境内资产托管人必须委托境外资产托管人负责境外资产托管业务☐受QDII境内托管人委托的境外托管人，其所在国家或地区证券监管机构必须已与中国证监会签订双边监管合作备忘录☐QDII可以直接委托境外资产托管机构负责资产托管业务', '☑QDII境外托管人在履行职责过程中，因本身过错、疏忽等原因而导致经济财产受损的，托管人应当承担相应责任', '解析:合格境内机构投资者（QDII）开展境外证券投资业务，应当由境内资产托管机构负责资产托管业务，可以委托境外证券服务机构代理买卖证券。所以“QDII的境内资产托管人必须委托境外资产托管人负责境外资产托管业务”不正确。境外投资顾问应该满足，所在国家或地区证券监管机构已与中国证监会签订双边监管合作谅解备忘录，并保持着有效的监管合作关系。所以，“受QDII境内托管人委托的境外托管人，其所在国家或地区证券监管机构必须已与中国证监会签订双边监管合作备忘录”选项，错误。境内机构投资者开阵境外证券投资业务时，应当有证券投资基金托管资格的银行（简称托管人）负责资产托管业务。所以，“QDII可以直接委托境外资产托管机构负责资产托管业务”选项，错误。', '押题说明:2015.9+2018.3月用题', '501337144959172608', 'fsf', '1', '1', '1', '00000000000', 'superAdmin', '2018-10-15 10:14:50', 'superAdmin', '2018-10-15 10:14:50');
INSERT INTO `exam_question_bank` VALUES ('501337147530280960', '期初某基金资产净值为1亿元，股票市值7000万元，现金3000万元；由于期间市场波动，期末的股票市值变为6000万，（不考虑费用和份额等其他因素的变动），则股票投资占基金资产净值的比例为（）。', '☑67%☑60%☐75%☐70%', '☑67%☑60%', '解析:股票投资占基金资产净值比例=股票投资/基金资产净值；6000万/6000万+3000万=67%', '押题说明:2018.6', '501337144959172608', 'fsf', '1', '1', '2', '00000000000', 'superAdmin', '2018-10-15 10:14:50', 'superAdmin', '2018-10-15 10:14:50');
INSERT INTO `exam_question_bank` VALUES ('501337147534475264', '关于复利终值系数（FV,6%,8）下列说法错误的是（）。', '☐正确☑错误', '☑错误', '解析:FV=PV(1+i)^n,其中(1+i)^n为复利终值系数或1元的复利终值，用符号（FV,i,n)。（FV,6%,8）=(1+6%)^8（PV,6%,8）=(1+6%)^-8=1/（FV,6%,8）', '押题说明:核心', '501337144959172608', 'fsf', '1', '1', '3', '00000000000', 'superAdmin', '2018-10-15 10:14:50', 'superAdmin', '2018-10-15 10:14:50');
INSERT INTO `exam_question_bank` VALUES ('501337147538669568', 'xxxxxxxxxxxxxx列说法错误的是（）。', '☐正确☑错误', '☑错误', '解析:FV=PV(1+i)^n,其中(1+i)^n为复利终值系数或1元的复利终值，用符号（FV,i,n)。（FV,6%,8）=(1+6%)^8（PV,6%,8）=(1+6%)^-8=1/（FV,6%,8）', '押题说明:核心', '501337144959172608', 'fsf', '1', '1', '3', '00000000000', 'superAdmin', '2018-10-15 10:14:50', 'superAdmin', '2018-10-15 10:14:50');

-- ----------------------------
-- Table structure for exam_question_bank_answer
-- ----------------------------
DROP TABLE IF EXISTS `exam_question_bank_answer`;
CREATE TABLE `exam_question_bank_answer` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT 'pk',
  `question_id` varchar(32) NOT NULL COMMENT '题目ID',
  `answer` varchar(500) NOT NULL COMMENT '答案',
  `isAnswer` tinyint(1) unsigned zerofill NOT NULL COMMENT '是否正确答案',
  `question_bank_id` varchar(32) NOT NULL DEFAULT '' COMMENT '题库ID',
  `sort` int(1) NOT NULL COMMENT '排序',
  `answerNo` varchar(1) NOT NULL DEFAULT '' COMMENT '答案号（ABCDEFGHI）',
  `type` varchar(1) DEFAULT '' COMMENT '题型（1：单选，2多选，3是非）',
  PRIMARY KEY (`id`),
  KEY `ind_q_id` (`question_id`) USING BTREE,
  KEY `index_qb_id` (`question_bank_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='答案';

-- ----------------------------
-- Records of exam_question_bank_answer
-- ----------------------------
INSERT INTO `exam_question_bank_answer` VALUES ('501337147777744896', '501337147517698048', 'QDII境外托管人在履行职责过程中，因本身过错、疏忽等原因而导致经济财产受损的，托管人应当承担相应责任', '1', '501337144959172608', '0', 'A', '1');
INSERT INTO `exam_question_bank_answer` VALUES ('501337147781939200', '501337147517698048', 'QDII的境内资产托管人必须委托境外资产托管人负责境外资产托管业务', '0', '501337144959172608', '1', 'B', '1');
INSERT INTO `exam_question_bank_answer` VALUES ('501337147781939201', '501337147517698048', '受QDII境内托管人委托的境外托管人，其所在国家或地区证券监管机构必须已与中国证监会签订双边监管合作备忘录', '0', '501337144959172608', '2', 'C', '1');
INSERT INTO `exam_question_bank_answer` VALUES ('501337147781939202', '501337147517698048', 'QDII可以直接委托境外资产托管机构负责资产托管业务', '0', '501337144959172608', '3', 'D', '1');
INSERT INTO `exam_question_bank_answer` VALUES ('501337147865825280', '501337147530280960', '67%', '0', '501337144959172608', '0', 'A', '2');
INSERT INTO `exam_question_bank_answer` VALUES ('501337147865825281', '501337147530280960', '60%', '1', '501337144959172608', '1', 'B', '2');
INSERT INTO `exam_question_bank_answer` VALUES ('501337147865825282', '501337147530280960', '75%', '1', '501337144959172608', '2', 'C', '2');
INSERT INTO `exam_question_bank_answer` VALUES ('501337147865825283', '501337147530280960', '70%', '0', '501337144959172608', '3', 'D', '2');
INSERT INTO `exam_question_bank_answer` VALUES ('501337147945517056', '501337147534475264', '正确', '0', '501337144959172608', '0', 'A', '3');
INSERT INTO `exam_question_bank_answer` VALUES ('501337147945517057', '501337147534475264', '错误', '1', '501337144959172608', '1', 'B', '3');
INSERT INTO `exam_question_bank_answer` VALUES ('501337147987460096', '501337147538669568', '正确', '0', '501337144959172608', '0', 'A', '3');
INSERT INTO `exam_question_bank_answer` VALUES ('501337147987460097', '501337147538669568', '错误', '1', '501337144959172608', '1', 'B', '3');

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
-- Records of exam_question_bank_score
-- ----------------------------

-- ----------------------------
-- Table structure for exam_question_bank_ya
-- ----------------------------
DROP TABLE IF EXISTS `exam_question_bank_ya`;
CREATE TABLE `exam_question_bank_ya` (
  `question_bank_ya_id` varchar(32) NOT NULL DEFAULT '' COMMENT '本期押题ID',
  `question_bank_id` varchar(32) NOT NULL COMMENT '题库ID',
  `course_id` varchar(30) DEFAULT '' COMMENT '科目名称',
  PRIMARY KEY (`question_bank_ya_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='押题';

-- ----------------------------
-- Records of exam_question_bank_ya
-- ----------------------------
INSERT INTO `exam_question_bank_ya` VALUES ('501337235652608000', '501337144959172608', '1');

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
-- Table structure for exam_user_answer_ya
-- ----------------------------
DROP TABLE IF EXISTS `exam_user_answer_ya`;
CREATE TABLE `exam_user_answer_ya` (
  `user_answer_id` varchar(32) NOT NULL COMMENT 'PK',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `course_id` varchar(30) DEFAULT '' COMMENT '科目ID',
  `question_bank_id` varchar(32) DEFAULT '' COMMENT '题库ID',
  `question_id` varchar(32) NOT NULL DEFAULT '' COMMENT '题目ID',
  `user_answer` varchar(20) DEFAULT '' COMMENT '用户答案',
  `true_answer` varchar(20) DEFAULT '' COMMENT '正确答案',
  `type` varchar(1) DEFAULT '' COMMENT '题目类型',
  PRIMARY KEY (`user_answer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_user_answer_ya
-- ----------------------------
INSERT INTO `exam_user_answer_ya` VALUES ('501823704859148288', 'jxzhang', '', '501337144959172608', '501337147534475264', 'A', '', '');
INSERT INTO `exam_user_answer_ya` VALUES ('501823704850759680', 'jxzhang', '', '501337144959172608', '501337147530280960', 'C', '', '');
INSERT INTO `exam_user_answer_ya` VALUES ('501823704846565376', 'jxzhang', '', '501337144959172608', '501337147530280960', 'B', '', '');
INSERT INTO `exam_user_answer_ya` VALUES ('501823704829788160', 'jxzhang', '', '501337144959172608', '501337147517698048', 'D', '', '');

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
-- Records of public_config
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

-- ----------------------------
-- Table structure for public_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `public_sys_permission`;
CREATE TABLE `public_sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `available` int(1) DEFAULT NULL,
  `name` varchar(30) DEFAULT '' COMMENT '名称',
  `parent_id` int(20) DEFAULT NULL COMMENT '父编号',
  `parent_ids` varchar(255) DEFAULT '' COMMENT '父编号列表',
  `permission` varchar(255) DEFAULT '' COMMENT '权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view',
  `resource_type` varchar(30) DEFAULT '' COMMENT '资源类型，[menu|button]',
  `url` varchar(255) DEFAULT '' COMMENT '资源路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of public_sys_permission
-- ----------------------------
INSERT INTO `public_sys_permission` VALUES ('1', '0', '用户管理', '0', '0/', 'userInfo:view', 'menu', 'userInfo/userList');
INSERT INTO `public_sys_permission` VALUES ('2', '0', '用户添加', '1', '0/1', 'userInfo:add', 'button', 'userInfo/userAdd');
INSERT INTO `public_sys_permission` VALUES ('3', '0', '用户删除', '1', '0/1', 'userInfo:del', 'button', 'userInfo/userDel');

-- ----------------------------
-- Table structure for public_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `public_sys_role`;
CREATE TABLE `public_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `available` int(1) DEFAULT NULL COMMENT '是否可用,如果不可用将不会添加给用户',
  `description` varchar(30) DEFAULT '' COMMENT '角色描述,UI界面显示使用',
  `role` varchar(30) DEFAULT '' COMMENT '角色标识程序中判断使用,如"admin",这个是唯一的:',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of public_sys_role
-- ----------------------------
INSERT INTO `public_sys_role` VALUES ('1', '0', '管理员', 'admin');
INSERT INTO `public_sys_role` VALUES ('2', '0', 'VIP会员', 'vip');
INSERT INTO `public_sys_role` VALUES ('3', '1', 'test', 'test');

-- ----------------------------
-- Table structure for public_sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `public_sys_role_permission`;
CREATE TABLE `public_sys_role_permission` (
  `permission_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`permission_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of public_sys_role_permission
-- ----------------------------
INSERT INTO `public_sys_role_permission` VALUES ('1', '1');
INSERT INTO `public_sys_role_permission` VALUES ('2', '1');
INSERT INTO `public_sys_role_permission` VALUES ('3', '2');

-- ----------------------------
-- Table structure for public_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `public_sys_user_role`;
CREATE TABLE `public_sys_user_role` (
  `role_id` int(11) NOT NULL,
  `uid` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`,`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of public_sys_user_role
-- ----------------------------
INSERT INTO `public_sys_user_role` VALUES ('1', '1');
INSERT INTO `public_sys_user_role` VALUES ('1', '2');

-- ----------------------------
-- Table structure for public_user_info
-- ----------------------------
DROP TABLE IF EXISTS `public_user_info`;
CREATE TABLE `public_user_info` (
  `uid` varchar(32) NOT NULL,
  `en_name` varchar(30) NOT NULL DEFAULT '' COMMENT '帐号',
  `cn_name` varchar(30) DEFAULT '' COMMENT '名称（昵称或者真实姓名，不同系统不同定义）',
  `password` varchar(60) DEFAULT '' COMMENT '密码',
  `salt` varchar(50) DEFAULT '' COMMENT '加密密码的盐',
  `state` int(1) DEFAULT NULL COMMENT '0:正常状态,1：用户被锁定.',
  PRIMARY KEY (`uid`,`en_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of public_user_info
-- ----------------------------
INSERT INTO `public_user_info` VALUES ('1', 'admin', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '0');
INSERT INTO `public_user_info` VALUES ('2', 'superAdmin', '超级管理员', 'a26cf971f3dabdbfe2b636b6ac1160fd', 'kingsing', '0');
INSERT INTO `public_user_info` VALUES ('3', 'test1', '测试', '3', '3', '1');
INSERT INTO `public_user_info` VALUES ('jxzhang', 'jxzhang', '景星', '123123', '123', '0');
