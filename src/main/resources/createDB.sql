DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_name` varchar(16) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

INSERT INTO `t_student` VALUES ('1', '王小军', '1', '17', '北京市东城区');
INSERT INTO `t_student` VALUES ('2', '李雷雷', '1', '16', '北京市朝阳区');
INSERT INTO `t_student` VALUES ('3', '张静', '2', '16', '北京市昌平区');
INSERT INTO `t_student` VALUES ('4', '王晓萌', '2', '17', '北京市顺义区');
INSERT INTO `t_student` VALUES ('5', '韩梅梅', '2', '16', '北京市朝阳区');
INSERT INTO `t_student` VALUES ('6', '李小军', '1', '17', '北京市海淀区');
INSERT INTO `t_student` VALUES ('7', '成龙', '1', '16', '北京市石景山区');
INSERT INTO `t_student` VALUES ('8', '李海飞', '2', '16', '北京市海淀区');
INSERT INTO `t_student` VALUES ('9', '罗红', '2', '16', '北京市朝阳区');
INSERT INTO `t_student` VALUES ('10', '孙海杰', '1', '16', '北京市石景山区');
INSERT INTO `t_student` VALUES ('11', '王海龙', '1', '16', '北京市东城区');