/*
 Navicat Premium Data Transfer

 Source Server         : localdb
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : clouddisk

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 28/11/2019 20:55:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for files_info
-- ----------------------------
DROP TABLE IF EXISTS `files_info`;
CREATE TABLE `files_info`  (
  `fileId` int(11) NOT NULL AUTO_INCREMENT,
  `user_Id` int(11) NULL DEFAULT 1,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `uploaddate` datetime(0) NULL DEFAULT NULL,
  `size` double NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `folder_Id` int(11) NULL DEFAULT 1 COMMENT '1',
  PRIMARY KEY (`fileId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of files_info
-- ----------------------------
INSERT INTO `files_info` VALUES (1, NULL, 'pom.xml', '2019-10-24 07:57:09', 2, 'H://upload/pom.xml', 1);
INSERT INTO `files_info` VALUES (2, NULL, 'README', '2019-10-24 08:00:53', 0, 'H://upload/README', 2);
INSERT INTO `files_info` VALUES (3, NULL, 'pom.xml', '2019-10-24 08:03:13', 2, 'H://upload/pom.xml', 3);
INSERT INTO `files_info` VALUES (4, NULL, 'pom.xml', '2019-10-24 08:04:17', 2, 'H://upload/pom.xml', NULL);
INSERT INTO `files_info` VALUES (5, NULL, 'pom.xml', '2019-10-24 08:04:20', 2, 'H://upload/pom.xml', NULL);
INSERT INTO `files_info` VALUES (6, NULL, 'pom.xml', '2019-10-24 08:20:43', 2, 'H://upload/pom.xml', NULL);
INSERT INTO `files_info` VALUES (7, NULL, 'pom.xml', '2019-10-24 08:21:00', 2, 'H://upload/pom.xml', NULL);
INSERT INTO `files_info` VALUES (8, NULL, 'clouddisk.iml', '2019-10-24 08:29:44', 8, 'H://upload/clouddisk.iml', NULL);
INSERT INTO `files_info` VALUES (9, 12, 'pom.xml', '2019-10-24 08:30:26', 2, 'H://upload/pom.xml', 0);
INSERT INTO `files_info` VALUES (10, 12, 'pom.xml', '2019-10-24 08:31:35', 2, 'H://upload/pom.xml', 0);
INSERT INTO `files_info` VALUES (11, 12, 'pom.xml', '2019-10-24 08:40:26', 2, 'H://upload/pom.xml', 0);
INSERT INTO `files_info` VALUES (12, 12, 'clouddisk.iml', '2019-10-24 08:41:21', 8, 'H://upload/clouddisk.iml', 0);
INSERT INTO `files_info` VALUES (13, 12, 'README', '2019-10-24 08:42:02', 0, 'H://upload/README', 0);
INSERT INTO `files_info` VALUES (14, 12, 'pom.xml', '2019-10-24 08:43:05', 2, 'H://upload/pom.xml', 0);
INSERT INTO `files_info` VALUES (15, 12, 'clouddisk.iml', '2019-10-24 08:46:07', 8, 'H://upload/clouddisk.iml', 0);
INSERT INTO `files_info` VALUES (16, 1, 'clouddisk.iml', '2019-10-24 08:48:01', 8, 'H://upload/clouddisk.iml', 0);
INSERT INTO `files_info` VALUES (17, 1, 'pom.xml', '2019-10-24 08:49:38', 2, 'H://upload/pom.xml', 0);
INSERT INTO `files_info` VALUES (18, 1, 'pom.xml', '2019-10-24 08:50:36', 2, 'H://upload/pom.xml', 0);
INSERT INTO `files_info` VALUES (19, 1, 'pom.xml', '2019-10-24 08:52:35', 2, 'H://upload/pom.xml', NULL);
INSERT INTO `files_info` VALUES (20, 1, 'pom.xml', '2019-10-24 09:00:22', 2, 'H://upload/pom.xml', NULL);
INSERT INTO `files_info` VALUES (21, NULL, 'clouddisk.iml', '2019-10-24 10:49:52', 8, 'H://upload/clouddisk.iml', NULL);
INSERT INTO `files_info` VALUES (22, NULL, 'clouddisk.iml', '2019-10-24 10:50:12', 8, 'H://upload/clouddisk.iml', NULL);
INSERT INTO `files_info` VALUES (23, NULL, 'README', '2019-10-24 10:55:43', 0, 'H://upload/README', NULL);
INSERT INTO `files_info` VALUES (24, NULL, 'README', '2019-10-24 10:56:25', 0, 'H://upload/README', NULL);
INSERT INTO `files_info` VALUES (25, 1, 'README', '2019-10-24 10:58:20', 0, 'H://upload/README', NULL);
INSERT INTO `files_info` VALUES (26, 1, 'README', '2019-10-24 10:59:34', 0, 'H://upload/README', NULL);
INSERT INTO `files_info` VALUES (27, 1, 'README', '2019-10-24 10:59:44', 0, 'H://upload/README', NULL);
INSERT INTO `files_info` VALUES (28, 1, 'README', '2019-10-24 10:59:50', 0, 'H://upload/README', NULL);
INSERT INTO `files_info` VALUES (29, 1, 'README', '2019-10-24 11:36:54', 0, 'H://upload/README', NULL);
INSERT INTO `files_info` VALUES (30, 1, 'README', '2019-10-24 11:36:56', 0, 'H://upload/README', NULL);
INSERT INTO `files_info` VALUES (31, 1, 'README', '2019-10-24 11:37:08', 0, 'H://upload/README', NULL);
INSERT INTO `files_info` VALUES (32, 1, 'pom.xml', '2019-10-24 11:45:39', 2, 'H://upload/pom.xml', NULL);
INSERT INTO `files_info` VALUES (33, 1, 'README', '2019-10-24 12:09:41', 0, 'H://upload/README', NULL);
INSERT INTO `files_info` VALUES (34, 1, 'clouddisk.iml', '2019-10-25 01:25:03', 8, 'H://upload/clouddisk.iml', NULL);
INSERT INTO `files_info` VALUES (35, 1, 'clouddisk.iml', '2019-10-25 01:25:06', 8, 'H://upload/clouddisk.iml', NULL);
INSERT INTO `files_info` VALUES (36, 1, 'clouddisk.iml', '2019-10-25 01:25:08', 8, 'H://upload/clouddisk.iml', NULL);
INSERT INTO `files_info` VALUES (37, 1, 'clouddisk.iml', '2019-10-25 01:25:16', 8, 'H://upload/clouddisk.iml', NULL);
INSERT INTO `files_info` VALUES (38, 1, 'clouddisk.iml', '2019-10-25 01:25:26', 8, 'H://upload/clouddisk.iml', NULL);
INSERT INTO `files_info` VALUES (39, 1, 'n0153282900000005.jpg', '2019-10-28 03:03:11', 5, '/kingfou/n0153282900000005.jpg', 1);

-- ----------------------------
-- Table structure for folders
-- ----------------------------
DROP TABLE IF EXISTS `folders`;
CREATE TABLE `folders`  (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `folderId` int(11) NOT NULL AUTO_INCREMENT,
  `uploaddate` datetime(0) NULL DEFAULT NULL,
  `size` double NULL DEFAULT NULL,
  `userId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`folderId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of folders
-- ----------------------------
INSERT INTO `folders` VALUES ('电影', 1, '2019-10-17 10:37:08', 300, 1);
INSERT INTO `folders` VALUES ('学习', 2, '2019-10-02 10:38:53', 20, 1);
INSERT INTO `folders` VALUES ('其他', 3, '2019-10-23 10:39:39', 30, 1);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stu_Numb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('kingfou', '201821033400', 1, '1111', '111@mail.com');
INSERT INTO `users` VALUES ('test', '1111', 2, '1111', NULL);
INSERT INTO `users` VALUES ('test', '2222', 3, '2222', NULL);

SET FOREIGN_KEY_CHECKS = 1;
