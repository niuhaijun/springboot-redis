/*
 Navicat MySQL Data Transfer

 Source Server         : localdb
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : spring_boot_redis

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 28/08/2019 13:55:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for spring_boot_redis_user
-- ----------------------------
DROP TABLE IF EXISTS `spring_boot_redis_user`;
CREATE TABLE `spring_boot_redis_user` (
  `uuid` varchar(32) NOT NULL COMMENT '唯一ID',
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spring_boot_redis_user
-- ----------------------------
BEGIN;
INSERT INTO `spring_boot_redis_user` VALUES ('05E34980886440C5A20C7261BC791571', '2', '2', 121, 1, '2019-04-18 20:09:14', '2019-04-18 20:09:14');
INSERT INTO `spring_boot_redis_user` VALUES ('31F8FFF29C37464DB9EDF14D7B769AE6', '1', '1', 13, 1, '2019-04-15 15:27:51', '2019-04-15 15:27:51');
INSERT INTO `spring_boot_redis_user` VALUES ('56BAF1C7F2A64B818F44E2961FB4D480', '1', '1', 13, 1, '2019-04-18 20:08:51', '2019-04-18 20:08:51');
INSERT INTO `spring_boot_redis_user` VALUES ('8B6F428ED7CA45FBBDC58B287C71E483', '4', '4', 17, 1, '2019-04-15 16:01:01', '2019-04-15 16:01:01');
INSERT INTO `spring_boot_redis_user` VALUES ('8FC9A4567BEF4F7B99BF7A65E8A1B32C', '1', '1', 13, 1, '2019-04-16 21:02:45', '2019-04-16 21:02:45');
INSERT INTO `spring_boot_redis_user` VALUES ('B4882731888341B1A898255AF6B91958', '牛海军', '3', 13, 0, '2019-04-15 15:15:18', '2019-04-15 15:15:19');
INSERT INTO `spring_boot_redis_user` VALUES ('C8F3F3A72B144E07BAA251D2056337E8', '3', '3', 121, 1, '2019-04-18 20:09:19', '2019-04-18 20:09:19');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
