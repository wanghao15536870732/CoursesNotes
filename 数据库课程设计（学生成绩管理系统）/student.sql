/*
 Navicat Premium Data Transfer

 Source Server         : StudentManagement
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : student

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 10/01/2020 11:22:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `CLno` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `CLnum` int(11) NULL DEFAULT NULL,
  `CLdept` char(50) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `CLmajor` char(50) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`CLno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gb2312 COLLATE = gb2312_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('17070141', 53, '大数据学院', '计算机科学与技术');
INSERT INTO `class` VALUES ('17070142', 52, '大数据学院', '计算机科学与技术');
INSERT INTO `class` VALUES ('17070143', 53, '大数据学院', '网络工程');
INSERT INTO `class` VALUES ('17070144', 55, '大数据学院', '网络工程');
INSERT INTO `class` VALUES ('17070241', 51, '大数据学院', '物联网工程');
INSERT INTO `class` VALUES ('17070242', 54, '大数据学院', '物联网工程');
INSERT INTO `class` VALUES ('17070541', 50, '大数据学院', '数据科学与大数据技术');
INSERT INTO `class` VALUES ('17070542', 48, '大数据学院', '数据科学与大数据技术');
INSERT INTO `class` VALUES ('17090041', 53, '经济管理学院', '国际经济与贸易');
INSERT INTO `class` VALUES ('17090042', 49, '经济管理学院', '国际经济与贸易');
INSERT INTO `class` VALUES ('17090143', 52, '经济管理学院', '工商管理');
INSERT INTO `class` VALUES ('17090144', 54, '经济管理学院', '工商管理');
INSERT INTO `class` VALUES ('17090245', 50, '经济管理学院', '人力资源管理');
INSERT INTO `class` VALUES ('17090246', 48, '经济管理学院', '人力资源管理');

-- ----------------------------
-- Table structure for classcourse
-- ----------------------------
DROP TABLE IF EXISTS `classcourse`;
CREATE TABLE `classcourse`  (
  `CLno` char(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Cno` char(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Cyear` char(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Cterm` char(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`CLno`, `Cno`) USING BTREE,
  INDEX `CCno`(`Cno`) USING BTREE,
  CONSTRAINT `CCno` FOREIGN KEY (`Cno`) REFERENCES `course` (`Cno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `CLno` FOREIGN KEY (`CLno`) REFERENCES `class` (`CLno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gb2312 COLLATE = gb2312_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classcourse
-- ----------------------------
INSERT INTO `classcourse` VALUES ('17070141', 'A04075501', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17070141', 'A04075502', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17070141', 'B02076605', '2019-2020', '第二学期');
INSERT INTO `classcourse` VALUES ('17070141', 'B03076601', '2019-2020', '第二学期');
INSERT INTO `classcourse` VALUES ('17070142', 'A04075501', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17070142', 'A04075502', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17070142', 'B02076605', '2019-2020', '第二学期');
INSERT INTO `classcourse` VALUES ('17070142', 'B02076607', '2019-2020', '第二学期');
INSERT INTO `classcourse` VALUES ('17070142', 'B03076601', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17070142', 'B03076602', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17070142', 'B03076603', '2019-2020', '第二学期');
INSERT INTO `classcourse` VALUES ('17070142', 'C02075504', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17070143', 'B02076607', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17070143', 'B03076602', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17070143', 'C02075504', '2019-2020', '第二学期');
INSERT INTO `classcourse` VALUES ('17070143', 'C02075506', '2019-2020', '第二学期');
INSERT INTO `classcourse` VALUES ('17070144', 'B03076603', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17070144', 'C02075506', '2019-2020', '第二学期');
INSERT INTO `classcourse` VALUES ('17070241', 'E14653213', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17070241', 'E14653444', '2019-2020', '第二学期');
INSERT INTO `classcourse` VALUES ('17070242', 'B03076602', '2019-2020', '第二学期');
INSERT INTO `classcourse` VALUES ('17070242', 'E15631653', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17070541', 'E14653213', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17070541', 'F16532136', '2019-2020', '第二学期');
INSERT INTO `classcourse` VALUES ('17090041', 'J18965163', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17090041', 'J18965324', '2019-2020', '第二学期');
INSERT INTO `classcourse` VALUES ('17090041', 'J29653215', '2019-2020', '第二学期');
INSERT INTO `classcourse` VALUES ('17090143', 'H15653163', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17090143', 'H15653164', '2019-2020', '第二学期');
INSERT INTO `classcourse` VALUES ('17090143', 'H48965321', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17090245', 'G18653216', '2019-2020', '第一学期');
INSERT INTO `classcourse` VALUES ('17090245', 'G18965322', '2019-2020', '第二学期');
INSERT INTO `classcourse` VALUES ('17090245', 'G49865326', '2019-2020', '第一学期');

-- ----------------------------
-- Table structure for classteacher
-- ----------------------------
DROP TABLE IF EXISTS `classteacher`;
CREATE TABLE `classteacher`  (
  `CTno` char(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Tno` char(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Cyear` char(255) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Cterm` char(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`CTno`, `Tno`) USING BTREE,
  INDEX `CTtno`(`Tno`) USING BTREE,
  CONSTRAINT `CTno` FOREIGN KEY (`CTno`) REFERENCES `class` (`CLno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `CTtno` FOREIGN KEY (`Tno`) REFERENCES `teacher` (`Tno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gb2312 COLLATE = gb2312_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classteacher
-- ----------------------------
INSERT INTO `classteacher` VALUES ('17070141', 'T01', '2019-2020', '第二学期');
INSERT INTO `classteacher` VALUES ('17070141', 'T02', '2019-2020', '第一学期');
INSERT INTO `classteacher` VALUES ('17070142', 'T03', '2019-2020', '第一学期');
INSERT INTO `classteacher` VALUES ('17070142', 'T05', '2019-2020', '第二学期');
INSERT INTO `classteacher` VALUES ('17070143', 'T02', '2019-2020', '第二学期');
INSERT INTO `classteacher` VALUES ('17070143', 'T05', '2019-2020', '第一学期');
INSERT INTO `classteacher` VALUES ('17070541', 'T02', '2019-2020', '第二学期');
INSERT INTO `classteacher` VALUES ('17070541', 'T05', '2019-2020', '第一学期');
INSERT INTO `classteacher` VALUES ('17090041', 'T06', '2019-2020', '第一学期');
INSERT INTO `classteacher` VALUES ('17090041', 'T07', '2019-2020', '第二学期');
INSERT INTO `classteacher` VALUES ('17090143', 'T08', '2019-2020', '第二学期');
INSERT INTO `classteacher` VALUES ('17090143', 'T09', '2019-2020', '第一学期');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `Cno` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Cname` varchar(50) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Cperiod` int(2) NULL DEFAULT NULL,
  `Ccredit` double(4, 0) NULL DEFAULT NULL,
  `Cattribute` char(4) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  PRIMARY KEY (`Cno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gb2312 COLLATE = gb2312_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('A04075501', '单片机原理及应用', 54, 4, '必修');
INSERT INTO `course` VALUES ('A04075502', '编译原理', 56, 4, '必修');
INSERT INTO `course` VALUES ('B02076605', '数据库系统概论', 36, 3, '必修');
INSERT INTO `course` VALUES ('B02076607', '软件工程', 34, 2, '必修');
INSERT INTO `course` VALUES ('B03076601', '操作系统', 56, 3, '必修');
INSERT INTO `course` VALUES ('B03076602', '计算机组成原理', 56, 4, '必修');
INSERT INTO `course` VALUES ('B03076603', '数值分析', 38, 2, '必修');
INSERT INTO `course` VALUES ('B03478H', '图像处理', 536, 3, '必修');
INSERT INTO `course` VALUES ('C02075504', 'C语言程序与设计', 48, 2, '必修');
INSERT INTO `course` VALUES ('C02075506', '概率论与统计', 48, 3, '必修');
INSERT INTO `course` VALUES ('C02075509', 'C++程序与设计', 48, 3, '必修');
INSERT INTO `course` VALUES ('C845945', 'C#程序语言', 36, 2, '专业选修');
INSERT INTO `course` VALUES ('D02075548', '高等数学(上)', 56, 4, '必修');
INSERT INTO `course` VALUES ('D02075550', '高等数学(下)', 56, 3, '必修');
INSERT INTO `course` VALUES ('D14896536', '大学英语4', 48, 3, '必修');
INSERT INTO `course` VALUES ('D15632166', '大学英语3', 48, 3, '必修');
INSERT INTO `course` VALUES ('D16584515', '大学英语2', 48, 3, '必修');
INSERT INTO `course` VALUES ('D25156345', '大学英语1', 48, 3, '必修');
INSERT INTO `course` VALUES ('E14653213', '离散数学', 56, 2, '必修');
INSERT INTO `course` VALUES ('E14653444', '嵌入式系统与单片机', 56, 3, '必修');
INSERT INTO `course` VALUES ('E14896536', '线代传感器技术', 48, 3, '必修');
INSERT INTO `course` VALUES ('E15214653', '网络通信协议分析与应用', 48, 2, '必修');
INSERT INTO `course` VALUES ('E15631653', '数字逻辑电路', 56, 2, '必修');
INSERT INTO `course` VALUES ('E16531636', '线性代数', 52, 3, '必修');
INSERT INTO `course` VALUES ('E16545496', 'java程序设计基础', 56, 4, '必修');
INSERT INTO `course` VALUES ('E16865325', '物联网工程导论', 48, 3, '必修');
INSERT INTO `course` VALUES ('E18965351', '云计算与物联网', 48, 3, '必修');
INSERT INTO `course` VALUES ('E25896532', '无线传感器网络与RFID技术', 48, 3, '必修');
INSERT INTO `course` VALUES ('E26211986', 'python', 48, 3, '必修');
INSERT INTO `course` VALUES ('E48965316', '计算机网络', 56, 4, '必修');
INSERT INTO `course` VALUES ('F16532136', '非结构化大数据分析', 48, 3, '必修');
INSERT INTO `course` VALUES ('F17858326', '统计学习', 32, 2, '专业选修');
INSERT INTO `course` VALUES ('F18653146', '数据科学算法导论', 32, 2, '专业选修');
INSERT INTO `course` VALUES ('F46513246', '算法分析与设计', 32, 2, '必修');
INSERT INTO `course` VALUES ('G14896532', '微观经济学', 48, 3, '必修');
INSERT INTO `course` VALUES ('G18653216', '微观经济学', 32, 2, '必修');
INSERT INTO `course` VALUES ('G18653256', '人力资源管理', 56, 4, '必修');
INSERT INTO `course` VALUES ('G18965322', '市场营销', 48, 3, '必修');
INSERT INTO `course` VALUES ('G48965362', '经济法', 48, 3, '必修');
INSERT INTO `course` VALUES ('G49865326', '会计学', 56, 4, '必修');
INSERT INTO `course` VALUES ('G65320638', '统计学', 48, 2, '必修');
INSERT INTO `course` VALUES ('H15653163', '管理学', 56, 4, '必修');
INSERT INTO `course` VALUES ('H15653164', '宏观经济学', 56, 4, '必修');
INSERT INTO `course` VALUES ('H24868988', '数据库', 48, 4, '必修');
INSERT INTO `course` VALUES ('H48965321', '财务管理', 48, 3, '必修');
INSERT INTO `course` VALUES ('H54633523', '饥饿反黑客', 48, 3, '专业选修');
INSERT INTO `course` VALUES ('J18965163', '投资分析与管理', 48, 3, '必修');
INSERT INTO `course` VALUES ('J18965164', '合同法', 56, 4, '必修');
INSERT INTO `course` VALUES ('J18965324', '商业法', 48, 3, '必修');
INSERT INTO `course` VALUES ('J29653215', '税务筹划', 48, 3, '必修');

-- ----------------------------
-- Table structure for csgrade
-- ----------------------------
DROP TABLE IF EXISTS `csgrade`;
CREATE TABLE `csgrade`  (
  `Sno` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Cno` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Grade` double(4, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`Sno`, `Cno`) USING BTREE,
  INDEX `Sno`(`Sno`) USING BTREE,
  INDEX `Cno`(`Cno`) USING BTREE,
  CONSTRAINT `Sno` FOREIGN KEY (`Sno`) REFERENCES `student` (`Sno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gb2312 COLLATE = gb2312_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of csgrade
-- ----------------------------
INSERT INTO `csgrade` VALUES ('1707004104', 'A04075501', 78);
INSERT INTO `csgrade` VALUES ('1707004104', 'A04075502', 85);
INSERT INTO `csgrade` VALUES ('1707004104', 'B02076605', 90);
INSERT INTO `csgrade` VALUES ('1707004104', 'B03076601', 88);
INSERT INTO `csgrade` VALUES ('1707004112', 'A04075501', 90);
INSERT INTO `csgrade` VALUES ('1707004112', 'A04075502', 88);
INSERT INTO `csgrade` VALUES ('1707004112', 'B02076605', 92);
INSERT INTO `csgrade` VALUES ('1707004112', 'B02076607', 86);
INSERT INTO `csgrade` VALUES ('1707004112', 'B03076601', 78);
INSERT INTO `csgrade` VALUES ('1707004112', 'B03076602', 86);
INSERT INTO `csgrade` VALUES ('1707004112', 'B03076603', 91);
INSERT INTO `csgrade` VALUES ('1707004112', 'C02075504', 85);
INSERT INTO `csgrade` VALUES ('1707004115', 'A04075501', 88);
INSERT INTO `csgrade` VALUES ('1707004115', 'A04075502', 89);
INSERT INTO `csgrade` VALUES ('1707004115', 'B02076605', 92);
INSERT INTO `csgrade` VALUES ('1707004115', 'B02076607', 91);
INSERT INTO `csgrade` VALUES ('1707004115', 'B03076601', 88);
INSERT INTO `csgrade` VALUES ('1707004115', 'B03076602', 86);
INSERT INTO `csgrade` VALUES ('1707004115', 'B03076603', 92);
INSERT INTO `csgrade` VALUES ('1707004115', 'C02075504', 86);
INSERT INTO `csgrade` VALUES ('1707004129', 'A04075502', 89);
INSERT INTO `csgrade` VALUES ('1707004129', 'B02076605', 98);
INSERT INTO `csgrade` VALUES ('1707004129', 'B02076607', 96);
INSERT INTO `csgrade` VALUES ('1707004129', 'B03076601', 89);
INSERT INTO `csgrade` VALUES ('1707004129', 'B03076602', 92);
INSERT INTO `csgrade` VALUES ('1707004129', 'B03076603', 88);
INSERT INTO `csgrade` VALUES ('1707004129', 'C02075504', 89);
INSERT INTO `csgrade` VALUES ('1707004130', 'A04075501', 85);
INSERT INTO `csgrade` VALUES ('1707004130', 'A04075502', 99);
INSERT INTO `csgrade` VALUES ('1707004130', 'B02076605', 98);
INSERT INTO `csgrade` VALUES ('1707004130', 'B02076607', 99);
INSERT INTO `csgrade` VALUES ('1707004130', 'B03076601', 88);
INSERT INTO `csgrade` VALUES ('1707004130', 'B03076602', 83);
INSERT INTO `csgrade` VALUES ('1707004130', 'B03076603', 96);
INSERT INTO `csgrade` VALUES ('1707004130', 'C02075504', 98);
INSERT INTO `csgrade` VALUES ('1707004138', 'A04075501', 80);
INSERT INTO `csgrade` VALUES ('1707004138', 'A04075502', 87);
INSERT INTO `csgrade` VALUES ('1707004138', 'B02076605', 89);
INSERT INTO `csgrade` VALUES ('1707004138', 'B03076601', 89);
INSERT INTO `csgrade` VALUES ('1707004141', 'A04075501', 88);
INSERT INTO `csgrade` VALUES ('1707004141', 'A04075502', 93);
INSERT INTO `csgrade` VALUES ('1707004141', 'B02076605', 93);
INSERT INTO `csgrade` VALUES ('1707004141', 'B03076601', 98);
INSERT INTO `csgrade` VALUES ('1707004203', 'A04075501', 95);
INSERT INTO `csgrade` VALUES ('1707004203', 'A04075502', 93);
INSERT INTO `csgrade` VALUES ('1707004203', 'B02076605', 94);
INSERT INTO `csgrade` VALUES ('1707004203', 'B03076601', 89);
INSERT INTO `csgrade` VALUES ('1707004206', 'A04075501', 93);
INSERT INTO `csgrade` VALUES ('1707004206', 'A04075502', 92);
INSERT INTO `csgrade` VALUES ('1707004206', 'B02076605', 88);
INSERT INTO `csgrade` VALUES ('1707004206', 'B02076607', 89);
INSERT INTO `csgrade` VALUES ('1707004206', 'B03076601', 87);
INSERT INTO `csgrade` VALUES ('1707004206', 'B03076602', 86);
INSERT INTO `csgrade` VALUES ('1707004206', 'B03076603', 88);
INSERT INTO `csgrade` VALUES ('1707004206', 'C02075504', 91);
INSERT INTO `csgrade` VALUES ('1707004219', 'A04075501', 89);
INSERT INTO `csgrade` VALUES ('1707004219', 'A04075502', 88);
INSERT INTO `csgrade` VALUES ('1707004219', 'B02076605', 96);
INSERT INTO `csgrade` VALUES ('1707004219', 'B03076601', 93);
INSERT INTO `csgrade` VALUES ('1707004226', 'B02076605', 88);
INSERT INTO `csgrade` VALUES ('1707004227', 'A04075501', 85);
INSERT INTO `csgrade` VALUES ('1707004227', 'A04075502', 97);
INSERT INTO `csgrade` VALUES ('1707004227', 'B02076605', 99);
INSERT INTO `csgrade` VALUES ('1707004227', 'B02076607', 96);
INSERT INTO `csgrade` VALUES ('1707004227', 'B03076601', 94);
INSERT INTO `csgrade` VALUES ('1707004227', 'B03076602', 89);
INSERT INTO `csgrade` VALUES ('1707004227', 'B03076603', 90);
INSERT INTO `csgrade` VALUES ('1707004227', 'C02075504', 99);
INSERT INTO `csgrade` VALUES ('1707004237', 'A04075501', 93);
INSERT INTO `csgrade` VALUES ('1707004237', 'A04075502', 96);
INSERT INTO `csgrade` VALUES ('1707004237', 'B02076605', 89);
INSERT INTO `csgrade` VALUES ('1707004237', 'B03076601', 95);
INSERT INTO `csgrade` VALUES ('1707004306', 'A04075501', 95);
INSERT INTO `csgrade` VALUES ('1707004306', 'A04075502', 93);
INSERT INTO `csgrade` VALUES ('1707004306', 'B02076605', 89);
INSERT INTO `csgrade` VALUES ('1707004306', 'B03076601', 88);
INSERT INTO `csgrade` VALUES ('1707004413', 'A04075501', 96);
INSERT INTO `csgrade` VALUES ('1707004413', 'A04075502', 93);
INSERT INTO `csgrade` VALUES ('1707004413', 'B02076605', 89);
INSERT INTO `csgrade` VALUES ('1707004413', 'B02076607', 88);
INSERT INTO `csgrade` VALUES ('1707004413', 'B03076601', 96);
INSERT INTO `csgrade` VALUES ('1707004413', 'B03076602', 97);
INSERT INTO `csgrade` VALUES ('1707004413', 'B03076603', 100);
INSERT INTO `csgrade` VALUES ('1707004413', 'C02075504', 84);
INSERT INTO `csgrade` VALUES ('1707004420', 'A04075501', 96);
INSERT INTO `csgrade` VALUES ('1707004420', 'A04075502', 86);
INSERT INTO `csgrade` VALUES ('1707004420', 'B02076605', 95);
INSERT INTO `csgrade` VALUES ('1707004420', 'B03076601', 98);
INSERT INTO `csgrade` VALUES ('1707004516', 'B02076605', 90);
INSERT INTO `csgrade` VALUES ('1707004716', 'A04075501', 95);
INSERT INTO `csgrade` VALUES ('1707004716', 'A04075502', 78);
INSERT INTO `csgrade` VALUES ('1707004716', 'B02076605', 93);
INSERT INTO `csgrade` VALUES ('1707004716', 'B02076607', 88);
INSERT INTO `csgrade` VALUES ('1707004716', 'B03076601', 94);
INSERT INTO `csgrade` VALUES ('1707004716', 'B03076602', 78);
INSERT INTO `csgrade` VALUES ('1707004716', 'B03076603', 95);
INSERT INTO `csgrade` VALUES ('1707004716', 'C02075504', 89);
INSERT INTO `csgrade` VALUES ('1707004716', 'D14896536', 90);
INSERT INTO `csgrade` VALUES ('1707004742', 'A04075501', 90);
INSERT INTO `csgrade` VALUES ('1707004742', 'A04075502', 95);
INSERT INTO `csgrade` VALUES ('1707004742', 'B02076605', 93);
INSERT INTO `csgrade` VALUES ('1707004742', 'B02076607', 96);
INSERT INTO `csgrade` VALUES ('1707004742', 'B03076601', 89);
INSERT INTO `csgrade` VALUES ('1707004742', 'B03076602', 91);
INSERT INTO `csgrade` VALUES ('1707004742', 'B03076603', 97);
INSERT INTO `csgrade` VALUES ('1707004742', 'C02075504', 88);
INSERT INTO `csgrade` VALUES ('1707014252', 'A04075501', 87);
INSERT INTO `csgrade` VALUES ('1707014252', 'A04075502', 88);
INSERT INTO `csgrade` VALUES ('1707014252', 'B02076605', 95);
INSERT INTO `csgrade` VALUES ('1707014252', 'B02076607', 84);
INSERT INTO `csgrade` VALUES ('1707014252', 'B03076601', 83);
INSERT INTO `csgrade` VALUES ('1707014252', 'B03076602', 89);
INSERT INTO `csgrade` VALUES ('1707014252', 'B03076603', 95);
INSERT INTO `csgrade` VALUES ('1707014252', 'C02075504', 92);
INSERT INTO `csgrade` VALUES ('1709004118', 'B02076607', 94);
INSERT INTO `csgrade` VALUES ('1709014308', 'G14896532', 88);

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `Eclno` char(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Ecno` char(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Eposition` char(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Etime` datetime(6) NULL DEFAULT NULL,
  `Endtime` datetime(6) NULL DEFAULT NULL,
  `Eno` char(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  PRIMARY KEY (`Eclno`, `Ecno`, `Eno`) USING BTREE,
  INDEX `Ecno`(`Ecno`) USING BTREE,
  CONSTRAINT `Eclno` FOREIGN KEY (`Eclno`) REFERENCES `class` (`CLno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Ecno` FOREIGN KEY (`Ecno`) REFERENCES `course` (`Cno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gb2312 COLLATE = gb2312_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES ('17070141', 'A04075501', '教学西区15301H', '2020-01-04 14:00:00.000000', '2020-01-04 16:00:00.000000', 'E07014104');
INSERT INTO `exam` VALUES ('17070141', 'B02076605', '教学东区01106H', '2020-01-06 19:00:00.000000', '2020-01-06 21:00:00.000000', 'E07465045');
INSERT INTO `exam` VALUES ('17070141', 'B03076603', '教学西区01104H', '2020-01-05 00:00:00.000000', '2020-01-05 00:00:00.000000', 'E3483253');
INSERT INTO `exam` VALUES ('17070142', 'A04075501', '教学东区01103H', '2019-12-24 09:00:00.000000', '2019-12-24 11:00:00.000000', 'E07014205');
INSERT INTO `exam` VALUES ('17070142', 'B02076605', '教学东区01102H', '2019-12-26 10:00:00.000000', '2019-12-26 12:00:00.000000', 'E07014201');
INSERT INTO `exam` VALUES ('17070142', 'B03076603', '教学西区01105H', '2020-01-05 00:00:00.000000', '2020-01-05 00:00:00.000000', 'E439585');
INSERT INTO `exam` VALUES ('17070142', 'D15632166', '教学西区01203H', '2020-01-10 00:00:00.000000', '2020-01-10 00:00:00.000000', 'E90684940');
INSERT INTO `exam` VALUES ('17070144', 'B02076607', '教学西区08302H', '2020-01-05 00:00:00.000000', '2020-01-05 00:00:00.000000', 'E07014401');
INSERT INTO `exam` VALUES ('17070241', 'A04075502', '教学西区08210H', '2020-01-05 00:00:00.000000', '2020-01-05 00:00:00.000000', 'E07024101');
INSERT INTO `exam` VALUES ('17090041', 'J18965163', '教学东区01203H', '2020-01-02 10:00:00.000000', '2020-01-02 12:00:00.000000', 'E07024102');
INSERT INTO `exam` VALUES ('17090041', 'J18965324', '教学东区01102H', '2020-01-03 10:00:00.000000', '2020-01-03 12:00:00.000000', 'E07024103');
INSERT INTO `exam` VALUES ('17090143', 'H15653163', '教学东区01201H', '2020-01-02 10:00:00.000000', '2020-01-02 12:00:00.000000', 'E07034101');
INSERT INTO `exam` VALUES ('17090143', 'H15653164', '教学东区01203H', '2020-01-03 10:00:00.000000', '2020-01-03 12:00:00.000000', 'E07034102');
INSERT INTO `exam` VALUES ('17090245', 'G18653216', '教学东区01202H', '2020-01-02 10:00:00.000000', '2020-01-02 12:00:00.000000', 'E07044101');
INSERT INTO `exam` VALUES ('17090245', 'G18965322', '教学东区01202H', '2020-01-03 10:00:00.000000', '2020-01-03 12:00:00.000000', 'E07044102');
INSERT INTO `exam` VALUES ('17090245', 'G49865326', '教学东区01202H', '2020-01-05 10:00:00.000000', '2020-01-05 12:00:00.000000', 'E07044103');

-- ----------------------------
-- Table structure for gradesystem
-- ----------------------------
DROP TABLE IF EXISTS `gradesystem`;
CREATE TABLE `gradesystem`  (
  `Mgrade` int(1) NOT NULL
) ENGINE = InnoDB CHARACTER SET = gb2312 COLLATE = gb2312_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gradesystem
-- ----------------------------
INSERT INTO `gradesystem` VALUES (0);

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `Mno` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Mname` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Msex` varchar(2) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Mage` int(2) NULL DEFAULT NULL,
  `Mpassword` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Mtel` varchar(11) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Memail` varchar(25) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Mno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gb2312 COLLATE = gb2312_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('M01', '张三', '女', 25, '123456', '15536870732', '1484290617@qq.com');
INSERT INTO `manager` VALUES ('M02', '江流', '男', 28, '152796', '15669698642', '1484946542qq.com');
INSERT INTO `manager` VALUES ('M03', '李四', '男', 29, '156656', '18534688553', '1594232742qq.com');
INSERT INTO `manager` VALUES ('M04', '王五', '男', 32, '198653', '18986515961', '1896559626qq.com');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `Sno` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Sname` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Ssex` char(2) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Sage` int(2) NULL DEFAULT NULL,
  `Sclass` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Spassword` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Stel` varchar(11) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Semail` varchar(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Sno`) USING BTREE,
  INDEX `Sclass`(`Sclass`) USING BTREE,
  CONSTRAINT `Sclass` FOREIGN KEY (`Sclass`) REFERENCES `class` (`CLno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gb2312 COLLATE = gb2312_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1707004104', '戴晓杰', '男', 22, '17070141', '789654', '1553692456', '28956354866@qq.com');
INSERT INTO `student` VALUES ('1707004112', '胡海蒂', '女', 22, '17070142', '123456', '16395789547', '14362846@qq.com');
INSERT INTO `student` VALUES ('1707004115', '段力菡', '女', 21, '17070142', '123456', '1636246624', '369865912@qq.com');
INSERT INTO `student` VALUES ('1707004129', '曹伟杰', '男', 18, '17070142', '123456', '163646633', '626262556@qq.com');
INSERT INTO `student` VALUES ('1707004130', '何锋', '男', 20, '17070142', '123456', '18135180140', '28202027103@qq.com');
INSERT INTO `student` VALUES ('1707004138', '张三', '男', 21, '17070141', '155658', '18965684669', '18965196852@qq.com');
INSERT INTO `student` VALUES ('1707004141', '郭鹏阳', '男', 20, '17070141', '123456', '13569876871', '67865895641@qq.com');
INSERT INTO `student` VALUES ('1707004203', '李娜', '女', 19, '17070141', '123456', '16587953125', '45869685478@qq.com');
INSERT INTO `student` VALUES ('1707004206', '樊耿鑫', '女', 20, '17070142', '123457', '169876986', '636463331@qq.com');
INSERT INTO `student` VALUES ('1707004219', '冯元庆', '男', 21, '17070141', '123456', '13985311564', '35345898554@qq.com');
INSERT INTO `student` VALUES ('1707004226', '王栋', '男', 21, '17070142', '123456', '18563247895', '674725465@qq.com');
INSERT INTO `student` VALUES ('1707004227', '林钊', '男', 20, '17070142', '456787', '18965163966', '67472544335@qq.com');
INSERT INTO `student` VALUES ('1707004237', '王眺', '男', 20, '17070141', '123456', '13569874231', '89565842454@qq.com');
INSERT INTO `student` VALUES ('1707004306', '梁硕', '女', 20, '17070141', '123456', '13536899587', '24565898954@qq.com');
INSERT INTO `student` VALUES ('1707004326', '王五', '男', 22, '17070144', '059653', '18965321965', '14896535463@qq.com');
INSERT INTO `student` VALUES ('1707004413', '高颖', '女', 20, '17070142', '123789', '19422165845', '19422165843@qq.com');
INSERT INTO `student` VALUES ('1707004420', '杨凯', '男', 22, '17070141', '123456', '369863916', '1235645144@qq.com');
INSERT INTO `student` VALUES ('1707004444', '李深', '男', 21, '17070143', '123456', '18965684669', '18965116553@qq.com');
INSERT INTO `student` VALUES ('1707004516', '李四', '男', 22, '17070542', '218923', '25965315892', '14896513296@qq.com');
INSERT INTO `student` VALUES ('1707004524', '张永辉', '男', 19, '17070141', '123456', '18536828148', '18865895780@qq.com');
INSERT INTO `student` VALUES ('1707004610', '闫瑞雪', '女', 19, '17070141', '123456', '18538569847', '78665898650@qq.com');
INSERT INTO `student` VALUES ('1707004716', '王浩', '女', 20, '17070142', '123456', '1553687072', '14842906135@qq.com');
INSERT INTO `student` VALUES ('1707004742', '文阳辉', '男', 21, '17070142', '123456', '159786592', '1456954565@qq.com');
INSERT INTO `student` VALUES ('1707014252', '王婷婷', '女', 20, '17070142', '123456', '1369555622', '1495680639@qq.com');
INSERT INTO `student` VALUES ('1709004118', '陈梅香', '女', 20, '17090041', '123456', '15653496855', '18653168531@qq.com');
INSERT INTO `student` VALUES ('1709004201', '纪思清', '女', 19, '17090042', '123456', '15653496855', '18653168531@qq.com');
INSERT INTO `student` VALUES ('1709004250', '何峰', '男', 21, '17090042', '123456', '478496546', '418965351@qq.com');
INSERT INTO `student` VALUES ('1709014308', '孙怡', '女', 20, '17090143', '123456', '18965498653', '18653168531@qq.com');
INSERT INTO `student` VALUES ('1709014425', '陈之凡', '男', 20, '17090144', '123456', '18965498653', '18653168531@qq.com');
INSERT INTO `student` VALUES ('1709014526', '葛青', '男', 20, '17090245', '123456', '18965314896', '18653168531@qq.com');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `Tno` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Tname` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Tsex` char(2) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Tage` int(2) NULL DEFAULT NULL,
  `Tdept` varchar(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Tdegree` varchar(8) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Ttitle` varchar(4) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Tduty` varchar(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Ttel` varchar(11) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Temail` varchar(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Tpassword` varchar(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  PRIMARY KEY (`Tno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gb2312 COLLATE = gb2312_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('T01', '魏颖', '女', 48, '大数据学院', '博士', '副教授', '学生科主任', '1783756353', '1586368865@qq.com', '123456');
INSERT INTO `teacher` VALUES ('T02', '李飞平', '男', 38, '大数据学院', '博士', '副教授', '就业部主任', '1348636596', '1896319866@qq.com', '123456');
INSERT INTO `teacher` VALUES ('T03', '王晓东', '男', 35, '大数据学院', '博士', '副教授', '财务部主任', '1783549682', '2871369593@qq.com', '159357');
INSERT INTO `teacher` VALUES ('T04', '王永顾', '男', 31, '大数据学院', '博士', '副教授', '学科部副主任', '1562477534', '2651161486@qq.com', '1477258');
INSERT INTO `teacher` VALUES ('T05', '夏若雪', '女', 30, '大数据学院', '博士', '副教授', '学生科副主任', '1783196385', '5146364355@qq.com', '1589633');
INSERT INTO `teacher` VALUES ('T06', '沐璇音', '女', 34, '经济管理学院', '博士', '副教授', '学生科主任', '1783756353', '1586368865@qq.com', '123456');
INSERT INTO `teacher` VALUES ('T07', '苏念慈', '女', 36, '经济管理学院', '博士', '副教授', '就业部副主任', '1783756353', '1586368865@qq.com', '147258');
INSERT INTO `teacher` VALUES ('T08', '莫南', '男', 36, '经济管理学院', '博士后', '副教授', '生活部主任', '1563498651', '1586368865@qq.com', '123456');
INSERT INTO `teacher` VALUES ('T09', '陆轻雪', '女', 32, '经济管理学院', '博士后', '副教授', '生活部主任副', '1489654968', '1586368865@qq.com', '123456');

-- ----------------------------
-- Table structure for teaching
-- ----------------------------
DROP TABLE IF EXISTS `teaching`;
CREATE TABLE `teaching`  (
  `Tno` char(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Cno` char(10) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Cposition` char(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Cweek` char(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Ctime1` char(30) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Ctime2` char(30) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Cyear` char(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  `Cterm` char(20) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Tno`, `Cno`) USING BTREE,
  INDEX `TCno`(`Cno`) USING BTREE,
  CONSTRAINT `TCno` FOREIGN KEY (`Cno`) REFERENCES `course` (`Cno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `TTno` FOREIGN KEY (`Tno`) REFERENCES `teacher` (`Tno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = gb2312 COLLATE = gb2312_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teaching
-- ----------------------------
INSERT INTO `teaching` VALUES ('T01', 'A04075501', '教学西区08302H', '1-2周，5-16 周', '周一 8:00-9:40', '周三 10:10-11:50', '2019-2020', '第一学期');
INSERT INTO `teaching` VALUES ('T01', 'B02076605', '教学西区08202H', '1-16 周', '周一 8：00-9:40', '周三 10：10-11:50', '2019-2020', '第二学期');
INSERT INTO `teaching` VALUES ('T02', 'C02075504', '教学西区15109H', '1-16 周', '周二10:10-11:50', '周五16:10-17:50', '2019-2020', '第一学期');
INSERT INTO `teaching` VALUES ('T02', 'D15632166', '教学西区15402H', '1-16 周', '周三10:10-11:50', '周五14:00-15:40', '2019-2020', '第二学期');
INSERT INTO `teaching` VALUES ('T06', 'G18965322', '教学主楼11308H', '1-17 周', '周一10:10-11:50', '周四16:10-17:50', '2019-2020', '第一学期');
INSERT INTO `teaching` VALUES ('T06', 'H15653164', '教学西区15308H', '1-16 周', '周二10:10-11:50', '周五16:10-17:50', '2019-2020', '第二学期');
INSERT INTO `teaching` VALUES ('T07', 'E26211986', '教学主楼11102H', '1-16周', '周一8：00-9：40', '周五14：00-15：40', '2019-2020', '第一学期');
INSERT INTO `teaching` VALUES ('T08', 'B02076607', '教学西区11310H', '1-16周', '周一8：00-9:40', '周五14：00-15：40', '2019-2020', '第一学期');
INSERT INTO `teaching` VALUES ('T09', 'F17858326', '教学西区15402H', '1-16 周', '周三 8:00-9:40', '周五 16:10-17:50', '2019-2020', '第一学期');
INSERT INTO `teaching` VALUES ('T09', 'F46513246', '教学西区08202H', '1-16 周', '周一 10:10-11:50', '周四 16:10-17:50', '2019-2020', '第二学期');

SET FOREIGN_KEY_CHECKS = 1;
