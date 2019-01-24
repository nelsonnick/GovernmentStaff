SET FOREIGN_KEY_CHECKS=1;

-- ----------------------------
-- Table structure for Department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `szcs` varchar(255) DEFAULT NULL COMMENT '所在城市',
  `dwzd` varchar(255) DEFAULT NULL COMMENT '单位驻地',
  `dwlb` varchar(255) DEFAULT NULL COMMENT '单位类别',
  `dwlx` varchar(255) DEFAULT NULL COMMENT '单位类型',
  `sjdw` varchar(255) DEFAULT NULL COMMENT '上级单位',
  `dwbh` varchar(255) DEFAULT NULL COMMENT '单位编号',
  `dwmc` varchar(255) DEFAULT NULL COMMENT '单位名称',
  `qtmc` varchar(255) DEFAULT NULL COMMENT '其他名称',
  `ldzs` varchar(255) DEFAULT NULL COMMENT '领导职数',
  `jb` varchar(255) DEFAULT NULL COMMENT '级别',
  `nsjg` varchar(7999) DEFAULT NULL COMMENT '内设机构',
  `zyzz` varchar(7999) DEFAULT NULL COMMENT '主要职责',
  `xz_plan_num` varchar(255) DEFAULT NULL COMMENT '行政编制数',
  `xz_real_num` varchar(255) DEFAULT NULL COMMENT '行政实际数',
  `xz_lone_num` varchar(255) DEFAULT NULL COMMENT '行政单列数',
  `sy_plan_num` varchar(255) DEFAULT NULL COMMENT '事业编制数',
  `sy_real_num` varchar(255) DEFAULT NULL COMMENT '事业实际数',
  `sy_lone_num` varchar(255) DEFAULT NULL COMMENT '事业单列数',
  `gq_plan_num` varchar(255) DEFAULT NULL COMMENT '工勤编制数',
  `gq_real_num` varchar(255) DEFAULT NULL COMMENT '工勤实际数',
  `gq_lone_num` varchar(255) DEFAULT NULL COMMENT '工勤单列数',
  `url` varchar(255) DEFAULT NULL COMMENT '访问网址',
  `time` varchar(255) DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for department_err
-- ----------------------------
DROP TABLE IF EXISTS `department_err`;
CREATE TABLE `department_err` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `szcs` varchar(255) DEFAULT NULL COMMENT '所在城市',
  `dwzd` varchar(255) DEFAULT NULL COMMENT '单位驻地',
  `dwlb` varchar(255) DEFAULT NULL COMMENT '单位类别',
  `dwlx` varchar(255) DEFAULT NULL COMMENT '单位类型',
  `sjdw` varchar(255) DEFAULT NULL COMMENT '上级单位',
  `dwbh` varchar(255) DEFAULT NULL COMMENT '单位编号',
  `dwmc` varchar(255) DEFAULT NULL COMMENT '单位名称',
  `base` varchar(255) DEFAULT NULL COMMENT '基础网址',
  `time` varchar(255) DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for Person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `szcs` varchar(255) DEFAULT NULL COMMENT '所在城市',
  `dwzd` varchar(255) DEFAULT NULL COMMENT '单位驻地',
  `dwlb` varchar(255) DEFAULT NULL COMMENT '单位类别',
  `dwlx` varchar(255) DEFAULT NULL COMMENT '单位类型',
  `sjdw` varchar(255) DEFAULT NULL COMMENT '上级单位',
  `dwbh` varchar(255) DEFAULT NULL COMMENT '单位编号',
  `dwmc` varchar(255) DEFAULT NULL COMMENT '单位名称',
  `ssbm` varchar(255) DEFAULT NULL COMMENT '所属部门',
  `ryxm` varchar(255) DEFAULT NULL COMMENT '人员姓名',
  `ryxb` varchar(255) DEFAULT NULL COMMENT '人员性别',
  `bzlx` varchar(255) DEFAULT NULL COMMENT '编制类型',
  `bzqk` varchar(255) DEFAULT NULL COMMENT '编制情况',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for person_err
-- ----------------------------
DROP TABLE IF EXISTS `person_err`;
CREATE TABLE `person_err` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `szcs` varchar(255) DEFAULT NULL COMMENT '所在城市',
  `dwzd` varchar(255) DEFAULT NULL COMMENT '单位驻地',
  `dwlb` varchar(255) DEFAULT NULL COMMENT '单位类别',
  `dwlx` varchar(255) DEFAULT NULL COMMENT '单位类型',
  `sjdw` varchar(255) DEFAULT NULL COMMENT '上级单位',
  `dwbh` varchar(255) DEFAULT NULL COMMENT '单位编号',
  `dwmc` varchar(255) DEFAULT NULL COMMENT '单位名称',
  `bzlx` varchar(255) DEFAULT NULL COMMENT '编制类型',
  `base` varchar(255) DEFAULT NULL COMMENT '基础网址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for advice
-- ----------------------------
DROP TABLE IF EXISTS `advice`;
CREATE TABLE `advice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(255) DEFAULT NULL COMMENT '识别号码',
  `advice` varchar(255) DEFAULT NULL COMMENT '建议内容',
  `name` varchar(255) DEFAULT NULL COMMENT '人员姓名',
  `phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '提交时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for jsonstr
-- ----------------------------
DROP TABLE IF EXISTS `jsonstr`;
CREATE TABLE `jsonstr` (
  `i` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(255) DEFAULT NULL COMMENT '本级序号',
  `pid` varchar(255) DEFAULT NULL COMMENT '上级序号',
  `name` varchar(255) DEFAULT NULL COMMENT '单位名称',
  `dwbh` varchar(255) DEFAULT NULL COMMENT '单位编号',
  PRIMARY KEY (`i`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;