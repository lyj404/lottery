/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : lottery

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 15/07/2023 14:15:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accept_prize
-- ----------------------------
DROP TABLE IF EXISTS `accept_prize`;
CREATE TABLE `accept_prize`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `record_id` bigint NOT NULL COMMENT '抽奖记录id',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni`(`record_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '领奖表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accept_prize
-- ----------------------------
INSERT INTO `accept_prize` VALUES (1, 43, '18664334554', '湖南省 怀化市 洪江市', '2023-07-13 21:45:04', '2023-07-13 21:45:04');

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动名称',
  `start_time` datetime NOT NULL COMMENT '活动开始时间',
  `end_time` datetime NOT NULL COMMENT '活动结束时间',
  `activity_description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '活动表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (7, '测试活动', '2023-07-03 00:00:00', '2023-08-03 00:00:00', '测试活动', '2023-07-12 23:15:15', '2023-07-12 23:15:15');

-- ----------------------------
-- Table structure for activity_rule
-- ----------------------------
DROP TABLE IF EXISTS `activity_rule`;
CREATE TABLE `activity_rule`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `activity_id` bigint NOT NULL COMMENT '活动id',
  `rule_id` bigint NOT NULL COMMENT '规则id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique`(`activity_id` ASC, `rule_id` ASC) USING BTREE COMMENT '唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '活动规则关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_rule
-- ----------------------------
INSERT INTO `activity_rule` VALUES (4, 4, 1, '2023-07-08 11:59:47', '2023-07-08 11:59:47');
INSERT INTO `activity_rule` VALUES (7, 7, 1, '2023-07-12 23:15:15', '2023-07-12 23:15:15');

-- ----------------------------
-- Table structure for award
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prize_id` bigint NOT NULL DEFAULT 0 COMMENT '奖品id',
  `activity_id` bigint NOT NULL COMMENT '活动id',
  `number` int NOT NULL COMMENT '奖项数量',
  `award_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '奖项名称',
  `probability` double NOT NULL COMMENT '概率',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '奖项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of award
-- ----------------------------
INSERT INTO `award` VALUES (17, 8, 7, 2, '特等奖', 0.01, '2023-07-12 23:15:16', '2023-07-12 23:15:16');
INSERT INTO `award` VALUES (18, 1, 7, 1, '一等奖', 0.05, '2023-07-12 23:15:16', '2023-07-12 23:15:16');
INSERT INTO `award` VALUES (19, 2, 7, 1, '二等奖', 0.1, '2023-07-12 23:15:16', '2023-07-12 23:15:16');
INSERT INTO `award` VALUES (20, 3, 7, 2, '三等奖', 0.2, '2023-07-12 23:15:16', '2023-07-12 23:15:16');
INSERT INTO `award` VALUES (21, 4, 7, 0, '四等奖', 0.5, '2023-07-12 23:15:16', '2023-07-12 23:15:16');
INSERT INTO `award` VALUES (22, 0, 7, 10, '谢谢参与', 0.65, '2023-07-12 23:15:16', '2023-07-12 23:15:16');

-- ----------------------------
-- Table structure for prize
-- ----------------------------
DROP TABLE IF EXISTS `prize`;
CREATE TABLE `prize`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prize_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '奖品名称',
  `inventory` int NOT NULL COMMENT '库存',
  `money` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '类型，1-商品，2-金钱',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '奖品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prize
-- ----------------------------
INSERT INTO `prize` VALUES (1, '苹果13pro', 98, 0.00, 1, '2023-07-04 23:39:00', '2023-07-04 23:39:00');
INSERT INTO `prize` VALUES (2, '华为p50 ', 88, 0.00, 1, '2023-07-04 23:39:20', '2023-07-04 23:39:20');
INSERT INTO `prize` VALUES (3, '小米13pro', 85, 0.00, 1, '2023-07-04 23:39:34', '2023-07-04 23:39:34');
INSERT INTO `prize` VALUES (4, '100元现金', 34, 100.00, 2, '2023-07-04 23:51:17', '2023-07-04 23:51:17');
INSERT INTO `prize` VALUES (5, '小米手环7', 100, 0.00, 1, '2023-07-08 12:01:17', '2023-07-08 12:01:19');
INSERT INTO `prize` VALUES (6, '5元现金', 200, 5.00, 2, '2023-07-08 12:01:43', '2023-07-08 12:01:46');
INSERT INTO `prize` VALUES (7, '蓝牙耳机', 200, 0.00, 1, '2023-07-08 12:02:08', '2023-07-08 12:02:11');
INSERT INTO `prize` VALUES (8, 'Macbook M2 Pro', 8, NULL, 1, '2023-07-08 12:04:16', '2023-07-08 12:04:20');

-- ----------------------------
-- Table structure for records
-- ----------------------------
DROP TABLE IF EXISTS `records`;
CREATE TABLE `records`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `activity_id` bigint NOT NULL COMMENT '活动id',
  `activity_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动名称',
  `award_id` bigint NOT NULL COMMENT '奖项id',
  `is_winning` tinyint(1) NULL DEFAULT 0 COMMENT '是否中将，0-未中将，1-中将',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态，0，1，2，3',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '抽奖记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of records
-- ----------------------------
INSERT INTO `records` VALUES (43, 1, 7, '测试活动', 21, 1, 4, '2023-07-13 09:29:59', '2023-07-13 09:29:59');
INSERT INTO `records` VALUES (44, 1, 7, '测试活动', 19, 1, 4, '2023-07-15 13:42:29', '2023-07-15 13:42:29');
INSERT INTO `records` VALUES (45, 1, 7, '测试活动', 22, 0, 1, '2023-07-15 13:50:59', '2023-07-15 13:50:59');
INSERT INTO `records` VALUES (46, 1, 7, '测试活动', 22, 0, 1, '2023-07-15 13:53:45', '2023-07-15 13:53:45');

-- ----------------------------
-- Table structure for rule
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rule_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '规则名称',
  `max_join_number` int NOT NULL COMMENT '最大可参与次数',
  `max_winning_number` int NOT NULL COMMENT '最大可中将次数',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rule
-- ----------------------------
INSERT INTO `rule` VALUES (1, '规则一', 10, 5, '2023-07-05 11:01:56', '2023-07-05 11:01:56');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名字',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'lyj', 'e10adc3949ba59abbe56e057f20f883e', '李某', '18652046781', '2023-07-02 10:54:56', '2023-07-02 10:54:56');
INSERT INTO `user` VALUES (2, 'Tom', '202cb962ac59075b964b07152d234b70', '戴杰', '18188345569', '2023-07-02 10:55:19', '2023-07-03 13:20:56');
INSERT INTO `user` VALUES (3, 'Martinez', 'e10adc3949ba59abbe56e057f20f883e', '马明', '18693946287', '2023-07-03 11:35:11', '2023-07-03 11:35:11');
INSERT INTO `user` VALUES (26, 'test', 'e10adc3949ba59abbe56e057f20f883e', '马杰', '18188345569', '2023-07-15 10:44:55', '2023-07-15 10:44:55');

-- ----------------------------
-- Table structure for wallet
-- ----------------------------
DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `balance` decimal(20, 2) NULL DEFAULT NULL COMMENT '余额',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '钱包表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wallet
-- ----------------------------
INSERT INTO `wallet` VALUES (1, 1, 100.00, '2023-07-14 10:43:51', '2023-07-14 10:43:51');
INSERT INTO `wallet` VALUES (3, 26, 0.00, '2023-07-15 10:44:55', '2023-07-15 10:45:00');

SET FOREIGN_KEY_CHECKS = 1;
