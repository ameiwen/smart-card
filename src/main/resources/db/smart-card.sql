/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : smart-card

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 29/04/2020 17:25:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sc_asset_history
-- ----------------------------
DROP TABLE IF EXISTS `sc_asset_history`;
CREATE TABLE `sc_asset_history`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `card_id` bigint(20) NULL DEFAULT NULL COMMENT '卡片ID',
  `event_id` bigint(20) NULL DEFAULT 0 COMMENT '事件ID（超市ID，食堂ID）',
  `event` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '类型事件（1.食堂2.超市3.充值4其他）',
  `pay_amt` float(32, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态1存在0删除',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资产记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_asset_history
-- ----------------------------
INSERT INTO `sc_asset_history` VALUES (5, 10016, 0, '3', 20.00, '1', '2020-04-25 18:12:41');
INSERT INTO `sc_asset_history` VALUES (6, 10017, 0, '3', 20.00, '1', '2020-04-25 18:14:06');
INSERT INTO `sc_asset_history` VALUES (7, 10016, 0, '3', 40.00, '1', '2020-04-25 18:37:04');

-- ----------------------------
-- Table structure for sc_book
-- ----------------------------
DROP TABLE IF EXISTS `sc_book`;
CREATE TABLE `sc_book`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_id` bigint(11) NULL DEFAULT NULL COMMENT '类型ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍名称',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型（文学，历史）',
  `publish_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社名称',
  `price` float(32, 2) NULL DEFAULT NULL COMMENT '价格',
  `book_num` int(11) NULL DEFAULT 1 COMMENT '数量',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态 1存在 0删除',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '书籍表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_book
-- ----------------------------
INSERT INTO `sc_book` VALUES (1, 4, '动物世界', '无名', '动物类', 'xxx出版社', 20.00, 2, '1', '2020-04-11 00:48:15', '2020-04-11 00:48:15');
INSERT INTO `sc_book` VALUES (2, 1, 'xxx', '无名', '文学类', 'xxx出版社', 10.00, 2, '1', '2020-04-11 00:48:46', '2020-04-11 00:48:46');
INSERT INTO `sc_book` VALUES (3, 2, 'xxx', '无名', '历史类', 'xxx出版社', 5.00, 2, '1', '2020-04-11 00:49:09', '2020-04-11 00:49:09');
INSERT INTO `sc_book` VALUES (4, 1, '1', '1', '文学类', '1', -1.00, 1, '0', '2020-04-29 13:09:04', '2020-04-29 13:09:04');
INSERT INTO `sc_book` VALUES (5, 1, '1111', '1', '文学类', '1', 1.00, -4, '0', '2020-04-29 13:23:41', '2020-04-29 13:23:41');

-- ----------------------------
-- Table structure for sc_book_type
-- ----------------------------
DROP TABLE IF EXISTS `sc_book_type`;
CREATE TABLE `sc_book_type`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态 1存在 0删除',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_book_type
-- ----------------------------
INSERT INTO `sc_book_type` VALUES (1, '文学类', '1', '2020-04-11 00:47:07', '2020-04-11 00:47:07');
INSERT INTO `sc_book_type` VALUES (2, '历史类', '1', '2020-04-11 00:47:11', '2020-04-11 00:47:11');
INSERT INTO `sc_book_type` VALUES (3, '自然类', '1', '2020-04-11 00:47:19', '2020-04-11 00:47:19');
INSERT INTO `sc_book_type` VALUES (4, '动物类', '1', '2020-04-11 00:47:22', '2020-04-11 00:47:22');
INSERT INTO `sc_book_type` VALUES (5, '111', '1', '2020-04-29 10:46:31', '2020-04-29 10:46:31');

-- ----------------------------
-- Table structure for sc_borrow_record
-- ----------------------------
DROP TABLE IF EXISTS `sc_borrow_record`;
CREATE TABLE `sc_borrow_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `card_id` bigint(20) NULL DEFAULT NULL COMMENT '卡片ID',
  `book_id` bigint(20) NULL DEFAULT NULL COMMENT '书籍ID',
  `type_id` bigint(20) NULL DEFAULT NULL COMMENT '书籍类型ID',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '借书/还书人姓名',
  `event_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件类型（1借书 2还书）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态 1存在 0删除',
  `option_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '借书记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_borrow_record
-- ----------------------------
INSERT INTO `sc_borrow_record` VALUES (1, 10016, 1, 4, NULL, '1', '1', 'xx', '2020-04-11 00:51:06');
INSERT INTO `sc_borrow_record` VALUES (2, 10016, 2, 1, NULL, '1', '1', 'xx', '2020-04-11 00:51:16');
INSERT INTO `sc_borrow_record` VALUES (3, 10017, 3, 2, NULL, '2', '1', 'xx', '2020-04-11 00:51:26');
INSERT INTO `sc_borrow_record` VALUES (4, 10017, 1, 4, NULL, '1', '1', '超级管理员', '2020-04-29 15:02:27');
INSERT INTO `sc_borrow_record` VALUES (5, 10017, 1, 4, NULL, '1', '1', '超级管理员', '2020-04-29 15:16:17');
INSERT INTO `sc_borrow_record` VALUES (6, 10017, 1, 4, NULL, '2', '1', '超级管理员', '2020-04-29 15:16:39');

-- ----------------------------
-- Table structure for sc_canteen
-- ----------------------------
DROP TABLE IF EXISTS `sc_canteen`;
CREATE TABLE `sc_canteen`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `leader` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所处位置（xx）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态1存在 0删除',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '食堂信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_canteen
-- ----------------------------
INSERT INTO `sc_canteen` VALUES (1, '111', 'xxx街道', '1', '2020-04-11 00:52:31', '2020-04-11 00:52:31');
INSERT INTO `sc_canteen` VALUES (2, 'xx', 'xx街道', '1', '2020-04-11 00:52:44', '2020-04-11 00:52:44');
INSERT INTO `sc_canteen` VALUES (3, 'xx', '图书馆旁', '1', '2020-04-11 00:52:54', '2020-04-11 00:52:54');
INSERT INTO `sc_canteen` VALUES (4, '111xxx', '11111', '1', '2020-04-15 20:10:50', '2020-04-15 20:10:50');
INSERT INTO `sc_canteen` VALUES (5, '11111111111111111', '11111111111111', '1', '2020-04-29 10:10:44', '2020-04-29 10:10:44');

-- ----------------------------
-- Table structure for sc_card_info
-- ----------------------------
DROP TABLE IF EXISTS `sc_card_info`;
CREATE TABLE `sc_card_info`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '所属用户ID',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '持卡人姓名',
  `user_sex` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '性别 1男 2女',
  `borrow_num` int(11) NULL DEFAULT 5 COMMENT '可借书数量',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `limit_amount` double(32, 2) NULL DEFAULT 1000.00 COMMENT '限额',
  `asset_amount` float(32, 2) NULL DEFAULT 0.00 COMMENT '余额',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态（1正常 2挂失）',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10018 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '校园卡片信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_card_info
-- ----------------------------
INSERT INTO `sc_card_info` VALUES (10016, 10001, '李四', '1', 5, 'e10adc3949ba59abbe56e057f20f883e', 1000.00, 40.00, '1', '2020-04-25 18:12:13', '2020-04-25 18:12:13');
INSERT INTO `sc_card_info` VALUES (10017, 10003, '杨哈哈', '1', 4, 'e10adc3949ba59abbe56e057f20f883e', 1000.00, 20.00, '1', '2020-04-25 18:14:06', '2020-04-25 18:14:06');

-- ----------------------------
-- Table structure for sc_classes
-- ----------------------------
DROP TABLE IF EXISTS `sc_classes`;
CREATE TABLE `sc_classes`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '逐渐ID',
  `faculty_id` bigint(255) NULL DEFAULT NULL COMMENT '所属院系',
  `specialty_id` bigint(20) NULL DEFAULT NULL COMMENT '所属专业',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级',
  `year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开班年份',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态1存在 0删除',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班级信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_classes
-- ----------------------------
INSERT INTO `sc_classes` VALUES (1, 1, 7, '20班', '2020', '1', '2020-04-04 15:56:40', '2020-04-04 15:56:40');
INSERT INTO `sc_classes` VALUES (2, 1, 7, '21班', '2020', '1', '2020-04-04 15:56:59', '2020-04-04 15:56:59');

-- ----------------------------
-- Table structure for sc_faculty_specialty
-- ----------------------------
DROP TABLE IF EXISTS `sc_faculty_specialty`;
CREATE TABLE `sc_faculty_specialty`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '院系名称',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '类型1院系 2专业',
  `parent_id` bigint(11) NULL DEFAULT 0 COMMENT '父级ID',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态 1存在 0删除',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '院系与专业表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_faculty_specialty
-- ----------------------------
INSERT INTO `sc_faculty_specialty` VALUES (1, '教育学院', '1', 0, '1', '2020-04-04 15:20:18', '2020-04-04 15:20:18');
INSERT INTO `sc_faculty_specialty` VALUES (2, '经济与管理学院', '1', 0, '1', '2020-04-04 15:20:45', '2020-04-04 15:20:45');
INSERT INTO `sc_faculty_specialty` VALUES (3, '化工学院', '1', 0, '1', '2020-04-04 15:20:52', '2020-04-04 15:20:52');
INSERT INTO `sc_faculty_specialty` VALUES (4, '人工智能学院', '1', 0, '1', '2020-04-04 15:21:41', '2020-04-04 15:21:41');
INSERT INTO `sc_faculty_specialty` VALUES (7, '体育教育', '2', 1, '1', '2020-04-04 22:16:02', '2020-04-04 22:16:02');
INSERT INTO `sc_faculty_specialty` VALUES (8, '小学教育', '2', 1, '1', '2020-04-04 22:16:19', '2020-04-04 22:16:19');
INSERT INTO `sc_faculty_specialty` VALUES (9, '学前教育', '2', 1, '1', '2020-04-04 22:16:32', '2020-04-04 22:16:32');
INSERT INTO `sc_faculty_specialty` VALUES (10, '教育技术学', '2', 1, '1', '2020-04-04 22:16:37', '2020-04-04 22:16:37');
INSERT INTO `sc_faculty_specialty` VALUES (11, '工商管理', '2', 2, '1', '2020-04-04 22:16:48', '2020-04-04 22:16:48');
INSERT INTO `sc_faculty_specialty` VALUES (12, '经济与金融', '2', 2, '1', '2020-04-04 22:16:54', '2020-04-04 22:16:54');
INSERT INTO `sc_faculty_specialty` VALUES (13, '金融数学', '2', 2, '1', '2020-04-04 22:17:01', '2020-04-04 22:17:01');
INSERT INTO `sc_faculty_specialty` VALUES (14, '会计', '2', 2, '1', '2020-04-04 22:17:09', '2020-04-04 22:17:09');
INSERT INTO `sc_faculty_specialty` VALUES (15, '化学系', '2', 3, '1', '2020-04-04 22:17:55', '2020-04-04 22:17:55');
INSERT INTO `sc_faculty_specialty` VALUES (16, '生物化学', '2', 3, '1', '2020-04-04 22:18:02', '2020-04-04 22:18:02');
INSERT INTO `sc_faculty_specialty` VALUES (17, '计算机科学与技术', '2', 4, '1', '2020-04-04 22:18:34', '2020-04-04 22:18:34');
INSERT INTO `sc_faculty_specialty` VALUES (18, '网络工程', '2', 4, '1', '2020-04-04 22:18:39', '2020-04-04 22:18:39');
INSERT INTO `sc_faculty_specialty` VALUES (19, '软件技术', '2', 4, '1', '2020-04-04 22:18:45', '2020-04-04 22:18:45');
INSERT INTO `sc_faculty_specialty` VALUES (20, '物联网技术', '2', 4, '1', '2020-04-04 22:18:49', '2020-04-04 22:18:49');
INSERT INTO `sc_faculty_specialty` VALUES (21, '计算机应用技术', '2', 4, '1', '2020-04-04 22:18:55', '2020-04-04 22:18:55');
INSERT INTO `sc_faculty_specialty` VALUES (22, '11111111', '1', 0, '1', '2020-04-15 10:25:35', '2020-04-15 10:25:35');

-- ----------------------------
-- Table structure for sc_library
-- ----------------------------
DROP TABLE IF EXISTS `sc_library`;
CREATE TABLE `sc_library`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `leader` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所处位置',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态1存在 0删除',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图书馆基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_library
-- ----------------------------
INSERT INTO `sc_library` VALUES (1, 'xx', 'xx', '1', '2020-04-11 00:57:22', '2020-04-11 00:57:22');
INSERT INTO `sc_library` VALUES (2, 'xx', 'xx', '1', '2020-04-11 00:57:26', '2020-04-11 00:57:26');
INSERT INTO `sc_library` VALUES (3, 'xx', 'xx', '1', '2020-04-11 00:57:28', '2020-04-11 00:57:28');
INSERT INTO `sc_library` VALUES (4, 'xx', 'xxx12222', '1', '2020-04-15 20:27:27', '2020-04-15 20:27:27');

-- ----------------------------
-- Table structure for sc_supermarket
-- ----------------------------
DROP TABLE IF EXISTS `sc_supermarket`;
CREATE TABLE `sc_supermarket`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `leader` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属地区',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态1存在 0删除',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '超市基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_supermarket
-- ----------------------------
INSERT INTO `sc_supermarket` VALUES (1, 'xx1', 'xx1', '1', '2020-04-11 00:58:03', '2020-04-11 00:58:03');
INSERT INTO `sc_supermarket` VALUES (2, 'xx', 'xx', '1', '2020-04-11 00:58:09', '2020-04-11 00:58:09');
INSERT INTO `sc_supermarket` VALUES (3, 'xx', 'xx', '1', '2020-04-11 00:58:15', '2020-04-11 00:58:15');
INSERT INTO `sc_supermarket` VALUES (4, 'xx', '1122', '1', '2020-04-15 21:54:00', '2020-04-15 21:54:00');

-- ----------------------------
-- Table structure for sc_teacher_class
-- ----------------------------
DROP TABLE IF EXISTS `sc_teacher_class`;
CREATE TABLE `sc_teacher_class`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `teacher_id` bigint(11) NULL DEFAULT NULL COMMENT '教师ID',
  `class_id` bigint(11) NULL DEFAULT NULL COMMENT '班级ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班级与老师对应表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_teacher_class
-- ----------------------------
INSERT INTO `sc_teacher_class` VALUES (1, 3, 1);
INSERT INTO `sc_teacher_class` VALUES (2, 3, 2);

-- ----------------------------
-- Table structure for sc_teacher_student
-- ----------------------------
DROP TABLE IF EXISTS `sc_teacher_student`;
CREATE TABLE `sc_teacher_student`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID(学号)',
  `faculty_id` bigint(11) NULL DEFAULT NULL COMMENT '院系ID',
  `specialty_id` bigint(11) NULL DEFAULT NULL COMMENT '专业ID',
  `class_id` bigint(11) NULL DEFAULT NULL COMMENT '班级ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `year` int(11) NULL DEFAULT NULL COMMENT '入学年份',
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '性别 1男 2女',
  `birthday` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出身年月',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `id_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `role` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '角色（0学生 1教师，2其他）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '0删除 1未删除',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10005 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_teacher_student
-- ----------------------------
INSERT INTO `sc_teacher_student` VALUES (10000, 1, 7, 2, '张三1', 2020, '20班', '2', '19950408', '1', '510921199504080318', '0', '1', '2020-04-04 15:23:02', '2020-04-04 15:23:02');
INSERT INTO `sc_teacher_student` VALUES (10001, 1, 7, 1, '李四', 2020, '21班', '2', '19950408', '1', '5109211995040803121', '0', '0', '2020-04-04 15:23:41', '2020-04-04 15:23:41');
INSERT INTO `sc_teacher_student` VALUES (10002, NULL, NULL, NULL, '李老师', NULL, '', '1', '19900307', '235252521', '110101199003079796', '1', '1', '2020-04-04 15:59:48', '2020-04-04 15:59:48');
INSERT INTO `sc_teacher_student` VALUES (10003, 1, 7, 1, '杨哈哈', 2020, '21班', '1', '19950408', '1', '51092119950408031811', '0', '1', '2020-04-05 01:59:32', '2020-04-05 01:59:32');
INSERT INTO `sc_teacher_student` VALUES (10004, 1, 7, 1, '嘻嘻', 2020, '20班', '1', '19950408', '15908401995', '5109211995040803185', '0', '1', '2020-04-15 09:21:28', '2020-04-15 09:21:28');

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
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_menu` VALUES (26, 0, '信息管理', '', 'work', '', 0, 'layui-icon layui-icon-tips', 1, '2019-09-18 10:58:18');
INSERT INTO `sys_menu` VALUES (27, 26, '学生信息', '/info/student', NULL, '', 1, '', 3, '2019-09-18 10:58:21');
INSERT INTO `sys_menu` VALUES (28, 27, '新增', '', NULL, 'info:student:add', 2, '', 1, '2019-09-18 10:58:24');
INSERT INTO `sys_menu` VALUES (29, 27, '删除', '', NULL, 'info:student:delete', 2, '', 2, '2019-09-18 10:58:29');
INSERT INTO `sys_menu` VALUES (30, 27, '编辑', '', NULL, 'info:student:edit', 2, '', 3, '2019-09-18 10:58:26');
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
INSERT INTO `sys_menu` VALUES (45, 27, '查询', '', NULL, 'info:student:student', 2, '', 5, '2019-09-19 20:00:19');
INSERT INTO `sys_menu` VALUES (46, 26, '院系信息', '/info/faculty', NULL, 'info:faculty:faculty', 1, '', 1, '2020-04-09 09:23:58');
INSERT INTO `sys_menu` VALUES (47, 26, '专业信息', '/info/specialty', NULL, '', 1, '', 2, '2020-04-09 09:24:25');
INSERT INTO `sys_menu` VALUES (48, 26, '班级信息', '/info/classes', NULL, '', 1, '', 3, '2020-04-09 09:24:50');
INSERT INTO `sys_menu` VALUES (49, 0, '卡片管理', '', NULL, '', 0, 'layui-icon layui-icon-template-1', 2, '2020-04-09 09:28:35');
INSERT INTO `sys_menu` VALUES (50, 0, '食堂管理', '', NULL, '', 0, 'layui-icon layui-icon-prev-circle', 3, '2020-04-09 09:29:05');
INSERT INTO `sys_menu` VALUES (52, 0, '超市管理', '', NULL, '', 0, 'layui-icon layui-icon-templeate-1', 4, '2020-04-09 09:29:33');
INSERT INTO `sys_menu` VALUES (53, 0, '图书馆管理', '', NULL, '', 0, 'layui-icon layui-icon-read', 5, '2020-04-09 09:30:36');
INSERT INTO `sys_menu` VALUES (54, 49, '卡片信息', '/card/info', NULL, '', 1, '', 3, '2020-04-09 09:34:49');
INSERT INTO `sys_menu` VALUES (55, 50, '食堂信息', '/manage/canteen', NULL, '', 1, '', 1, '2020-04-09 09:35:01');
INSERT INTO `sys_menu` VALUES (56, 52, '超市信息', '/manage/market', NULL, '', 1, '', 1, '2020-04-09 09:35:08');
INSERT INTO `sys_menu` VALUES (57, 53, '图书馆信息', '/manage/library', NULL, '', 1, '', 1, '2020-04-09 09:35:24');
INSERT INTO `sys_menu` VALUES (58, 49, '卡片办理', '/card/info/add', NULL, '', 1, '', 1, '2020-04-15 22:50:51');
INSERT INTO `sys_menu` VALUES (59, 49, '卡片充值', '/card/info/edit', NULL, '', 1, '', 2, '2020-04-15 22:51:42');
INSERT INTO `sys_menu` VALUES (60, 49, '消费充值', '/card/record', NULL, '', 1, '', 4, '2020-04-15 22:52:24');
INSERT INTO `sys_menu` VALUES (61, 53, '借还记录', '/manage/record', NULL, '', 1, '', 2, '2020-04-15 22:54:23');
INSERT INTO `sys_menu` VALUES (63, 53, '图书管理', '/manage/book', NULL, '', 1, '', 1, '2020-04-15 22:57:40');
INSERT INTO `sys_menu` VALUES (64, 53, '类型管理', '/manage/type', NULL, '', 1, '', 3, '2020-04-15 22:58:05');
INSERT INTO `sys_menu` VALUES (65, 26, '教师信息', '/info/teacher', NULL, '', 1, '', 5, '2020-04-25 19:44:59');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '拥有最高权限', '1', '2018-12-03 17:42:09');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `menu_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 809 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_role_menu` VALUES (755, 1, 5);
INSERT INTO `sys_role_menu` VALUES (756, 1, 6);
INSERT INTO `sys_role_menu` VALUES (757, 1, 7);
INSERT INTO `sys_role_menu` VALUES (758, 1, 39);
INSERT INTO `sys_role_menu` VALUES (759, 1, 8);
INSERT INTO `sys_role_menu` VALUES (760, 1, 9);
INSERT INTO `sys_role_menu` VALUES (761, 1, 10);
INSERT INTO `sys_role_menu` VALUES (762, 1, 32);
INSERT INTO `sys_role_menu` VALUES (763, 1, 40);
INSERT INTO `sys_role_menu` VALUES (764, 1, 11);
INSERT INTO `sys_role_menu` VALUES (765, 1, 12);
INSERT INTO `sys_role_menu` VALUES (766, 1, 13);
INSERT INTO `sys_role_menu` VALUES (767, 1, 41);
INSERT INTO `sys_role_menu` VALUES (768, 1, 15);
INSERT INTO `sys_role_menu` VALUES (769, 1, 23);
INSERT INTO `sys_role_menu` VALUES (770, 1, 24);
INSERT INTO `sys_role_menu` VALUES (771, 1, 43);
INSERT INTO `sys_role_menu` VALUES (772, 1, 35);
INSERT INTO `sys_role_menu` VALUES (773, 1, 36);
INSERT INTO `sys_role_menu` VALUES (774, 1, 37);
INSERT INTO `sys_role_menu` VALUES (775, 1, 42);
INSERT INTO `sys_role_menu` VALUES (776, 1, 44);
INSERT INTO `sys_role_menu` VALUES (777, 1, 28);
INSERT INTO `sys_role_menu` VALUES (778, 1, 29);
INSERT INTO `sys_role_menu` VALUES (779, 1, 30);
INSERT INTO `sys_role_menu` VALUES (780, 1, 45);
INSERT INTO `sys_role_menu` VALUES (781, 1, 46);
INSERT INTO `sys_role_menu` VALUES (782, 1, 47);
INSERT INTO `sys_role_menu` VALUES (783, 1, 48);
INSERT INTO `sys_role_menu` VALUES (784, 1, 54);
INSERT INTO `sys_role_menu` VALUES (785, 1, 58);
INSERT INTO `sys_role_menu` VALUES (786, 1, 59);
INSERT INTO `sys_role_menu` VALUES (787, 1, 60);
INSERT INTO `sys_role_menu` VALUES (788, 1, 55);
INSERT INTO `sys_role_menu` VALUES (789, 1, 56);
INSERT INTO `sys_role_menu` VALUES (790, 1, 57);
INSERT INTO `sys_role_menu` VALUES (791, 1, 2);
INSERT INTO `sys_role_menu` VALUES (792, 1, 3);
INSERT INTO `sys_role_menu` VALUES (793, 1, 4);
INSERT INTO `sys_role_menu` VALUES (794, 1, 1);
INSERT INTO `sys_role_menu` VALUES (795, 1, 17);
INSERT INTO `sys_role_menu` VALUES (796, 1, 33);
INSERT INTO `sys_role_menu` VALUES (797, 1, 14);
INSERT INTO `sys_role_menu` VALUES (798, 1, 27);
INSERT INTO `sys_role_menu` VALUES (799, 1, 49);
INSERT INTO `sys_role_menu` VALUES (800, 1, 50);
INSERT INTO `sys_role_menu` VALUES (801, 1, 52);
INSERT INTO `sys_role_menu` VALUES (802, 1, 65);
INSERT INTO `sys_role_menu` VALUES (803, 1, 26);
INSERT INTO `sys_role_menu` VALUES (804, 1, 61);
INSERT INTO `sys_role_menu` VALUES (805, 1, 63);
INSERT INTO `sys_role_menu` VALUES (806, 1, 64);
INSERT INTO `sys_role_menu` VALUES (807, 1, 53);
INSERT INTO `sys_role_menu` VALUES (808, 1, -1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '超级管理员', 'admin', '982d3b4564f2cf1f3c27c3dcafe5e705', '15908401993', 1, '238618edccb4395e7a2bcd852ad06b95', '2020-04-09 09:12:36');

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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (14, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
