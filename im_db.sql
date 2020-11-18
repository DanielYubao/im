/*
Navicat MySQL Data Transfer

Source Server         : 172.16.4.17-fssma-dev
Source Server Version : 50625
Source Host           : 172.16.4.17:3306
Source Database       : im_db

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2020-11-18 14:45:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for channel_customer_info
-- ----------------------------
DROP TABLE IF EXISTS `channel_customer_info`;
CREATE TABLE `channel_customer_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_code` varchar(500) DEFAULT NULL COMMENT '客户账号',
  `customer_name` varchar(100) DEFAULT NULL COMMENT '客户名称',
  `contact` varchar(100) DEFAULT NULL COMMENT '联系人',
  `contact_info` varchar(500) DEFAULT NULL COMMENT '联系方式',
  `channel_id` bigint(20) NOT NULL COMMENT '渠道id',
  `audit_role` varchar(30) DEFAULT NULL COMMENT '审核人角色',
  `audit_id` bigint(20) DEFAULT NULL COMMENT '审核人id',
  `status` varchar(20) DEFAULT 'unchanged' COMMENT '状态  unchanged :未改变  audit:审核中  refuse：拒绝 completed:审核完成',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `disable_flag` int(11) DEFAULT '0',
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of channel_customer_info
-- ----------------------------
INSERT INTO `channel_customer_info` VALUES ('45', '演示客户账号', '55555', '555', '5555', '183', 'admin', null, 'audit', '63', '2020-07-06 21:16:46', '63', '2020-07-16 10:32:05', '0', '0');
INSERT INTO `channel_customer_info` VALUES ('46', '测试渠道客户创建时间==========', '嗯嗯嗯', '嗯嗯', '嗯嗯嗯', '185', null, null, 'unchanged', '60', '2020-07-16 08:00:00', '60', '2020-07-16 11:17:15', '0', '0');
INSERT INTO `channel_customer_info` VALUES ('47', 'rr', 'r', 'rr', 'rr', '186', null, null, 'unchanged', '60', '2020-07-17 18:22:00', '60', '2020-07-17 18:22:00', '0', '0');

-- ----------------------------
-- Table structure for channel_customer_info_edit
-- ----------------------------
DROP TABLE IF EXISTS `channel_customer_info_edit`;
CREATE TABLE `channel_customer_info_edit` (
  `id` bigint(20) NOT NULL,
  `customer_code` varchar(500) DEFAULT NULL COMMENT '客户账号',
  `customer_name` varchar(100) DEFAULT NULL COMMENT '客户名称',
  `contact` varchar(100) DEFAULT NULL COMMENT '联系人',
  `contact_info` varchar(500) DEFAULT NULL COMMENT '联系方式',
  `channel_id` bigint(20) NOT NULL COMMENT '渠道id',
  `audit_role` varchar(30) DEFAULT NULL COMMENT '审核人角色',
  `audit_id` bigint(20) DEFAULT NULL COMMENT '审核人id',
  `change_type` varchar(20) DEFAULT '' COMMENT '变更类型  delete：删除 edit：修改',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `disable_flag` int(11) DEFAULT '0',
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of channel_customer_info_edit
-- ----------------------------
INSERT INTO `channel_customer_info_edit` VALUES ('45', '演示客户账号', '55555', '555', '5555', '184', 'admin', null, 'edit', '63', '2020-07-06 08:00:00', '63', '2020-07-16 10:32:05', '0', '0');

-- ----------------------------
-- Table structure for channel_customer_info_excel
-- ----------------------------
DROP TABLE IF EXISTS `channel_customer_info_excel`;
CREATE TABLE `channel_customer_info_excel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_code` varchar(500) DEFAULT NULL COMMENT '客户账号',
  `customer_name` varchar(100) DEFAULT NULL COMMENT '客户名称',
  `contact` varchar(100) DEFAULT NULL COMMENT '联系人',
  `contact_info` varchar(500) DEFAULT NULL COMMENT '联系方式',
  `channel_name` varchar(500) DEFAULT NULL,
  `channel_id` bigint(20) DEFAULT NULL COMMENT '渠道id',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `disable_flag` int(11) DEFAULT '0',
  `delete_flag` int(11) DEFAULT '0',
  `validate_msg` varchar(500) DEFAULT NULL COMMENT '验证信息',
  `validate_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of channel_customer_info_excel
-- ----------------------------

-- ----------------------------
-- Table structure for channel_info
-- ----------------------------
DROP TABLE IF EXISTS `channel_info`;
CREATE TABLE `channel_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `channel_name` varchar(200) NOT NULL COMMENT '渠道名称',
  `contact` varchar(100) DEFAULT NULL COMMENT '联系人',
  `contact_info` varchar(500) DEFAULT NULL COMMENT '联系方式',
  `new_amount_rate` double(10,2) DEFAULT '0.00' COMMENT '新购佣金比例',
  `renew_amount_rate` double(10,2) DEFAULT '0.00' COMMENT '续费佣金比例',
  `payment_type` varchar(100) DEFAULT NULL COMMENT '支付方式 bank：银行卡 other：其他 all：两种',
  `channel_bank_code` varchar(100) DEFAULT NULL COMMENT '银行账号',
  `channel_bank_name` varchar(100) DEFAULT NULL COMMENT '银行开户行',
  `payment_code` varchar(200) DEFAULT NULL COMMENT '支付账号',
  `audit_role` varchar(30) DEFAULT NULL COMMENT '审核人角色',
  `audit_id` bigint(20) DEFAULT NULL COMMENT '审核人id',
  `status` varchar(20) DEFAULT 'unchanged' COMMENT '状态  unchanged :未改变  audit:审核中  refuse：拒绝 completed:审核完成',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `disable_flag` int(11) DEFAULT '0',
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of channel_info
-- ----------------------------
INSERT INTO `channel_info` VALUES ('183', '演示渠道', '曾总', '13452616911', '20.00', '40.00', 'all', '演示银行账号', '开户行', '支付账号', null, null, 'unchanged', '63', '2020-07-06 21:13:38', '63', '2020-07-06 21:13:38', '0', '0');
INSERT INTO `channel_info` VALUES ('184', '2222====', '222', '22', '22.00', '22.00', 'bank', '22', '22', null, 'admin', null, 'audit', '63', '2020-07-06 08:00:00', '63', '2020-07-16 10:31:48', '0', '0');
INSERT INTO `channel_info` VALUES ('185', '测试渠道', '111', '11', '11.00', '11.00', 'other', null, null, '1111', null, null, 'unchanged', '60', '2020-07-16 09:40:11', '60', '2020-07-16 09:40:11', '0', '0');
INSERT INTO `channel_info` VALUES ('186', '演示渠道222', '22', '22', '22.00', '22.00', 'all', '222', '222', '222', null, null, 'unchanged', '60', '2020-07-17 18:21:44', '60', '2020-07-17 18:21:44', '0', '0');

-- ----------------------------
-- Table structure for channel_info_edit
-- ----------------------------
DROP TABLE IF EXISTS `channel_info_edit`;
CREATE TABLE `channel_info_edit` (
  `id` bigint(11) NOT NULL,
  `channel_name` varchar(200) NOT NULL COMMENT '渠道名称',
  `contact` varchar(100) DEFAULT NULL COMMENT '联系人',
  `contact_info` varchar(500) DEFAULT NULL COMMENT '联系方式',
  `new_amount_rate` double(10,2) DEFAULT '0.00' COMMENT '新购佣金比例',
  `renew_amount_rate` double(10,2) DEFAULT '0.00' COMMENT '续费佣金比例',
  `payment_type` varchar(100) DEFAULT NULL COMMENT '支付方式 bank：银行卡 other：其他 all：两种',
  `channel_bank_code` varchar(100) DEFAULT NULL COMMENT '银行账号',
  `channel_bank_name` varchar(100) DEFAULT NULL COMMENT '银行开户行',
  `payment_code` varchar(200) DEFAULT NULL COMMENT '支付账号',
  `audit_role` varchar(30) DEFAULT NULL COMMENT '审核人角色',
  `audit_id` bigint(20) DEFAULT NULL COMMENT '审核人id',
  `change_type` varchar(20) DEFAULT '' COMMENT '变更类型  delete：删除 edit：修改',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `disable_flag` int(11) DEFAULT '0',
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of channel_info_edit
-- ----------------------------
INSERT INTO `channel_info_edit` VALUES ('184', '2222====', '222', '22', '22.00', '40.00', 'bank', '22', '22', null, 'admin', null, 'edit', '63', '2020-07-06 08:00:00', '63', '2020-07-16 10:31:48', '0', '0');

-- ----------------------------
-- Table structure for channel_info_excel
-- ----------------------------
DROP TABLE IF EXISTS `channel_info_excel`;
CREATE TABLE `channel_info_excel` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `channel_name` varchar(200) NOT NULL COMMENT '渠道名称',
  `contact` varchar(100) DEFAULT NULL COMMENT '联系人',
  `contact_info` varchar(500) DEFAULT NULL COMMENT '联系方式',
  `new_amount_rate` varchar(10) DEFAULT '0.00' COMMENT '新购佣金比例',
  `renew_amount_rate` varchar(10) DEFAULT '0.00' COMMENT '续费佣金比例',
  `payment_type` varchar(100) DEFAULT NULL COMMENT '支付方式 blank:银行卡 other：其他',
  `channel_bank_code` varchar(100) DEFAULT NULL COMMENT '银行账号',
  `channel_bank_name` varchar(100) DEFAULT NULL COMMENT '银行开户行',
  `payment_code` varchar(200) DEFAULT NULL COMMENT '支付账号',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `validate_msg` varchar(500) DEFAULT NULL COMMENT '验证信息',
  `validate_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of channel_info_excel
-- ----------------------------

-- ----------------------------
-- Table structure for customer_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `customer_code` varchar(500) DEFAULT NULL COMMENT '客户账号',
  `customer_name` varchar(100) DEFAULT NULL COMMENT '客户名称',
  `contact` varchar(100) DEFAULT NULL COMMENT '联系人',
  `contact_info` varchar(500) DEFAULT NULL COMMENT '联系方式',
  `new_amount_rate` double(10,2) DEFAULT '0.00' COMMENT '新购佣金比例',
  `renew_amount_rate` double(10,2) DEFAULT '0.00' COMMENT '续费佣金比例',
  `payment_type` varchar(100) DEFAULT NULL COMMENT '支付方式 bank：银行卡 other：其他 all：两种',
  `customer_bank_code` varchar(100) DEFAULT NULL COMMENT '客户银行账号',
  `customer_bank_name` varchar(100) DEFAULT NULL COMMENT '银行开户行',
  `payment_code` varchar(200) DEFAULT NULL COMMENT '支付账号',
  `audit_role` varchar(30) DEFAULT NULL COMMENT '审核人角色',
  `audit_id` bigint(20) DEFAULT NULL COMMENT '审核人id',
  `status` varchar(20) DEFAULT 'unchanged' COMMENT '状态  unchanged :未改变  audit:审核中  refuse：拒绝 completed:审核完成',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `disable_flag` int(11) DEFAULT '0',
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_info
-- ----------------------------
INSERT INTO `customer_info` VALUES ('196', 'E24A046D31C786F4F11A0C145D089DAD329CB9CD99084C3684C9A6E257EE43AC', '直接客户名称', '直接客户联系人', '6F094F6E035A7C07E816AAF219920338', '22.00', '10.00', 'bank', '直接客户银行卡', '直接客户开户行', null, 'admin', null, 'audit', '63', '2020-07-06 21:17:46', '63', '2020-07-16 10:32:15', '0', '0');
INSERT INTO `customer_info` VALUES ('197', '15E8DBC7A6F3FA43CF15F5D35866974058480D63AD95EFE3C83B792E50AE460A', '333', '33', '65EDFD22925D9520B56857C9AF006B11', '33.00', '33.00', 'bank', '33', '33热热热', null, null, null, 'unchanged', '61', '2020-07-16 10:20:37', '61', '2020-07-16 10:20:37', '0', '0');
INSERT INTO `customer_info` VALUES ('198', '35E7ABD0A65A768DE38362D744D83F85', '管理员客户', '管理员客户', 'D2390B92B3ED00EF63A58EFF0BD21463', '33.00', '33.00', 'all', '33', '管理员客户', '管理员客户', null, null, 'unchanged', '60', '2020-07-17 19:05:47', '60', '2020-07-17 19:05:47', '0', '0');

-- ----------------------------
-- Table structure for customer_info_edit
-- ----------------------------
DROP TABLE IF EXISTS `customer_info_edit`;
CREATE TABLE `customer_info_edit` (
  `id` bigint(11) NOT NULL,
  `customer_code` varchar(500) DEFAULT NULL COMMENT '客户账号',
  `customer_name` varchar(100) DEFAULT NULL COMMENT '客户名称',
  `contact` varchar(100) DEFAULT NULL COMMENT '联系人',
  `contact_info` varchar(500) DEFAULT NULL COMMENT '联系方式',
  `new_amount_rate` double(10,4) DEFAULT '0.0000' COMMENT '新购佣金比例',
  `renew_amount_rate` double(10,4) DEFAULT '0.0000' COMMENT '续费佣金比例',
  `payment_type` varchar(100) DEFAULT NULL COMMENT '支付方式 bank：银行卡 other：其他 all：两种',
  `customer_bank_code` varchar(100) DEFAULT NULL COMMENT '客户银行账号',
  `customer_bank_name` varchar(100) DEFAULT NULL COMMENT '银行开户行',
  `payment_code` varchar(200) DEFAULT NULL COMMENT '支付账号',
  `audit_role` varchar(30) DEFAULT NULL COMMENT '审核人角色',
  `audit_id` bigint(20) DEFAULT NULL COMMENT '审核人id',
  `change_type` varchar(20) DEFAULT '' COMMENT '变更类型  delete：删除 edit：修改',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `disable_flag` int(11) DEFAULT '0',
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_info_edit
-- ----------------------------
INSERT INTO `customer_info_edit` VALUES ('196', 'E24A046D31C786F4F11A0C145D089DAD329CB9CD99084C3684C9A6E257EE43AC', '直接客户名称', '直接客户联系人', '4EA5CA8B9C418593DE651B126BD38FC6', '22.0000', '10.0000', 'bank', '直接客户银行卡', '直接客户开户行', null, 'admin', null, 'edit', '63', '2020-07-06 08:00:00', '63', '2020-07-16 10:32:15', '0', '0');

-- ----------------------------
-- Table structure for customer_info_excel
-- ----------------------------
DROP TABLE IF EXISTS `customer_info_excel`;
CREATE TABLE `customer_info_excel` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `customer_code` varchar(100) DEFAULT NULL COMMENT '客户账号',
  `customer_name` varchar(100) DEFAULT NULL COMMENT '客户名称',
  `contact` varchar(100) DEFAULT NULL COMMENT '联系人',
  `contact_info` varchar(100) DEFAULT NULL COMMENT '联系方式',
  `new_amount_rate` varchar(100) DEFAULT '0.00' COMMENT '新购佣金比例',
  `renew_amount_rate` varchar(100) DEFAULT '0.00' COMMENT '续费佣金比例',
  `payment_type` varchar(100) DEFAULT NULL COMMENT '支付方式 blank:银行卡 other：其他',
  `customer_bank_code` varchar(100) DEFAULT NULL COMMENT '客户银行账号',
  `customer_bank_name` varchar(100) DEFAULT NULL COMMENT '银行开户行',
  `payment_code` varchar(200) DEFAULT NULL COMMENT '支付账号',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `validate_msg` varchar(500) DEFAULT NULL COMMENT '验证信息',
  `validate_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of customer_info_excel
-- ----------------------------

-- ----------------------------
-- Table structure for customer_upload_log
-- ----------------------------
DROP TABLE IF EXISTS `customer_upload_log`;
CREATE TABLE `customer_upload_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `disable_flag` int(11) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_upload_log
-- ----------------------------

-- ----------------------------
-- Table structure for give_record
-- ----------------------------
DROP TABLE IF EXISTS `give_record`;
CREATE TABLE `give_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_code` varchar(100) NOT NULL COMMENT '客户账号',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户信息id',
  `customer_type` varchar(20) DEFAULT NULL COMMENT '客户类型 channel：渠道客户 direct：直接客户',
  `donee_name` varchar(100) DEFAULT NULL COMMENT '受赠人',
  `account_info` varchar(200) DEFAULT NULL COMMENT '账户信息',
  `amount` double(16,2) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `give_status` varchar(100) DEFAULT 'ungive' COMMENT '赠送状态 ：ungive未赠送 audit：审核中  refuse：拒绝 give：已赠送',
  `give_audit_role` varchar(100) DEFAULT NULL COMMENT '赠送审核人角色',
  `audit_role` varchar(20) DEFAULT NULL COMMENT '数据变更审核人角色',
  `status` varchar(20) DEFAULT 'unchanged' COMMENT '状态  unchanged :未改变  audit:审核中  refuse：拒绝  completed:审核完成',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `disable_flag` int(11) DEFAULT '0',
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of give_record
-- ----------------------------
INSERT INTO `give_record` VALUES ('127', '测试渠道客户创建时间', null, 'channel', 'gagda', '', '1000.00', '', 'audit', 'finance', null, 'unchanged', '60', '2020-07-16 10:46:55', '60', '2020-07-16 10:53:51', '0', '0');
INSERT INTO `give_record` VALUES ('128', '测试渠道客户创建时间==========', null, 'channel', 'rrr-------------', 'rr', '22.00', '22', 'ungive', null, null, 'unchanged', '60', '2020-07-17 08:00:00', '60', '2020-07-17 19:12:52', '0', '0');
INSERT INTO `give_record` VALUES ('129', '测试渠道客户创建时间==========', '46', 'channel', '435345', '', '10000.00', '', 'ungive', null, null, 'unchanged', '60', '2020-07-17 08:00:00', '60', '2020-07-17 18:20:26', '0', '0');
INSERT INTO `give_record` VALUES ('130', '测试渠道客户创建时间==========', '46', 'channel', '林淼', '林淼的账户信息', '1000.00', '赠送给林淼壹仟元', 'ungive', null, null, 'unchanged', '60', '2020-07-17 18:51:30', '60', '2020-07-17 18:51:30', '0', '0');
INSERT INTO `give_record` VALUES ('131', '管理员客户', '198', 'direct', '管理员客户测试EE------------============', '管理员客户先休息', '444.00', '管理员客户', 'ungive', null, null, 'unchanged', '60', '2020-07-17 08:00:00', '60', '2020-07-17 19:13:01', '0', '0');

-- ----------------------------
-- Table structure for give_record_edit
-- ----------------------------
DROP TABLE IF EXISTS `give_record_edit`;
CREATE TABLE `give_record_edit` (
  `id` bigint(20) NOT NULL,
  `customer_code` varchar(100) NOT NULL COMMENT '客户账号',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户信息id',
  `customer_type` varchar(20) DEFAULT NULL COMMENT '客户类型 channel：渠道客户 direct：直接客户',
  `donee_name` varchar(100) DEFAULT NULL COMMENT '受赠人',
  `account_info` varchar(200) DEFAULT NULL COMMENT '账户信息',
  `amount` double(16,2) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `audit_role` varchar(30) DEFAULT NULL COMMENT '数据变更审核人角色',
  `change_type` varchar(20) DEFAULT '' COMMENT '变更类型  delete：删除 edit：修改',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `disable_flag` int(11) DEFAULT '0',
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of give_record_edit
-- ----------------------------

-- ----------------------------
-- Table structure for give_record_excel
-- ----------------------------
DROP TABLE IF EXISTS `give_record_excel`;
CREATE TABLE `give_record_excel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_code` varchar(100) NOT NULL COMMENT '客户账号',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户信息id',
  `customer_type` varchar(20) DEFAULT NULL COMMENT '客户类型 channel：渠道客户 direct：直接客户',
  `donee_name` varchar(100) DEFAULT NULL COMMENT '受赠人',
  `account_info` varchar(200) DEFAULT NULL COMMENT '账户信息',
  `amount` varchar(16) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `validate_msg` varchar(500) DEFAULT NULL COMMENT '验证信息',
  `validate_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of give_record_excel
-- ----------------------------

-- ----------------------------
-- Table structure for payment_record
-- ----------------------------
DROP TABLE IF EXISTS `payment_record`;
CREATE TABLE `payment_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_code` varchar(100) NOT NULL COMMENT '客户账号',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户信息id',
  `customer_type` varchar(20) DEFAULT NULL COMMENT '客户类型 channel：渠道客户 direct：直接客户',
  `payment_type` varchar(50) NOT NULL COMMENT '支付方式 bank：银行卡 other：其他',
  `new_amount` double(10,2) DEFAULT NULL,
  `new_payment_amount` double(10,2) DEFAULT NULL,
  `renew_amount` double(10,2) DEFAULT NULL COMMENT '续费金额',
  `renew_payment_amount` double(10,2) DEFAULT NULL COMMENT '续费打款金额',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `payment_status` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT 'unpayment' COMMENT '打款状态 unpayment：未打款 audit：审核中  refuse：拒绝 payment：已打款',
  `payment_audit_role` varchar(20) DEFAULT NULL COMMENT '打款审核角色',
  `audit_role` varchar(20) DEFAULT NULL COMMENT '数据变更审核人角色',
  `status` varchar(20) DEFAULT 'unchanged' COMMENT '数据变更状态  unchanged :未改变  audit:审核中  refuse：拒绝 completed:审核完成',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `disable_flag` int(11) DEFAULT '0',
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3256 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of payment_record
-- ----------------------------
INSERT INTO `payment_record` VALUES ('1', '演示直接客户信息', '196', 'direct', 'bank', '13000.00', '150.00', '63000.00', '720.00', '直接客户添加打款', 'payment', 'administrator', 'admin', 'audit', '63', '2020-07-06 21:19:51', '63', '2020-07-16 10:41:53', '0', '0');
INSERT INTO `payment_record` VALUES ('2', '演示直接客户信息', '196', 'direct', 'bank', '2000.00', '1000.00', '6200.00', '230.00', '测试使用', 'audit', 'finance', null, 'unchanged', '63', '2020-07-07 08:00:00', '60', '2020-07-16 10:52:06', '0', '0');
INSERT INTO `payment_record` VALUES ('3', '演示客户账号', '45', 'channel', 'bank', '111.00', '1111.00', '11.00', '111.00', '11111', 'unpayment', null, null, 'unchanged', '60', '2020-07-16 09:36:43', '60', '2020-07-16 09:36:43', '0', '0');
INSERT INTO `payment_record` VALUES ('4', '测试显示时间', '197', 'direct', 'bank', '2.00', '23.00', '33.00', '222.00', '33333333333', 'unpayment', null, null, 'unchanged', '61', '2020-07-16 10:21:16', '61', '2020-07-16 10:21:16', '0', '0');
INSERT INTO `payment_record` VALUES ('5', 'rr', '47', 'channel', 'other', '333.00', '33.00', '33.00', '333.00', '测试渠道客户打款方式', 'unpayment', null, null, 'unchanged', '60', '2020-07-17 18:22:47', '60', '2020-07-17 18:22:47', '0', '0');
INSERT INTO `payment_record` VALUES ('6', 'rr', '47', 'channel', 'other', '111.00', '11.00', '2.00', '2.00', '12e', 'refuse', 'finance', null, 'unchanged', '60', '2020-09-05 20:10:16', '60', '2020-09-05 20:17:01', '0', '0');
INSERT INTO `payment_record` VALUES ('7', 'cdhftkj', '298', 'direct', 'other', '680000.00', '15000.00', null, null, '9月2日前给客户天猫账户打15000', 'payment', 'administrator', null, 'unchanged', '61', '2020-08-30 20:59:17', '61', '2020-09-04 08:41:28', '0', '0');
INSERT INTO `payment_record` VALUES ('8', 'smartccteg', '299', 'direct', 'other', '4800.00', '588.00', null, null, '手动将创建时间改成0831', 'payment', 'administrator', null, 'unchanged', '61', '2020-08-31 21:03:06', '61', '2020-09-04 08:41:28', '0', '0');
INSERT INTO `payment_record` VALUES ('146', '演示直接客户信息', '196', 'direct', 'bank', '13000.00', '150.00', '63000.00', '720.00', '直接客户添加打款', 'payment', 'administrator', null, 'unchanged', '63', '2020-07-06 21:19:51', '60', '2020-07-14 08:53:55', '0', '1');
INSERT INTO `payment_record` VALUES ('151', '学习用的', '200', 'direct', 'other', '1000.00', '150.00', null, null, '', 'refuse', 'finance', null, 'unchanged', '66', '2020-07-08 02:42:26', '60', '2020-07-14 08:53:18', '0', '1');
INSERT INTO `payment_record` VALUES ('153', 'shiqi', '201', 'direct', 'other', '5000.00', '750.00', null, null, '', 'refuse', 'finance', null, 'unchanged', '70', '2020-07-08 02:43:03', '60', '2020-07-14 08:53:41', '0', '1');
INSERT INTO `payment_record` VALUES ('155', '123', '197', 'direct', 'other', '120000.00', '120000.00', '20000.00', '2000.00', '', 'refuse', 'finance', null, 'unchanged', '73', '2020-07-07 19:00:00', '60', '2020-07-14 08:53:43', '0', '1');
INSERT INTO `payment_record` VALUES ('157', 'shiqi', '201', 'direct', 'other', '5000.00', '750.00', null, null, '', 'unpayment', null, null, 'unchanged', '70', '2020-07-08 02:44:45', '60', '2020-07-14 08:53:51', '0', '1');
INSERT INTO `payment_record` VALUES ('159', 'abc1', '203', 'direct', 'other', '5000.00', '3000.00', '1500.00', '500.00', '', 'refuse', 'finance', null, 'unchanged', '75', '2020-07-08 02:44:57', '60', '2020-07-14 08:53:46', '0', '1');
INSERT INTO `payment_record` VALUES ('160', 'lbwnb', '204', 'direct', 'other', '5000000.00', '5000000.00', '5000000.00', '5000000.00', '慈善家', 'refuse', 'finance', null, 'unchanged', '73', '2020-07-08 02:54:55', '60', '2020-07-14 08:53:49', '0', '1');
INSERT INTO `payment_record` VALUES ('161', 'longkin4006819888', '206', 'direct', 'bank', '6000.00', '0.00', '172000.00', '11340.00', '已经扣除税点', 'payment', 'administrator', null, 'unchanged', '61', '2020-07-13 19:48:01', '61', '2020-07-16 14:33:30', '0', '0');
INSERT INTO `payment_record` VALUES ('162', 'a港陆a', '208', 'direct', 'bank', '5068.80', '684.00', null, null, '已经扣除税点', 'payment', 'administrator', null, 'unchanged', '61', '2020-07-13 19:51:11', '61', '2020-07-16 14:33:30', '0', '0');
INSERT INTO `payment_record` VALUES ('163', 'zhongtaixiaofang123', '209', 'direct', 'bank', '10366.00', '500.00', null, null, '已经扣除税点', 'payment', 'administrator', null, 'unchanged', '61', '2020-07-13 19:53:08', '61', '2020-07-16 14:33:30', '0', '0');
INSERT INTO `payment_record` VALUES ('164', '小康金康车联网平台', '210', 'direct', 'other', null, null, '300000.00', '6800.00', '已经扣除税点，客户需要现金', 'payment', 'administrator', null, 'unchanged', '61', '2020-07-13 19:58:02', '61', '2020-07-16 14:33:30', '0', '0');
INSERT INTO `payment_record` VALUES ('165', 'guhaojia2019', '49', 'channel', 'bank', '98593.00', '6212.00', null, null, '陀图7个点', 'payment', 'administrator', null, 'unchanged', '60', '2020-07-13 20:23:59', '60', '2020-07-16 14:33:30', '0', '0');
INSERT INTO `payment_record` VALUES ('166', '华网卫士', '211', 'direct', 'bank', null, null, '6907.32', '498.00', '已经扣除税点', 'payment', 'administrator', null, 'unchanged', '61', '2020-07-13 22:28:09', '61', '2020-07-16 14:33:30', '0', '0');
INSERT INTO `payment_record` VALUES ('167', 'zhsml888', '212', 'direct', 'bank', '5038.80', '450.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '61', '2020-07-15 18:58:27', '61', '2020-07-16 14:33:30', '0', '0');
INSERT INTO `payment_record` VALUES ('168', 'zhongtaixiaofang123', '209', 'direct', 'bank', '5038.80', '230.00', null, null, '渠道打款', 'unpayment', null, null, 'unchanged', '61', '2020-07-15 18:59:36', '61', '2020-07-16 08:01:09', '0', '1');
INSERT INTO `payment_record` VALUES ('169', ' 深圳美哒化妆品有限公司', '213', 'direct', 'other', '1397.05', '209.00', '0.00', '0.00', '新购返佣', 'payment', 'administrator', null, 'unchanged', '68', '2020-07-15 19:56:16', '68', '2020-07-16 14:33:30', '0', '0');
INSERT INTO `payment_record` VALUES ('170', '健康常青', '53', 'channel', 'other', '15989.84', '3163.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '69', '2020-07-15 20:42:40', '69', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('174', '易兔外贸', '54', 'channel', 'other', null, null, '4161.60', '210.00', '', 'payment', 'administrator', null, 'unchanged', '70', '2020-07-16 01:26:18', '70', '2020-07-16 14:33:30', '0', '0');
INSERT INTO `payment_record` VALUES ('175', 'tomaxhu@gmail.com', '216', 'direct', 'bank', '1536.00', '292.00', null, null, '2020-7-14订单', 'payment', 'administrator', null, 'unchanged', '66', '2020-07-16 01:45:30', '66', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('176', 'lancuixinxi', '217', 'direct', 'bank', '17122.80', '2568.00', null, null, '2020-7-10订单', 'payment', 'administrator', null, 'unchanged', '66', '2020-07-16 01:52:00', '66', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('177', '欧菲斯办公伙伴控股', '218', 'direct', 'bank', '38902.80', '5485.00', null, null, '2020-7-10订单；扣了税点。', 'payment', 'administrator', null, 'unchanged', '66', '2020-07-16 01:56:49', '66', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('178', '维修通', '219', 'direct', 'bank', null, null, '3058.80', '144.00', '2020-7-6续费订单；扣了税点。', 'payment', 'administrator', null, 'unchanged', '66', '2020-07-16 02:01:36', '66', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('179', 'admin@yaozh.com', '220', 'direct', 'other', null, null, '8842.00', '442.00', '2020年6月整月续费', 'payment', 'administrator', null, 'unchanged', '66', '2020-07-16 02:07:49', '66', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('180', 'admin@cqhzcx.com', '221', 'direct', 'other', null, null, '3314.00', '331.00', '2020年6月整月续费', 'payment', 'administrator', null, 'unchanged', '66', '2020-07-16 02:10:11', '66', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('181', 't_1506563018733_0787', '222', 'direct', 'bank', '1829.00', '274.00', '89677.00', '8968.00', '2020年6月整月新购和续费', 'payment', 'administrator', null, 'unchanged', '66', '2020-07-16 02:16:22', '66', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('182', 'lancuixinxi', '55', 'channel', 'other', '17122.80', '200.00', null, null, '介绍大客户，返点给了客户，200的介绍费', 'payment', 'administrator', null, 'unchanged', '66', '2020-07-16 02:24:09', '66', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('184', 'admin@cqliving.com', '223', 'direct', 'bank', '24619.00', '3567.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-07-16 02:53:17', '65', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('185', 'deaocompass', '224', 'direct', 'other', '2525.00', '126.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-07-16 02:59:23', '65', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('186', 'lancq0608', '56', 'channel', 'other', '3070.20', '461.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-07-16 03:04:54', '65', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('187', '重庆及食商贸有限公司', '57', 'channel', 'bank', '35944.20', '2365.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-07-16 03:15:30', '65', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('188', '稻穗金服', '59', 'channel', 'other', '5118.40', '768.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-07-16 03:24:25', '65', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('189', 'webmaster@yizhoucp.cn', '60', 'channel', 'bank', '26026.00', '3106.00', '7938.00', '357.00', '续费是6月的按量付费', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-16 03:47:43', '71', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('190', 'cppisgood', '61', 'channel', 'bank', '1437.00', '194.00', '138.00', '6.20', '续费是6月按量付费', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-16 04:24:33', '71', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('191', 'leo@vhash.onaliyun.com', '62', 'channel', 'bank', '0.00', null, '1099.00', '49.00', '6月按量付费960', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-16 04:25:57', '71', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('192', 'admin@cqhzcx.com', '221', 'direct', 'other', '482.71', '72.00', '2445.00', '244.00', '5月新购和5月续费', 'payment', 'administrator', null, 'unchanged', '66', '2020-07-16 04:26:13', '66', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('193', 'admin@yaozh.com', '220', 'direct', 'other', '313.41', '31.00', '7386.00', '369.00', '5月新购和5月续费', 'payment', 'administrator', null, 'unchanged', '66', '2020-07-16 04:26:45', '66', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('194', 'wcniis@163.com', '214', 'direct', 'other', '357.00', '48.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-16 04:27:29', '71', '2020-07-17 15:42:18', '0', '0');
INSERT INTO `payment_record` VALUES ('195', 'rongxing562020', '63', 'channel', 'bank', '11268.00', '710.00', null, null, '', 'audit', 'administrator', null, 'unchanged', '60', '2020-07-17 00:08:59', '60', '2020-08-27 16:04:42', '0', '0');
INSERT INTO `payment_record` VALUES ('196', '1951044896@qq.com', '64', 'channel', 'bank', '3682.00', '130.00', null, null, '', 'unpayment', null, null, 'unchanged', '60', '2020-07-17 00:21:32', '60', '2020-07-17 00:21:32', '0', '0');
INSERT INTO `payment_record` VALUES ('197', 'hi35664445@aliyun.com', '225', 'direct', 'bank', '12515.00', '1689.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-23 02:43:13', '71', '2020-07-24 10:39:17', '0', '0');
INSERT INTO `payment_record` VALUES ('198', '727976376@qq.com', '66', 'channel', 'other', null, null, '3179.00', '143.00', '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-23 02:48:05', '71', '2020-07-24 10:39:17', '0', '0');
INSERT INTO `payment_record` VALUES ('199', '江西约车', '65', 'channel', 'other', '15788.86', '2051.00', null, null, '已扣10个税点', 'payment', 'administrator', null, 'unchanged', '68', '2020-07-23 02:48:33', '68', '2020-07-24 10:39:17', '0', '0');
INSERT INTO `payment_record` VALUES ('200', 'wcniis@163.com', '214', 'direct', 'other', '341.00', '46.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-23 02:49:04', '71', '2020-07-24 10:39:17', '0', '0');
INSERT INTO `payment_record` VALUES ('201', ' 深圳美哒化妆品有限公司', '213', 'direct', 'other', '1765.79', '265.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '68', '2020-07-23 02:51:00', '68', '2020-07-24 10:39:17', '0', '0');
INSERT INTO `payment_record` VALUES ('202', 'webmaster@yizhoucp.cn', '60', 'channel', 'bank', '737.00', '99.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-23 02:51:03', '71', '2020-07-24 10:39:17', '0', '0');
INSERT INTO `payment_record` VALUES ('203', 'cppisgood', '61', 'channel', 'bank', '674.00', '91.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-23 02:51:26', '71', '2020-07-24 10:39:13', '0', '0');
INSERT INTO `payment_record` VALUES ('204', 'leo@vhash.onaliyun.com', '62', 'channel', 'bank', null, null, '387.00', '17.00', '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-23 02:52:05', '71', '2020-07-24 10:39:13', '0', '0');
INSERT INTO `payment_record` VALUES ('205', '重庆芸享实业', '226', 'direct', 'bank', '6853.00', '1028.00', null, null, '客户实际付款金额6853元，佣金返点1028元。打公账', 'payment', 'administrator', null, 'unchanged', '75', '2020-07-23 02:54:37', '75', '2020-07-24 10:39:13', '0', '0');
INSERT INTO `payment_record` VALUES ('206', 'lesipai', '227', 'direct', 'bank', '8618.00', '1293.00', null, null, '2020-7-14之前新购返点', 'payment', 'administrator', null, 'unchanged', '66', '2020-07-23 02:56:51', '66', '2020-07-24 10:39:13', '0', '0');
INSERT INTO `payment_record` VALUES ('207', '速厚科技', '229', 'direct', 'other', '12331.00', '1664.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-23 03:00:00', '71', '2020-07-24 10:39:13', '0', '0');
INSERT INTO `payment_record` VALUES ('208', 'hi30512143@aliyun.com', '228', 'direct', 'other', '652.00', '65.00', '1536.00', '77.00', '7月新购1536，5个点\n2020-7月升级652，10个点', 'payment', 'administrator', null, 'unchanged', '66', '2020-07-23 03:04:59', '66', '2020-07-24 10:39:13', '0', '0');
INSERT INTO `payment_record` VALUES ('209', 'yuanshanyuan', '230', 'direct', 'other', '2484.00', '373.00', null, null, '2020-7-21新购订单', 'payment', 'administrator', null, 'unchanged', '66', '2020-07-23 03:08:51', '66', '2020-07-24 10:39:13', '0', '0');
INSERT INTO `payment_record` VALUES ('210', 'tomaxhu@gmail.com', '216', 'direct', 'bank', '1536.00', '292.00', null, null, '2020-7-22新购订单', 'payment', 'administrator', null, 'unchanged', '66', '2020-07-23 03:11:33', '66', '2020-07-24 10:39:13', '0', '0');
INSERT INTO `payment_record` VALUES ('211', '健康常青', '53', 'channel', 'other', '658.68', '132.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '69', '2020-07-23 03:13:35', '69', '2020-07-24 10:39:13', '0', '0');
INSERT INTO `payment_record` VALUES ('212', '金伙伴网络科技', '231', 'direct', 'other', null, null, '1087.50', '87.00', '', 'payment', 'administrator', null, 'unchanged', '69', '2020-07-23 03:17:17', '69', '2020-07-24 10:39:13', '0', '0');
INSERT INTO `payment_record` VALUES ('213', 'bbsstep2017', '232', 'direct', 'other', null, null, '3880.00', '194.00', '', 'payment', 'administrator', null, 'unchanged', '69', '2020-07-23 03:20:57', '69', '2020-07-24 10:19:40', '0', '0');
INSERT INTO `payment_record` VALUES ('214', '好宜佳666999', '233', 'direct', 'other', '3239.27', '485.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '69', '2020-07-23 03:25:12', '69', '2020-07-24 10:19:40', '0', '0');
INSERT INTO `payment_record` VALUES ('216', 'haochi2020', '235', 'direct', 'other', '42763.53', '2993.00', '0.00', '0.00', '买的三年', 'payment', 'administrator', null, 'unchanged', '68', '2020-07-23 04:04:27', '68', '2020-07-24 10:19:40', '0', '0');
INSERT INTO `payment_record` VALUES ('217', 'daizhengyuan333', '67', 'channel', 'other', '10430.00', '1423.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-07-23 04:04:53', '65', '2020-07-24 10:19:40', '0', '0');
INSERT INTO `payment_record` VALUES ('218', 'ett7264', '68', 'channel', 'other', '2770.00', '416.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-07-23 04:09:41', '65', '2020-07-24 10:19:40', '0', '0');
INSERT INTO `payment_record` VALUES ('219', 'admin@cqliving.com', '223', 'direct', 'bank', '5826.00', '874.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-07-23 04:11:14', '65', '2020-07-24 10:19:40', '0', '0');
INSERT INTO `payment_record` VALUES ('220', 'mini_app', '236', 'direct', 'other', '1091.00', '55.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-07-23 04:14:10', '65', '2020-07-24 10:19:40', '0', '0');
INSERT INTO `payment_record` VALUES ('221', 'zwjones2009@aliyun.com', '237', 'direct', 'other', '10872.00', '543.60', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-07-23 04:17:40', '65', '2020-07-24 10:19:40', '0', '0');
INSERT INTO `payment_record` VALUES ('222', 'kevinlarry@vip.163.com', '238', 'direct', 'other', '899.94', '135.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '68', '2020-07-23 19:47:06', '68', '2020-07-24 10:19:40', '0', '0');
INSERT INTO `payment_record` VALUES ('223', '神州能源集团股份有限公司', '239', 'direct', 'bank', '38000.00', '2000.00', null, null, '他们要求私对公。这个收款的是公司账户', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-23 20:02:31', '71', '2020-07-24 10:19:40', '0', '0');
INSERT INTO `payment_record` VALUES ('224', 'mofajiazhang', '69', 'channel', 'other', '1332.00', '240.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '70', '2020-07-23 21:32:24', '70', '2020-07-31 14:27:18', '0', '0');
INSERT INTO `payment_record` VALUES ('225', '雪山飞狐高1211', '240', 'direct', 'other', '3496.80', '525.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '70', '2020-07-23 21:33:52', '70', '2020-07-31 14:27:18', '0', '0');
INSERT INTO `payment_record` VALUES ('226', ' 深圳美哒化妆品有限公司', '213', 'direct', 'other', '17640.00', '882.00', null, null, '买的三年，数据库polar-db', 'payment', 'administrator', null, 'unchanged', '68', '2020-07-26 21:53:20', '68', '2020-07-31 14:27:18', '0', '0');
INSERT INTO `payment_record` VALUES ('227', ' 深圳美哒化妆品有限公司', '213', 'direct', 'other', '1220.65', '183.00', '376.37', '19.00', '', 'payment', 'administrator', null, 'unchanged', '68', '2020-07-26 22:13:05', '68', '2020-07-31 14:27:14', '0', '0');
INSERT INTO `payment_record` VALUES ('228', 'bybp2p2018', '70', 'channel', 'other', null, null, '404.00', '20.00', '续费', 'payment', 'administrator', null, 'unchanged', '68', '2020-07-29 20:00:30', '68', '2020-07-31 14:27:14', '0', '0');
INSERT INTO `payment_record` VALUES ('229', 'singex2018', '71', 'channel', 'other', null, null, '91211.00', '4560.00', '续费', 'payment', 'administrator', null, 'unchanged', '68', '2020-07-29 20:01:52', '68', '2020-07-31 14:27:14', '0', '0');
INSERT INTO `payment_record` VALUES ('230', 'xhy@xwinvest.com.cn', '72', 'channel', 'bank', '25434.00', '1271.70', null, null, '', 'payment', 'administrator', null, 'unchanged', '76', '2020-07-29 20:14:58', '76', '2020-07-31 14:27:14', '0', '0');
INSERT INTO `payment_record` VALUES ('231', '重庆卡腾信息', '73', 'channel', 'bank', '2799.00', '420.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '60', '2020-07-29 22:00:06', '60', '2020-07-31 14:27:14', '0', '0');
INSERT INTO `payment_record` VALUES ('232', 'cqyzz@xsxlapp.com', '74', 'channel', 'bank', '6425.00', '964.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '60', '2020-07-29 22:01:31', '60', '2020-07-31 14:27:14', '0', '0');
INSERT INTO `payment_record` VALUES ('233', '趣问科技1', '78', 'channel', 'other', '18530.00', '1668.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-30 02:03:38', '71', '2020-07-31 14:27:14', '0', '0');
INSERT INTO `payment_record` VALUES ('234', 'baizihua2018', '77', 'channel', 'other', null, null, '454.00', '20.00', '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-30 02:04:09', '71', '2020-07-31 14:27:14', '0', '0');
INSERT INTO `payment_record` VALUES ('235', '727976376@qq.com', '66', 'channel', 'other', '110.00', '5.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-30 02:04:30', '71', '2020-07-31 14:27:14', '0', '0');
INSERT INTO `payment_record` VALUES ('236', '山东水发紫光大数据', '241', 'direct', 'other', '634.00', '28.60', null, null, '短信返5%', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-30 02:06:15', '71', '2020-07-31 14:27:14', '0', '0');
INSERT INTO `payment_record` VALUES ('237', 'hi35664445@aliyun.com', '225', 'direct', 'bank', '5574.00', '752.50', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-30 02:07:40', '71', '2020-07-31 14:27:10', '0', '0');
INSERT INTO `payment_record` VALUES ('238', 'wcniis@163.com', '214', 'direct', 'other', '682.00', '92.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-30 02:08:17', '71', '2020-07-31 14:27:10', '0', '0');
INSERT INTO `payment_record` VALUES ('239', 'webmaster@yizhoucp.cn', '60', 'channel', 'bank', '324.00', '44.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-30 02:08:56', '71', '2020-07-31 14:27:10', '0', '0');
INSERT INTO `payment_record` VALUES ('240', 'leo@vhash.onaliyun.com', '62', 'channel', 'bank', null, null, '142.00', '6.00', '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-30 02:09:20', '71', '2020-07-31 14:27:10', '0', '0');
INSERT INTO `payment_record` VALUES ('241', '尚景美佳客厅', '79', 'channel', 'other', '1814.84', '273.00', null, null, '新购金额1814.84元，佣金返点273元，走私账', 'payment', 'administrator', null, 'unchanged', '75', '2020-07-30 02:15:10', '75', '2020-07-31 14:27:10', '0', '0');
INSERT INTO `payment_record` VALUES ('242', 'tomaxhu@gmail.com', '216', 'direct', 'bank', '1536.00', '292.00', null, null, '2020-7-29新购', 'payment', 'administrator', null, 'unchanged', '66', '2020-07-30 02:17:16', '66', '2020-07-31 14:27:10', '0', '0');
INSERT INTO `payment_record` VALUES ('243', 'lancq0608', '80', 'channel', 'other', '2870.20', '431.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-07-30 02:22:00', '65', '2020-07-31 14:27:10', '0', '0');
INSERT INTO `payment_record` VALUES ('244', '万逸之家电子商务', '243', 'direct', 'bank', '54689.40', '1641.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-07-30 02:22:50', '65', '2020-07-31 14:27:10', '0', '0');
INSERT INTO `payment_record` VALUES ('245', '成都奇异云裳科技有限公司', '244', 'direct', 'other', '3172.20', '317.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-07-30 02:23:57', '65', '2020-07-31 14:27:10', '0', '0');
INSERT INTO `payment_record` VALUES ('246', '昭阳出行网约车', '81', 'channel', 'bank', '2150.00', '151.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-07-30 02:26:07', '65', '2020-07-31 14:27:10', '0', '0');
INSERT INTO `payment_record` VALUES ('247', '优学购', '82', 'channel', 'bank', '7653.00', '1033.20', null, null, '已扣10税点', 'payment', 'administrator', null, 'unchanged', '77', '2020-07-30 03:19:45', '77', '2020-07-31 14:27:01', '0', '0');
INSERT INTO `payment_record` VALUES ('248', 'zhangyao90@qq.com', '245', 'direct', 'other', '377.00', '17.00', null, null, '共享流量包5%返点，已扣10税点', 'payment', 'administrator', null, 'unchanged', '77', '2020-07-30 03:27:34', '77', '2020-07-31 14:27:01', '0', '0');
INSERT INTO `payment_record` VALUES ('249', 'wxid_he53xax4z2lw22', '246', 'direct', 'other', '656.00', '98.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-30 03:46:19', '71', '2020-07-31 14:27:01', '0', '0');
INSERT INTO `payment_record` VALUES ('250', 'cppisgood', '61', 'channel', 'bank', '399.00', '54.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-07-30 03:54:33', '71', '2020-07-31 14:27:01', '0', '0');
INSERT INTO `payment_record` VALUES ('251', 'bbsstep2017', '232', 'direct', 'other', '1872.00', '94.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '69', '2020-07-30 03:59:11', '69', '2020-07-31 14:27:01', '0', '0');
INSERT INTO `payment_record` VALUES ('252', 'gty8790', '83', 'channel', 'other', '3942.00', '591.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '69', '2020-07-30 04:03:05', '69', '2020-07-31 14:27:01', '0', '0');
INSERT INTO `payment_record` VALUES ('253', 'xinshijidianqi2018', '242', 'direct', 'other', null, null, '3355.47', '168.00', '', 'payment', 'administrator', null, 'unchanged', '70', '2020-07-30 04:21:53', '70', '2020-07-31 14:27:01', '0', '0');
INSERT INTO `payment_record` VALUES ('254', ' 深圳美哒化妆品有限公司', '213', 'direct', 'other', '17315.88', '866.00', null, null, '新购买的是5年和一个48元的oss', 'payment', 'administrator', null, 'unchanged', '68', '2020-07-30 20:51:42', '68', '2020-07-31 14:27:01', '0', '0');
INSERT INTO `payment_record` VALUES ('255', ' 深圳美哒化妆品有限公司', '213', 'direct', 'other', '1046.44', '157.00', null, null, '购买的一年', 'payment', 'administrator', null, 'unchanged', '68', '2020-07-30 20:52:29', '68', '2020-07-31 14:27:01', '0', '0');
INSERT INTO `payment_record` VALUES ('256', 'mynameschang', '84', 'channel', 'other', '1674.00', '251.00', null, null, '新购数据库1674元，返佣251元，对私', 'payment', 'administrator', null, 'unchanged', '75', '2020-07-30 20:57:23', '75', '2020-07-31 14:27:01', '0', '0');
INSERT INTO `payment_record` VALUES ('257', '小康金康车联网平台', '247', 'direct', 'bank', null, null, '200000.00', '4500.00', '已经扣除客户10%的手续费', 'payment', 'administrator', null, 'unchanged', '61', '2020-08-03 01:34:27', '61', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('258', '东风小康scrm系统', '248', 'direct', 'bank', '210000.00', '7560.00', null, null, '已经扣除客户10%的手续费', 'payment', 'administrator', null, 'unchanged', '61', '2020-08-03 01:37:02', '61', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('259', 'scfzbs', '85', 'channel', 'other', '1741.48', '235.00', '279.73', '20.00', '总计255', 'payment', 'administrator', null, 'unchanged', '70', '2020-08-06 01:29:32', '70', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('260', 'liguanchan@sina.com', '251', 'direct', 'other', '46290.00', '6943.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '69', '2020-08-06 01:30:00', '69', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('261', 'yl1277', '252', 'direct', 'other', '1717.20', '232.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '69', '2020-08-06 01:35:23', '69', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('262', '四库一平台', '253', 'direct', 'other', '8400.00', '378.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '70', '2020-08-06 01:36:29', '70', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('263', ' 深圳美哒化妆品有限公司', '213', 'direct', 'other', '2683.12', '402.00', '376.37', '15.00', '7月30号到8.6号消费情况与打款', 'payment', 'administrator', null, 'unchanged', '68', '2020-08-06 01:50:59', '68', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('264', '柳州风雨者', '254', 'direct', 'other', '3876.00', '581.00', null, null, '首单合作', 'payment', 'administrator', null, 'unchanged', '68', '2020-08-06 01:53:42', '68', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('265', 'ytjr2018', '255', 'direct', 'other', null, null, '7935.60', '357.00', '已扣10个税点', 'payment', 'administrator', null, 'unchanged', '68', '2020-08-06 01:55:48', '68', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('266', 'clp程立朋', '86', 'channel', 'bank', '3906.60', '586.00', null, null, '新购服务器实付3906.6，返佣586元，走公账，开普票', 'payment', 'administrator', null, 'unchanged', '75', '2020-08-06 01:57:26', '75', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('267', '18502127172@163.com', '256', 'direct', 'other', '1098.00', '165.00', null, null, '新购服务器1098元，返佣165元，对私', 'payment', 'administrator', null, 'unchanged', '75', '2020-08-06 02:01:22', '75', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('268', 'tomaxhu@gmail.com', '216', 'direct', 'bank', '8601.00', '1634.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '66', '2020-08-06 02:13:43', '66', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('269', '1327196858@qq.com', '257', 'direct', 'other', '5148.00', '772.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '66', '2020-08-06 02:20:19', '66', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('270', '宋娃娃0729', '250', 'direct', 'bank', '8323.00', '748.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-06 03:30:59', '71', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('271', 'cnet_shanghai', '249', 'direct', 'bank', '5395.00', '760.00', null, null, '返佣到阿里云专属账号', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-06 03:32:42', '71', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('272', 'gzmihanecard', '258', 'direct', 'other', '140.00', '19.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-06 03:35:19', '71', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('273', '727976376@qq.com', '66', 'channel', 'other', null, null, '1790.00', '80.00', '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-06 03:36:47', '71', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('274', 'leo@vhash.onaliyun.com', '62', 'channel', 'bank', '2200.00', '297.00', '3357.00', '151.00', '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-06 03:37:47', '71', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('275', 'webmaster@yizhoucp.cn', '60', 'channel', 'bank', '4235.00', '429.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-06 03:39:01', '71', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('276', 'cppisgood', '61', 'channel', 'bank', '1486.00', '200.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-06 03:39:35', '71', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('277', 'lambdacommchenshuai', '259', 'direct', 'other', '6850.00', '342.00', null, null, '2020-6-24新购3年。', 'payment', 'administrator', null, 'unchanged', '66', '2020-08-06 03:47:18', '66', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('278', 'pywqkj2', '260', 'direct', 'other', '2025.00', '365.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-08-06 04:16:12', '65', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('279', 'gzmihanecard', '258', 'direct', 'other', '5150.00', '695.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-06 19:42:31', '71', '2020-08-14 08:20:20', '0', '0');
INSERT INTO `payment_record` VALUES ('280', 'xsf@xsxlapp.com', '87', 'channel', 'bank', '33804.00', '5070.00', null, null, '', 'unpayment', null, null, 'unchanged', '60', '2020-08-06 20:12:20', '60', '2020-08-06 20:12:20', '0', '0');
INSERT INTO `payment_record` VALUES ('282', '慧铸梦', '263', 'direct', 'other', '1199.20', '162.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '70', '2020-08-12 20:14:50', '70', '2020-08-14 08:20:29', '0', '0');
INSERT INTO `payment_record` VALUES ('283', 'lianjiuwang@163.com', '262', 'direct', 'other', '6900.78', '1118.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '70', '2020-08-12 20:15:23', '70', '2020-08-14 08:20:29', '0', '0');
INSERT INTO `payment_record` VALUES ('284', '陆文琪liyan', '264', 'direct', 'other', null, null, '10367.00', '518.00', '', 'payment', 'administrator', null, 'unchanged', '69', '2020-08-12 20:20:01', '69', '2020-08-14 08:20:29', '0', '0');
INSERT INTO `payment_record` VALUES ('285', 'paopaocat123', '265', 'direct', 'other', '96.00', '14.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '69', '2020-08-13 01:33:11', '69', '2020-08-14 08:20:29', '0', '0');
INSERT INTO `payment_record` VALUES ('286', '1016160548@qq.com', '266', 'direct', 'other', '180.00', '9.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '74', '2020-08-13 01:33:30', '74', '2020-08-14 08:20:29', '0', '0');
INSERT INTO `payment_record` VALUES ('287', 'hi.wanghong@163.com', '88', 'channel', 'other', '3049.80', '350.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '77', '2020-08-13 01:40:04', '77', '2020-08-14 08:20:29', '0', '0');
INSERT INTO `payment_record` VALUES ('288', 'tomaxhu@gmail.com', '216', 'direct', 'bank', '1044.00', '198.00', null, null, '2020-8-7新购', 'payment', 'administrator', null, 'unchanged', '66', '2020-08-13 01:49:41', '66', '2020-08-14 08:20:29', '0', '0');
INSERT INTO `payment_record` VALUES ('289', '贵阳39yy', '268', 'direct', 'other', '4008.60', '241.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-08-13 02:59:01', '65', '2020-08-21 09:41:40', '0', '0');
INSERT INTO `payment_record` VALUES ('290', 'pywqkj2', '267', 'direct', 'other', null, null, '2882.00', '260.00', '', 'payment', 'administrator', null, 'unchanged', '65', '2020-08-13 02:59:48', '65', '2020-08-21 09:41:40', '0', '0');
INSERT INTO `payment_record` VALUES ('291', ' 深圳美哒化妆品有限公司', '213', 'direct', 'other', '108.00', '16.00', '1087.00', '54.00', '共70元', 'payment', 'administrator', null, 'unchanged', '68', '2020-08-13 04:00:03', '68', '2020-08-21 09:41:40', '0', '0');
INSERT INTO `payment_record` VALUES ('292', '	 tb3040464', '269', 'direct', 'other', null, null, '18892.00', '932.00', '', 'payment', 'administrator', null, 'unchanged', '60', '2020-08-17 20:37:58', '61', '2020-08-27 14:44:15', '0', '0');
INSERT INTO `payment_record` VALUES ('293', 'cppisgood', '61', 'channel', 'bank', '4524.00', '520.00', '6705.00', '301.00', '7月按量付费4812', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-17 20:39:22', '61', '2020-08-27 14:44:15', '0', '0');
INSERT INTO `payment_record` VALUES ('294', 'leo@vhash.onaliyun.com', '62', 'channel', 'bank', null, null, '526.00', '23.60', '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-17 20:42:12', '61', '2020-08-27 14:44:11', '0', '0');
INSERT INTO `payment_record` VALUES ('295', 'webmaster@yizhoucp.cn', '60', 'channel', 'bank', '36000.00', '4453.00', '43542.00', '1960.00', '7月按量付费43454', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-17 20:45:32', '61', '2020-08-27 14:44:11', '0', '0');
INSERT INTO `payment_record` VALUES ('296', '727976376@qq.com', '66', 'channel', 'other', null, null, '2528.00', '113.80', '6-7按量付费', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-17 20:46:32', '61', '2020-08-27 14:44:11', '0', '0');
INSERT INTO `payment_record` VALUES ('297', 'shishan@cartechfin.com', '270', 'direct', 'bank', '25350.00', '4095.00', null, null, '已经扣除税点，客户账号是短信平台账号', 'payment', 'administrator', null, 'unchanged', '61', '2020-08-18 03:39:46', '61', '2020-08-27 14:44:11', '0', '0');
INSERT INTO `payment_record` VALUES ('298', 'carrlei@163.com', '271', 'direct', 'other', '2874.30', '388.00', null, null, '已经扣除税点，客户费用已经垫付，直接转款给陈晋', 'payment', 'administrator', null, 'unchanged', '61', '2020-08-18 03:43:37', '61', '2020-08-27 14:44:11', '0', '0');
INSERT INTO `payment_record` VALUES ('299', '迈创智慧供应链', '272', 'direct', 'bank', '11000.00', '1800.00', '3500.00', null, '已经扣除了税点', 'payment', 'administrator', null, 'unchanged', '61', '2020-08-19 21:59:10', '61', '2020-08-27 14:44:11', '0', '0');
INSERT INTO `payment_record` VALUES ('300', 'jsonmedia20160829', '273', 'direct', 'bank', '97600.00', '7028.00', null, null, '已经扣除了税点', 'payment', 'administrator', null, 'unchanged', '61', '2020-08-19 22:02:36', '61', '2020-08-27 14:44:11', '0', '0');
INSERT INTO `payment_record` VALUES ('301', 'business@cqzhonghuan.com', '274', 'direct', 'other', '198.00', '30.00', null, null, '佣金之前垫付给客户的，之后没再消费', 'payment', 'administrator', null, 'unchanged', '75', '2020-08-20 01:37:40', '75', '2020-08-27 14:44:11', '0', '0');
INSERT INTO `payment_record` VALUES ('302', '禹达环保技术', '90', 'channel', 'other', '225.00', '12.00', null, null, '购买的短信返5%', 'payment', 'administrator', null, 'unchanged', '75', '2020-08-20 01:40:51', '75', '2020-08-27 14:44:11', '0', '0');
INSERT INTO `payment_record` VALUES ('303', '莉蒙企业咨询', '91', 'channel', 'other', '225.00', '12.00', null, null, '购买的短信返5%', 'payment', 'administrator', null, 'unchanged', '75', '2020-08-20 01:42:37', '75', '2020-08-27 14:44:05', '0', '0');
INSERT INTO `payment_record` VALUES ('304', '欧菲斯办公伙伴控股', '218', 'direct', 'bank', '16926.00', '846.00', null, null, '2020-8月新购短信2150和3年服务器14776', 'payment', 'administrator', null, 'unchanged', '66', '2020-08-20 01:47:04', '66', '2020-08-27 14:44:05', '0', '0');
INSERT INTO `payment_record` VALUES ('305', '福分通', '275', 'direct', 'bank', '9922.00', '595.00', null, null, '2020-8月新购短信8400，升级slb，补之前算错的续费。扣除了税点', 'payment', 'administrator', null, 'unchanged', '66', '2020-08-20 01:51:05', '66', '2020-08-27 14:44:05', '0', '0');
INSERT INTO `payment_record` VALUES ('306', 'tomaxhu@gmail.com', '216', 'direct', 'bank', '1805.00', '151.00', null, null, '2020-8-19消费订单1805，减去退费1005。', 'payment', 'administrator', null, 'unchanged', '66', '2020-08-20 01:55:48', '66', '2020-08-27 14:44:05', '0', '0');
INSERT INTO `payment_record` VALUES ('307', 'lancuixinxi', '217', 'direct', 'bank', '10666.00', '1600.00', null, null, '2020-8月新购5793.7数据库，服务器4782', 'payment', 'administrator', null, 'unchanged', '66', '2020-08-20 01:58:44', '66', '2020-08-27 14:44:05', '0', '0');
INSERT INTO `payment_record` VALUES ('308', 'cqsdsever', '92', 'channel', 'other', '8200.56', '1230.00', '39866.61', '1993.00', '', 'payment', 'administrator', null, 'unchanged', '69', '2020-08-20 02:01:53', '69', '2020-08-27 14:44:05', '0', '0');
INSERT INTO `payment_record` VALUES ('309', 'ling.zhou@hlc-china.com', '276', 'direct', 'bank', '43244.60', '5996.00', null, null, '2020-8月新购43245', 'payment', 'administrator', null, 'unchanged', '66', '2020-08-20 02:03:52', '66', '2020-08-27 14:44:05', '0', '0');
INSERT INTO `payment_record` VALUES ('310', '重庆神缘智能科技有限公司', '277', 'direct', 'other', '19572.00', '880.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-20 02:04:04', '71', '2020-08-27 14:44:05', '0', '0');
INSERT INTO `payment_record` VALUES ('311', '727976376@qq.com', '66', 'channel', 'other', '3654.00', '450.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-20 02:05:09', '71', '2020-08-27 14:44:05', '0', '0');
INSERT INTO `payment_record` VALUES ('312', 'cppisgood', '61', 'channel', 'bank', '1281.00', '57.70', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-19 19:00:00', '71', '2020-08-27 14:44:11', '0', '0');
INSERT INTO `payment_record` VALUES ('314', 'cantonaibest', '93', 'channel', 'bank', '561.00', '75.70', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-20 02:07:12', '71', '2020-08-27 14:44:05', '0', '0');
INSERT INTO `payment_record` VALUES ('315', '汇贤汇优', '94', 'channel', 'other', null, null, '1620.15', '81.00', '', 'payment', 'administrator', null, 'unchanged', '69', '2020-08-20 02:08:56', '69', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('316', '有你有家823', '278', 'direct', 'other', '1130.00', '170.00', null, null, '2020-8月新购1130', 'payment', 'administrator', null, 'unchanged', '66', '2020-08-20 02:09:07', '66', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('317', 'ling.zhou@hlc-china.com', '96', 'channel', 'other', '43245.00', '200.00', null, null, '介绍的客户，给200介绍红包。一次性', 'refuse', 'finance', null, 'unchanged', '66', '2020-08-20 02:16:17', '66', '2020-08-20 15:25:52', '0', '0');
INSERT INTO `payment_record` VALUES ('318', '1936831283@qq.com', '95', 'channel', 'other', '2615.04', '375.00', null, null, '新购服务器2136.24元，OSS金额298.8元，短信180元，除了短信返5%，其他15%', 'payment', 'administrator', null, 'unchanged', '75', '2020-08-20 02:18:16', '75', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('319', 'lpmzs', '99', 'channel', 'other', '3068.97', '460.00', null, null, '新购服务器2670.2元，升配398.77，返点15%', 'payment', 'administrator', null, 'unchanged', '75', '2020-08-20 02:21:39', '75', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('320', '汇优长江师范', '97', 'channel', 'other', null, null, '5608.00', '280.00', '', 'payment', 'administrator', null, 'unchanged', '69', '2020-08-20 02:24:58', '69', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('321', '星伙人', '279', 'direct', 'other', '4780.68', '658.00', null, null, '扣了6%税点', 'payment', 'administrator', null, 'unchanged', '75', '2020-08-20 02:29:06', '75', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('322', 'hi35664445@aliyun.com', '225', 'direct', 'bank', '11179.00', '1509.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-20 02:36:34', '71', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('323', '18266520888y', '280', 'direct', 'other', '1468.80', '221.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '75', '2020-08-20 02:39:00', '75', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('324', 'tjgl1765', '281', 'direct', 'other', '6970.68', '983.00', null, null, '已扣6个税点', 'payment', 'administrator', null, 'unchanged', '68', '2020-08-20 03:25:56', '68', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('325', 'jazhong334066', '282', 'direct', 'other', '1016.00', '50.00', null, null, '购买的一个月，返佣5个点', 'payment', 'administrator', null, 'unchanged', '68', '2020-08-20 03:27:40', '68', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('326', 'leo@vhash.onaliyun.com', '62', 'channel', 'bank', null, null, '3357.00', '151.00', '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-20 20:29:58', '71', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('327', 'xsf@xsxlapp.com', '87', 'channel', 'bank', '1620.00', '243.00', null, null, '', 'audit', 'administrator', null, 'unchanged', '60', '2020-08-23 21:17:32', '60', '2020-08-27 14:31:15', '0', '1');
INSERT INTO `payment_record` VALUES ('328', 'cqyzz@xsxlapp.com', '74', 'channel', 'bank', '1256.30', '188.00', null, null, '', 'audit', 'administrator', null, 'unchanged', '60', '2020-08-23 21:18:27', '60', '2020-08-27 14:31:57', '0', '1');
INSERT INTO `payment_record` VALUES ('329', 'mainyzj2018', '283', 'direct', 'bank', '70000.00', '2000.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '60', '2020-08-23 21:19:14', '60', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('330', '星伙人', '279', 'direct', 'other', '6555.42', '925.00', null, null, '扣除了6%个税', 'payment', 'administrator', null, 'unchanged', '75', '2020-08-25 01:19:46', '75', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('331', 'lrj@kepo.com.cn', '285', 'direct', 'other', '781.80', '78.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-25 21:26:20', '71', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('332', '727976376@qq.com', '66', 'channel', 'other', '1272.00', '171.00', '5659.00', '254.00', '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-25 21:27:27', '71', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('333', '紫微商贸xazw', '286', 'direct', 'other', '1639.00', '139.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-25 21:30:14', '71', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('334', 'hi35664445@aliyun.com', '225', 'direct', 'bank', '1795.00', '242.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-25 21:31:28', '71', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('335', 'cppisgood', '61', 'channel', 'bank', '5046.00', '328.00', '306.00', '14.00', '', 'payment', 'administrator', null, 'unchanged', '71', '2020-08-25 21:32:25', '71', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('336', 'hi20851822@aliyun.com', '100', 'channel', 'bank', '1186.80', '178.02', '0.00', '0.00', '', 'payment', 'administrator', null, 'unchanged', '76', '2020-08-25 22:09:38', '76', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('337', 'shiquanyuan', '287', 'direct', 'other', '9400.00', '1326.00', null, null, '佣金返点15%。扣了6%的个税', 'payment', 'administrator', null, 'unchanged', '75', '2020-08-26 01:30:52', '75', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('338', 'deaocompass', '290', 'direct', 'other', '2864.00', '217.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-08-26 02:08:38', '65', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('339', '贵阳星界科技有限公司', '289', 'direct', 'other', '14565.60', '2185.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '65', '2020-08-26 02:09:21', '65', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('340', 'hi30633906@aliyun.com', '288', 'direct', 'bank', '48430.27', '2422.00', null, null, '银行卡', 'payment', 'administrator', null, 'unchanged', '65', '2020-08-26 02:10:03', '65', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('341', 'zhongleihantian', '291', 'direct', 'other', '1281.80', '64.00', null, null, '报备新购首单客户', 'payment', 'administrator', null, 'unchanged', '68', '2020-08-26 19:51:03', '68', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('342', 'qizhou.nb@eumexgroup.com', '292', 'direct', 'other', '16977.15', '2546.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '69', '2020-08-26 20:07:53', '69', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('343', '杜李郑蔡牛', '293', 'direct', 'other', '2950.00', '442.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '69', '2020-08-26 20:13:38', '69', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('344', '华网卫士', '294', 'direct', 'other', null, null, '8748.62', '700.00', '', 'payment', 'administrator', null, 'unchanged', '69', '2020-08-26 20:19:26', '69', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('345', '奋斗波比123', '103', 'channel', 'other', '408.00', '66.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '70', '2020-08-26 20:20:08', '70', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('346', '汇贤汇优', '94', 'channel', 'other', '2150.00', '107.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '69', '2020-08-26 20:25:31', '69', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('347', 'cqyzz@xsxlapp.com', '74', 'channel', 'bank', '2733.60', '410.00', null, null, '', 'audit', 'admin', null, 'unchanged', '60', '2020-08-26 20:48:55', '60', '2020-08-27 14:31:44', '0', '1');
INSERT INTO `payment_record` VALUES ('348', 'fuyin2020', '295', 'direct', 'other', '1440.00', '144.00', null, null, '', 'payment', 'administrator', null, 'unchanged', '69', '2020-08-26 22:07:19', '69', '2020-08-27 14:43:56', '0', '0');
INSERT INTO `payment_record` VALUES ('349', 'xsf@xsxlapp.com', '87', 'channel', 'bank', '10580.00', '1587.00', null, null, '', 'audit', 'administrator', null, 'unchanged', '60', '2020-08-27 01:37:33', '60', '2020-08-27 16:04:40', '0', '0');
INSERT INTO `payment_record` VALUES ('350', 'cqyzz@xsxlapp.com', '74', 'channel', 'bank', '3990.00', '599.00', null, null, '', 'audit', 'administrator', null, 'unchanged', '60', '2020-08-27 01:38:19', '60', '2020-08-27 16:04:39', '0', '0');
INSERT INTO `payment_record` VALUES ('351', 'tomaxhu@gmail.com', '216', 'direct', 'bank', '16218.00', '3081.00', null, null, '2020-8-21到2020-8-27新购。', 'audit', 'administrator', null, 'unchanged', '66', '2020-08-27 03:03:47', '66', '2020-08-27 16:57:31', '0', '0');
INSERT INTO `payment_record` VALUES ('352', 'mexingroup', '296', 'direct', 'bank', null, null, '25245.00', '2524.00', '2020-7月续费', 'audit', 'administrator', null, 'unchanged', '66', '2020-08-27 03:08:02', '66', '2020-08-27 16:57:29', '0', '0');
INSERT INTO `payment_record` VALUES ('353', '贵州一加二金融科技', '297', 'direct', 'other', null, null, '20389.00', '2039.00', '2020-7月续费', 'audit', 'administrator', null, 'unchanged', '66', '2020-08-27 03:09:22', '66', '2020-08-27 16:57:27', '0', '0');
INSERT INTO `payment_record` VALUES ('354', 'cdhftkj', '298', 'direct', 'other', '680000.00', '15000.00', null, null, '9月2日前给客户天猫账户打15000', 'audit', 'finance', null, 'unchanged', '61', '2020-08-30 20:59:17', '61', '2020-08-31 11:15:15', '0', '0');
INSERT INTO `payment_record` VALUES ('3255', 'smartccteg', '299', 'direct', 'other', '4800.00', '588.00', null, null, '588我已经通过微信转给客户了，打款的时候打陈晋账号', 'audit', 'finance', null, 'unchanged', '61', '2020-08-30 21:03:06', '61', '2020-08-31 11:15:15', '0', '0');

-- ----------------------------
-- Table structure for payment_record_edit
-- ----------------------------
DROP TABLE IF EXISTS `payment_record_edit`;
CREATE TABLE `payment_record_edit` (
  `id` bigint(20) NOT NULL,
  `customer_code` varchar(100) NOT NULL COMMENT '客户账号',
  `customer_id` bigint(20) DEFAULT NULL COMMENT '客户信息id',
  `customer_type` varchar(20) DEFAULT NULL COMMENT '客户类型 channel：渠道客户 direct：直接客户',
  `payment_type` varchar(50) NOT NULL COMMENT '支付方式 bank：银行卡 other：其他',
  `new_amount` double(10,2) DEFAULT NULL,
  `new_payment_amount` double(10,2) DEFAULT NULL,
  `renew_amount` double(10,2) DEFAULT NULL COMMENT '续费金额',
  `renew_payment_amount` double(10,2) DEFAULT NULL COMMENT '续费打款金额',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `audit_role` varchar(30) DEFAULT NULL COMMENT '数据变更审核人角色',
  `change_type` varchar(20) DEFAULT '' COMMENT '变更类型  delete：删除 edit：修改',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `disable_flag` int(11) DEFAULT '0',
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of payment_record_edit
-- ----------------------------
INSERT INTO `payment_record_edit` VALUES ('146', '演示直接客户信息', '196', 'direct', 'bank', '13000.00', '150.00', '63000.00', '720.00', '直接客户添加打款', 'admin', 'delete', '63', '2020-07-06 21:19:51', '63', '2020-07-16 10:41:53', '0', '0');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_type_label` (`dict_label`,`dict_type`) USING BTREE COMMENT '同类型的字典标签唯一',
  UNIQUE KEY `dict_type_value` (`dict_value`,`dict_type`) USING BTREE COMMENT '同类型的字典值唯一'
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES ('174', '正常', '0', 'sys_normal_disable', '1', '0', null, null, null, null, '');
INSERT INTO `sys_dict_data` VALUES ('175', '停用', '1', 'sys_normal_disable', '2', '0', null, null, null, null, '');
INSERT INTO `sys_dict_data` VALUES ('184', '目录', 'catalog', 'sys_menu_type', '1', '0', '1', '2019-07-01 01:58:38', '1', '2019-07-01 01:58:38', '');
INSERT INTO `sys_dict_data` VALUES ('185', '菜单', 'menu', 'sys_menu_type', '2', '0', '1', '2019-07-01 01:58:53', '1', '2019-07-01 01:58:53', '');
INSERT INTO `sys_dict_data` VALUES ('186', '按钮', 'button', 'sys_menu_type', '3', '0', '1', '2019-07-01 01:59:05', '1', '2019-07-01 01:59:05', '');
INSERT INTO `sys_dict_data` VALUES ('188', '隐藏', '0', 'sys_menu_status', '2', '0', '1', '2019-06-30 11:00:00', '1', '2019-07-02 21:17:06', '');
INSERT INTO `sys_dict_data` VALUES ('190', '显示', '1', 'sys_menu_status', '0', '0', '1', '2019-07-02 21:17:17', '1', '2019-07-02 21:17:17', '');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `classify_code` varchar(100) NOT NULL COMMENT '分类编码',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `classify_code` (`classify_code`) USING BTREE COMMENT '字典分类code唯一'
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('118', 'sys_normal_disable', '系统状态', '0', null, null, null, null, '');
INSERT INTO `sys_dict_type` VALUES ('119', 'sys_menu_type', '菜单类型', '0', null, null, null, null, '');
INSERT INTO `sys_dict_type` VALUES ('120', 'sys_menu_status', '菜单状态', '0', null, null, null, null, '');

-- ----------------------------
-- Table structure for sys_groups
-- ----------------------------
DROP TABLE IF EXISTS `sys_groups`;
CREATE TABLE `sys_groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `group_name` varchar(100) NOT NULL COMMENT '用户id',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='组';

-- ----------------------------
-- Records of sys_groups
-- ----------------------------
INSERT INTO `sys_groups` VALUES ('1', '管理组', '0', null, '2019-06-26 00:00:00', '33', '2020-03-20 20:19:48', '');
INSERT INTO `sys_groups` VALUES ('2', '销售组', '0', null, null, '33', '2020-03-20 20:19:57', '');
INSERT INTO `sys_groups` VALUES ('3', '财务组', '0', '49', '2020-05-10 15:08:22', '49', '2020-05-10 15:08:22', '');

-- ----------------------------
-- Table structure for sys_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_menus`;
CREATE TABLE `sys_menus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `visible` int(11) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `disable_flag` int(11) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menus
-- ----------------------------
INSERT INTO `sys_menus` VALUES ('18', '系统管理1', 'catalog', null, null, '30', '0', '1', 'el-icon-setting', '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('19', '用户管理', 'menu', 'system:user:index', '/system/user/index', '31', '18', '1', null, '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('20', '角色管理', 'menu', 'system:role:index', '/system/role/index', '32', '18', '1', null, '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('21', '菜单管理', 'menu', 'system:menu:index', '/system/menu/index', '33', '18', '1', null, '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('31', '组管理', 'menu', 'system:group:index', '/system/group/index', '35', '18', '1', null, '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('32', '销售管理', 'catalog', null, null, '40', '0', '1', 'el-icon-phone-outline', '49', '2020-05-04', '59', '2020-06-15', '0', '0');
INSERT INTO `sys_menus` VALUES ('33', '直接客户信息', 'menu', null, '/system/pageManage/customerIndex', '23', '57', '1', null, '49', '2020-05-04', '59', '2020-06-01', '0', '0');
INSERT INTO `sys_menus` VALUES ('34', '财务管理', 'catalog', null, null, '60', '0', '1', 'el-icon-coin', '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('35', '佣金计算', 'menu', null, '/system/pageManage/amountIndex', '63', '34', '1', null, '49', '2020-05-04', '49', '2020-05-04', '0', '1');
INSERT INTO `sys_menus` VALUES ('36', '系统管理', 'catalog', null, null, '80', '0', '1', 'el-icon-s-tools', '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('37', '用户管理', 'menu', null, '/system/pageManage/userIndex', '81', '36', '1', null, '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('38', '直接客户信息变更审核', 'menu', null, '/system/pageManage/customerAuditIndex', '24', '57', '1', null, '49', '2020-05-04', '59', '2020-06-10', '0', '0');
INSERT INTO `sys_menus` VALUES ('39', '打款记录', 'menu', null, '/system/pageManage/paymentRecordIndex', '41', '32', '1', null, '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('41', '打款记录变更审核', 'menu', null, '/system/pageManage/paymentRecordAuditIndex', '42', '32', '1', null, '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('42', '测试页面', 'menu', null, 'system/pageManage/test', '35', '18', '1', null, '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('43', '打款记录审核', 'menu', null, '/system/pageManage/paymentFlowAuditIndex', '61', '34', '1', null, '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('44', '赠送管理', 'catalog', null, null, '50', '0', '1', 'el-icon-present', '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('45', '赠送记录', 'menu', null, '/system/pageManage/giveRecordIndex', '51', '44', '1', null, '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('46', '赠送记录变更审核', 'menu', null, '/system/pageManage/giveRecordAuditIndex', '52', '44', '1', null, '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('47', '赠送记录审核', 'menu', null, '/system/pageManage/giveFlowAuditIndex', '62', '34', '1', null, '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('48', '统计管理', 'catalog', null, null, '70', '0', '1', 'el-icon-s-data', '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('49', '客户信息统计', 'menu', null, '/system/pageManage/customerDataIndex', '71', '48', '0', null, '49', '2020-05-04', '59', '2020-06-10', '0', '0');
INSERT INTO `sys_menus` VALUES ('50', '赠送信息统计', 'menu', null, '/system/pageManage/giveDataIndex', '72', '48', '1', null, '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('51', '渠道管理', 'catalog', null, null, '10', '0', '1', 'el-icon-bank-card', '49', '2020-05-04', '59', '2020-06-01', '0', '0');
INSERT INTO `sys_menus` VALUES ('52', '渠道信息', 'menu', null, '/system/pageManage/channelIndex', '11', '51', '1', null, '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('53', '渠道信息变更审核', 'menu', null, '/system/pageManage/channelAuditIndex', '12', '51', '1', null, '49', '2020-05-04', '49', '2020-05-04', '0', '0');
INSERT INTO `sys_menus` VALUES ('54', '渠道客户信息', 'menu', null, '/system/pageManage/channelCustomerIndex', '21', '57', '1', null, '59', '2020-06-01', '59', '2020-06-01', '0', '0');
INSERT INTO `sys_menus` VALUES ('55', '渠道客户信息变更审核', 'menu', null, '/system/pageManage/channelCustomerAuditIndex', '22', '57', '1', null, '59', '2020-06-01', '59', '2020-06-10', '0', '0');
INSERT INTO `sys_menus` VALUES ('56', '打款信息统计', 'menu', null, '/system/pageManage/paymentDataIndex', null, '48', '1', null, '59', '2020-06-10', '59', '2020-06-10', '0', '0');
INSERT INTO `sys_menus` VALUES ('57', '客户管理', 'catalog', null, null, '20', '0', '1', 'el-icon-s-custom', '59', '2020-06-15', '59', '2020-06-15', '0', '0');

-- ----------------------------
-- Table structure for sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `disable_flag` int(11) DEFAULT '0',
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles
-- ----------------------------
INSERT INTO `sys_roles` VALUES ('1', 'admin', '管理员', '1', '2020-03-17', '59', '2020-06-16', '0', '0');
INSERT INTO `sys_roles` VALUES ('2', 'sale', '销售用户', '1', '2020-03-18', '59', '2020-06-16', '0', '0');
INSERT INTO `sys_roles` VALUES ('3', 'administrator', '超级管理员', '49', '2020-04-14', '59', '2020-06-16', '0', '0');
INSERT INTO `sys_roles` VALUES ('8', 'finance', '财务用户', '49', '2020-05-03', '59', '2020-07-07', '0', '0');
INSERT INTO `sys_roles` VALUES ('9', 'develop', '开发角色', '49', '2020-05-10', '59', '2020-06-15', '0', '0');

-- ----------------------------
-- Table structure for sys_role_menu_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu_rel`;
CREATE TABLE `sys_role_menu_rel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_menu_index` (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=586 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu_rel
-- ----------------------------
INSERT INTO `sys_role_menu_rel` VALUES ('540', '1', '32');
INSERT INTO `sys_role_menu_rel` VALUES ('538', '1', '33');
INSERT INTO `sys_role_menu_rel` VALUES ('546', '1', '34');
INSERT INTO `sys_role_menu_rel` VALUES ('551', '1', '36');
INSERT INTO `sys_role_menu_rel` VALUES ('552', '1', '37');
INSERT INTO `sys_role_menu_rel` VALUES ('539', '1', '38');
INSERT INTO `sys_role_menu_rel` VALUES ('541', '1', '39');
INSERT INTO `sys_role_menu_rel` VALUES ('542', '1', '41');
INSERT INTO `sys_role_menu_rel` VALUES ('547', '1', '43');
INSERT INTO `sys_role_menu_rel` VALUES ('543', '1', '44');
INSERT INTO `sys_role_menu_rel` VALUES ('544', '1', '45');
INSERT INTO `sys_role_menu_rel` VALUES ('545', '1', '46');
INSERT INTO `sys_role_menu_rel` VALUES ('548', '1', '47');
INSERT INTO `sys_role_menu_rel` VALUES ('553', '1', '48');
INSERT INTO `sys_role_menu_rel` VALUES ('550', '1', '50');
INSERT INTO `sys_role_menu_rel` VALUES ('532', '1', '51');
INSERT INTO `sys_role_menu_rel` VALUES ('533', '1', '52');
INSERT INTO `sys_role_menu_rel` VALUES ('534', '1', '53');
INSERT INTO `sys_role_menu_rel` VALUES ('536', '1', '54');
INSERT INTO `sys_role_menu_rel` VALUES ('537', '1', '55');
INSERT INTO `sys_role_menu_rel` VALUES ('549', '1', '56');
INSERT INTO `sys_role_menu_rel` VALUES ('535', '1', '57');
INSERT INTO `sys_role_menu_rel` VALUES ('529', '2', '32');
INSERT INTO `sys_role_menu_rel` VALUES ('522', '2', '33');
INSERT INTO `sys_role_menu_rel` VALUES ('523', '2', '39');
INSERT INTO `sys_role_menu_rel` VALUES ('530', '2', '44');
INSERT INTO `sys_role_menu_rel` VALUES ('524', '2', '45');
INSERT INTO `sys_role_menu_rel` VALUES ('531', '2', '48');
INSERT INTO `sys_role_menu_rel` VALUES ('526', '2', '50');
INSERT INTO `sys_role_menu_rel` VALUES ('527', '2', '51');
INSERT INTO `sys_role_menu_rel` VALUES ('520', '2', '52');
INSERT INTO `sys_role_menu_rel` VALUES ('521', '2', '54');
INSERT INTO `sys_role_menu_rel` VALUES ('525', '2', '56');
INSERT INTO `sys_role_menu_rel` VALUES ('528', '2', '57');
INSERT INTO `sys_role_menu_rel` VALUES ('562', '3', '32');
INSERT INTO `sys_role_menu_rel` VALUES ('560', '3', '33');
INSERT INTO `sys_role_menu_rel` VALUES ('568', '3', '34');
INSERT INTO `sys_role_menu_rel` VALUES ('573', '3', '36');
INSERT INTO `sys_role_menu_rel` VALUES ('574', '3', '37');
INSERT INTO `sys_role_menu_rel` VALUES ('561', '3', '38');
INSERT INTO `sys_role_menu_rel` VALUES ('563', '3', '39');
INSERT INTO `sys_role_menu_rel` VALUES ('564', '3', '41');
INSERT INTO `sys_role_menu_rel` VALUES ('569', '3', '43');
INSERT INTO `sys_role_menu_rel` VALUES ('565', '3', '44');
INSERT INTO `sys_role_menu_rel` VALUES ('566', '3', '45');
INSERT INTO `sys_role_menu_rel` VALUES ('567', '3', '46');
INSERT INTO `sys_role_menu_rel` VALUES ('570', '3', '47');
INSERT INTO `sys_role_menu_rel` VALUES ('575', '3', '48');
INSERT INTO `sys_role_menu_rel` VALUES ('572', '3', '50');
INSERT INTO `sys_role_menu_rel` VALUES ('554', '3', '51');
INSERT INTO `sys_role_menu_rel` VALUES ('555', '3', '52');
INSERT INTO `sys_role_menu_rel` VALUES ('556', '3', '53');
INSERT INTO `sys_role_menu_rel` VALUES ('558', '3', '54');
INSERT INTO `sys_role_menu_rel` VALUES ('559', '3', '55');
INSERT INTO `sys_role_menu_rel` VALUES ('571', '3', '56');
INSERT INTO `sys_role_menu_rel` VALUES ('557', '3', '57');
INSERT INTO `sys_role_menu_rel` VALUES ('69', '4', '18');
INSERT INTO `sys_role_menu_rel` VALUES ('70', '4', '19');
INSERT INTO `sys_role_menu_rel` VALUES ('71', '4', '20');
INSERT INTO `sys_role_menu_rel` VALUES ('72', '4', '21');
INSERT INTO `sys_role_menu_rel` VALUES ('73', '4', '31');
INSERT INTO `sys_role_menu_rel` VALUES ('75', '5', '18');
INSERT INTO `sys_role_menu_rel` VALUES ('74', '5', '19');
INSERT INTO `sys_role_menu_rel` VALUES ('77', '6', '18');
INSERT INTO `sys_role_menu_rel` VALUES ('76', '6', '19');
INSERT INTO `sys_role_menu_rel` VALUES ('79', '7', '18');
INSERT INTO `sys_role_menu_rel` VALUES ('78', '7', '19');
INSERT INTO `sys_role_menu_rel` VALUES ('583', '8', '32');
INSERT INTO `sys_role_menu_rel` VALUES ('580', '8', '34');
INSERT INTO `sys_role_menu_rel` VALUES ('579', '8', '39');
INSERT INTO `sys_role_menu_rel` VALUES ('581', '8', '43');
INSERT INTO `sys_role_menu_rel` VALUES ('584', '8', '44');
INSERT INTO `sys_role_menu_rel` VALUES ('585', '8', '45');
INSERT INTO `sys_role_menu_rel` VALUES ('582', '8', '47');
INSERT INTO `sys_role_menu_rel` VALUES ('499', '9', '18');
INSERT INTO `sys_role_menu_rel` VALUES ('500', '9', '19');
INSERT INTO `sys_role_menu_rel` VALUES ('501', '9', '20');
INSERT INTO `sys_role_menu_rel` VALUES ('502', '9', '21');
INSERT INTO `sys_role_menu_rel` VALUES ('503', '9', '31');
INSERT INTO `sys_role_menu_rel` VALUES ('505', '9', '32');
INSERT INTO `sys_role_menu_rel` VALUES ('497', '9', '33');
INSERT INTO `sys_role_menu_rel` VALUES ('511', '9', '34');
INSERT INTO `sys_role_menu_rel` VALUES ('518', '9', '36');
INSERT INTO `sys_role_menu_rel` VALUES ('519', '9', '37');
INSERT INTO `sys_role_menu_rel` VALUES ('498', '9', '38');
INSERT INTO `sys_role_menu_rel` VALUES ('506', '9', '39');
INSERT INTO `sys_role_menu_rel` VALUES ('507', '9', '41');
INSERT INTO `sys_role_menu_rel` VALUES ('504', '9', '42');
INSERT INTO `sys_role_menu_rel` VALUES ('512', '9', '43');
INSERT INTO `sys_role_menu_rel` VALUES ('508', '9', '44');
INSERT INTO `sys_role_menu_rel` VALUES ('509', '9', '45');
INSERT INTO `sys_role_menu_rel` VALUES ('510', '9', '46');
INSERT INTO `sys_role_menu_rel` VALUES ('513', '9', '47');
INSERT INTO `sys_role_menu_rel` VALUES ('514', '9', '48');
INSERT INTO `sys_role_menu_rel` VALUES ('516', '9', '49');
INSERT INTO `sys_role_menu_rel` VALUES ('517', '9', '50');
INSERT INTO `sys_role_menu_rel` VALUES ('491', '9', '51');
INSERT INTO `sys_role_menu_rel` VALUES ('492', '9', '52');
INSERT INTO `sys_role_menu_rel` VALUES ('493', '9', '53');
INSERT INTO `sys_role_menu_rel` VALUES ('495', '9', '54');
INSERT INTO `sys_role_menu_rel` VALUES ('496', '9', '55');
INSERT INTO `sys_role_menu_rel` VALUES ('515', '9', '56');
INSERT INTO `sys_role_menu_rel` VALUES ('494', '9', '57');

-- ----------------------------
-- Table structure for sys_users
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `login_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `disable_flag` int(11) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users
-- ----------------------------
INSERT INTO `sys_users` VALUES ('49', '管理员', 'yzy', '{MD5}{TxHYv1B62Edx2gzpl8O5qc2YaUj8AlPp3VtQRJjR1tM=}a7aa352277e507432bcd924e08995ab7', '48', '2020-04-14', '48', '2020-04-14', '0', '1');
INSERT INTO `sys_users` VALUES ('50', '超级管理员', 'root', '{MD5}{MeAr/u1DzVXaTLxcVgIR0BcFnVS5A9/GXifYOucj9i0=}14969e26a39d34bc7fb7c259153a7f9b', '49', '2020-04-14', '49', '2020-04-14', '0', '1');
INSERT INTO `sys_users` VALUES ('51', 'admin', 'admin', '{MD5}{j5O1QEoWv3+Ex8XeqgcFhio2VMlL8pnEchUeYD4dJfs=}2309fe5ce6c19c173a10b2b345a9c73c', '49', '2020-04-14', '49', '2020-04-14', '0', '1');
INSERT INTO `sys_users` VALUES ('52', '林淼', 'linmiao01', '{MD5}{R3PnJBn+9FGegl2ZZYxQozIMdx8GKN7LQ24/NhtvQ8k=}3195b1a4a581159940635d68a7fe706d', '51', '2020-04-14', '49', '2020-04-14', '0', '1');
INSERT INTO `sys_users` VALUES ('53', 'test', 'test', '{MD5}{NKYy6JgXZeDGVo+d2LG25b8nUlRWRACR/Idr/v5sx+Y=}bb8a72b5a9e3af5305d7b13daaff56d7', '49', '2020-04-21', '49', '2020-04-21', '0', '1');
INSERT INTO `sys_users` VALUES ('54', '林淼01', 'linmiao01', '{MD5}{Ec9hsM3HdG7O7qxOLwu2v/Bi4pRgBSlz94RS3WfFifU=}58e37fb47ae0f8098ff95574c9614581', '49', '2020-04-22', '49', '2020-04-22', '0', '1');
INSERT INTO `sys_users` VALUES ('55', '林淼02', 'linmiao02', '{MD5}{qvzuvyBFla0WVQ5OuLscEO/DUZieodexUWikHPasRUA=}88a42e4d82f1292c6ed3c3c02510d108', '49', '2020-04-22', '49', '2020-04-22', '0', '1');
INSERT INTO `sys_users` VALUES ('57', 'sale', 'sale', '{MD5}{j9PYuhtSPlsC9o2cebQGzQv65AZp+4twWSROBO7Jh4Q=}7f2c97cbcd6516a6a51873c9aeea9a35', '49', '2020-05-03', '49', '2020-05-03', '0', '1');
INSERT INTO `sys_users` VALUES ('58', 'finance', 'finance', '{MD5}{tHxVu0RtWYapnfg0zVvoGrAfdnhpE8esgA3g41zTIig=}4b4a52b0c54110313fced8a6556beeaf', '49', '2020-05-03', '49', '2020-05-03', '0', '1');
INSERT INTO `sys_users` VALUES ('59', '开发人员', 'dev', '{MD5}{64ejuQbJuY+GEgHxOAE53nVbvmeajSxqrt7eeTQROFI=}cf8c6a02dca64d37d619f460677fbfa2', '49', '2020-05-07', '60', '2020-07-09', '1', '0');
INSERT INTO `sys_users` VALUES ('60', '管理员', 'admin', '{MD5}{BXlIAEFJkkiSKG5ZgW/5tjc/B2uPYH+A9z5RmGrxb1I=}b1656c8da7097f6c0b67debc7c8a9030', '49', '2020-05-10', '49', '2020-05-10', '0', '0');
INSERT INTO `sys_users` VALUES ('61', '超级管理员', 'root', '{MD5}{b+kglWcaXDrC+MpjWUiGWhCFmRSPVEO7OPAj29hYJ/w=}3b0e5074083b0c2ca87ec5e93dbec46f', '49', '2020-05-10', '49', '2020-05-10', '0', '0');
INSERT INTO `sys_users` VALUES ('62', '财务人员', 'finance', '{MD5}{NCBs7Jb0FiAnXga/XMFA51k4N2z24s/8VbsYWJYzdFM=}6e4432b82f23d21c4e0d24c6d10bb8ae', '49', '2020-05-10', '49', '2020-05-10', '0', '0');
INSERT INTO `sys_users` VALUES ('63', '销售用户', 'sale', '{MD5}{IWyQCVfRkbZhqV3fXDXPUGzBrlGOOfC6QgVgwd6/PJY=}2fe883d29ff0e274a71a7bf27b2142ca', '49', '2020-05-10', '49', '2020-05-10', '0', '0');
INSERT INTO `sys_users` VALUES ('64', '销售', 'sale1', '{MD5}{TW1+QKqOSs8lt9zBEo4e/QAQD+xlVDaQcC95hpQJr4c=}022b22041fd66cc108e2665f44eff740', '60', '2020-05-08', '60', '2020-07-09', '0', '1');
INSERT INTO `sys_users` VALUES ('65', '销售1', 'sale1', '{MD5}{MZQJogSjjZhIrimwx3D+LwqpdBYFlM6MTO+598iKSIg=}ec86695894d2c1eb12f97c21b9f73f74', '60', '2020-07-07', '60', '2020-07-09', '0', '0');
INSERT INTO `sys_users` VALUES ('66', '测试用户', 'test', '{MD5}{a95Px5Xe2+02ewbWkO5AVTHc7MucStRBKQhRiusMXx8=}3b91cd708d4b5b33a67f5ab01218b45b', '60', '2020-08-03', '60', '2020-08-03', '0', '0');

-- ----------------------------
-- Table structure for sys_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_rel`;
CREATE TABLE `sys_user_role_rel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_role_index` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role_rel
-- ----------------------------
INSERT INTO `sys_user_role_rel` VALUES ('1', '48', '1');
INSERT INTO `sys_user_role_rel` VALUES ('2', '49', '1');
INSERT INTO `sys_user_role_rel` VALUES ('3', '50', '3');
INSERT INTO `sys_user_role_rel` VALUES ('4', '51', '1');
INSERT INTO `sys_user_role_rel` VALUES ('5', '52', '1');
INSERT INTO `sys_user_role_rel` VALUES ('6', '53', '2');
INSERT INTO `sys_user_role_rel` VALUES ('7', '54', '1');
INSERT INTO `sys_user_role_rel` VALUES ('8', '55', '2');
INSERT INTO `sys_user_role_rel` VALUES ('9', '56', '2');
INSERT INTO `sys_user_role_rel` VALUES ('10', '57', '2');
INSERT INTO `sys_user_role_rel` VALUES ('11', '58', '8');
INSERT INTO `sys_user_role_rel` VALUES ('13', '60', '1');
INSERT INTO `sys_user_role_rel` VALUES ('14', '61', '3');
INSERT INTO `sys_user_role_rel` VALUES ('15', '62', '8');
INSERT INTO `sys_user_role_rel` VALUES ('16', '63', '2');
INSERT INTO `sys_user_role_rel` VALUES ('18', '65', '2');
INSERT INTO `sys_user_role_rel` VALUES ('19', '66', '2');

-- ----------------------------
-- Table structure for user_group_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_group_rel`;
CREATE TABLE `user_group_rel` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(32) NOT NULL COMMENT '用户id',
  `group_id` bigint(32) NOT NULL COMMENT '组id',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) DEFAULT NULL,
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='用户和组的关系表';

-- ----------------------------
-- Records of user_group_rel
-- ----------------------------
INSERT INTO `user_group_rel` VALUES ('1', '48', '1', '0', '45', '2020-04-14 17:16:44', '2020-04-14 17:16:44', '48', '');
INSERT INTO `user_group_rel` VALUES ('2', '49', '1', '0', '48', '2020-04-14 17:42:30', '2020-04-14 17:42:30', '48', '');
INSERT INTO `user_group_rel` VALUES ('3', '50', '1', '0', '49', '2020-04-14 18:10:03', '2020-04-14 18:10:03', '49', '');
INSERT INTO `user_group_rel` VALUES ('4', '51', '1', '0', '49', '2020-04-14 21:18:27', '2020-04-14 21:18:27', '49', '');
INSERT INTO `user_group_rel` VALUES ('5', '52', '1', '0', '51', '2020-04-14 21:31:50', '2020-04-14 21:31:50', '51', '');
INSERT INTO `user_group_rel` VALUES ('6', '53', '2', '0', '49', '2020-04-21 19:04:47', '2020-04-21 19:04:47', '49', '');
INSERT INTO `user_group_rel` VALUES ('7', '54', '1', '0', '49', '2020-04-22 17:12:19', '2020-04-22 17:12:19', '49', '');
INSERT INTO `user_group_rel` VALUES ('8', '55', '2', '0', '49', '2020-04-22 17:12:51', '2020-04-22 17:12:51', '49', '');
INSERT INTO `user_group_rel` VALUES ('9', '56', '2', '0', '49', '2020-05-01 21:03:27', '2020-05-01 21:03:27', '49', '');
INSERT INTO `user_group_rel` VALUES ('10', '57', '2', '0', '49', '2020-05-03 23:12:41', '2020-05-03 23:12:41', '49', '');
INSERT INTO `user_group_rel` VALUES ('11', '58', '2', '0', '49', '2020-05-03 23:13:22', '2020-05-03 23:13:22', '49', '');
INSERT INTO `user_group_rel` VALUES ('12', '59', '1', '0', '49', '2020-05-10 15:09:48', '2020-07-09 11:39:23', '60', '');
INSERT INTO `user_group_rel` VALUES ('13', '60', '1', '0', '49', '2020-05-10 15:10:41', '2020-05-10 15:10:41', '49', '');
INSERT INTO `user_group_rel` VALUES ('14', '61', '1', '0', '49', '2020-05-10 15:11:18', '2020-05-10 15:11:18', '49', '');
INSERT INTO `user_group_rel` VALUES ('15', '62', '3', '0', '49', '2020-05-10 15:11:54', '2020-05-10 15:11:54', '49', '');
INSERT INTO `user_group_rel` VALUES ('16', '63', '2', '0', '49', '2020-05-10 15:15:57', '2020-05-10 15:15:57', '49', '');
INSERT INTO `user_group_rel` VALUES ('17', '64', '2', '0', '60', '2020-05-10 20:42:55', '2020-07-09 11:37:38', '60', '');
INSERT INTO `user_group_rel` VALUES ('18', '65', '2', '0', '60', '2020-07-09 11:41:03', '2020-07-09 11:41:59', '60', '');
INSERT INTO `user_group_rel` VALUES ('19', '66', '2', '0', '60', '2020-08-03 14:37:16', '2020-08-03 14:37:16', '60', '');
