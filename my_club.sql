/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : my_club

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 14/12/2021 20:00:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department_table
-- ----------------------------
DROP TABLE IF EXISTS `department_table`;
CREATE TABLE `department_table`  (
  `department_id` int NOT NULL,
  `department_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department_brief` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`department_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of department_table
-- ----------------------------
INSERT INTO `department_table` VALUES (1, '所有部', '会长专属');
INSERT INTO `department_table` VALUES (2, '组织部', '发布活动');
INSERT INTO `department_table` VALUES (3, '宣传部', '撰写档案');
INSERT INTO `department_table` VALUES (4, '外联部', '管理经费');

-- ----------------------------
-- Table structure for event_table
-- ----------------------------
DROP TABLE IF EXISTS `event_table`;
CREATE TABLE `event_table`  (
  `event_id` int NOT NULL AUTO_INCREMENT,
  `place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_end_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `finish_flag` tinyint NULL DEFAULT NULL,
  `checked_y` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `checked_n` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件名',
  `type_flag` tinyint NULL DEFAULT NULL COMMENT '0 活动  1 会议',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`event_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of event_table
-- ----------------------------
INSERT INTO `event_table` VALUES (1, '校园北门口', '2021/12/09-2021/12/11', '测试一下该活动的修改', 0, '', '1;', '杂草志愿者活动', 0, '2021-12-14 19:41:52');
INSERT INTO `event_table` VALUES (2, '学校三食堂门口', '2021/11/29-2021/12/4', '收集弃置书本与衣物以及空的易拉罐和矿泉水瓶等，空的易拉罐以及水瓶等大量收集后卖给废品商，赚取钱财用以购置贫困地区儿童用品，书本与衣物可以直接捐献过去供贫困地区同胞使用。', 1, NULL, NULL, '爱心捐献活动', 0, '2021-12-12 20:21:44');
INSERT INTO `event_table` VALUES (3, '学校各个食堂出口', '2021/11/1-2021/12/1', '志愿者在各个食堂门口摆好摊位，分时段值班，该活动只有在食堂用餐的同学才可以参加，打包食物的人无法参加。吃完饭的同学将空的餐盘给志愿者查看后打卡盖章。根据盖章多少给予不同的奖励', 0, NULL, '2;', '光盘行动', 0, '2021-12-11 17:00:42');
INSERT INTO `event_table` VALUES (4, '学校外自助餐厅', '2021/11/20-2021/11-20', '协会使用活动经费自行开展活动，协会成员可以自愿参加或者不参加。不参加的人按照日常生活正常活动即可，没有额外要求。参与者同一时间到学校北门集合后前往聚餐。聚餐后其他活动同样可以自行选择参加与否', 0, NULL, '2;', '协会集体活动', 0, '2021-12-12 20:21:37');
INSERT INTO `event_table` VALUES (5, '学校玉兰路', '2021/11/6-2021/11/7', '几个同学在早些时候将器械搬运至活动地点然后进行帐篷搭建与器材安装等。分时段安排成员进行值班，引导过往同学参与活动，同时招募一些志愿者。不同的活动可以获得不同类型的学时。进行记载后统一发放学时', 1, NULL, NULL, '消防知识学习活动', 0, '2021-12-03 20:42:24');
INSERT INTO `event_table` VALUES (6, '学校南北门入口', '2021/11/27-2021/11/27', '在学校的北门和南门门口分别安排一些同学，指引其他学校来参与竞赛的队伍路径，以及车辆停放等问题，并提供矿泉水等。使前来竞赛的队伍感到宾至如归。', 0, NULL, '2;', '给来宾指路并答疑', 0, '2021-12-12 20:21:39');
INSERT INTO `event_table` VALUES (7, '学校南北门入口', '2021/11/27-2021/11/27', '在学校的北门和南门门口分别安排一些同学，指引其他学校来参与竞赛的队伍路径，以及车辆停放等问题，并提供矿泉水等。使前来竞赛的队伍感到宾至如归。', 0, NULL, '2;', '给来宾指路并答疑', 0, '2021-12-12 20:21:40');
INSERT INTO `event_table` VALUES (8, '学校逸夫楼', '2021/12/15-2021/12/15', '青年是实现中国梦的先锋力量，为了让身为青年的同学们更加深刻得了解新思想。宣传部于逸夫楼开展本活动', 0, NULL, NULL, '新思想理论演讲', 0, '2021-12-12 13:19:00');
INSERT INTO `event_table` VALUES (9, '线上进行', '2021/12/15-2021/12/15', '活动以线上答疑的形式展开，针对协会成员反应的困难点由志愿者进行解答，以解决日常学习的困难', 0, NULL, NULL, '协会内辅学活动', 0, '2021-12-12 13:20:48');
INSERT INTO `event_table` VALUES (10, '校内井盖以及石制圆球', '2021/12/14-2021/12/14', '向全校同学收集绘画投稿并进行评分工作，评选出一二三等奖。获奖同学联通宣传部工作人员对校内井盖与石球涂鸦', 0, NULL, NULL, '涂鸦设计活动', 0, '2021-12-12 13:20:54');
INSERT INTO `event_table` VALUES (11, '校外小学', '2021/12/13-2021/12/13', '每周一去给小学的学生们进行义务教育活动，给他们普及消防安全知识以及消防安全意识。使其对消防产生好感', 0, NULL, '2;', '小学义教志愿者', 0, '2021-12-12 20:46:29');
INSERT INTO `event_table` VALUES (12, '线上进行', '2021/12/13-2021/12/14', '在繁重的课业中，与书籍进行一次触及心灵的交流，选择一本喜爱的书目并写一篇推荐它的评价。', 0, NULL, NULL, '读书分享会', 0, '2021-12-12 13:24:57');
INSERT INTO `event_table` VALUES (13, '协会会议室', '2021/12/7-2021/12/7', '由组织部成员将学期内的各项收入与支出数据详细列出供组织成员了解', 0, '1;5;6;2;3;9;11;12;15;20;21;', NULL, '收支透明', 1, '2021-12-14 19:28:26');
INSERT INTO `event_table` VALUES (14, '教四楼', '2021/12/15-2021/12/15', '协会成员自愿竞选，各自宣讲演讲，演讲完毕后由各个到场成员投票评选会长以及部长', 0, '1;5;6;2;3;9;11;12;15;20;21;', NULL, '职位评选', 1, '2021-12-14 19:28:27');
INSERT INTO `event_table` VALUES (15, '教四楼', '2021/9/7-2021/9/7', '协会成员中各个部门分别投票评选优秀干事以及部长', 0, '1;5;6;2;3;9;11;12;15;20;21;', NULL, '部门评选', 1, '2021-12-14 19:28:28');
INSERT INTO `event_table` VALUES (16, '协会会议室', '2021/12/21-2021/12/21', '由协会会长演讲PPT，宣告部门发展计划。同时协会各个成员可以发表意见，择其善者而从之，其不善者而改之。', 0, '1;5;6;', '2;3;9;11;12;15;20;21;', '发展规划', 1, '2021-12-14 19:28:46');
INSERT INTO `event_table` VALUES (17, '协会会议室', '2021-12/17-2021/12/17', '各个部门的部长各自准备PPT与演讲稿，对成员宣告这一学期以来部门的活动以及取得成就等等。增强社团凝聚力。', 0, '1;5;6;', '2;3;9;11;12;15;20;21;', '回望成就', 1, '2021-12-14 19:29:01');
INSERT INTO `event_table` VALUES (19, '随机地点', '2021/12/14-2021/12/14', '全员要刀叉', 0, '2;5;6;9;11;', '1;3;12;13;15;20;21;', '新添加一个会议', 1, '2021-12-14 19:52:53');

-- ----------------------------
-- Table structure for fund_table
-- ----------------------------
DROP TABLE IF EXISTS `fund_table`;
CREATE TABLE `fund_table`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` tinyint NULL DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amount` int NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fund_table
-- ----------------------------
INSERT INTO `fund_table` VALUES (1, 0, '修改一下第一条经费条目', 2021, '2021-12-14 19:55:15');
INSERT INTO `fund_table` VALUES (3, 1, '采购设备', 500, '2021-12-01 17:27:31');
INSERT INTO `fund_table` VALUES (4, 1, '社团团建', 200, '2021-12-01 17:27:33');
INSERT INTO `fund_table` VALUES (5, 1, '校内活动举办支出', 300, '2021-12-01 17:27:35');
INSERT INTO `fund_table` VALUES (6, 1, '发放社团福利', 300, '2021-12-01 17:27:38');
INSERT INTO `fund_table` VALUES (7, 1, '期末总结活动', 200, '2021-12-01 17:27:40');
INSERT INTO `fund_table` VALUES (8, 0, '学校发放经费', 2000, '2021-12-16 17:27:42');
INSERT INTO `fund_table` VALUES (9, 0, '社团成员自行捐赠', 500, '2021-12-03 17:27:45');
INSERT INTO `fund_table` VALUES (10, 0, '外企赞助', 1000, '2021-12-08 17:27:50');
INSERT INTO `fund_table` VALUES (11, 0, '社团服务报酬', 200, '2021-12-07 17:27:54');
INSERT INTO `fund_table` VALUES (17, 1, '测试添加一条经费', 2021, '2021-12-14 19:56:14');

-- ----------------------------
-- Table structure for info_table
-- ----------------------------
DROP TABLE IF EXISTS `info_table`;
CREATE TABLE `info_table`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `info_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `detail` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of info_table
-- ----------------------------
INSERT INTO `info_table` VALUES (1, '社团简介', '消防协会隶属于学院的团委，是学校团委领导下和保卫处指导下的学生社团组织。本协会遵照国家宪法和有关政策，法令，校纪校规的规定，在学校党委和行政领导下，在校保卫处的具体指导下，团结和组织学生消防积极分子，充分发挥大学生参加活动积极，文化程度高，工作针对性强，富于创造力等特点，积极开展消防宣传教育，抢险救灾演练，在大学生中普及消防知识，进行初期火灾扑救和相关活动的执勤，提高大学生总体防范水平和自我管理能力，为保障大学生生命财产安全、学院发展和稳定服务。我们的宗旨是：宣传消防知识，增强消防意识，掌握消防技能，防范消防事故。', '2021-12-14 19:44:54');
INSERT INTO `info_table` VALUES (2, '社团条例', '1.为了保障学生的结社自由，维护社团的合法权益，加强对团体的登记管理，促进社会主义物质文明、精神文明建设，制定本条例。\r\n2.由学委机构编制管理机关核定，并经学委批准免于登记的团体。\r\n3.社团不得从事营利性经营活动。\r\n4.社团必须遵守国家法律法规以及我校相关规定。', '2021-12-11 11:08:12');

-- ----------------------------
-- Table structure for member_table
-- ----------------------------
DROP TABLE IF EXISTS `member_table`;
CREATE TABLE `member_table`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `position_id` int NULL DEFAULT NULL,
  `gender` tinyint NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department_id` int NULL DEFAULT NULL,
  `profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `in_club` tinyint NULL DEFAULT NULL,
  `creat_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `qq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `position_id`(`position_id`) USING BTREE,
  INDEX `department_id`(`department_id`) USING BTREE,
  UNIQUE INDEX `qq`(`qq`) USING BTREE,
  CONSTRAINT `department_id` FOREIGN KEY (`department_id`) REFERENCES `department_table` (`department_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `position_id` FOREIGN KEY (`position_id`) REFERENCES `position_table` (`position_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member_table
-- ----------------------------
INSERT INTO `member_table` VALUES (1, 1, 0, '火钳', '192017213', '计科1220', 1, '热爱编程，热爱加班。', '17816778541', 1, '2021-12-09 00:09:15', '2021-12-14 19:58:40', '17816778541', '123456');
INSERT INTO `member_table` VALUES (2, 9, 0, '刘强', '115019310', '人工智能1930', 4, '很喜欢读书', '17707956321', 1, '2021-12-01 18:19:45', '2021-12-14 19:58:40', '17816778542', '123456');
INSERT INTO `member_table` VALUES (3, 9, 0, '张三', '115019322', '人工智能1930', 2, '喜欢看书、打电动、看漫画、游山玩水', '18365471922', 1, '2021-12-01 18:19:48', '2021-12-12 09:08:46', '17816778543', '123456');
INSERT INTO `member_table` VALUES (4, 1, 0, '赵谨辰', '114085397', '计科1910', 1, '性格开朗，待人友好，为人诚实谦虚。工作勤奋，认真负责，能吃苦耐劳，尽职尽责，有耐心。具有亲和力，平易近人，善于与人沟通。', '18359475246', 0, '2021-12-01 18:20:25', '2021-12-12 09:31:41', '17878945147', '123456');
INSERT INTO `member_table` VALUES (5, 2, 1, '钱白', '168074354', '动医1910', 1, '性格开朗，待人友好，为人诚实谦虚。工作勤奋，认真负责，能吃苦耐劳，尽职尽责，有耐心。具有亲和力，平易近人，善于与人沟通。', '18354876821', 1, '2021-12-01 17:52:11', '2021-12-12 08:49:34', '13313578945', '123456');
INSERT INTO `member_table` VALUES (6, 3, 0, '孙子骞', '158046754', '网工1910', 2, '性格开朗，待人友好，为人诚实谦虚。工作勤奋，认真负责，能吃苦耐劳，尽职尽责，有耐心。具有亲和力，平易近人，善于与人沟通。', '18352487954', 1, '2021-12-01 17:52:13', '2021-12-12 08:49:38', '14325687954', '123456');
INSERT INTO `member_table` VALUES (7, 4, 0, '李正阳', '13526784', '园艺1910', 4, '性格开朗，待人友好，为人诚实谦虚。工作勤奋，认真负责，能吃苦耐劳，尽职尽责，有耐心。具有亲和力，平易近人，善于与人沟通。', '13857462148', 1, '2021-12-01 17:52:14', '2021-12-14 19:51:53', '12213578945', '123456');
INSERT INTO `member_table` VALUES (8, 5, 1, '周冉萱', '11568749', '计科1910', 2, '性格开朗，待人友好，为人诚实谦虚。工作勤奋，认真负责，能吃苦耐劳，尽职尽责，有耐心。具有亲和力，平易近人，善于与人沟通。', '15974689741', 1, '2021-12-01 17:52:16', '2021-12-14 19:51:57', '11713546789', '123456');
INSERT INTO `member_table` VALUES (9, 5, 1, '吴温谨', '13247897', '园艺1910', 3, '性格开朗，待人友好，为人诚实谦虚。工作勤奋，认真负责，能吃苦耐劳，尽职尽责，有耐心。具有亲和力，平易近人，善于与人沟通。', '18147952147', 1, '2021-12-01 18:21:07', '2021-12-12 08:49:00', '13812378945', '123456');
INSERT INTO `member_table` VALUES (10, 7, 1, '郑悠兰', '11524867', '计科1910', 3, '性格开朗，待人友好，为人诚实谦虚。工作勤奋，认真负责，能吃苦耐劳，尽职尽责，有耐心。具有亲和力，平易近人，善于与人沟通。', '13674219751', 0, '2021-12-01 17:52:20', '2021-12-12 08:49:01', '14713567894', '123456');
INSERT INTO `member_table` VALUES (11, 8, 0, '王七麟', '15746894', '网工1930', 3, '性格开朗，待人友好，为人诚实谦虚。工作勤奋，认真负责，能吃苦耐劳，尽职尽责，有耐心。具有亲和力，平易近人，善于与人沟通。', '18167984213', 1, '2021-12-01 18:21:10', '2021-12-12 08:49:02', '17585123876', '123456');
INSERT INTO `member_table` VALUES (12, 9, 0, '冯英勋', '15547886', '网工1920', 2, '性格开朗，待人友好，为人诚实谦虚。工作勤奋，认真负责，能吃苦耐劳，尽职尽责，有耐心。具有亲和力，平易近人，善于与人沟通。', '17321478541', 1, '2021-12-01 17:52:25', '2021-12-14 19:27:10', '14978943125', '123456');
INSERT INTO `member_table` VALUES (13, 9, 1, '李四', '19319401', '动医1940', 2, '邮箱:123456@qq.com\\n热爱跑步，热爱游戏。特长是打篮球', '17321478541', 1, '2021-12-12 21:59:38', '2021-12-12 21:59:38', '12300612306', '123456');
INSERT INTO `member_table` VALUES (15, 9, 1, '李五', '19319401', '动医1940', 2, '邮箱:123456@qq.com\\n热爱跑步，热爱游戏。特长是打篮球', '12300612306', 1, '2021-12-11 08:10:58', '2021-12-12 11:37:46', '2545531794', '123456');
INSERT INTO `member_table` VALUES (16, 9, 0, '李安澜', '11489445', '动医1910', 2, '邮箱：1007322242@qq.com\\n 喜欢看书，听音乐，看风景。能够较好地处理与同学之间的人际关系，组织能力较强', '18359754876', 2, '2021-12-11 08:11:03', '2021-12-12 08:49:06', '1007322242', '123456');
INSERT INTO `member_table` VALUES (17, 9, 1, '姜红芍', '19865784', '园艺1930', 2, '邮箱：1558764971@qq.com\\n  具有良好的绘画能力与文学功底，在中学生活中表现出色。相信自己能够承担一些责任', '13867459872', 2, '2021-12-11 08:11:06', '2021-12-12 08:49:06', '1558764971', '123456');
INSERT INTO `member_table` VALUES (18, 9, 0, '虞轩', '14562175', '生科2010', 3, '邮箱：1657849364@qq.com \\n  从小表现出较强的学习模仿能力，能够很快的学习完成一些相对重要的工作。', '15798458716', 2, '2021-12-11 08:11:08', '2021-12-12 08:49:08', '1657849364', '123456');
INSERT INTO `member_table` VALUES (19, 9, 1, '赵匡寅', '13789452', '食品1920', 4, '邮箱：1058796825@qq.com \\n  具有自来熟的性质，能够很快的与陌生人打成一片，自信能够胜任外联部的工作', '18575136479', 2, '2021-12-11 08:11:10', '2021-12-12 08:49:14', '1058796825', '123456');
INSERT INTO `member_table` VALUES (20, 9, 0, '冯英', '15547886', '网工1920', 2, '性格开朗，待人友好，为人诚实谦虚。工作勤奋，认真负责，能吃苦耐劳，尽职尽责，有耐心。具有亲和力，平易近人，善于与人沟通。', '17321478541', 1, '2021-12-01 17:52:25', '2021-12-14 19:27:07', '14978943126', '123456');
INSERT INTO `member_table` VALUES (21, 9, 0, '冯英逊', '15547886', '网工1910', 2, '性格开朗，待人友好，为人诚实谦虚。工作勤奋，认真负责，能吃苦耐劳，尽职尽责，有耐心。具有亲和力，平易近人，善于与人沟通。', '17321478541', 1, '2021-12-01 17:52:25', '2021-12-12 09:32:11', '14978943127', '123456');
INSERT INTO `member_table` VALUES (22, 9, 0, '测试12', '11519108', '人工智能191', 2, '测试一下注册', '17707956321', 1, '2021-12-14 19:57:07', '2021-12-14 19:57:57', '2745531794', '123456');

-- ----------------------------
-- Table structure for notice_table
-- ----------------------------
DROP TABLE IF EXISTS `notice_table`;
CREATE TABLE `notice_table`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `detail` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice_table
-- ----------------------------
INSERT INTO `notice_table` VALUES (1, '不需要去站岗', '站岗2', '2021-12-14 19:45:51');
INSERT INTO `notice_table` VALUES (2, '开展消防知识竞赛，以增强校内同学的消防安全意识，使其在可能到来的火灾等灾害中获得尽可能高的逃生率，以及一些拯救他人的能力。', '维护场内秩序', '2021-12-01 17:26:47');
INSERT INTO `notice_table` VALUES (3, '举办团建活动，成员自行选择是否参与。不参加的人按照日常生活正常活动即可，没有额外要求。参与者同一时间到学校北门集合后前往聚餐。聚餐后其他活动同样可以自行选择参加与否', '参加活动外出游玩', '2021-12-01 17:26:49');
INSERT INTO `notice_table` VALUES (4, '在早些时候将器械搬运至活动地点然后进行帐篷搭建与器材安装等。以使后续的活动能够正常顺利地进行下去。', '搬运必要器械', '2021-12-01 17:26:52');
INSERT INTO `notice_table` VALUES (8, '每周末都要进行一次大扫除', '大扫除', '2021-12-11 11:15:27');
INSERT INTO `notice_table` VALUES (10, '测试123', '测试添加一个公告', '2021-12-14 19:46:42');

-- ----------------------------
-- Table structure for position_table
-- ----------------------------
DROP TABLE IF EXISTS `position_table`;
CREATE TABLE `position_table`  (
  `position_id` int NOT NULL AUTO_INCREMENT,
  `position_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `right_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`position_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of position_table
-- ----------------------------
INSERT INTO `position_table` VALUES (1, '会长', '0');
INSERT INTO `position_table` VALUES (2, '副会长', '0');
INSERT INTO `position_table` VALUES (3, '组织部部长', '1');
INSERT INTO `position_table` VALUES (4, '组织部副部长', '1');
INSERT INTO `position_table` VALUES (5, '宣传部部长', '2');
INSERT INTO `position_table` VALUES (6, '宣传部副部长', '2');
INSERT INTO `position_table` VALUES (7, '外联部部长', '4');
INSERT INTO `position_table` VALUES (8, '外联部副部长', '4');
INSERT INTO `position_table` VALUES (9, '普通成员', '7');

-- ----------------------------
-- View structure for mempos
-- ----------------------------
DROP VIEW IF EXISTS `mempos`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `mempos` AS select `member_table`.`id` AS `user_id`,`member_table`.`user_name` AS `user_name`,`position_table`.`position_name` AS `pos_name`,`position_table`.`right_level` AS `right_level`,`position_table`.`position_id` AS `pos_id` from (`member_table` join `position_table`) where (`member_table`.`position_id` = `position_table`.`position_id`);

-- ----------------------------
-- View structure for memposdep
-- ----------------------------
DROP VIEW IF EXISTS `memposdep`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `memposdep` AS select `member_table`.`id` AS `id`,`member_table`.`user_name` AS `user_name`,`member_table`.`in_club` AS `in_club`,`position_table`.`position_id` AS `position_id`,`position_table`.`position_name` AS `position_name`,`position_table`.`right_level` AS `right_level`,`department_table`.`department_id` AS `department_id`,`department_table`.`department_name` AS `department_name` from ((`member_table` join `department_table`) join `position_table`) where ((`member_table`.`position_id` = `position_table`.`position_id`) and (`member_table`.`department_id` = `department_table`.`department_id`));

-- ----------------------------
-- Triggers structure for table member_table
-- ----------------------------
DROP TRIGGER IF EXISTS `mem_insert_trigger`;
delimiter ;;
CREATE TRIGGER `mem_insert_trigger` BEFORE INSERT ON `member_table` FOR EACH ROW BEGIN
	IF(new.gender > 1)
-- 	抛出异常
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "gender value error";
	END IF;
	IF(new.in_club > 2)
-- 	抛出异常
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "in_club value error";
	END IF;
	IF(LENGTH(new.telephone)!=11)
	-- 	抛出异常
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "telephone length error";
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table member_table
-- ----------------------------
DROP TRIGGER IF EXISTS `mem_update_trigger`;
delimiter ;;
CREATE TRIGGER `mem_update_trigger` BEFORE UPDATE ON `member_table` FOR EACH ROW BEGIN
	IF(new.gender > 1)
-- 	抛出异常
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "gender value error";
	END IF;
	IF(new.in_club > 2)
-- 	抛出异常
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "in_club value error";
	END IF;
	IF(LENGTH(new.telephone)!=11)
	-- 	抛出异常
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "telephone length error";
	END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
