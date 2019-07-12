/*
Navicat MySQL Data Transfer

Source Server         : 项目实训
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : mall

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-07-12 09:26:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `action_address`
-- ----------------------------
DROP TABLE IF EXISTS `action_address`;
CREATE TABLE `action_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `name` varchar(20) NOT NULL COMMENT '收件人姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '收件人固定电话号码',
  `mobile` varchar(20) NOT NULL COMMENT '收件人手机号码',
  `province` varchar(20) NOT NULL COMMENT '省份',
  `city` varchar(20) NOT NULL COMMENT '城市',
  `district` varchar(20) DEFAULT NULL COMMENT '区/县',
  `addr` varchar(300) DEFAULT NULL COMMENT '详细地址',
  `zip` varchar(6) DEFAULT NULL COMMENT '邮编',
  `default_addr` tinyint(1) DEFAULT '0' COMMENT '是否是默认地址，0-非默认，1-默认',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  `del_state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除，0：正常，1：已删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `action_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `action_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of action_address
-- ----------------------------
INSERT INTO `action_address` VALUES ('4', '15', '李四', null, '18966336652', '山东省', '烟台市', '芝罘区', '魁玉路100号', '264001', '0', '2018-02-19 19:12:28', '2019-03-16 08:55:32', '0');
INSERT INTO `action_address` VALUES ('5', '15', '张三天', null, '12345678000', '山东省', '烟台市', '芝罘区', '魁玉路100号', '264000', '0', '2018-02-19 19:12:36', '2019-07-01 17:30:00', '0');
INSERT INTO `action_address` VALUES ('7', '15', '李思城123', null, '13356899978', '天津市', '天津市市辖区', '南开区', '天赐龙城12-2', '255668', '1', '2019-03-15 14:52:53', '2019-07-08 00:55:21', '0');
INSERT INTO `action_address` VALUES ('8', '15', '梁静茹', null, '15563876666', '江西省', '南昌市', '东湖区', '前进路100号', '568899', '0', '2019-03-15 15:53:03', '2019-07-01 16:08:06', '1');
INSERT INTO `action_address` VALUES ('9', '15', '张媛媛', null, '13333334455', '北京市', '北京市市辖区', '东城区', '南山路78-2', '', '0', '2019-03-16 09:55:21', '2019-07-08 00:55:21', '0');
INSERT INTO `action_address` VALUES ('10', '15', '填好', null, '11111111111', '河北省', '秦皇岛市', '山海关区', '南山', '', '0', '2019-03-16 12:06:39', '2019-07-01 17:29:46', '0');
INSERT INTO `action_address` VALUES ('11', '15', '张三丰hhh', null, '12222222222', '山东省', '烟台市', '龙口市', '南山公元10-1', '', '0', '2019-03-16 15:42:09', '2019-07-07 15:06:37', '0');
INSERT INTO `action_address` VALUES ('12', '15', '急急急急急急吗', null, '11111111111', '上海市', '上海市市辖区', '徐汇区', '舜华路1500', '250100', '0', '2019-07-01 17:14:52', '2019-07-01 20:39:52', '1');
INSERT INTO `action_address` VALUES ('13', '15', '111', null, '11111111111', '北京市', '北京市市辖区', '西城区', '111', '111111', '1', '2019-07-01 17:30:19', '2019-07-01 17:32:34', '1');
INSERT INTO `action_address` VALUES ('14', '15', '二狗子', null, '12345678900', '辽宁省', '大连市', '沙河口区', '2333', '123456', '1', '2019-07-01 21:33:35', '2019-07-01 21:33:47', '1');
INSERT INTO `action_address` VALUES ('15', '15', '111', null, '11111111111', '河北省', '张家口市', '宣化区', '话术安陆', '123456', '0', '2019-07-01 21:35:53', '2019-07-07 15:05:55', '0');
INSERT INTO `action_address` VALUES ('16', '27', '文涛', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '1', '2019-07-09 09:02:08', '2019-07-09 16:25:22', '1');
INSERT INTO `action_address` VALUES ('17', '27', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '1', '2019-07-09 10:24:57', '2019-07-10 18:05:35', '1');
INSERT INTO `action_address` VALUES ('18', '27', 'Tao Snoopy', null, '18340018206', '山东省', '枣庄市', '薛城区', '山东省枣庄市薛城区街道1号', '', '1', '2019-07-09 10:25:35', '2019-07-10 16:27:33', '1');
INSERT INTO `action_address` VALUES ('19', '28', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '1', '2019-07-09 15:48:38', '2019-07-09 15:53:18', '0');
INSERT INTO `action_address` VALUES ('20', '28', 'lily', null, '18340018206', '天津市', '天津市市辖区', '河西区', '山东省济南市山东大学软件学院', '250001', '1', '2019-07-09 15:49:29', '2019-07-09 15:51:19', '1');
INSERT INTO `action_address` VALUES ('21', '28', '如题如题', null, '18340018206', '河北省', '邯郸市', '邯郸县', '山东省济南市山东大学软件学院', '250001', '0', '2019-07-09 15:52:22', '2019-07-09 15:53:17', '0');
INSERT INTO `action_address` VALUES ('22', '28', 'uu', null, '18340018206', '吉林省', '通化市', '柳河县', '山东省济南市山东大学软件学院', '250001', '0', '2019-07-09 15:52:35', '2019-07-09 15:53:15', '0');
INSERT INTO `action_address` VALUES ('23', '27', 'Tao Snoopy', null, '18340018206', '河北省', '石家庄市', '桥西区', '山东省济南市山东大学软件学院', '250001', '0', '2019-07-09 16:24:54', '2019-07-10 16:22:07', '1');
INSERT INTO `action_address` VALUES ('24', '27', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '0', '2019-07-09 16:25:01', '2019-07-10 16:22:01', '1');
INSERT INTO `action_address` VALUES ('25', '27', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '1', '2019-07-09 16:25:04', '2019-07-10 17:38:28', '1');
INSERT INTO `action_address` VALUES ('26', '27', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '1', '2019-07-09 16:25:06', '2019-07-09 16:26:51', '1');
INSERT INTO `action_address` VALUES ('27', '27', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '1', '2019-07-10 16:31:24', '2019-07-10 18:05:28', '1');
INSERT INTO `action_address` VALUES ('28', '30', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '1', '2019-07-10 17:32:48', '2019-07-10 17:32:59', '1');
INSERT INTO `action_address` VALUES ('29', '30', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '1', '2019-07-10 17:33:08', '2019-07-10 17:33:21', '0');
INSERT INTO `action_address` VALUES ('30', '30', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '1', '2019-07-10 17:33:09', '2019-07-10 17:33:20', '1');
INSERT INTO `action_address` VALUES ('31', '27', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '0', '2019-07-10 18:05:42', '2019-07-10 18:05:58', '1');
INSERT INTO `action_address` VALUES ('32', '27', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '1', '2019-07-10 18:06:04', '2019-07-10 18:06:21', '1');
INSERT INTO `action_address` VALUES ('33', '27', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '1', '2019-07-10 18:06:08', '2019-07-10 18:07:04', '1');
INSERT INTO `action_address` VALUES ('34', '27', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '0', '2019-07-10 18:06:14', '2019-07-10 18:06:58', '1');
INSERT INTO `action_address` VALUES ('35', '27', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '1', '2019-07-10 18:07:24', '2019-07-10 18:07:45', '1');
INSERT INTO `action_address` VALUES ('36', '27', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250002', '1', '2019-07-10 18:07:33', '2019-07-10 18:07:52', '1');
INSERT INTO `action_address` VALUES ('37', '27', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '1', '2019-07-10 18:08:06', '2019-07-10 18:09:21', '1');
INSERT INTO `action_address` VALUES ('38', '27', 'Tao Snoopy', null, '18340018206', '山东省', '济南市', '历城区', '山东省济南市山东大学软件学院', '250001', '1', '2019-07-10 18:09:28', '2019-07-10 18:09:38', '0');

-- ----------------------------
-- Table structure for `action_carts`
-- ----------------------------
DROP TABLE IF EXISTS `action_carts`;
CREATE TABLE `action_carts` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户Id',
  `product_id` int(11) NOT NULL COMMENT '商品Id',
  `quantity` int(11) NOT NULL COMMENT '商品数量',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  `checked` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1：选中，0：未选中',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `action_carts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `action_users` (`id`),
  CONSTRAINT `action_carts_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `action_products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of action_carts
-- ----------------------------
INSERT INTO `action_carts` VALUES ('62', '15', '10', '1', '2019-07-08 10:19:12', '2019-07-08 10:19:12', '0');
INSERT INTO `action_carts` VALUES ('75', '27', '45', '1', '2019-07-10 16:23:55', '2019-07-10 16:23:55', '0');
INSERT INTO `action_carts` VALUES ('78', '30', '62', '1', '2019-07-10 17:32:16', '2019-07-10 17:32:16', '0');
INSERT INTO `action_carts` VALUES ('80', '27', '46', '1', '2019-07-11 17:30:59', '2019-07-11 17:30:59', '0');

-- ----------------------------
-- Table structure for `action_like`
-- ----------------------------
DROP TABLE IF EXISTS `action_like`;
CREATE TABLE `action_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `del` tinyint(1) DEFAULT '0' COMMENT '0-关注（默认）1-已取消关注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `action_like_ibfk_1_idx` (`user_id`),
  KEY `action_like_ibfk_2_idx` (`product_id`),
  CONSTRAINT `action_like_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `action_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `action_like_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `action_products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of action_like
-- ----------------------------
INSERT INTO `action_like` VALUES ('4', '15', '7', '0');
INSERT INTO `action_like` VALUES ('5', '15', '7', '0');
INSERT INTO `action_like` VALUES ('6', '27', '23', '1');
INSERT INTO `action_like` VALUES ('7', '27', '19', '1');
INSERT INTO `action_like` VALUES ('8', '27', '18', '0');
INSERT INTO `action_like` VALUES ('9', '27', '27', '0');
INSERT INTO `action_like` VALUES ('10', '28', '19', '1');
INSERT INTO `action_like` VALUES ('11', '27', '45', '0');
INSERT INTO `action_like` VALUES ('12', '30', '49', '0');
INSERT INTO `action_like` VALUES ('13', '30', '45', '1');
INSERT INTO `action_like` VALUES ('14', '27', '62', '0');
INSERT INTO `action_like` VALUES ('15', '27', '46', '0');

-- ----------------------------
-- Table structure for `action_orders`
-- ----------------------------
DROP TABLE IF EXISTS `action_orders`;
CREATE TABLE `action_orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_no` bigint(20) NOT NULL COMMENT '订单编号',
  `uid` int(11) NOT NULL COMMENT '用户编号',
  `addr_id` int(11) NOT NULL COMMENT '收货地址编号',
  `amount` decimal(20,2) NOT NULL COMMENT '订单付款金额',
  `type` int(11) NOT NULL COMMENT '付款类型，1-在线支付，2-货到付款',
  `freight` int(11) NOT NULL COMMENT '运费',
  `status` int(11) NOT NULL COMMENT '订单状态，1-未付款，2-已付款，3-已发货，4-交易成功，5-交易关闭，6-已取消，',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `finish_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `addr_id` (`addr_id`),
  CONSTRAINT `action_orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `action_users` (`id`),
  CONSTRAINT `action_orders_ibfk_2` FOREIGN KEY (`addr_id`) REFERENCES `action_address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of action_orders
-- ----------------------------
INSERT INTO `action_orders` VALUES ('26', '1562219864363', '15', '9', '1943.01', '1', '0', '6', null, null, null, null, '2019-07-04 15:58:03', '2019-07-04 13:57:44');
INSERT INTO `action_orders` VALUES ('27', '1562220448074', '15', '7', '1943.01', '1', '0', '6', null, null, null, null, '2019-07-05 09:01:17', '2019-07-04 14:07:28');
INSERT INTO `action_orders` VALUES ('28', '1562220706394', '15', '15', '647.67', '1', '0', '6', null, null, null, null, '2019-07-04 15:58:23', '2019-07-04 14:11:46');
INSERT INTO `action_orders` VALUES ('29', '1562220896860', '15', '11', '1295.34', '1', '0', '1', null, null, null, null, '2019-07-04 14:14:56', '2019-07-04 14:14:56');
INSERT INTO `action_orders` VALUES ('30', '1562221044223', '15', '9', '1079.45', '1', '0', '4', null, null, null, null, '2019-07-04 15:50:55', '2019-07-04 14:17:24');
INSERT INTO `action_orders` VALUES ('31', '1562221469687', '15', '7', '1079.45', '1', '0', '1', null, null, null, null, '2019-07-04 14:24:29', '2019-07-04 14:24:29');
INSERT INTO `action_orders` VALUES ('32', '1562221683784', '15', '15', '863.56', '1', '0', '6', null, null, null, null, '2019-07-05 09:01:24', '2019-07-04 14:28:03');
INSERT INTO `action_orders` VALUES ('33', '1562221951797', '15', '7', '863.56', '1', '0', '3', null, null, null, null, '2019-07-04 14:33:02', '2019-07-04 14:32:31');
INSERT INTO `action_orders` VALUES ('34', '1562288294693', '15', '7', '2806.57', '1', '0', '3', null, null, null, null, '2019-07-05 08:59:26', '2019-07-05 08:58:14');
INSERT INTO `action_orders` VALUES ('35', '1562288442140', '15', '9', '431.78', '1', '0', '3', null, null, null, null, '2019-07-05 09:01:09', '2019-07-05 09:00:42');
INSERT INTO `action_orders` VALUES ('36', '1562483144054', '15', '15', '647.67', '1', '0', '1', null, null, null, null, '2019-07-07 15:05:43', '2019-07-07 15:05:43');
INSERT INTO `action_orders` VALUES ('37', '1562483157349', '15', '11', '647.67', '1', '0', '1', null, null, null, null, '2019-07-07 15:05:57', '2019-07-07 15:05:57');
INSERT INTO `action_orders` VALUES ('38', '1562483200283', '15', '9', '863.56', '1', '0', '1', null, null, null, null, '2019-07-07 15:06:40', '2019-07-07 15:06:40');
INSERT INTO `action_orders` VALUES ('39', '1562518524595', '15', '7', '2158.90', '1', '0', '1', null, null, null, null, '2019-07-08 00:55:24', '2019-07-08 00:55:24');
INSERT INTO `action_orders` VALUES ('40', '1562518969806', '15', '7', '431.78', '1', '0', '3', null, null, null, null, '2019-07-08 01:03:32', '2019-07-08 01:02:49');
INSERT INTO `action_orders` VALUES ('41', '1562519054303', '15', '7', '215.89', '1', '0', '1', null, null, null, null, '2019-07-08 01:04:14', '2019-07-08 01:04:14');
INSERT INTO `action_orders` VALUES ('42', '1562634138272', '27', '16', '250000.00', '1', '0', '4', '2019-07-09 09:03:04', null, '2019-07-09 10:39:34', null, '2019-07-09 10:39:34', '2019-07-09 09:02:18');
INSERT INTO `action_orders` VALUES ('43', '1562634240143', '27', '16', '1500000.00', '1', '0', '6', null, null, null, '2019-07-10 18:10:14', '2019-07-10 18:10:14', '2019-07-09 09:04:00');
INSERT INTO `action_orders` VALUES ('44', '1562639168071', '27', '18', '42.00', '1', '0', '6', null, null, null, '2019-07-10 18:10:20', '2019-07-10 18:10:20', '2019-07-09 10:26:08');
INSERT INTO `action_orders` VALUES ('45', '1562646098290', '27', '18', '22.00', '1', '0', '6', null, null, null, '2019-07-10 18:10:24', '2019-07-10 18:10:24', '2019-07-09 12:21:38');
INSERT INTO `action_orders` VALUES ('46', '1562652726282', '27', '16', '6460.00', '1', '0', '6', null, null, null, '2019-07-10 18:10:28', '2019-07-10 18:10:28', '2019-07-09 14:12:06');
INSERT INTO `action_orders` VALUES ('47', '1562658697362', '28', '20', '84.00', '1', '0', '4', '2019-07-09 16:11:35', null, '2019-07-09 16:12:51', null, '2019-07-09 16:12:51', '2019-07-09 15:51:37');
INSERT INTO `action_orders` VALUES ('48', '1562659163554', '28', '19', '11.00', '1', '0', '1', null, null, null, null, '2019-07-09 15:59:23', '2019-07-09 15:59:23');
INSERT INTO `action_orders` VALUES ('49', '1562660209720', '27', '16', '33.00', '1', '0', '6', null, null, null, '2019-07-10 18:10:33', '2019-07-10 18:10:33', '2019-07-09 16:16:49');
INSERT INTO `action_orders` VALUES ('50', '1562660818622', '27', '26', '328.00', '1', '0', '6', null, null, null, '2019-07-10 18:10:36', '2019-07-10 18:10:36', '2019-07-09 16:26:58');
INSERT INTO `action_orders` VALUES ('51', '1562729208987', '27', '24', '420000.00', '1', '0', '6', null, null, null, '2019-07-10 18:10:39', '2019-07-10 18:10:39', '2019-07-10 11:26:48');
INSERT INTO `action_orders` VALUES ('52', '1562742795327', '27', '18', '352.00', '1', '0', '6', null, null, null, '2019-07-10 18:10:43', '2019-07-10 18:10:43', '2019-07-10 15:13:15');
INSERT INTO `action_orders` VALUES ('53', '1562742800531', '27', '18', '352.00', '1', '0', '6', null, null, null, '2019-07-10 18:10:47', '2019-07-10 18:10:47', '2019-07-10 15:13:20');
INSERT INTO `action_orders` VALUES ('54', '1562742815565', '27', '18', '352.00', '1', '0', '6', null, null, null, '2019-07-10 18:10:51', '2019-07-10 18:10:51', '2019-07-10 15:13:35');
INSERT INTO `action_orders` VALUES ('55', '1562742902314', '27', '18', '352.00', '1', '0', '1', null, null, null, null, '2019-07-10 15:15:02', '2019-07-10 15:15:02');
INSERT INTO `action_orders` VALUES ('56', '1562744526756', '27', '18', '1092.00', '1', '0', '1', null, null, null, null, '2019-07-10 15:42:06', '2019-07-10 15:42:06');
INSERT INTO `action_orders` VALUES ('57', '1562751232881', '30', '29', '271.00', '1', '0', '1', null, null, null, null, '2019-07-10 17:33:52', '2019-07-10 17:33:52');
INSERT INTO `action_orders` VALUES ('58', '1562751292242', '27', '25', '8672.00', '1', '0', '4', '2019-07-10 17:35:38', null, '2019-07-10 17:36:48', null, '2019-07-10 17:36:48', '2019-07-10 17:34:52');
INSERT INTO `action_orders` VALUES ('59', '1562751481358', '27', '25', '352.00', '1', '0', '1', null, null, null, null, '2019-07-10 17:38:01', '2019-07-10 17:38:01');
INSERT INTO `action_orders` VALUES ('60', '1562753397597', '27', '38', '42.00', '1', '0', '1', null, null, null, null, '2019-07-10 18:09:57', '2019-07-10 18:09:57');
INSERT INTO `action_orders` VALUES ('61', '1562804680084', '27', '38', '1505405.00', '1', '0', '1', null, null, null, null, '2019-07-11 08:24:40', '2019-07-11 08:24:40');
INSERT INTO `action_orders` VALUES ('62', '1562836495395', '27', '38', '704.00', '1', '0', '1', null, null, null, null, '2019-07-11 17:14:55', '2019-07-11 17:14:55');

-- ----------------------------
-- Table structure for `action_order_items`
-- ----------------------------
DROP TABLE IF EXISTS `action_order_items`;
CREATE TABLE `action_order_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单项id',
  `uid` int(11) NOT NULL COMMENT '用户编号',
  `order_no` bigint(20) NOT NULL COMMENT '所属订单编号',
  `goods_id` int(11) NOT NULL COMMENT '商品编号',
  `goods_name` varchar(100) NOT NULL COMMENT '商品名称',
  `icon_url` varchar(500) NOT NULL COMMENT '商品主图',
  `price` decimal(20,2) NOT NULL COMMENT '商品单价',
  `quantity` int(11) NOT NULL COMMENT '购买的商品数量',
  `total_price` decimal(20,2) NOT NULL COMMENT '订单项总价格',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `comment` longtext,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `action_order_items_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `action_users` (`id`),
  CONSTRAINT `action_order_items_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `action_products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of action_order_items
-- ----------------------------
INSERT INTO `action_order_items` VALUES ('19', '15', '1519095514229', '6', '锂基脂 00#', '[地址1', '215.89', '2', '431.78', '2018-02-20 10:58:34', '2018-02-20 10:58:34', null);
INSERT INTO `action_order_items` VALUES ('20', '15', '1519095514229', '7', '锂基脂 00#', '[地址1', '215.89', '2', '431.78', '2018-02-20 10:58:34', '2018-02-20 10:58:34', null);
INSERT INTO `action_order_items` VALUES ('21', '15', '1519099875344', '7', '锂基脂 00#', '[地址1', '215.89', '2', '431.78', '2018-02-20 12:11:15', '2018-02-20 12:11:15', null);
INSERT INTO `action_order_items` VALUES ('22', '15', '1519099875344', '6', '锂基脂 00#', '[地址1', '215.89', '2', '431.78', '2018-02-20 12:11:15', '2018-02-20 12:11:15', null);
INSERT INTO `action_order_items` VALUES ('23', '15', '1552995071508', '6', '锂基脂 00#', '/upload/d88b7591-47f5-4125-b9a6-3ad29c74cba3.png', '215.89', '3', '647.67', '2019-03-19 19:31:11', '2019-03-19 19:31:11', null);
INSERT INTO `action_order_items` VALUES ('24', '15', '1553226067154', '10', '壁挂臂级起重机', '/upload/68fb85a6-48f3-4e46-967d-965a51ca22a3.jpg', '1500000.00', '1', '1500000.00', '2019-03-22 11:41:07', '2019-03-22 11:41:07', null);
INSERT INTO `action_order_items` VALUES ('25', '15', '1562026739345', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '7', '1511.23', '2019-07-02 08:18:59', '2019-07-02 08:18:59', null);
INSERT INTO `action_order_items` VALUES ('26', '15', '1562031647219', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '6', '1295.34', '2019-07-02 09:40:47', '2019-07-02 09:40:47', null);
INSERT INTO `action_order_items` VALUES ('27', '15', '1562031647219', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '4', '863.56', '2019-07-02 09:40:47', '2019-07-02 09:40:47', null);
INSERT INTO `action_order_items` VALUES ('28', '15', '1562033148158', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '3', '647.67', '2019-07-02 10:05:48', '2019-07-02 10:05:48', null);
INSERT INTO `action_order_items` VALUES ('29', '15', '1562033148158', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '6', '1295.34', '2019-07-02 10:05:48', '2019-07-02 10:05:48', null);
INSERT INTO `action_order_items` VALUES ('30', '15', '1562033279122', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '4', '863.56', '2019-07-02 10:07:59', '2019-07-02 10:07:59', null);
INSERT INTO `action_order_items` VALUES ('31', '15', '1562033279122', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '5', '1079.45', '2019-07-02 10:07:59', '2019-07-02 10:07:59', null);
INSERT INTO `action_order_items` VALUES ('32', '15', '1562115988034', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '6', '1295.34', '2019-07-03 09:06:27', '2019-07-03 09:06:27', null);
INSERT INTO `action_order_items` VALUES ('33', '15', '1562115988034', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '3', '647.67', '2019-07-03 09:06:27', '2019-07-03 09:06:27', null);
INSERT INTO `action_order_items` VALUES ('34', '15', '1562115988034', '11', '轮胎起重机F#0090型', '/images/test/6.png', '250000.00', '2', '500000.00', '2019-07-03 09:06:27', '2019-07-03 09:06:27', null);
INSERT INTO `action_order_items` VALUES ('35', '15', '1562142144311', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '5', '1079.45', '2019-07-03 16:22:24', '2019-07-03 16:22:24', null);
INSERT INTO `action_order_items` VALUES ('36', '15', '1562142144311', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '5', '1079.45', '2019-07-03 16:22:24', '2019-07-03 16:22:24', null);
INSERT INTO `action_order_items` VALUES ('37', '15', '1562199455169', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '6', '1295.34', '2019-07-04 08:17:35', '2019-07-04 08:17:35', null);
INSERT INTO `action_order_items` VALUES ('38', '15', '1562199455169', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '4', '863.56', '2019-07-04 08:17:35', '2019-07-04 08:17:35', null);
INSERT INTO `action_order_items` VALUES ('39', '15', '1562199653589', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '4', '863.56', '2019-07-04 08:20:53', '2019-07-04 08:20:53', null);
INSERT INTO `action_order_items` VALUES ('40', '15', '1562203749785', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '5', '1079.45', '2019-07-04 09:29:09', '2019-07-04 09:29:09', null);
INSERT INTO `action_order_items` VALUES ('41', '15', '1562203749785', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '4', '863.56', '2019-07-04 09:29:09', '2019-07-04 09:29:09', null);
INSERT INTO `action_order_items` VALUES ('42', '15', '1562203749785', '10', '壁挂臂级起重机', '/images/test/5.png', '1500000.00', '5', '7500000.00', '2019-07-04 09:29:09', '2019-07-04 09:29:09', null);
INSERT INTO `action_order_items` VALUES ('43', '15', '1562203856266', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '3', '647.67', '2019-07-04 09:30:56', '2019-07-04 09:30:56', null);
INSERT INTO `action_order_items` VALUES ('44', '15', '1562203856266', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '3', '647.67', '2019-07-04 09:30:56', '2019-07-04 09:30:56', null);
INSERT INTO `action_order_items` VALUES ('45', '15', '1562205809712', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '3', '647.67', '2019-07-04 10:03:29', '2019-07-04 10:03:29', null);
INSERT INTO `action_order_items` VALUES ('46', '15', '1562205809712', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '4', '863.56', '2019-07-04 10:03:29', '2019-07-04 10:03:29', null);
INSERT INTO `action_order_items` VALUES ('47', '15', '1562206677596', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '3', '647.67', '2019-07-04 10:17:57', '2019-07-04 10:17:57', null);
INSERT INTO `action_order_items` VALUES ('48', '15', '1562206677596', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '4', '863.56', '2019-07-04 10:17:57', '2019-07-04 10:17:57', null);
INSERT INTO `action_order_items` VALUES ('49', '15', '1562217409371', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '5', '1079.45', '2019-07-04 13:16:49', '2019-07-04 13:16:49', null);
INSERT INTO `action_order_items` VALUES ('50', '15', '1562217409371', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '5', '1079.45', '2019-07-04 13:16:49', '2019-07-04 13:16:49', null);
INSERT INTO `action_order_items` VALUES ('51', '15', '1562219677732', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '3', '647.67', '2019-07-04 13:54:37', '2019-07-04 13:54:37', null);
INSERT INTO `action_order_items` VALUES ('52', '15', '1562219677732', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '5', '1079.45', '2019-07-04 13:54:37', '2019-07-04 13:54:37', null);
INSERT INTO `action_order_items` VALUES ('53', '15', '1562219864363', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '5', '1079.45', '2019-07-04 13:57:44', '2019-07-04 13:57:44', null);
INSERT INTO `action_order_items` VALUES ('54', '15', '1562219864363', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '4', '863.56', '2019-07-04 13:57:44', '2019-07-04 13:57:44', null);
INSERT INTO `action_order_items` VALUES ('55', '15', '1562220448074', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '5', '1079.45', '2019-07-04 14:07:28', '2019-07-04 14:07:28', null);
INSERT INTO `action_order_items` VALUES ('56', '15', '1562220448074', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '4', '863.56', '2019-07-04 14:07:28', '2019-07-04 14:07:28', null);
INSERT INTO `action_order_items` VALUES ('57', '15', '1562220706394', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '3', '647.67', '2019-07-04 14:11:46', '2019-07-04 14:11:46', null);
INSERT INTO `action_order_items` VALUES ('58', '15', '1562220896860', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '6', '1295.34', '2019-07-04 14:14:56', '2019-07-04 14:14:56', null);
INSERT INTO `action_order_items` VALUES ('59', '15', '1562221044223', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '5', '1079.45', '2019-07-04 14:17:24', '2019-07-04 14:17:24', null);
INSERT INTO `action_order_items` VALUES ('60', '15', '1562221469687', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '5', '1079.45', '2019-07-04 14:24:29', '2019-07-04 14:24:29', null);
INSERT INTO `action_order_items` VALUES ('61', '15', '1562221683784', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '4', '863.56', '2019-07-04 14:28:03', '2019-07-04 14:28:03', null);
INSERT INTO `action_order_items` VALUES ('62', '15', '1562221951797', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '4', '863.56', '2019-07-04 14:32:31', '2019-07-04 14:32:31', null);
INSERT INTO `action_order_items` VALUES ('63', '15', '1562288294693', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '7', '1511.23', '2019-07-05 08:58:14', '2019-07-05 08:58:14', null);
INSERT INTO `action_order_items` VALUES ('64', '15', '1562288294693', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '6', '1295.34', '2019-07-05 08:58:14', '2019-07-05 08:58:14', null);
INSERT INTO `action_order_items` VALUES ('65', '15', '1562288442140', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '2', '431.78', '2019-07-05 09:00:42', '2019-07-05 09:00:42', null);
INSERT INTO `action_order_items` VALUES ('66', '15', '1562483144054', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '3', '647.67', '2019-07-07 15:05:44', '2019-07-07 15:05:44', null);
INSERT INTO `action_order_items` VALUES ('67', '15', '1562483157349', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '3', '647.67', '2019-07-07 15:05:57', '2019-07-07 15:05:57', null);
INSERT INTO `action_order_items` VALUES ('68', '15', '1562483200283', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '4', '863.56', '2019-07-07 15:06:40', '2019-07-07 15:06:40', null);
INSERT INTO `action_order_items` VALUES ('69', '15', '1562518524595', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '7', '1511.23', '2019-07-08 00:55:24', '2019-07-08 00:55:24', null);
INSERT INTO `action_order_items` VALUES ('70', '15', '1562518524595', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '3', '647.67', '2019-07-08 00:55:24', '2019-07-08 00:55:24', null);
INSERT INTO `action_order_items` VALUES ('71', '15', '1562518969806', '6', '锂基脂 00#', '/images/test/1.png', '215.89', '2', '431.78', '2019-07-08 01:02:49', '2019-07-08 01:02:49', null);
INSERT INTO `action_order_items` VALUES ('72', '15', '1562519054303', '7', '锂基脂 01#', '/images/test/2.png', '215.89', '1', '215.89', '2019-07-08 01:04:14', '2019-07-08 01:04:14', null);
INSERT INTO `action_order_items` VALUES ('73', '27', '1562634138272', '11', '轮胎起重机F#0090型', '/images/test/6.png', '250000.00', '1', '250000.00', '2019-07-09 09:02:18', '2019-07-09 09:02:18', null);
INSERT INTO `action_order_items` VALUES ('74', '27', '1562634240143', '10', '壁挂臂级起重机', '/images/test/5.png', '1500000.00', '1', '1500000.00', '2019-07-09 09:04:00', '2019-07-09 09:04:00', null);
INSERT INTO `action_order_items` VALUES ('75', '27', '1562639168071', '23', '国四底盘 专用尿素', '/images/products/20190709082233818.jpg', '42.00', '1', '42.00', '2019-07-09 10:26:08', '2019-07-09 10:26:08', null);
INSERT INTO `action_order_items` VALUES ('76', '27', '1562646098290', '27', '滑块 1087-470040-1010', '/images/products/20190709084659725.jpg', '11.00', '2', '22.00', '2019-07-09 12:21:38', '2019-07-09 12:21:38', null);
INSERT INTO `action_order_items` VALUES ('77', '27', '1562652726282', '18', '中联-美孚液压油', '/images/products/20190709004524805.jpg', '3230.00', '2', '6460.00', '2019-07-09 14:12:06', '2019-07-09 14:12:06', null);
INSERT INTO `action_order_items` VALUES ('78', '28', '1562658697362', '23', '国四底盘 专用尿素', '/images/products/20190709082233818.jpg', '42.00', '2', '84.00', '2019-07-09 15:51:37', '2019-07-09 15:51:37', null);
INSERT INTO `action_order_items` VALUES ('79', '28', '1562659163554', '27', '滑块 1087-470040-1010', '/images/products/20190709084659725.jpg', '11.00', '1', '11.00', '2019-07-09 15:59:23', '2019-07-09 15:59:23', null);
INSERT INTO `action_order_items` VALUES ('80', '27', '1562660209720', '27', '滑块 1087-470040-1010', '/images/products/20190709084659725.jpg', '11.00', '3', '33.00', '2019-07-09 16:16:49', '2019-07-09 16:16:49', null);
INSERT INTO `action_order_items` VALUES ('81', '27', '1562660818622', '20', '发动机专用机油', '/images/products/20190709081120204.jpg', '328.00', '1', '328.00', '2019-07-09 16:26:58', '2019-07-09 16:26:58', null);
INSERT INTO `action_order_items` VALUES ('82', '27', '1562729208987', '44', '尿素', '/images/products/20190709202338418.png', '42.00', '10000', '420000.00', '2019-07-10 11:26:49', '2019-07-10 11:26:49', null);
INSERT INTO `action_order_items` VALUES ('83', '27', '1562742795327', '62', '搅拌叶片', '/images/products/20190709204407290.png', '352.00', '1', '352.00', '2019-07-10 15:13:15', '2019-07-10 15:13:15', null);
INSERT INTO `action_order_items` VALUES ('84', '27', '1562742800531', '62', '搅拌叶片', '/images/products/20190709204407290.png', '352.00', '1', '352.00', '2019-07-10 15:13:20', '2019-07-10 15:13:20', null);
INSERT INTO `action_order_items` VALUES ('85', '27', '1562742815565', '62', '搅拌叶片', '/images/products/20190709204407290.png', '352.00', '1', '352.00', '2019-07-10 15:13:35', '2019-07-10 15:13:35', null);
INSERT INTO `action_order_items` VALUES ('86', '27', '1562742902314', '62', '搅拌叶片', '/images/products/20190709204407290.png', '352.00', '1', '352.00', '2019-07-10 15:15:02', '2019-07-10 15:15:02', null);
INSERT INTO `action_order_items` VALUES ('87', '27', '1562744526756', '44', '尿素', '/images/products/20190709202338418.png', '42.00', '26', '1092.00', '2019-07-10 15:42:06', '2019-07-10 15:42:06', null);
INSERT INTO `action_order_items` VALUES ('88', '30', '1562751232881', '49', '锂基脂ZL-1', '/images/products/20190709202812906.png', '271.00', '1', '271.00', '2019-07-10 17:33:52', '2019-07-10 17:33:52', null);
INSERT INTO `action_order_items` VALUES ('89', '27', '1562751292242', '42', 'SPC全合成齿轮油', '/images/products/20190709202127407.png', '2168.00', '4', '8672.00', '2019-07-10 17:34:52', '2019-07-10 17:37:23', '真的棒');
INSERT INTO `action_order_items` VALUES ('90', '27', '1562751481358', '62', '搅拌叶片', '/images/products/20190709204407290.png', '352.00', '1', '352.00', '2019-07-10 17:38:01', '2019-07-10 17:38:01', null);
INSERT INTO `action_order_items` VALUES ('91', '27', '1562753397597', '44', '尿素', '/images/products/20190709202338418.png', '42.00', '1', '42.00', '2019-07-10 18:09:57', '2019-07-10 18:09:57', null);
INSERT INTO `action_order_items` VALUES ('92', '27', '1562804680084', '49', '锂基脂ZL-1', '/images/products/20190709202812906.png', '271.00', '5555', '1505405.00', '2019-07-11 08:24:40', '2019-07-11 08:24:40', null);
INSERT INTO `action_order_items` VALUES ('93', '27', '1562836495395', '62', '搅拌叶片', '/images/products/20190709204407290.png', '352.00', '2', '704.00', '2019-07-11 17:14:55', '2019-07-11 17:14:55', null);

-- ----------------------------
-- Table structure for `action_params`
-- ----------------------------
DROP TABLE IF EXISTS `action_params`;
CREATE TABLE `action_params` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别ID',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父类ID，id为0时为根结点，为一类节点',
  `name` varchar(50) NOT NULL COMMENT '类别名称',
  `sort_order` int(11) DEFAULT NULL COMMENT '同类展示顺序',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态编码，1有效，0无效',
  `level` int(11) DEFAULT NULL COMMENT '节点级别,顶层节点为0，依次类推',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10093 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of action_params
-- ----------------------------
INSERT INTO `action_params` VALUES ('10023', '0', '混凝土机械', '1', '1', '0', '2018-02-12 15:44:18', '2018-02-12 15:44:18');
INSERT INTO `action_params` VALUES ('10024', '0', '建筑起重机械', '2', '1', '0', '2018-02-12 15:44:33', '2018-02-12 15:44:33');
INSERT INTO `action_params` VALUES ('10025', '0', '路面机械', '3', '1', '0', '2018-02-12 15:44:43', '2018-02-12 15:44:43');
INSERT INTO `action_params` VALUES ('10026', '0', '土方机械', '4', '1', '0', '2018-02-12 15:44:53', '2018-02-12 15:44:53');
INSERT INTO `action_params` VALUES ('10027', '0', '环卫机械', '5', '1', '0', '2018-02-12 15:45:02', '2018-02-12 15:45:02');
INSERT INTO `action_params` VALUES ('10028', '0', '工业车辆', '6', '1', '0', '2018-02-12 15:45:13', '2018-02-12 15:45:13');
INSERT INTO `action_params` VALUES ('10029', '0', '模型专区', '7', '1', '0', '2018-02-12 15:45:22', '2018-02-12 15:45:22');
INSERT INTO `action_params` VALUES ('10030', '0', '特辑专区', '8', '1', '0', '2018-02-12 15:45:36', '2018-02-12 15:45:36');
INSERT INTO `action_params` VALUES ('10032', '10023', '泵送搅拌系统', '1', '1', '1', '2018-02-12 15:46:58', '2018-02-12 15:46:58');
INSERT INTO `action_params` VALUES ('10033', '10023', '油品', '2', '1', '1', '2018-02-12 15:47:08', '2018-02-12 15:47:08');
INSERT INTO `action_params` VALUES ('10034', '10023', '电器元件', '3', '1', '1', '2018-02-12 15:47:17', '2018-02-12 15:47:17');
INSERT INTO `action_params` VALUES ('10035', '10023', '地盘配件', '4', '1', '1', '2018-02-12 15:47:28', '2018-02-12 15:47:28');
INSERT INTO `action_params` VALUES ('10036', '10023', '发动机件', '5', '1', '1', '2018-02-12 15:47:42', '2018-02-12 15:47:42');
INSERT INTO `action_params` VALUES ('10037', '10023', '轮胎', '6', '1', '1', '2018-02-12 15:47:50', '2018-02-12 15:47:50');
INSERT INTO `action_params` VALUES ('10038', '10032', '管阀类', '1', '1', '2', '2018-02-12 15:48:26', '2018-02-12 15:48:26');
INSERT INTO `action_params` VALUES ('10039', '10032', '易损类', '2', '1', '2', '2018-02-12 15:48:41', '2018-02-12 15:48:41');
INSERT INTO `action_params` VALUES ('10040', '10033', '防冻液', '1', '1', '2', '2018-02-12 15:49:07', '2018-02-12 15:49:07');
INSERT INTO `action_params` VALUES ('10041', '10033', '齿轮油', '2', '1', '2', '2018-02-12 15:49:17', '2018-02-12 15:49:17');
INSERT INTO `action_params` VALUES ('10042', '10033', '润滑油', '3', '1', '2', '2018-02-12 15:49:26', '2018-02-12 15:49:26');
INSERT INTO `action_params` VALUES ('10043', '10033', '液压油', '4', '1', '2', '2018-02-12 15:49:35', '2018-02-12 15:49:35');
INSERT INTO `action_params` VALUES ('10044', '10033', '锂基油', '5', '1', '2', '2018-02-12 15:49:58', '2018-02-12 15:49:58');
INSERT INTO `action_params` VALUES ('10045', '10034', '接触器', '1', '1', '2', '2018-02-12 15:50:35', '2018-02-12 15:50:35');
INSERT INTO `action_params` VALUES ('10046', '10034', '开关', '2', '1', '2', '2018-02-12 15:50:43', '2018-02-12 15:50:43');
INSERT INTO `action_params` VALUES ('10047', '10034', '继电器', '3', '1', '2', '2018-02-12 15:50:50', '2018-02-12 15:50:50');
INSERT INTO `action_params` VALUES ('10048', '10034', '遥控器', '4', '1', '2', '2018-02-12 15:50:56', '2018-02-12 15:50:56');
INSERT INTO `action_params` VALUES ('10049', '10034', '断路器', '5', '1', '2', '2018-02-12 15:51:17', '2018-02-12 15:51:17');
INSERT INTO `action_params` VALUES ('10050', '10034', '控制器', '6', '1', '2', '2018-02-12 15:51:26', '2018-02-12 15:51:26');
INSERT INTO `action_params` VALUES ('10051', '10024', '轮胎起重机', '1', '1', '1', '2018-02-12 17:06:24', '2018-02-12 17:06:24');
INSERT INTO `action_params` VALUES ('10052', '10028', '内燃平衡重叉车', '1', '1', '1', '2018-02-12 17:07:39', '2018-02-12 17:07:39');
INSERT INTO `action_params` VALUES ('10053', '10052', 'Z系列', '1', '1', '2', '2018-02-12 17:08:05', '2018-02-12 17:08:05');
INSERT INTO `action_params` VALUES ('10054', '10052', 'E系列', '2', '1', '2', '2018-02-12 17:08:15', '2018-02-12 17:08:15');
INSERT INTO `action_params` VALUES ('10059', '10031', '免运费', '0', '1', '1', '2019-03-07 11:00:52', '2019-03-07 11:00:52');
INSERT INTO `action_params` VALUES ('10060', '10038', 'pvc管阀', '0', '1', '3', '2019-03-21 15:17:47', '2019-03-21 15:17:47');
INSERT INTO `action_params` VALUES ('10061', '10024', '结构件', null, '1', '1', '2019-07-09 00:30:32', '2019-07-09 00:30:32');
INSERT INTO `action_params` VALUES ('10062', '10030', '特殊卖品', null, '1', '1', '2019-07-09 00:46:13', '2019-07-09 00:46:13');
INSERT INTO `action_params` VALUES ('10063', '0', '工程起重机械', null, '1', '0', '2019-07-09 08:45:07', '2019-07-09 08:45:07');
INSERT INTO `action_params` VALUES ('10064', '10063', '底盘配件', null, '1', '1', '2019-07-09 08:45:27', '2019-07-09 08:45:27');
INSERT INTO `action_params` VALUES ('10065', '10024', '电气件', null, '1', '1', '2019-07-09 09:58:36', '2019-07-09 09:58:36');
INSERT INTO `action_params` VALUES ('10066', '10025', ' 摊铺机件 ', null, '1', '1', '2019-07-09 10:49:36', '2019-07-09 10:49:36');
INSERT INTO `action_params` VALUES ('10067', '10025', '压路机件', null, '1', '1', '2019-07-09 11:31:28', '2019-07-09 11:31:28');
INSERT INTO `action_params` VALUES ('10068', '10026', '易损类', null, '1', '1', '2019-07-09 12:15:09', '2019-07-09 12:15:09');
INSERT INTO `action_params` VALUES ('10069', '10026', '电器类', null, '1', '1', '2019-07-09 12:18:03', '2019-07-09 12:18:03');
INSERT INTO `action_params` VALUES ('10070', '10026', '滤芯类', null, '1', '1', '2019-07-09 21:37:45', '2019-07-09 21:37:45');
INSERT INTO `action_params` VALUES ('10073', '10064', '电气件', null, '1', '2', '2019-07-10 11:12:17', '2019-07-10 11:12:17');
INSERT INTO `action_params` VALUES ('10074', '10033', '尿素溶液', null, '1', '2', '2019-07-10 11:14:08', '2019-07-10 11:14:08');
INSERT INTO `action_params` VALUES ('10076', '10033', '发动机油', null, '1', '2', '2019-07-10 11:16:43', '2019-07-10 11:16:43');
INSERT INTO `action_params` VALUES ('10077', '10061', '标准节', null, '1', '2', '2019-07-10 11:18:06', '2019-07-10 11:18:06');
INSERT INTO `action_params` VALUES ('10078', '10061', '地脚螺栓', null, '1', '2', '2019-07-10 11:19:18', '2019-07-10 11:19:18');
INSERT INTO `action_params` VALUES ('10079', '10065', '可编程控制器', null, '1', '2', '2019-07-10 11:19:57', '2019-07-10 11:19:57');
INSERT INTO `action_params` VALUES ('10080', '10061', '支腿', null, '1', '2', '2019-07-10 11:20:33', '2019-07-10 11:20:33');
INSERT INTO `action_params` VALUES ('10081', '10066', '螺旋叶片', null, '1', '2', '2019-07-10 11:21:50', '2019-07-10 11:21:50');
INSERT INTO `action_params` VALUES ('10082', '10066', '滤芯类', null, '1', '2', '2019-07-10 11:22:23', '2019-07-10 11:22:23');
INSERT INTO `action_params` VALUES ('10083', '10066', '搅拌叶片', null, '1', '2', '2019-07-10 11:24:40', '2019-07-10 11:24:40');
INSERT INTO `action_params` VALUES ('10084', '10066', ' 履带板橡胶', null, '1', '2', '2019-07-10 11:32:21', '2019-07-10 11:32:21');
INSERT INTO `action_params` VALUES ('10085', '10067', '圆形减震器', null, '1', '2', '2019-07-10 11:34:36', '2019-07-10 11:34:36');
INSERT INTO `action_params` VALUES ('10088', '10068', '易损类', null, '1', '2', '2019-07-10 11:42:16', '2019-07-10 11:43:39');
INSERT INTO `action_params` VALUES ('10090', '10070', '滤芯', null, '1', '2', '2019-07-10 11:50:30', '2019-07-10 11:50:30');
INSERT INTO `action_params` VALUES ('10091', '10069', '电器', null, '1', '2', '2019-07-10 11:51:19', '2019-07-10 11:51:19');

-- ----------------------------
-- Table structure for `action_products`
-- ----------------------------
DROP TABLE IF EXISTS `action_products`;
CREATE TABLE `action_products` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `name` varchar(100) NOT NULL COMMENT '商品名称(配件)',
  `product_id` int(11) NOT NULL COMMENT '产品类型编号，对应action_params表中parent_id为0的分类',
  `parts_id` int(11) NOT NULL COMMENT '配件分类,对应action_params表中parent_id非0参数',
  `icon_url` varchar(500) DEFAULT NULL COMMENT '商品主图片',
  `sub_images` text COMMENT '图片地址，一组小图',
  `detail` text COMMENT '商品详情',
  `spec_param` text COMMENT '规格参数',
  `price` decimal(20,2) NOT NULL COMMENT '价格：单位元，保留两位小数',
  `stock` int(11) NOT NULL COMMENT '库存',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '商品的状态：1-待售，刚保存；2-上架，在售；3-下架，停售',
  `is_hot` int(11) NOT NULL DEFAULT '2' COMMENT '是否热销，1-是，2-否',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `parts_id` (`parts_id`),
  CONSTRAINT `action_products_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `action_params` (`id`),
  CONSTRAINT `action_products_ibfk_2` FOREIGN KEY (`parts_id`) REFERENCES `action_params` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of action_products
-- ----------------------------
INSERT INTO `action_products` VALUES ('6', '锂基脂 00#', '10023', '10038', '/upload/d88b7591-47f5-4125-b9a6-3ad29c74cba3.png', '/images/products/20190709002426433.jpg,/images/products/20190709003355891.jpg,/images/products/20190709004108212.jpg,/images/products/20190709004108502.jpg,/images/products/20190709004108571.jpg,/images/products/20190709004113985.jpg,/images/products/201907090041131.jpg,,', '<p>商<u style=\"\">品</u>详情</p>', '规格参数1', '215.89', '2', '3', '1', '2018-02-20 10:55:52', '2019-07-11 15:00:51');
INSERT INTO `action_products` VALUES ('7', '锂基脂 01#', '10023', '10044', '/images/test/2.png', '/upload/9106771f-7ae7-46e6-acb1-c63c991ed40e.png', '<p>商品详情</p>', '规格参数2', '215.89', '0', '3', '2', '2018-02-20 10:55:55', '2019-07-09 09:24:42');
INSERT INTO `action_products` VALUES ('10', '壁挂臂级起重机', '10024', '10051', '/images/test/5.png', '/upload/e1ae9346-2076-4115-b295-e30ca2ce6be1.png,/upload/c8b0f45b-1416-475c-9285-de59042d1cb8.png', '<p>起重机<br></p>', '规格参数5', '1500000.00', '9', '3', '2', '2019-03-19 18:14:09', '2019-07-09 10:07:23');
INSERT INTO `action_products` VALUES ('11', '轮胎起重机F#0090型', '10024', '10051', '/images/test/6.png', null, '', null, '250000.00', '9', '3', '2', '2019-03-21 15:33:21', '2019-07-09 10:07:25');
INSERT INTO `action_products` VALUES ('12', '混凝土输送泵', '10023', '10032', '/images/products/20190708234523883.png', null, '<p><span style=\"color: rgb(98, 102, 112);\">1、可靠</span></p><p><span style=\"color: rgb(98, 102, 112);\">①配置可靠：搭所有零部件均经过超10万方的工地考核</span></p><p><span style=\"color: rgb(98, 102, 112);\">包括核心部件柴油机、主泵、主油缸</span></p><p><span style=\"color: rgb(98, 102, 112);\">②实施自适应泵送控制技术，堵管率降低50%&nbsp;</span></p><p><span style=\"color: rgb(98, 102, 112);\">2、省钱</span></p><p><span style=\"color: rgb(98, 102, 112);\">①实施自适应节能技术，综合油耗降低5%&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ②易损件性价比行业第一,三大易损件远超竞争对手</span></p>', '规格参数5', '95000.00', '5', '3', '2', '2019-07-08 23:45:52', '2019-07-09 09:57:18');
INSERT INTO `action_products` VALUES ('13', '抗磨液压油', '10023', '10033', '/images/products/20190708235754594.jpg', '/images/products/2019070900293147.jpg,/images/products/20190709004017762.jpg,/images/products/20190709004017420.jpg,/images/products/20190709004017185.jpg,', '<p>中联重科专用高级抗磨液压油HM+46(第三代）200L<br></p>', '规格参数4', '2904.00', '58', '3', '1', '2019-07-08 23:57:40', '2019-07-09 09:27:11');
INSERT INTO `action_products` VALUES ('14', '结构件E2', '10024', '10061', '/images/products/20190709003242911.jpg', 'null,/images/products/20190709004055233.jpg,/images/products/20190709004055139.jpg,/images/products/2019070900405542.jpg,/images/products/20190709004055326.jpg,', '<p><strong><strong><strong><strong>注意：此E2标准节价格包含8个螺栓，如不需螺栓请联系客服改价。</strong></strong></strong></strong><br></p>', '规格参数3', '8160.00', '2', '3', '2', '2019-07-09 00:32:57', '2019-07-09 23:23:40');
INSERT INTO `action_products` VALUES ('16', '美孚超级黑霸王15W', '10023', '10033', '/images/products/201907090035534.jpg', '/images/products/20190709004040935.jpg,/images/products/20190709004040942.jpg,/images/products/20190709004040675.jpg,/images/products/20190709004040342.jpg,/images/products/20190709004040189.jpg,', '<p>美孚超级黑霸王15W-40 CI-4 18L<br></p>', '规格参数1', '419.00', '99', '3', '2', '2019-07-09 00:36:05', '2019-07-09 17:28:01');
INSERT INTO `action_products` VALUES ('18', '中联-美孚液压油', '10023', '10033', '/images/products/20190709004524805.jpg', '/images/products/20190709004524320.jpg,/images/products/20190709004524948.jpg,', '<p>中联-美孚液压油（208L）<br></p><hr><h1><span style=\"\"><strong style=\"\">美孚液压油208L</strong></span></h1><h4><span style=\"\">中联重科联合出品</span></h4>', null, '3230.00', '52', '3', '1', '2019-07-09 00:45:51', '2019-07-09 17:28:02');
INSERT INTO `action_products` VALUES ('19', '活塞+导向环套装Ф200', '10023', '10032', '/images/products/20190709080958775.jpg', '/images/products/20190709080958282.jpg,', '<p>活塞+导向环套装Ф200<br></p><hr>', null, '289.00', '158', '3', '1', '2019-07-09 08:10:08', '2019-07-09 17:28:04');
INSERT INTO `action_products` VALUES ('20', '发动机专用机油', '10023', '10033', '/images/products/20190709081120204.jpg', '/images/products/20190709081119980.jpg,/images/products/20190709081120342.jpg,/images/products/20190709081120295.jpg,/images/products/20190709081120917.jpg,', '<p>发动机专用机油<br></p><hr><p><strong>产品适配主机：</strong>柴油发动机适用<br><br></p>', null, '328.00', '88', '3', '1', '2019-07-09 08:11:35', '2019-07-09 17:28:06');
INSERT INTO `action_products` VALUES ('23', '国四底盘 专用尿素', '10023', '10033', '/images/products/20190709082233818.jpg', '/images/products/2019070908223390.jpg,/images/products/20190709082233393.jpg,/images/products/20190709082233290.jpg,/images/products/20190709082233892.jpg,/images/products/20190709082233504.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">国四底盘 专用尿素</span></p><hr><p><strong>产品适配主机：国四底盘 泵车/汽车吊/搅拌车</strong>&nbsp;<strong>10桶起售包邮</strong></p>', null, '42.00', '5555', '3', '1', '2019-07-09 08:22:45', '2019-07-09 17:28:07');
INSERT INTO `action_products` VALUES ('24', '眼镜板Φ230', '10023', '10032', '/images/products/20190709082544306.jpg', '/images/products/20190709082544239.jpg,/images/products/20190709082544244.jpg,/images/products/20190709082544229.jpg,/images/products/20190709082544962.jpg,', '<p>眼镜板Φ230<br></p><hr><p><strong>产品适配主机：</strong>49米以下泵车、车载泵<br></p>', null, '2305.00', '8', '3', '2', '2019-07-09 08:26:38', '2019-07-09 09:25:55');
INSERT INTO `action_products` VALUES ('25', '标准节EQ7（带螺栓）', '10024', '10061', '/images/products/20190709083648374.jpg', null, '<p>标准节EQ7（带螺栓）<br></p><hr><p style=\"text-align: center;\"><strong><strong><strong><strong>注意：此EQ7标准节价格包含12个螺栓，如不需螺栓请联系客服改价。</strong></strong></strong></strong></p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\"><strong></strong></p><p><strong><strong>物流及发货申明：</strong></strong></p><p><strong><strong>标准节可到中联重科常德基地自提</strong><strong>，具体请咨询平台客服人员,目前仅预售，提货周期预计在2月以上</strong></strong></p><p style=\"text-align: center;\"><strong>展示图片为示意图，产品请以实物为准。&nbsp;</strong></p><p style=\"text-align: center;\"><strong>产品适配机型：</strong>TC6012A-6A、TC5613-6、TC6016-6、H6012-6A、TC6012-6</p><h1 style=\"text-align: center;\"><span style=\"color: rgb(229, 51, 51);\">塔式起重机标准节产品视频，请猛戳下方链接</span><span style=\"color: rgb(229, 51, 51);\">:</span></h1><p style=\"text-align: center;\"></p><p><a href=\"http://v.youku.com/v_show/id_XMTQxMDEzMTk3Mg==.html#paction\" target=\"_blank\"><u style=\"\"><span style=\"color: rgb(229, 51, 51);\">http://v.youku.com/v_show/id_XMTQxMDEzMTk3Mg==.html#paction</span></u></a></p>', null, '9310.00', '5', '3', '2', '2019-07-09 08:37:01', '2019-07-09 23:28:03');
INSERT INTO `action_products` VALUES ('26', '标准节ABZJA1部件', '10024', '10061', '/images/products/20190709084038250.jpg', '/images/products/20190709084038410.jpg,/images/products/20190709084038923.jpg,/images/products/20190709084038967.jpg,/images/products/20190709084038840.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">标准节ABZJA1部件</span></p><hr><p><strong><strong><strong><strong>注意：此A1标准节价格包含8个螺栓，如不需螺栓请联系客服改价。</strong></strong></strong></strong></p><p><strong><strong><strong></strong></strong></strong>&nbsp;</p><p><strong><strong><strong><strong>物流及发货申明：</strong></strong></strong></strong></p><p><strong><strong><strong><strong><strong>标准节可到中联重科常德基地自提</strong><strong>，具体请咨询平台客服人员</strong></strong></strong></strong></strong></p><p><strong><strong><span style=\"color: rgb(229, 51, 51);\">塔式起重机标准节产品视频，请猛戳下方链接</span><span style=\"color: rgb(229, 51, 51);\">:</span></strong></strong></p><p><strong><strong><a href=\"http://v.youku.com/v_show/id_XMTQxMDEzMTk3Mg==.html#paction\" target=\"_blank\"><u><span style=\"color: rgb(229, 51, 51);\">http://v.youku.com/v_show/id_XMTQxMDEzMTk3Mg==.html#paction</span></u></a></strong></strong></p><p><strong><strong></strong></strong></p><p style=\"text-align: center;\"><strong>展示图片为示意图，产品请以实物为准。</strong><strong>&nbsp;</strong></p><p style=\"text-align: center;\"><strong>产品适配机型：TC6013A-6</strong></p>', null, '11012.00', '59', '3', '2', '2019-07-09 08:40:49', '2019-07-09 17:28:10');
INSERT INTO `action_products` VALUES ('27', '滑块 1087-470040-1010', '10024', '10051', '/images/products/20190709084659725.jpg', '/images/products/20190709084659929.jpg,/images/products/20190709084659147.jpg,/images/products/20190709084659266.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">滑块 1087-470040-1010</span></p><p><strong>产品适配机型：<strong>QY20V441.2/QY20V541/QY25V431.3/QY25V531.2/QY25V542.1</strong></strong><br></p><p><strong>QY25V542.4/QY25V441.2/QY30V532.3/QY30V542/QY30V532.5</strong></p>', null, '11.00', '5581', '3', '1', '2019-07-09 08:47:11', '2019-07-09 17:28:11');
INSERT INTO `action_products` VALUES ('28', '可编程控制器MC100-1410BTD-ZC', '10024', '10065', '/images/products/20190709095951710.jpg', '/images/products/20190709095951716.jpg,/images/products/2019070909595154.jpg,/images/products/20190709095952824.jpg,/images/products/20190709095952739.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">可编程控制器MC100-1410BTD-ZC</span></p><p><strong>适配机型：TC</strong><strong>5010 GPS版</strong><br></p>', null, '950.00', '47', '3', '2', '2019-07-09 10:00:03', '2019-07-09 17:28:12');
INSERT INTO `action_products` VALUES ('29', '胶管总成', '10024', '10065', '/images/products/20190709103213828.jpg', '/images/products/20190709103213887.jpg,/images/products/20190709103213741.jpg,/images/products/20190709103213878.jpg,/images/products/20190709103214618.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">胶管总成</span></p><hr><p><strong>适配机型：</strong><strong>TC5610-6、TC5013B-6、TC5613-6、TC6010-6</strong></p><p>&nbsp;</p>', null, '120.00', '254', '3', '2', '2019-07-09 10:32:27', '2019-07-09 17:31:49');
INSERT INTO `action_products` VALUES ('30', '主电缆 YC3×16+2×6/米', '10024', '10065', '/images/products/20190709103616756.jpg', '/images/products/20190709103616451.jpg,/images/products/2019070910361677.jpg,/images/products/20190709103616796.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">主电缆 YC3×16+2×6/米</span></p><hr><p>&nbsp;</p><p><strong><strong></strong></strong></p><p style=\"text-align: center;\"><strong><strong><strong><strong>物流申明：</strong>可到中联重科基地自提或由我司代办物流，</strong></strong></strong></p><p style=\"text-align: center;\"><strong><strong>代办物流费用将使用到付形式，具体运费请咨询平台客服人员。</strong></strong><strong>&nbsp;</strong></p><p><strong>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 适配机型</strong>：<strong>SC系列施工升降机100m以下高度使用</strong></p>', null, '60.00', '12', '1', '2', '2019-07-09 10:36:34', '2019-07-09 10:36:34');
INSERT INTO `action_products` VALUES ('31', '履带板橡胶两孔', '10025', '10066', '/images/products/20190709105006916.jpg', '/images/products/20190709105006283.jpg,/images/products/20190709105006947.jpg,/images/products/20190709105006216.jpg,/images/products/20190709105006731.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">带板橡胶两孔</span></p><hr><p><strong>产品适配机型：</strong>通用两孔摊铺机</p>', null, '155.00', '54', '3', '2', '2019-07-09 10:50:16', '2019-07-09 17:31:51');
INSERT INTO `action_products` VALUES ('32', '空气滤清器（外滤芯）', '10025', '10066', '/images/products/20190709111106639.jpg', '/images/products/20190709111106942.jpg,/images/products/20190709111106551.jpg,/images/products/20190709111106895.jpg,/images/products/20190709111106729.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">空气滤清器（外滤芯）</span></p><hr><p><strong>&nbsp;</strong><strong>产品适配机型：</strong>&nbsp;&nbsp; (1) 摊铺机:LTU120、LTU120G、LTU90G、LTU90K、LTUH90GD、LTUH90G;</p><p>(2)压路机:YZ25C、YZ20R、YZ18R、YZK25、YZ25B、YZ27</p><p>&nbsp;</p>', null, '355.00', '27', '3', '2', '2019-07-09 11:11:28', '2019-07-09 17:31:53');
INSERT INTO `action_products` VALUES ('33', '滚子链24B-1-60', '10025', '10066', '/images/products/20190709112032511.jpg', '/images/products/20190709112032287.jpg,/images/products/20190709112032333.jpg,/images/products/20190709112032619.jpg,/images/products/20190709112032122.jpg,', '<p>滚子链24B-1-60</p><hr><p><strong>产品适配机型：</strong>DTU90G、DTUH90G、DTU90/95/100、DTU100、DTU90G、LTU90K、LTUH90K、LTU90E、LTUH90E、LTUH90E、LTUH90G</p><p>&nbsp;</p>', null, '2006.00', '23', '3', '2', '2019-07-09 11:20:41', '2019-07-09 17:31:55');
INSERT INTO `action_products` VALUES ('34', '柴油精滤器1117050-D142', '10025', '10067', '/images/products/20190709113226154.jpg', '/images/products/20190709113226112.jpg,/images/products/2019070911322635.jpg,/images/products/20190709113229176.jpg,/images/products/20190709113229730.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">柴油精滤器1117050-D142</span></p><p><strong>产品适配机型：</strong>YZK25、YZ25C、YZC13H、YZK21、YZ20R、YZC13E、YZK19、YZ18R、LTU120G、LTU90G、LTU90K、LTUH90GD、LTUH90K、LTUH90G</p>', null, '79.00', '62', '3', '2', '2019-07-09 11:32:34', '2019-07-09 17:31:56');
INSERT INTO `action_products` VALUES ('35', '柴油粗滤器', '10025', '10066', '/images/products/20190709113348680.jpg', '/images/products/20190709113348264.jpg,/images/products/20190709113348609.jpg,/images/products/20190709113348919.jpg,/images/products/20190709113348695.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">柴油粗滤器</span></p><p>&nbsp;<strong>产品适配机型：</strong>LTU120G、LTUH90G、LTU120、LTU120D</p><p>&nbsp;</p>', null, '176.00', '55', '3', '2', '2019-07-09 11:34:02', '2019-07-09 17:31:57');
INSERT INTO `action_products` VALUES ('36', '圆形减振器', '10025', '10067', '/images/products/20190709113519417.jpg', '/images/products/20190709113519283.jpg,/images/products/20190709113519160.jpg,/images/products/2019070911351938.jpg,/images/products/20190709113519403.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">圆形减振器</span></p><p><strong>产品适配机型：</strong>YZ25C、YZK25、YZ35A</p><p>&nbsp;</p>', null, '302.00', '15', '3', '2', '2019-07-09 11:35:28', '2019-07-09 17:31:58');
INSERT INTO `action_products` VALUES ('37', '齿座A22ZD200A', '10026', '10068', '/images/products/20190709121528776.jpg', '/images/products/20190709121528483.jpg,/images/products/20190709121528907.jpg,/images/products/20190709121529620.jpg,/images/products/20190709121529300.jpg,', '<p>齿座A22ZD200A<br></p>', null, '59.00', '24', '3', '2', '2019-07-09 12:15:35', '2019-07-09 17:32:00');
INSERT INTO `action_products` VALUES ('38', '销40CrZD200Pin', '10026', '10068', '/images/products/2019070912164792.jpg', '/images/products/20190709121647875.jpg,/images/products/20190709121647583.jpg,/images/products/20190709121647615.jpg,/images/products/20190709121647962.jpg,', '<p>销40CrZD200Pin<br></p>', null, '7.00', '54', '3', '2', '2019-07-09 12:16:53', '2019-07-09 17:32:01');
INSERT INTO `action_products` VALUES ('39', '行程开关250V5A组件VX-56-1A3', '10026', '10069', '/images/products/20190709121830193.jpg', '/images/products/20190709121830865.jpg,/images/products/20190709121830592.jpg,/images/products/2019070912183036.jpg,/images/products/20190709121830632.jpg,', '<p>行程开关250V5A组件VX-56-1A3<br></p>', null, '80.00', '46', '3', '2', '2019-07-09 12:18:36', '2019-07-09 17:32:03');
INSERT INTO `action_products` VALUES ('40', '电瓶总开关组件DK238BY', '10026', '10069', '/images/products/20190709121953204.jpg', '/images/products/20190709121953297.jpg,/images/products/20190709121953720.jpg,/images/products/20190709121953823.jpg,/images/products/20190709121953681.jpg,', '<p>电瓶总开关组件DK238BY<br></p>', null, '191.00', '15', '3', '2', '2019-07-09 12:19:58', '2019-07-09 17:32:04');
INSERT INTO `action_products` VALUES ('41', '电子油门踏板', '10063', '10073', '/images/products/20190709201732720.jpg', '/images/products/20190709201732678.jpg,/images/products/20190709201732145.jpg,/images/products/20190709201732442.jpg,,/images/products/20190709223220524.jpg,/images/products/20190709223220411.jpg,/images/products/20190709223220114.jpg,/images/products/20190709223220394.jpg,', '<p>电子油门踏板&nbsp;<br></p><p><span style=\"\">产品适配机型：QY25V542.4;QY20V542;QY20D441;QY25V532.2;QY30V542;QY40V532.2</span></p>', null, '488.00', '12', '2', '2', '2019-07-09 20:15:53', '2019-07-10 13:55:32');
INSERT INTO `action_products` VALUES ('42', 'SPC全合成齿轮油', '10023', '10038', '/images/products/20190709202127407.png', '/images/products/20190709223122955.jpg,/images/products/20190709223122847.jpg,/images/products/20190709223122932.jpg,/images/products/20190709223122385.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">SPC全合成齿轮油</span></p><hr><p>&nbsp;</p><p><strong>适用范围：泵车分动箱</strong></p>', null, '2168.00', '120', '2', '1', '2019-07-09 20:21:31', '2019-07-10 17:34:52');
INSERT INTO `action_products` VALUES ('43', '活塞+导向环套装Ф200', '10023', '10038', '/images/products/20190709202234452.png', '/images/products/20190709223005792.jpg,/images/products/20190709223005646.jpg,', '<p>活塞+导向环套装Ф200<br></p><hr>', null, '289.00', '12', '2', '2', '2019-07-09 20:22:36', '2019-07-10 13:58:52');
INSERT INTO `action_products` VALUES ('44', '尿素', '10023', '10074', '/images/products/20190709202338418.png', '/images/products/20190709222800891.jpg,/images/products/2019070922280084.jpg,/images/products/20190709222800196.jpg,/images/products/20190709222800347.jpg,', '<p>国四底盘 专用尿素</p><hr><p><strong>产品适配主机：国四底盘 泵车/汽车吊/搅拌车</strong>&nbsp;<strong>10桶起售包邮</strong></p><p><strong><br><br></strong></p>', null, '42.00', '9951', '2', '1', '2019-07-09 20:23:40', '2019-07-10 18:09:57');
INSERT INTO `action_products` VALUES ('45', '眼睛板', '10023', '10039', '/images/products/20190709202437691.jpg', '/images/products/20190709222734891.jpg,/images/products/20190709222734977.jpg,/images/products/20190709222734665.jpg,/images/products/20190709222734144.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">眼镜板Φ230</span></p><hr><p><strong>产品适配主机：</strong>49米以下泵车、车载泵</p>', null, '2305.00', '25', '3', '1', '2019-07-09 20:24:39', '2019-07-10 17:31:36');
INSERT INTO `action_products` VALUES ('46', '中联-美孚液压油208L', '10023', '10043', '/images/products/20190709202531323.png', '/images/products/20190709222639445.jpg,/images/products/20190709222639261.jpg,/images/products/20190709222639536.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">中联-美孚液压油（208L）</span></p><hr><h1><span style=\"\"><strong style=\"\">美孚液压油208L</strong></span></h1><h4><span style=\"\">中联重科联合出品</span></h4>', null, '2308.00', '24', '2', '2', '2019-07-09 20:25:33', '2019-07-10 13:57:17');
INSERT INTO `action_products` VALUES ('47', '发动机专用机油', '10023', '10033', '/images/products/20190709202618337.png', '/images/products/20190709222533658.jpg,/images/products/20190709222533866.jpg,/images/products/20190709222533982.jpg,/images/products/20190709222533884.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">发动机专用机油</span></p><hr><p><strong>产品适配主机：</strong>柴油发动机适用</p>', null, '328.00', '175', '2', '2', '2019-07-09 20:26:19', '2019-07-09 22:26:05');
INSERT INTO `action_products` VALUES ('48', '道达尔液压油200L', '10023', '10043', '/images/products/20190709202721472.png', '/images/products/20190709222352983.jpg,/images/products/201907092223530.jpg,/images/products/20190709222353783.jpg,/images/products/20190709222353971.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">道达尔液压油200L</span></p><hr><p><span style=\"\"><strong style=\"\"></strong></span></p><p><strong style=\"\">&nbsp;&nbsp;</strong></p><p><strong style=\"\"><p>&nbsp;适配各种工程机械</p></strong></p><p></p><p>&nbsp;<strong></strong></p><p><strong>申明：产品外包装以实际收货为准</strong></p><p><strong>&nbsp;</strong></p>', null, '2970.00', '93', '2', '2', '2019-07-09 20:27:22', '2019-07-10 13:57:42');
INSERT INTO `action_products` VALUES ('49', '锂基脂ZL-1', '10023', '10033', '/images/products/20190709202812906.png', '/images/products/20190709222012193.jpg,/images/products/2019070922201265.jpg,/images/products/20190709222012178.jpg,/images/products/20190709222012485.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">锂基脂 ZL-1</span></p><p>&nbsp;</p><p><strong>产品适配主机：</strong>搅拌产品通用</p>', null, '271.00', '-3908', '2', '1', '2019-07-09 20:28:14', '2019-07-11 08:24:40');
INSERT INTO `action_products` VALUES ('50', '标准节A1', '10024', '10061', '/images/products/20190709203007405.png', '/images/products/20190709221931583.jpg,/images/products/20190709221931579.jpg,/images/products/20190709221931516.jpg,/images/products/20190709221931633.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">标准节ABZJA1部件</span></p><p><strong><strong><strong><strong>注意：此A1标准节价格包含8个螺栓，如不需螺栓请联系客服改价。</strong></strong></strong></strong></p><p><strong><strong><strong></strong></strong></strong>&nbsp;</p><p><strong><strong><strong><strong>物流及发货申明：</strong></strong></strong></strong></p><p><strong><strong><strong><strong><strong>标准节可到中联重科常德基地自提</strong><strong>，具体请咨询平台客服人员</strong></strong></strong></strong></strong></p><p><strong><strong><span style=\"color: rgb(229, 51, 51);\">塔式起重机标准节产品视频，请猛戳下方链接</span><span style=\"color: rgb(229, 51, 51);\">:</span></strong></strong></p><p><strong><strong><a href=\"http://v.youku.com/v_show/id_XMTQxMDEzMTk3Mg==.html#paction\" target=\"_blank\"><u><span style=\"color: rgb(229, 51, 51);\">http://v.youku.com/v_show/id_XMTQxMDEzMTk3Mg==.html#paction</span></u></a></strong></strong></p><p><strong><strong></strong></strong></p><p style=\"text-align: center;\"><strong>展示图片为示意图，产品请以实物为准。</strong><strong>&nbsp;</strong></p><p style=\"text-align: center;\"><strong>产品适配机型：TC6013A-6</strong></p><p>&nbsp;</p>', null, '11012.00', '7', '2', '2', '2019-07-09 20:30:09', '2019-07-09 23:27:54');
INSERT INTO `action_products` VALUES ('51', '标准节E2', '10024', '10061', '/images/products/20190709203135645.png', '/images/products/20190709221910721.jpg,/images/products/20190709221910575.jpg,/images/products/20190709221910722.jpg,/images/products/20190709221910730.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">标准节E2</span></p><p style=\"text-align: center;\"><strong></strong></p><p><br></p><p><strong><strong></strong></strong></p><p><strong><strong><strong><strong>注意：此E2标准节价格包含8个螺栓，如不需螺栓请联系客服改价。</strong></strong></strong></strong></p><p>&nbsp;</p><p><strong><strong><strong></strong></strong></strong></p><p><strong><strong>物流及发货申明：</strong></strong></p><p><strong><strong>标准节可到中联重科常德基地自提</strong><strong>，具体请咨询平台客服人员,目前仅预售，提货周期预计在2月以上</strong></strong></p><p>&nbsp;</p><p><strong><strong><span style=\"color: rgb(229, 51, 51);\">塔式起重机标准节产品视频，请猛戳下方链接</span><span style=\"color: rgb(229, 51, 51);\">:</span></strong></strong></p><p><strong><strong><a href=\"http://v.youku.com/v_show/id_XMTQxMDEzMTk3Mg==.html#paction\" target=\"_blank\"><u><span style=\"color: rgb(229, 51, 51);\">http://v.youku.com/v_show/id_XMTQxMDEzMTk3Mg==.html#paction</span></u></a></strong></strong></p><p></p><p style=\"text-align: center;\"><strong>产品适配机型：</strong>TC5610-6A、TC5610-6、TC5013B-6、H5010-4A、H5013-5A</p>', null, '8160.00', '54', '2', '2', '2019-07-09 20:31:37', '2019-07-09 23:27:54');
INSERT INTO `action_products` VALUES ('52', '标准节EQ7', '10024', '10061', '/images/products/2019070920344879.png', '/images/products/20190709221827588.jpg,/images/products/20190709221827911.jpg,/images/products/20190709221827790.jpg,/images/products/20190709221827716.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">标准节EQ7（带螺栓）</span></p><p style=\"text-align: center;\"><strong></strong><strong><strong><strong><strong>注意：此EQ7标准节价格包含12个螺栓，如不需螺栓请联系客服改价。</strong></strong></strong></strong></p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\"><strong></strong></p><p><strong><strong>物流及发货申明：</strong></strong></p><p><strong><strong>标准节可到中联重科常德基地自提</strong><strong>，具体请咨询平台客服人员,目前仅预售，提货周期预计在2月以上</strong></strong></p><p style=\"text-align: center;\"><strong>展示图片为示意图，产品请以实物为准。&nbsp;</strong></p><p style=\"text-align: center;\"><strong>产品适配机型：</strong>TC6012A-6A、TC5613-6、TC6016-6、H6012-6A、TC6012-6</p><h1 style=\"text-align: center;\"><span style=\"color: rgb(229, 51, 51);\">塔式起重机标准节产品视频，请猛戳下方链接</span><span style=\"color: rgb(229, 51, 51);\">:</span></h1><p style=\"text-align: center;\"></p><p><a href=\"http://v.youku.com/v_show/id_XMTQxMDEzMTk3Mg==.html#paction\" target=\"_blank\"><u style=\"\"><span style=\"color: rgb(229, 51, 51);\">http://v.youku.com/v_show/id_XMTQxMDEzMTk3Mg==.html#paction</span></u></a></p>', null, '9740.00', '12', '2', '2', '2019-07-09 20:34:50', '2019-07-09 23:27:55');
INSERT INTO `action_products` VALUES ('53', '标准节MQ1', '10024', '10061', '/images/products/20190709203528205.png', '/images/products/20190709221759662.jpg,/images/products/20190709221759805.jpg,/images/products/20190709221759943.jpg,/images/products/20190709221759555.jpg,', '<p>标准节MQ1</p><hr><p></p><p style=\"text-align: center;\"><strong>&nbsp;</strong></p><p><strong><p><strong><strong><strong><strong>注意：此MQ1标准节价格包含12个螺栓，如不需螺栓请联系客服改价。</strong></strong></strong></strong></p><p><strong><strong><strong></strong></strong></strong>&nbsp;</p><p><strong><strong><strong><strong>物流及发货申明：</strong></strong></strong></strong></p></strong></p><p><strong><strong><strong><strong><strong><strong>标准节可到中联重科常德基地自提</strong><strong>，具体请咨询平台客服人员</strong></strong></strong></strong></strong></strong></p><h1 style=\"text-align: center;\"><span style=\"color: rgb(229, 51, 51);\">塔式起重机标准节产品视频，请猛戳下方链接</span><span style=\"color: rgb(229, 51, 51);\">:</span></h1><p style=\"text-align: center;\"></p><p><a href=\"http://v.youku.com/v_show/id_XMTQxMDEzMTk3Mg==.html#paction\" target=\"_blank\"><u style=\"\"><span style=\"color: rgb(229, 51, 51);\">http://v.youku.com/v_show/id_XMTQxMDEzMTk3Mg==.html#paction</span></u></a></p><p><strong></strong></p><p style=\"text-align: center;\"><strong>展示图片为示意图，产品请以实物为准。</strong><strong>&nbsp;</strong></p><p><strong>适配机型：</strong>TC6016A-8、TC6015A-10、TC6517B-10、TC6515B-12</p>', null, '15538.00', '54', '2', '2', '2019-07-09 20:35:29', '2019-07-09 23:28:06');
INSERT INTO `action_products` VALUES ('54', '地脚螺栓总成', '10024', '10061', '/images/products/20190709203559311.png', '/images/products/20190709221701161.jpg,/images/products/20190709221701547.jpg,/images/products/20190709221701196.jpg,/images/products/20190709221701485.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">地脚螺栓总成(M39*1050)</span></p><hr><p style=\"text-align: center;\"><span style=\"color: rgb(229, 51, 51);\">注：活动款已售罄，此为原价购买。</span>&nbsp;</p><p style=\"text-align: center;\"><strong>销售及物流申明：</strong><strong>地脚螺栓总成</strong>成套销售，一套为16根；</p><p style=\"text-align: center;\">物流方式可到中联重科就近仓库自提或由我司代办物流，</p><p style=\"text-align: center;\">代办物流费用将使用到付形式，具体运费请咨询平台客服人员。<strong><br><br></strong></p><p style=\"text-align: center;\"><strong>产品适配机型:</strong>TCT5510-6，TC5610-6，TCT5010A-4 ，TC5613-6</p><p style=\"text-align: center;\">TC6010-6，TC6012-6，H6012-6A，H5013-5Y</p>', null, '2400.00', '28', '2', '1', '2019-07-09 20:36:00', '2019-07-09 23:27:25');
INSERT INTO `action_products` VALUES ('55', '可编程控制器', '10024', '10065', '/images/products/2019070920363259.png', '/images/products/20190709221642958.jpg,/images/products/20190709221643931.jpg,/images/products/20190709221643570.jpg,/images/products/20190709221643738.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">可编程控制器MC200-4040BTA-ZC</span></p><hr><p><strong>适配机型：</strong><strong>TCT5513-8/TCT5513-8F/TCT7015-8E/TCT7015-10E/TCT7520-16D/</strong></p><p><strong>TCT7527-20/TC7030B-12/TC7035B-16/TC7525-16D/TC7052A-25/TC8039-25/T6013-6/T6513B-8</strong></p>', null, '2480.00', '24', '2', '2', '2019-07-09 20:36:34', '2019-07-09 23:28:18');
INSERT INTO `action_products` VALUES ('56', '支腿TC5610', '10024', '10061', '/images/products/2019070920371744.png', '/images/products/20190709221555565.jpg,/images/products/20190709221555313.jpg,/images/products/20190709221555943.jpg,/images/products/201907092215556.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">支腿/TC5610/4个</span></p><hr><p style=\"text-align: center;\"><strong>物流申明：</strong>&nbsp;<strong><span style=\"color: rgb(229, 51, 51);\">本品</span></strong><strong><span style=\"color: rgb(229, 51, 51);\">不包邮</span></strong><span style=\"color: rgb(229, 51, 51);\">！</span><strong>塔机支腿</strong>可到中联重科就近仓库自提或由我司代办物流，</p><p style=\"text-align: center;\">代办物流费用将使用到付形式，具体运费请咨询平台客服人员</p><p style=\"text-align: center;\"><strong>适配机型：</strong>TC5013B-6、TC5510-6、TC5610-6、TCT5513-6、TCT5513-8、TC5613-6、TC6012-6</p><p style=\"text-align: center;\">TCT5512-10、TC6010-6、TC5010E-4T、H6012-6、H6012-6A、H5010-4A、H5013-5A、TCT5513-8</p>', null, '4960.00', '41', '2', '2', '2019-07-09 20:37:19', '2019-07-09 23:26:40');
INSERT INTO `action_products` VALUES ('57', '支腿TC6517', '10024', '10061', '/images/products/20190709203746897.png', '/images/products/20190709221539648.jpg,/images/products/20190709221539468.jpg,/images/products/20190709221539355.jpg,/images/products/20190709221539521.jpg,', '<p>支腿/TC6517B10.15/4个</p><hr><p style=\"text-align: center;\"><strong>物流申明：<strong><span style=\"color: rgb(229, 51, 51);\">本品</span></strong><strong><span style=\"color: rgb(229, 51, 51);\">不包邮</span></strong><span style=\"color: rgb(229, 51, 51);\">！</span>塔机支脚</strong>可到中联重科就近仓库自提或由我司代办物流，</p><p style=\"text-align: center;\">代办物流费用将使用到付形式，具体运费请咨询平台客服人员。</p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\"><strong>产品适配机型：</strong>TC6015A-10\\TC6016A-8\\TC6517B-10\\TC6515B-12</p>', null, '9280.00', '45', '2', '2', '2019-07-09 20:37:48', '2019-07-09 23:26:41');
INSERT INTO `action_products` VALUES ('58', '支腿TC7030B', '10024', '10061', '/images/products/20190709203817716.png', '/images/products/20190709221457188.jpg,/images/products/20190709221457601.jpg,/images/products/2019070922145713.jpg,/images/products/20190709221457887.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">支腿TC7030/4个</span>&nbsp;</p><hr><p style=\"text-align: center;\"><strong>物流申明：<strong><span style=\"color: rgb(229, 51, 51);\">本品</span></strong><strong><span style=\"color: rgb(229, 51, 51);\">不包邮</span></strong><span style=\"color: rgb(229, 51, 51);\">！</span>塔机支脚</strong>可到中联重科就近仓库自提或由我司代办物流，</p><p style=\"text-align: center;\">代办物流费用将使用到付形式，具体运费请咨询平台客服人员。</p><p style=\"text-align: center;\">&nbsp;</p><p style=\"text-align: center;\"><strong>产品适配机型：</strong>TC7030B-12 TC7035B-16 TCT7527-20 L250-16</p>', null, '26840.00', '23', '2', '2', '2019-07-09 20:38:19', '2019-07-09 23:26:42');
INSERT INTO `action_products` VALUES ('59', '180°右螺旋叶片', '10025', '10066', '/images/products/20190709204233538.png', '/images/products/20190709221412950.jpg,/images/products/20190709221412760.jpg,/images/products/20190709221412867.jpg,/images/products/20190709221412726.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">180°右螺旋叶片（倒角）</span></p><hr><p><strong>产品适配机型：</strong>LTU120G、DTUH90D、LTUH90K、LTU90、DTU90D、LTU90K、DTU90G、DTU95G、DTU100G、LTU90G、LTUH90G、DTU100、SUPER130、LTU90E、LTU165、LTUH90E</p>', null, '218.00', '548', '2', '2', '2019-07-09 20:42:34', '2019-07-09 22:14:14');
INSERT INTO `action_products` VALUES ('60', '柴油粗滤器', '10025', '10066', '/images/products/20190709204309296.jpg', '/images/products/20190709221304897.jpg,/images/products/201907092213041.jpg,/images/products/20190709221304478.jpg,/images/products/20190709221304175.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">柴油粗滤器</span></p><hr><p>&nbsp;<strong>产品适配机型：</strong>LTU120G、LTUH90G、LTU120、LTU120D</p><p>&nbsp;</p>', null, '219.00', '564', '2', '2', '2019-07-09 20:43:11', '2019-07-09 22:13:30');
INSERT INTO `action_products` VALUES ('61', '刀头 RT06', '10025', '10066', '/images/products/20190709204339839.jpg', '', '', null, '32.00', '2154', '2', '2', '2019-07-09 20:43:41', '2019-07-09 20:47:25');
INSERT INTO `action_products` VALUES ('62', '搅拌叶片', '10025', '10066', '/images/products/20190709204407290.png', '/images/products/20190709220834774.jpg,/images/products/20190709220834768.jpg,/images/products/20190709220834150.jpg,/images/products/20190709220834246.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">搅拌叶片Ⅰ（A）</span></p><hr><p><strong>适配机型：</strong>沥青搅拌站LB4000、LBX4000、LBX3000、LB3000、LBM3000</p>', null, '352.00', '1517', '2', '1', '2019-07-09 20:44:09', '2019-07-11 17:14:56');
INSERT INTO `action_products` VALUES ('63', '履带板橡胶', '10025', '10066', '/images/products/20190709204441636.png', '/images/products/20190709220721335.jpg,/images/products/20190709220721389.jpg,/images/products/20190709220721487.jpg,/images/products/20190709220721292.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">履带板橡胶四孔</span></p><hr><p><strong>产品适配机型：</strong>通用四孔摊铺机</p>', null, '154.00', '545', '2', '2', '2019-07-09 20:44:42', '2019-07-09 22:07:59');
INSERT INTO `action_products` VALUES ('64', '吸油过滤器', '10025', '10066', '/images/products/2019070920451259.jpg', '/images/products/20190709220643290.jpg,/images/products/20190709220643953.jpg,/images/products/20190709220643709.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">吸油过滤器WU-160×80-J</span></p><hr><p>&nbsp;<strong>产品适配机型：</strong>LTUH90E、DTU90D、DTUH90D、DTU90G、DTU95G、DTU100G、LTU120G、DTUH90G、LTU90E、DTU90G、LTUH90E、LTUH90G</p><p>&nbsp;</p>', null, '48.00', '54', '2', '2', '2019-07-09 20:45:14', '2019-07-09 22:07:00');
INSERT INTO `action_products` VALUES ('65', '压力管路过滤器', '10025', '10066', '/images/products/20190709204547313.jpg', '/images/products/20190709220609284.jpg,/images/products/20190709220609219.jpg,/images/products/20190709220609802.jpg,/images/products/20190709220609938.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">压力管路过滤器</span></p><p><strong>适配机型：</strong><strong>DTU90D、DTUH90D、SUPER130、DTU90G、DTUH90G、LTU120G、LTU120C</strong></p>', null, '240.00', '12', '2', '2', '2019-07-09 20:45:48', '2019-07-09 22:06:21');
INSERT INTO `action_products` VALUES ('66', '压油过滤器', '10025', '10066', '/images/products/20190709204628273.jpg', '/images/products/20190709220543286.jpg,/images/products/20190709220543864.jpg,/images/products/20190709220543711.jpg,/images/products/20190709220543915.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">压油过滤器 LH0160D10BN3HC</span></p><p><strong>适配机型：</strong><strong>DTU90G/95G/100G萨奥系统、DTUH90G萨奥系统、DTUH90D萨奥系统、LTUH90E</strong></p>', null, '247.00', '224', '2', '2', '2019-07-09 20:46:32', '2019-07-09 22:05:45');
INSERT INTO `action_products` VALUES ('67', '圆形减振器', '10025', '10067', '/images/products/20190709204659446.png', '/images/products/20190709220409874.jpg,/images/products/20190709220409185.jpg,/images/products/20190709220409999.jpg,/images/products/2019070922040982.jpg,', '<p><span style=\"color: rgb(51, 51, 51);\">圆形减振器</span><img src=\"http://zoomlionmall.com/resources/zmall/images/product_details/productDetailsTabTop_line.png\"></p><p>&nbsp;</p><p><strong>产品适配机型：</strong>YZ25C、YZK25、YZ35A</p>', null, '302.00', '456', '2', '2', '2019-07-09 20:47:01', '2019-07-09 22:04:30');
INSERT INTO `action_products` VALUES ('68', '齿座18S ADAPTOR', '10026', '10068', '/images/products/2019070921361332.jpg', '/images/products/20190709220252368.jpg,/images/products/20190709220252487.jpg,/images/products/20190709220252620.jpg,', '<p>齿座18S ADAPTOR<br></p>', null, '46.00', '546', '2', '2', '2019-07-09 21:36:16', '2019-07-09 23:34:41');
INSERT INTO `action_products` VALUES ('69', '齿座A22ZD200A', '10026', '10068', '/images/products/20190709213700372.jpg', '/images/products/20190709220205446.jpg,/images/products/20190709220205187.jpg,/images/products/20190709220205272.jpg,', '<p>齿座A22ZD200A<br></p>', null, '59.00', '56', '2', '2', '2019-07-09 21:37:02', '2019-07-09 22:02:21');
INSERT INTO `action_products` VALUES ('70', '空气内滤芯+外滤芯', '10026', '10070', '/images/products/20190709213815280.jpg', '/images/products/2019070922014322.jpg,', '<p>空气内滤芯+外滤芯（ZE60E）<br></p>', null, '211.00', '545', '2', '2', '2019-07-09 21:38:18', '2019-07-10 15:58:31');
INSERT INTO `action_products` VALUES ('71', '销40CrZD200Pin', '10026', '10068', '/images/products/2019070922011138.jpg', '/images/products/20190709213900720.jpg,/images/products/20190709220111379.jpg,/images/products/20190709220111193.jpg', '<p>销40CrZD200Pin<br></p><hr>', null, '7.00', '5445', '2', '2', '2019-07-09 21:39:02', '2019-07-10 15:59:17');
INSERT INTO `action_products` VALUES ('73', '蓄电池继电器组件DK138BY', '10026', '10069', '/images/products/20190709220024912.jpg', '/images/products/20190709220024846.jpg,/images/products/20190709214447412.jpg,/images/products/20190709220024398.jpg', '<p><span style=\"color: rgb(51, 51, 51);\">蓄电池继电器组件DK138BY</span><br></p><hr>', null, '267.00', '441', '3', '2', '2019-07-09 21:44:48', '2019-07-09 23:32:04');
INSERT INTO `action_products` VALUES ('74', '胶块ZD200RUB', '10026', '10068', '/images/products/20190709214534891.jpg', '/images/products/20190709220009272.jpg,/images/products/20190709220009258.jpg,/images/products/20190709220009119.jpg,', '<p>胶块ZD200RUB<br></p>', null, '6.00', '1231', '2', '1', '2019-07-09 21:45:35', '2019-07-11 15:00:58');
INSERT INTO `action_products` VALUES ('76', '水温传感器组件', '10026', '10069', '/images/products/20190709215237975.jpg', '/images/products/20190709215913179.jpg,/images/products/20190709215913599.jpg,', '<p>水温传感器组件WD-1615M<br></p><hr>', null, '65.00', '541', '2', '2', '2019-07-09 21:52:38', '2019-07-09 21:59:26');
INSERT INTO `action_products` VALUES ('77', '垫18S-D', '10026', '10068', '/images/products/20190709215440441.jpg', '/images/products/20190709215838970.jpg,/images/products/20190709215838630.jpg,', '<p>垫18S-D<br></p><hr>', null, '6.00', '4512', '2', '2', '2019-07-09 21:54:41', '2019-07-09 21:58:52');
INSERT INTO `action_products` VALUES ('78', '斗齿18STOOTH', '10026', '10068', '/images/products/20190709215637899.jpg', '/images/products/20190709215809520.jpg,/images/products/20190709215809353.jpg,/images/products/20190709215809155.jpg,', '<p>斗齿18STOOTH<br></p>', null, '31.00', '214', '2', '2', '2019-07-09 21:56:39', '2019-07-09 21:58:20');
INSERT INTO `action_products` VALUES ('79', '横销18S-H', '10026', '10088', '/images/products/20190709233304851.jpg', '/images/products/20190709233319255.jpg,/images/products/20190709233319798.jpg,/images/products/20190709233319551.jpg,', '<p>横<b>销<i>18S-H</i></b><br></p>', null, '9.00', '546', '2', '2', '2019-07-09 23:33:05', '2019-07-10 17:41:03');

-- ----------------------------
-- Table structure for `action_users`
-- ----------------------------
DROP TABLE IF EXISTS `action_users`;
CREATE TABLE `action_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码，MD5加密',
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `question` varchar(50) DEFAULT NULL COMMENT '密码问题',
  `asw` varchar(50) DEFAULT NULL COMMENT '找回密码答案',
  `role` int(11) NOT NULL COMMENT '角色2-管理员，1-普通用户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `age` int(11) NOT NULL DEFAULT '20' COMMENT '年龄，在0到120之间取值',
  `sex` tinyint(1) NOT NULL DEFAULT '1' COMMENT '性别，1：男、0：女',
  `del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '正常0、删除1',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `points` int(11) DEFAULT '0' COMMENT '积分点数',
  `manager` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of action_users
-- ----------------------------
INSERT INTO `action_users` VALUES ('1', 'admin', '1234', '1@qq.com', '12345678911', '1', '1', '1', '2018-02-03 23:35:34', '2019-07-10 16:38:31', '19', '0', '0', '李三', '0', null);
INSERT INTO `action_users` VALUES ('2', 'admin01', '81dc9bdb52d04dc20036dbd8313ed055', 'action@action.cn', '12345678911', '这是什么问题？', '不知道', '2', '2018-02-06 23:23:54', '2018-02-08 22:37:09', '5', '0', '0', '李武', '0', null);
INSERT INTO `action_users` VALUES ('3', 'action01', '202cb962ac59075b964b07152d234b70', 'action01@action.cn', '15566889991', '你的密码是什么', '不告诉你', '1', '2018-02-08 14:49:33', '2019-03-11 15:29:39', '23', '0', '0', '李六', '0', null);
INSERT INTO `action_users` VALUES ('4', 'action02', '202cb962ac59075b964b07152d234b70', 'action02@action.cn', '12345678911', '你的密码是什么', '不告诉你', '1', '2018-02-08 14:53:16', '2019-07-09 23:09:04', '20', '0', '1', '李四', '0', null);
INSERT INTO `action_users` VALUES ('5', 'zhangsan', '96e79218965eb72c92a549dd5a330112', 'zhangsan@sohu.com', '13356896698', 'my name', 'zhang', '1', '2019-03-12 09:19:06', '2019-03-18 15:06:53', '35', '1', '0', '张三', '0', null);
INSERT INTO `action_users` VALUES ('6', 'lisi', '96e79218965eb72c92a549dd5a330112', 'lisi@163.com', '12222222222', 'my name', 'lisi', '1', '2019-03-12 09:22:29', '2019-03-12 09:22:29', '20', '1', '0', '文一', '0', null);
INSERT INTO `action_users` VALUES ('15', 'jingfh', 'e10adc3949ba59abbe56e057f20f883e', '123456@163.com', '12345678900', '猜猜我是谁？', '皮卡丘！', '1', '2019-06-27 21:44:42', '2019-06-29 09:15:12', '99', '1', '0', '荆4', '4', null);
INSERT INTO `action_users` VALUES ('27', 'wentao', '96e79218965eb72c92a549dd5a330112', '201600301267@mail.sdu.edu.cn', '18340018206', '123', '123', '1', '2019-07-09 08:59:53', '2019-07-11 17:13:14', '20', '1', '0', '文三', '0', null);
INSERT INTO `action_users` VALUES ('28', 'zhangsan11', '96e79218965eb72c92a549dd5a330112', '201600301267@mail.sdu.edu.cn', '18340018206', 'name', 'zhangsan', '1', '2019-07-09 15:38:13', '2019-07-09 16:00:41', '20', '1', '0', '刘一', '0', null);
INSERT INTO `action_users` VALUES ('29', 'zhangsan111', '1bbd886460827015e5d605ed44252251', '201600301267@mail.sdu.edu.cn', '18340018206', '123', '123', '1', '2019-07-09 16:14:27', '2019-07-09 16:14:27', '20', '1', '0', '王一', '0', null);
INSERT INTO `action_users` VALUES ('30', 'liuhu', 'e10adc3949ba59abbe56e057f20f883e', '1234@qq.com', '12345678910', '123', '123', '1', '2019-07-10 17:29:27', '2019-07-10 17:29:27', '20', '1', '0', null, '0', null);
