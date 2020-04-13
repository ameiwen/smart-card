/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : base_cms

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 02/03/2020 09:06:19

 数据库名称： smart-card

*/
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标签名',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据值',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述',
  `sort` decimal(10, 0) NULL DEFAULT NULL COMMENT '排序（升序）',
  `parent_id` bigint(64) NULL DEFAULT 0 COMMENT '父级编号',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注信息',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '删除标记',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_dict_value`(`value`) USING BTREE,
  INDEX `sys_dict_label`(`name`) USING BTREE,
  INDEX `sys_dict_del_flag`(`status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'status', '1', 'char', '是', NULL, 0, '是否禁用', '0', NULL, '2019-09-18 11:26:36');
INSERT INTO `sys_dict` VALUES (2, 'status', '0', 'char', '否', NULL, 0, '是否禁用', '0', NULL, '2019-09-18 11:26:58');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` tinyint(255) NULL DEFAULT 0 COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序值',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', '', 'system', '', 0, 'layui-icon-menu-fill\r\nlayui-icon-menu-fill\r\nlayui-icon-menu-fill\r\nlayui-icon-menu-fill\r\nlayui-icon-menu-fill\r\nlayui-icon-menu-fill\r\nlayui-icon-menu-fill\r\nlayui-icon-menu-fill\r\nlayui-icon layui-icon-menu-fill', 1, '2018-12-03 17:43:47');
INSERT INTO `sys_menu` VALUES (2, 1, '系统菜单', '/system/menu', NULL, '', 1, '', 2, '2018-12-03 17:44:31');
INSERT INTO `sys_menu` VALUES (3, 1, '用户管理', '/system/user', NULL, '', 1, '', 3, '2018-12-03 17:45:09');
INSERT INTO `sys_menu` VALUES (4, 1, '角色管理', '/system/role', NULL, '', 1, '', 4, '2018-12-03 17:45:46');
INSERT INTO `sys_menu` VALUES (5, 2, '新增', NULL, NULL, 'sys:menu:add', 2, NULL, 0, '2018-12-03 17:50:28');
INSERT INTO `sys_menu` VALUES (6, 2, '编辑', NULL, NULL, 'sys:menu:edit', 2, NULL, 0, '2018-12-03 17:50:27');
INSERT INTO `sys_menu` VALUES (7, 2, '删除', '', NULL, 'sys:menu:remove', 2, NULL, 0, '2018-12-03 17:50:29');
INSERT INTO `sys_menu` VALUES (8, 3, '新增', '', NULL, 'sys:user:add', 2, '', 0, '2018-12-03 17:49:08');
INSERT INTO `sys_menu` VALUES (9, 3, '编辑', '', NULL, 'sys:user:edit', 2, '', 0, '2018-12-03 17:49:11');
INSERT INTO `sys_menu` VALUES (10, 3, '删除', '', NULL, 'sys:user:remove', 2, '', 0, '2018-12-03 17:49:13');
INSERT INTO `sys_menu` VALUES (11, 4, '新增', NULL, NULL, 'sys:role:add', 2, NULL, 0, '2018-12-03 17:50:22');
INSERT INTO `sys_menu` VALUES (12, 4, '编辑', NULL, NULL, 'sys:role:edit', 2, NULL, 0, '2018-12-03 17:50:24');
INSERT INTO `sys_menu` VALUES (13, 4, '删除', NULL, NULL, 'sys:role:remove', 2, NULL, 0, '2018-12-03 17:50:25');
INSERT INTO `sys_menu` VALUES (14, 0, '系统工具', '', 'tool', '', 0, 'layui-icon layui-icon-util', 10, '2019-09-18 10:58:07');
INSERT INTO `sys_menu` VALUES (15, 14, '代码生成', '/system/generator', NULL, '', 1, '', 2, '2019-09-18 10:58:09');
INSERT INTO `sys_menu` VALUES (17, 14, '数据字典', '/system/dict', NULL, '', 1, '', 1, '2019-09-18 10:58:11');
INSERT INTO `sys_menu` VALUES (23, 17, '新增', '', NULL, 'sys:dict:add', 2, '', NULL, '2019-09-18 10:58:17');
INSERT INTO `sys_menu` VALUES (24, 17, '删除', '', NULL, 'sys:dict:remove', 2, '', NULL, '2019-09-18 10:58:13');
INSERT INTO `sys_menu` VALUES (26, 0, '订单管理', '', 'work', '', 0, 'layui-icon layui-icon-app', 2, '2019-09-18 10:58:18');
INSERT INTO `sys_menu` VALUES (27, 26, '订单信息', '/trade/order', NULL, '', 1, '', 1, '2019-09-18 10:58:21');
INSERT INTO `sys_menu` VALUES (28, 27, '新增', '', NULL, 'trade:order:add', 2, '', 1, '2019-09-18 10:58:24');
INSERT INTO `sys_menu` VALUES (29, 27, '删除', '', NULL, 'trade:order:remove', 2, '', 2, '2019-09-18 10:58:29');
INSERT INTO `sys_menu` VALUES (30, 27, '编辑', '', NULL, 'trade:order:edit', 2, '', 3, '2019-09-18 10:58:26');
INSERT INTO `sys_menu` VALUES (31, 27, '导入', '', NULL, 'trade:order:import', 2, '', 4, '2019-09-18 10:58:32');
INSERT INTO `sys_menu` VALUES (32, 3, '重置密码', '', NULL, 'sys:user:resetPwd', 2, '', NULL, '2019-09-18 10:58:35');
INSERT INTO `sys_menu` VALUES (33, 14, '任务计划', '/system/task', NULL, '', 1, '', 1, '2019-09-18 13:43:17');
INSERT INTO `sys_menu` VALUES (35, 33, '新增', '', NULL, 'system:task:add', 2, '', 2, '2019-09-18 13:44:57');
INSERT INTO `sys_menu` VALUES (36, 33, '编辑', '', NULL, 'system:task:edit', 2, '', 3, '2019-09-18 13:45:19');
INSERT INTO `sys_menu` VALUES (37, 33, '删除', '', NULL, 'system:task:remove', 2, '', 4, '2019-09-18 13:53:57');
INSERT INTO `sys_menu` VALUES (39, 2, '查询', '', NULL, 'sys:menu:menu', 2, '', 1, '2019-09-19 17:07:44');
INSERT INTO `sys_menu` VALUES (40, 3, '查询', '', NULL, 'sys:user:user', 2, '', 1, '2019-09-19 17:08:03');
INSERT INTO `sys_menu` VALUES (41, 4, '查询', '', NULL, 'sys:role:role', 2, '', 1, '2019-09-19 17:08:23');
INSERT INTO `sys_menu` VALUES (42, 33, '查询', '', NULL, 'system:task:task', 2, '', 1, '2019-09-19 17:08:58');
INSERT INTO `sys_menu` VALUES (43, 17, '查询', '', NULL, 'sys:dict:dict', 2, '', 1, '2019-09-19 17:09:24');
INSERT INTO `sys_menu` VALUES (44, 33, '任务状态控制', '', NULL, 'system:task:change', 0, '', 4, '2019-09-19 17:26:22');
INSERT INTO `sys_menu` VALUES (45, 27, '查询', '', NULL, 'trade:order:order', 2, '', 5, '2019-09-19 20:00:19');

-- ----------------------------
-- Table structure for sys_notify
-- ----------------------------
DROP TABLE IF EXISTS `sys_notify`;
CREATE TABLE `sys_notify`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notify
-- ----------------------------
INSERT INTO `sys_notify` VALUES (1, 'dsa', 'sad', '0', 'asdsa', NULL, '2019-04-01 16:16:56');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注描述',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '拥有最高权限', '1', '2018-12-03 17:42:09');
INSERT INTO `sys_role` VALUES (2, '客服', '一般业务处理权限', '1', NULL);
INSERT INTO `sys_role` VALUES (3, '业务专员', '特定流程权限', '1', NULL);
INSERT INTO `sys_role` VALUES (4, '人事专员', '人员管理权限', '1', NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `menu_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 540 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (213, 3, 26);
INSERT INTO `sys_role_menu` VALUES (214, 3, 28);
INSERT INTO `sys_role_menu` VALUES (215, 3, 29);
INSERT INTO `sys_role_menu` VALUES (216, 3, 30);
INSERT INTO `sys_role_menu` VALUES (217, 3, 31);
INSERT INTO `sys_role_menu` VALUES (218, 3, 27);
INSERT INTO `sys_role_menu` VALUES (219, 3, -1);
INSERT INTO `sys_role_menu` VALUES (220, 4, 26);
INSERT INTO `sys_role_menu` VALUES (221, 4, 28);
INSERT INTO `sys_role_menu` VALUES (222, 4, 29);
INSERT INTO `sys_role_menu` VALUES (223, 4, 30);
INSERT INTO `sys_role_menu` VALUES (224, 4, 31);
INSERT INTO `sys_role_menu` VALUES (225, 4, 27);
INSERT INTO `sys_role_menu` VALUES (226, 4, -1);
INSERT INTO `sys_role_menu` VALUES (454, 2, 39);
INSERT INTO `sys_role_menu` VALUES (455, 2, 40);
INSERT INTO `sys_role_menu` VALUES (456, 2, 41);
INSERT INTO `sys_role_menu` VALUES (457, 2, 43);
INSERT INTO `sys_role_menu` VALUES (458, 2, 42);
INSERT INTO `sys_role_menu` VALUES (459, 2, -1);
INSERT INTO `sys_role_menu` VALUES (460, 2, 1);
INSERT INTO `sys_role_menu` VALUES (461, 2, 2);
INSERT INTO `sys_role_menu` VALUES (462, 2, 3);
INSERT INTO `sys_role_menu` VALUES (463, 2, 4);
INSERT INTO `sys_role_menu` VALUES (464, 2, 14);
INSERT INTO `sys_role_menu` VALUES (465, 2, 17);
INSERT INTO `sys_role_menu` VALUES (466, 2, 33);
INSERT INTO `sys_role_menu` VALUES (503, 1, 5);
INSERT INTO `sys_role_menu` VALUES (504, 1, 6);
INSERT INTO `sys_role_menu` VALUES (505, 1, 7);
INSERT INTO `sys_role_menu` VALUES (506, 1, 39);
INSERT INTO `sys_role_menu` VALUES (507, 1, 8);
INSERT INTO `sys_role_menu` VALUES (508, 1, 9);
INSERT INTO `sys_role_menu` VALUES (509, 1, 10);
INSERT INTO `sys_role_menu` VALUES (510, 1, 32);
INSERT INTO `sys_role_menu` VALUES (511, 1, 40);
INSERT INTO `sys_role_menu` VALUES (512, 1, 11);
INSERT INTO `sys_role_menu` VALUES (513, 1, 12);
INSERT INTO `sys_role_menu` VALUES (514, 1, 13);
INSERT INTO `sys_role_menu` VALUES (515, 1, 41);
INSERT INTO `sys_role_menu` VALUES (516, 1, 15);
INSERT INTO `sys_role_menu` VALUES (517, 1, 23);
INSERT INTO `sys_role_menu` VALUES (518, 1, 24);
INSERT INTO `sys_role_menu` VALUES (519, 1, 43);
INSERT INTO `sys_role_menu` VALUES (520, 1, 35);
INSERT INTO `sys_role_menu` VALUES (521, 1, 36);
INSERT INTO `sys_role_menu` VALUES (522, 1, 37);
INSERT INTO `sys_role_menu` VALUES (523, 1, 42);
INSERT INTO `sys_role_menu` VALUES (524, 1, 44);
INSERT INTO `sys_role_menu` VALUES (525, 1, 28);
INSERT INTO `sys_role_menu` VALUES (526, 1, 29);
INSERT INTO `sys_role_menu` VALUES (527, 1, 30);
INSERT INTO `sys_role_menu` VALUES (528, 1, 31);
INSERT INTO `sys_role_menu` VALUES (529, 1, 2);
INSERT INTO `sys_role_menu` VALUES (530, 1, 3);
INSERT INTO `sys_role_menu` VALUES (531, 1, 4);
INSERT INTO `sys_role_menu` VALUES (532, 1, 1);
INSERT INTO `sys_role_menu` VALUES (533, 1, 17);
INSERT INTO `sys_role_menu` VALUES (534, 1, 33);
INSERT INTO `sys_role_menu` VALUES (535, 1, 14);
INSERT INTO `sys_role_menu` VALUES (536, 1, -1);
INSERT INTO `sys_role_menu` VALUES (537, 1, 45);
INSERT INTO `sys_role_menu` VALUES (538, 1, 27);
INSERT INTO `sys_role_menu` VALUES (539, 1, 26);

-- ----------------------------
-- Table structure for sys_task
-- ----------------------------
DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务调用的方法名',
  `is_concurrent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务是否有状态',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `bean_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `job_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务状态',
  `job_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务分组',
  `spring_bean` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_task
-- ----------------------------
INSERT INTO `sys_task` VALUES (1, '0/10 * * * * ?', 'run1', '1', '111', 'com.base.boot.task.WelcomeJob', '0', 'group1', NULL, 'welcomJob', '2019-01-03 17:12:31', '2019-01-03 17:12:28');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '可用 1 不可用0',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '随机字符串作为salt因子',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '超级管理员', 'admin', '982d3b4564f2cf1f3c27c3dcafe5e705', '15908401995', 1, '238618edccb4395e7a2bcd852ad06b95', '2018-12-03 17:52:09');
INSERT INTO `sys_user` VALUES (8, '杨洪波', 'yanghb', 'c0464d55b2bc6bb679b2006cb735f887', '15908401995', 1, '283cc801538894eef3bac5557fe09ac7', '2019-09-18 10:56:51');

-- ----------------------------
-- Table structure for sys_user_notify
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_notify`;
CREATE TABLE `sys_user_notify`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `notify_id` int(255) NULL DEFAULT NULL COMMENT '消息ID',
  `is_read` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0未读 1已读',
  `read_time` datetime(0) NULL DEFAULT NULL COMMENT '阅读时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (2, 1, 1);
INSERT INTO `sys_user_role` VALUES (13, 8, 2);

-- ----------------------------
-- Table structure for zt_order
-- ----------------------------
DROP TABLE IF EXISTS `zt_order`;
CREATE TABLE `zt_order`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `STOREID` int(11) NULL DEFAULT NULL COMMENT '215 京东 0:淘宝，1：天猫',
  `USERID` bigint(20) NULL DEFAULT NULL,
  `ONEUSERID` bigint(20) NULL DEFAULT NULL COMMENT '没有一级填0',
  `TWOUSERID` bigint(20) NULL DEFAULT NULL COMMENT '没有二级代理  填0',
  `ORDERNO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `COSTS` decimal(11, 2) NULL DEFAULT 0.00,
  `ORDERMONEY` decimal(11, 2) NULL DEFAULT NULL,
  `PRECOMMISION` decimal(11, 2) NULL DEFAULT 0.00,
  `COMMISION` decimal(11, 2) NULL DEFAULT 0.00,
  `ISFROMMOBILE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成交平台，PC:1，无线:2',
  `STOREOSTATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' M 创建订单消息(下单未付款)   D 下单已付款(未确认收货后)      X 无效订单   S 交易成功(确认收货后)   ',
  `PARENTNO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CHECKSTATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0 待核  1 有效  2无效',
  `FIRSTCOMMISION` decimal(11, 2) NULL DEFAULT 0.00,
  `SECONDCOMMISION` decimal(11, 2) NULL DEFAULT 0.00,
  `THIRDCOMMISION` decimal(11, 2) NULL DEFAULT 0.00,
  `PROMOTIONNAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SHOPID` int(11) NULL DEFAULT NULL,
  `SHOPNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `FINISHTIME` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `SURENAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PROMOTIONID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USERCOMMISION` decimal(11, 2) NULL DEFAULT 0.00,
  `UNIONID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `COMMOSIONRATE` decimal(11, 2) NULL DEFAULT NULL,
  `ISDELETE` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0:删除，1：未删除',
  `INVALIDREASON` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BALANCESTATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0:未结算，1：已结算',
  `USERLEVEL` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1,2,3级别',
  `CREATETIME` timestamp(0) NOT NULL,
  `ORDERTIME` timestamp(0) NOT NULL,
  `BALANCETIME` timestamp(0) NOT NULL,
  `PREBALANCETIME` timestamp(0) NOT NULL COMMENT '下单时间往后推',
  `SURETIME` timestamp(0) NOT NULL,
  `UPDATETIME` timestamp(0) NOT NULL,
  `GOODSNAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `GOODSPICTURE` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USERNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TZID` bigint(20) NULL DEFAULT NULL,
  `TZCOMMISION` decimal(11, 2) NULL DEFAULT NULL,
  `THREEUSERID` bigint(20) NULL DEFAULT NULL,
  `ITEMID` bigint(20) NULL DEFAULT NULL,
  `ISWQORDER` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0:未维权，1：已维权',
  `WQMONEY` decimal(11, 2) NULL DEFAULT NULL,
  `VALICODE` int(11) NULL DEFAULT NULL,
  `ISSHOUDANG` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0:不是首单，1：是',
  `ISPINGGOU` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0:不是，1：是拼购',
  `ACTIVITYID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ISACTIVITY` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `EXTRMONEY` decimal(11, 2) NULL DEFAULT 0.00,
  `subunionid` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fromsource` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `finalRate` decimal(11, 2) NULL DEFAULT 0.00,
  `goodsnum` int(11) NULL DEFAULT NULL,
  `uptzid` bigint(20) NULL DEFAULT 0,
  `uptzcommission` decimal(11, 2) NULL DEFAULT 0.00,
  `upuptzid` bigint(20) NULL DEFAULT 0,
  `upuptzcommission` decimal(11, 2) NULL DEFAULT 0.00,
  `goodsCateName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `UK_ORDER_OIDSID`(`ORDERNO`, `STOREID`) USING BTREE,
  INDEX `I_ORDER_USERID`(`USERID`) USING BTREE,
  INDEX `I_ORDER_OTIME`(`ORDERTIME`) USING BTREE,
  INDEX `I_ORDER_CTIME`(`CREATETIME`) USING BTREE,
  INDEX `I_ORDER_UTIME`(`PREBALANCETIME`) USING BTREE,
  INDEX `I_ORDER_STORESTATUS`(`STOREOSTATUS`) USING BTREE,
  INDEX `I_ORDER_PROMOTION`(`CHECKSTATUS`) USING BTREE,
  INDEX `I_ORDER_BALANCE`(`BALANCESTATUS`) USING BTREE,
  INDEX `I_ORDER_OTHERUDERID`(`ONEUSERID`, `TWOUSERID`, `TZID`) USING BTREE,
  INDEX `I_ORDER_ONEUSERID`(`ONEUSERID`) USING BTREE,
  INDEX `I_ORDER_TWOUSERID`(`TWOUSERID`) USING BTREE,
  INDEX `I_ORDER_TZUSERID`(`TZID`) USING BTREE,
  INDEX `I_ORDER_ISDELETE`(`ISDELETE`) USING BTREE,
  INDEX `I_ORDER_ORDERNO`(`ORDERNO`) USING BTREE,
  INDEX `I_ORDER_ITEMID`(`ITEMID`) USING BTREE,
  INDEX `I_ORDER_POSID`(`PROMOTIONID`) USING BTREE,
  INDEX `I_ORDER_THREEUSERID`(`THREEUSERID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68478 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zt_order
-- ----------------------------
INSERT INTO `zt_order` VALUES (54838, 215, 0, 0, 0, '81461326425', 9.90, 9.90, 4.70, 4.70, '2', 'S', '0', '0', 0.00, 0.00, 0.00, '', 0, '爱上小屋拼购店', '2019-08-19 16:58:07', NULL, '1000260394-1380634308', 0.00, '1000260394', 0.50, '1', '', '0', '1', '2018-12-05 23:04:32', '2018-11-09 07:17:18', '0000-00-00 00:00:00', '2018-12-26 23:04:32', '0000-00-00 00:00:00', '2019-01-18 10:34:54', '家用肥皂洗衣皂透明皂 款式颜色随机A-75', 'http://img14.360buyimg.com/ads/jfs/t1/2860/10/16312/223844/5be194dcE835d090c/d7a74506db2185ad.jpg', NULL, 0, 0.00, 0, 36227412957, '0', NULL, 18, '0', '1', NULL, '0', 0.00, NULL, NULL, 0.00, NULL, 0, 0.00, 0, 0.00, NULL);
INSERT INTO `zt_order` VALUES (54839, 215, 0, 0, 0, '81496114458', 9.90, 9.90, 4.70, 4.70, '2', 'S', '0', '0', 0.00, 0.00, 0.00, '', 0, NULL, '2019-08-19 16:58:07', NULL, '1000260394-1380636194', 0.00, '1000260394', 0.50, '1', '', '0', '1', '2018-12-05 23:12:32', '2018-11-09 21:39:36', '0000-00-00 00:00:00', '2018-12-26 23:12:32', '0000-00-00 00:00:00', '2019-01-18 10:34:55', 'EGO燕麦巧克力10小包', NULL, NULL, 0, 0.00, 0, 36084658090, '0', NULL, 18, '0', '1', NULL, '0', 0.00, NULL, NULL, 0.00, NULL, 0, 0.00, 0, 0.00, NULL);
SET FOREIGN_KEY_CHECKS = 1;
