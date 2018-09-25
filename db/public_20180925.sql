
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

-- ----------------------------
-- Table structure for public_user_info
-- ----------------------------
DROP TABLE IF EXISTS `public_user_info`;
CREATE TABLE `public_user_info` (
  `uid` varchar(255) NOT NULL,
  `en_name` varchar(30) DEFAULT '' COMMENT '帐号',
  `cn_name` varchar(30) DEFAULT '' COMMENT '名称（昵称或者真实姓名，不同系统不同定义）',
  `password` varchar(60) DEFAULT '' COMMENT '密码',
  `salt` varchar(50) DEFAULT '' COMMENT '加密密码的盐',
  `state` int(1) DEFAULT NULL COMMENT '用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of public_user_info
-- ----------------------------
INSERT INTO `public_user_info` VALUES ('1', 'admin', '管理员', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '0');
