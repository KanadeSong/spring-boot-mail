/*
 Navicat Premium Data Transfer

 Source Server         : MySQL5.8
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : mail

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 29/04/2020 01:28:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oa_email
-- ----------------------------
DROP TABLE IF EXISTS `oa_email`;
CREATE TABLE `oa_email`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `receive_email` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接收人邮箱(多个逗号分开)',
  `subject` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发送内容',
  `template` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板',
  `send_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发送时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oa_email
-- ----------------------------
INSERT INTO `oa_email` VALUES (6, '[721680672@qq.com]', '你好', '<!doctype html>\r\n<html lang=\"zh-cmn-Hans\">\r\n<head>\r\n    <meta charset=\"UTF-8\">\r\n    <meta name=\"renderer\" content=\"webkit\" />\r\n    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\r\n    <title>Document</title>\r\n</head>\r\n<body>\r\n    <div class=\"container\"\r\n         style=\" width: 800px;\r\n         height: auto;\r\n         margin: 0 auto;\r\n         border: 1px #dddddd solid;\r\n         border-top: 4px #3498db solid;\r\n         font: 14px Microsoft Yahei;\r\n         color: #333;\">\r\n        <div class=\"main\" style=\"padding: 0 15px;\">\r\n            <div style=\"\r\n                 border: 1px #f39d12 dashed;\r\n                 background-color: #fffdf4;\r\n                 margin: 20px 0;\r\n                 border-radius: 6px;\">\r\n                <table width=\"100%\" style=\"border: none\">\r\n                    <tr>\r\n                        <td style=\"width: 20%; text-align: center; padding-top: 25px\">\r\n                            <img src=\"http://localhost:8080/image/icon_email_prompt.png\" alt=\"\">\r\n                        </td>\r\n                        <td colspan=\"2\" style=\"font-size: 18px; line-height: 1.6;padding-top: 30px\">\r\n                            <div style=\"text-indent: 36px; padding-right: 24px\">\r\n                                 并不是因为我需要用到什么领域的知识而去学习，而只是单纯的我想知道，我想弄明白。搞明白之后，到底有什么价值，可以创造多少财富，这是我从来不关心的。反而这样，知识给予的回馈是最大的。\r\n                            </div>\r\n                        </td>\r\n                    </tr>\r\n                </table>\r\n            </div>\r\n        </div>\r\n        <div class=\"footer\" style=\"margin: auto;\r\n                            padding: 15px 0 15px 15px;\r\n                            background-color: #fafafa;\r\n                            border-top: 1px #ddd solid;\r\n                            color: #333;\r\n                            height: auto;\r\n                            zoom: 1;\r\n                            overflow: auto;\">\r\n            <table style=\"width: 100%\">\r\n                <tr>\r\n                    <td style=\"width: 60%\">\r\n                        <h4 style=\"font-size: 16px;\r\n                             margin: 10px 0;\">\r\n                          科帮网科技服务有限公司\r\n                        </h4>\r\n                        <p>\r\n                            公司地址：\r\n                            <b>宇宙银河系太阳系地球村88号</b>\r\n                        </p>\r\n                        <p>\r\n                            官方网站：\r\n                            <a href=\"http://www.52itstyle.vip\" style=\"text-decoration: none; color: #333; font-weight: bold;\">www.52itstyle.com</a>\r\n                        </p>\r\n                        <p>\r\n                            服务热线：\r\n                            <b>\r\n                                <span style=\"display: inline-block; margin-right: 16px;\">17762018584</span>\r\n                            </b>\r\n                        </p>\r\n                    </td>\r\n                    <td style=\"text-align: right;\">\r\n                        <div style=\" display: inline-block;width: 120px ; padding-top: 10px; padding-right: 200px;\">\r\n                            <img src=\"http://localhost:8080/springboot_mail/image/service.jpg\" alt=\"\" style=\"vertical-align: top\">\r\n                        </div>\r\n                    </td>\r\n                </tr>\r\n            </table>\r\n        </div>\r\n    </div>\r\n</body>\r\n</html> ', 'welcome', '2018-05-03 10:21:32');

SET FOREIGN_KEY_CHECKS = 1;
