/*
Navicat MySQL Data Transfer

Source Server         : localhost_3307
Source Server Version : 50547
Source Host           : localhost:3307
Source Database       : goods

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2017-04-26 10:11:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `T_Admin`
-- ----------------------------
DROP TABLE IF EXISTS `T_Admin`;
CREATE TABLE `T_Admin` (
  `adminId` char(32) NOT NULL,
  `adminname` varchar(50) DEFAULT NULL,
  `adminpwd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of T_Admin
-- ----------------------------
INSERT INTO `T_Admin` VALUES ('a4', 'Shayne', 'admin');
INSERT INTO `T_Admin` VALUES ('a5', 'root', 'admin');

-- ----------------------------
-- Table structure for `T_Book`
-- ----------------------------
DROP TABLE IF EXISTS `T_Book`;
CREATE TABLE `T_Book` (
  `bid` char(32) NOT NULL,
  `bname` varchar(200) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `price` decimal(8,2) DEFAULT NULL,
  `currPrice` decimal(8,2) DEFAULT NULL,
  `discount` decimal(3,1) DEFAULT NULL,
  `press` varchar(100) DEFAULT NULL,
  `publishtime` char(10) DEFAULT NULL,
  `edition` int(11) DEFAULT NULL,
  `pageNum` int(11) DEFAULT NULL,
  `wordNum` int(11) DEFAULT NULL,
  `printtime` char(10) DEFAULT NULL,
  `booksize` int(11) DEFAULT NULL,
  `paper` varchar(50) DEFAULT NULL,
  `cid` char(32) DEFAULT NULL,
  `image_w` varchar(100) DEFAULT NULL,
  `image_b` varchar(100) DEFAULT NULL,
  `orderBy` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`bid`),
  KEY `orderBy` (`orderBy`),
  KEY `FK_T_Book_T_Category` (`cid`),
  CONSTRAINT `FK_T_Book_T_Category` FOREIGN KEY (`cid`) REFERENCES `T_Category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of T_Book
-- ----------------------------
INSERT INTO `T_Book` VALUES ('0EE8A0AE69154287A378FB110FF2C780', 'Java核心技术：卷Ⅰ基础知识（原书第8版）', '昊斯特曼', '98.00', '67.60', '6.9', '机械工业出版社', '2008-6-1', '1', '694', '0', '2008-6-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20285763-1_w.jpg', 'book_img/20285763-1_b.jpg', '12');
INSERT INTO `T_Book` VALUES ('113D0D1BB9174DD19A7DE7E2B37F677F', 'Java7入门经典（跟编程导师Ivor Horton学Java 预计到货日期9月初）', '霍尔顿', '118.00', '81.40', '6.9', '清华大学出版社', '2012-8-1', '1', '1124', '1918000', '2012-8-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22813026-1_w.jpg', 'book_img/22813026-1_b.jpg', '94');
INSERT INTO `T_Book` VALUES ('1286B13F0EA54E4CB75434762121486A', 'Java核心技术 卷I：基础知识(第9版·英文版)(上、下册)', '霍斯特曼', '109.00', '75.20', '6.9', '人民邮电出版社', '2013-7-1', '1', '0', '1197000', '2013-7-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23280479-1_w.jpg', 'book_img/23280479-1_b.jpg', '65');
INSERT INTO `T_Book` VALUES ('13EBF9B1FDE346A683A1C45BD6773ECB', 'Java开发实战1200例（第Ⅱ卷）（史上最全的“编程实例”类图书，代码分析、实例速查、练习巩固的绝好帮手）', '无', '99.00', '68.30', '6.9', '清华大学出版社', '0', '1', '0', '1754000', '2011-6-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/21110930-1_w_1.jpg', 'book_img/21110930-1_b.jpg', '83');
INSERT INTO `T_Book` VALUES ('1A15DC5E8A014A58862ED741D579B530', 'Java深入解析——透析Java本质的36个话题', '梁勇', '49.00', '33.80', '6.9', '电子工业出版社', '2013-11-1', '1', '298', '424000', '2013-11-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23363997-1_w_1.jpg', 'book_img/23363997-1_b.jpg', '86');
INSERT INTO `T_Book` VALUES ('210A3DCA429049C78B623C3986BEB136', 'JavaScript经典教程套装：JavaScript高级程序设计(第3版)+JavaScript DOM编程艺术(第2版)(套装共2册)(超值附赠《码农》光盘1张)', 'Nicholas C. Zakas', '148.00', '88.80', '6.0', '人民邮电出版社', '2013-4-1', '1', '1048', '0', '2013-4-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23224089-1_w.jpg', 'book_img/23224089-1_b.jpg', '93');
INSERT INTO `T_Book` VALUES ('22234CECF15F4ECB813F0B433DDA5365', 'JavaScript从入门到精通（附光盘1张）（连续8月JavaScript类全国零售排行前2名，13小时视频，400个经典实例、369项面试真题、138项测试史上最全资源库）', '明日科技', '69.80', '48.20', '6.9', '清华大学出版社', '2012-9-1', '1', '532', '939000', '2012-9-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22862057-1_w.jpg', 'book_img/22862057-1_b.jpg', '20');
INSERT INTO `T_Book` VALUES ('230A00EC22BF4A1DBA87C64800B54C8D', 'Struts2技术内幕：深入解析Struts架构设计与实现原理', '陆舟', '69.00', '47.60', '6.9', '机械工业出版社', '2012-1-1', '1', '379', '0', '2012-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22577578-1_w.jpg', 'book_img/22577578-1_b.jpg', '56');
INSERT INTO `T_Book` VALUES ('260F8A3594F741C1B0EB69616F65045B', 'Tomcat与Java Web开发技术详解（第2版）(含光盘1张)', '孙卫琴', '79.50', '54.90', '6.9', '电子工业出版社', '2009-1-1', '1', '734', '1216000', '2009-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20420983-1_w.jpg', 'book_img/20420983-1_b.jpg', '74');
INSERT INTO `T_Book` VALUES ('28A03D28BAD449659A77330BE35FCD65', 'JAVA核心技术卷II：高级特性（原书第8版）', '霍斯特曼', '118.00', '81.40', '6.9', '机械工业出版社', '2008-12-1', '1', '852', '0', '2008-12-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20446562-1_w.jpg', 'book_img/20446562-1_b.jpg', '18');
INSERT INTO `T_Book` VALUES ('2EE1A20A6AF742E387E18619D7E3BB94', 'Java虚拟机并发编程(Java并发编程领域的里程碑之作，资深Java技术专家、并发编程专家、敏捷开发专家和Jolt大奖得主撰写，Amazon五星畅销书)', 'Venkat Subramaniam', '59.00', '40.70', '6.9', '机械工业出版社', '2013-5-1', '1', '215', '0', '2013-5-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23239786-1_w.jpg', 'book_img/23239786-1_b.jpg', '54');
INSERT INTO `T_Book` VALUES ('33ACF97A9A374352AE9F5E89BB791262', '基于MVC的JavaScript Web富应用开发', '麦卡劳', '59.00', '40.70', '6.9', '电子工业出版社', '2012-5-1', '1', '282', '462000', '2012-5-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22757564-1_w.jpg', 'book_img/22757564-1_b.jpg', '34');
INSERT INTO `T_Book` VALUES ('37F75BEAE1FE46F2B14674923F1E7987', '数据结构与算法分析Java语言描述 第2版', '韦斯', '55.00', '38.00', '6.9', '机械工业出版社', '2009-1-1', '2', '440', '0', '2009-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20417467-1_w.jpg', 'book_img/20417467-1_b.jpg', '32');
INSERT INTO `T_Book` VALUES ('39F1D0803E8F4592AE1245CACE683214', 'Java程序员修炼之道', '埃文斯', '89.00', '61.40', '6.9', '人民邮电出版社', '2013-8-1', '1', '395', '658000', '2013-8-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23301847-1_w_1.jpg', 'book_img/23301847-1_b.jpg', '36');
INSERT INTO `T_Book` VALUES ('3AE5C8B976B6448A9D3A155C1BDE12BC', '深入理解Java虚拟机:JVM高级特性与最佳实践（超级畅销书，6个月5次印刷，从实践角度解析JVM工作原理，Java程序员必备）', '周志明', '69.00', '47.60', '6.9', '机械工业出版社', '0', '1', '0', '0', '2011-7-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/21108671-1_w_1.jpg', 'book_img/21108671-1_b.jpg', '66');
INSERT INTO `T_Book` VALUES ('3DD187217BF44A99B86DD18A4DC628BA', 'Java核心技术 卷1 基础知识（原书第9版）', '霍斯特曼，科内尔', '119.00', '82.10', '6.9', '机械工业出版社', '2014-1-1', '1', '704', '0', '2014-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23362142-1_w_1.jpg', 'book_img/23362142-1_b.jpg', '9');
INSERT INTO `T_Book` VALUES ('3E1990E19989422E9DA735978CB1E4CE', 'Effective Java中文版(第2版)', '布洛克', '52.00', '35.90', '6.9', '机械工业出版社', '2009-1-1', '2', '287', '0', '2009-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20459091-1_w.jpg', 'book_img/20459091-1_b.jpg', '25');
INSERT INTO `T_Book` VALUES ('400D94DE5A0742B3A618FC76DF107183', 'JavaScript宝典（第7版）（配光盘）', '古德曼', '128.00', '88.30', '6.9', '清华大学出版社', '2013-1-1', '1', '1012', '1657000', '2013-1-1', '32', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23169892-1_w.jpg', 'book_img/23169892-1_b.jpg', '88');
INSERT INTO `T_Book` VALUES ('4491EA4832E04B8B94F334B71E871983', 'Java语言程序设计：进阶篇（原书第8版）', '梁勇', '79.00', '54.50', '6.9', '机械工业出版社', '2011-6-1', '1', '507', '0', '2011-6-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/21117631-1_w_1.jpg', 'book_img/21117631-1_b.jpg', '48');
INSERT INTO `T_Book` VALUES ('48BBFBFC07074ADE8CC906A45BE5D9A6', 'JavaScript权威指南（第6版）（淘宝前端团队倾情翻译！经典权威的JavaScript犀牛书！第6版特别涵盖了HTML5和ECMAScript5！）（经典巨著，当当独家首发）', '弗兰纳根', '139.00', '95.30', '6.9', '机械工业出版社', '2012-4-1', '1', '1004', '0', '2012-4-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22722790-1_w.jpg', 'book_img/22722790-1_b.jpg', '4');
INSERT INTO `T_Book` VALUES ('49D98E7916B94232862F7DCD1B0BAB66', 'HTML5+JavaScript动画基础', '兰贝塔', '69.00', '47.60', '6.9', '人民邮电出版社', '2013-6-1', '1', '393', '553000', '2013-6-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23266633-1_w.jpg', 'book_img/23266633-1_b.jpg', '51');
INSERT INTO `T_Book` VALUES ('4BF6D97DD18A4B77B8DED9B057577F8F', 'Java Web从入门到精通（附光盘1张）（连续8月Java类全国零售排行前2名，27小时视频，951个经典实例、369项面试真题、596项测试史上最全资源库）', '明日科技', '69.80', '48.20', '6.9', '清华大学出版社', '2012-9-1', '1', '547', '979000', '2012-9-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22862056-1_w.jpg', 'book_img/22862056-1_b.jpg', '23');
INSERT INTO `T_Book` VALUES ('4C3331CAD5A5453787A94B8D7CCEAA29', 'Java Web整合开发王者归来（JSP+Servlet+Struts+Hibernate+Spring）（配光盘', '刘京华', '99.80', '68.90', '6.9', '清华大学出版社', '2010-1-1', '1', '1010', '1368000', '2010-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20756351-1_w_1.jpg', 'book_img/20756351-1_b_1.jpg', '17');
INSERT INTO `T_Book` VALUES ('4D20D2450B084113A331D909FF4975EB', 'jQuery实战(第2版)(畅销书升级版，掌握Web开发利器必修宝典)', 'Bear Bibeault　Yehuda Katz ', '69.00', '47.60', '6.9', '人民邮电出版社', '2012-3-1', '1', '394', '617000', '2012-3-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22638286-1_w.jpg', 'book_img/22638286-1_b.jpg', '79');
INSERT INTO `T_Book` VALUES ('4E44405DAFB7413E8A13BBFFBEE73AC7', 'JavaScript经典实例', '鲍尔斯', '78.00', '53.80', '6.9', '中国电力出版社', '2012-3-1', '1', '512', '625000', '2012-3-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22692811-1_w.jpg', 'book_img/22692811-1_b.jpg', '68');
INSERT INTO `T_Book` VALUES ('504FB999B0444B339907090927FDBE8A', '深入浅出Ext JS(第3版)', '徐会生', '69.00', '47.60', '6.9', '人民邮电出版社', '2013-10-1', '3', '413', '642000', '2013-10-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23351049-1_w_1.jpg', 'book_img/23351049-1_b.jpg', '71');
INSERT INTO `T_Book` VALUES ('52077C8423B645A9BADA96A5E0B14422', 'Spring源码深度解析', '郝佳', '69.00', '47.60', '6.9', '人民邮电出版社', '2013-9-1', '1', '386', '545000', '2013-8-30', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23329703-1_w_1.jpg', 'book_img/23329703-1_b.jpg', '52');
INSERT INTO `T_Book` VALUES ('52B0EDFF966E4A058BDA5B18EEC698C4', '亮剑Java Web项目开发案例导航(含DVD光盘1张)', '朱雪琴', '69.00', '41.40', '6.0', '电子工业出版社', '2012-3-1', '1', '526', '875000', '2012-3-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22623766-1_w.jpg', 'book_img/22623766-1_b.jpg', '81');
INSERT INTO `T_Book` VALUES ('5315DA60D24042889400AD4C93A37501', 'Spring 3.x企业应用开发实战(含CD光盘1张)', '陈雄华', '90.00', '62.10', '6.9', '电子工业出版社', '2012-2-1', '1', '710', '1158000', '2012-2-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22605701-1_w.jpg', 'book_img/22605701-1_b.jpg', '24');
INSERT INTO `T_Book` VALUES ('56B1B7D8CD8740B098677C7216A673C4', '疯狂 Java 程序员的基本修养（《疯狂Java讲义》最佳拍档，扫清知识死角，夯实基本功）', '李刚', '59.00', '40.70', '6.9', '电子工业出版社', '2013-1-1', '1', '484', '7710000', '2013-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23042420-1_w.jpg', 'book_img/23042420-1_b.jpg', '89');
INSERT INTO `T_Book` VALUES ('57B6FF1B89C843C38BA39C717FA557D6', '了不起的Node.js: 将JavaScript进行到底（Web开发首选实时 跨多服务器 高并发）', 'Guillermo Rauch', '79.00', '54.50', '6.9', '电子工业出版社', '2014-1-1', '1', '292', '436000', '2014-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23368351-1_w_2.jpg', 'book_img/23368351-1_b.jpg', '8');
INSERT INTO `T_Book` VALUES ('5C4A6F0F4A3B4672AD8C5F89BF5D37D2', 'Java从入门到精通（第3版）（附光盘1张）（连续8月Java类全国零售排行前2名，32小时视频，732个经典实例、369项面试真题、616项测试史上最全资源库）', '明日科技', '59.80', '50.00', '6.9', '清华大学出版社', '2012-9-1', '3', '564', '1013000', '2012-9-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22862060-1_w.jpg', 'book_img/22862060-1_b.jpg', '1');
INSERT INTO `T_Book` VALUES ('5C68141786B84A4CB8929A2415040739', 'JavaScript高级程序设计(第3版)(JavaScript技术名著，国内JavasScript第一书，销量超过8万册)', 'Nicholas C. Zakas', '99.00', '68.30', '6.9', '人民邮电出版社', '2012-3-1', '1', '730', '1092000', '2012-3-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22628333-1_w.jpg', 'book_img/22628333-1_b.jpg', '5');
INSERT INTO `T_Book` VALUES ('5EDB981339C342ED8DB17D5A198D50DC', 'Java程序性能优化', '葛一鸣', '59.00', '40.70', '6.9', '清华大学出版社', '2012-10-1', '1', '400', '649000', '2012-10-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22881618-1_w.jpg', 'book_img/22881618-1_b.jpg', '27');
INSERT INTO `T_Book` VALUES ('6398A7BA400D40258796BCBB2B256068', 'JavaScript设计模式', 'Addy Osmani', '49.00', '33.80', '6.9', '人民邮电出版社', '2013-6-1', '1', '241', '301000', '2013-6-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23266635-1_w.jpg', 'book_img/23266635-1_b.jpg', '40');
INSERT INTO `T_Book` VALUES ('676B56A612AF4E968CF0F6FFE289269D', 'JavaScript和jQuery实战手册（原书第2版）', '麦克法兰', '99.00', '68.30', '6.9', '机械工业出版社', '2013-3-11', '1', '504', '0', '2013-3-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23201813-1_w.jpg', 'book_img/23201813-1_b.jpg', '42');
INSERT INTO `T_Book` VALUES ('7917F5B19A0948FD9551932909328E4E', 'Java项目开发案例全程实录（第2版）（配光盘）（软件项目开发全程实录丛书）', '明日科技', '69.80', '48.20', '6.9', '清华大学出版社', '2011-1-1', '2', '605', '1037000', '2011-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20991549-1_w_1.jpg', 'book_img/20991549-1_b.jpg', '64');
INSERT INTO `T_Book` VALUES ('7CD79C20258F477AB841518D9312E843', 'Java程序员面试宝典（第三版）', '欧立奇', '49.00', '33.80', '6.9', '电子工业出版社', '2013-9-1', '1', '359', '446400', '2013-9-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23348683-1_w_1.jpg', 'book_img/23348683-1_b.jpg', '44');
INSERT INTO `T_Book` VALUES ('7D7FE81293124793BDB2C6FF1F1C943D', '21天学通Java(第6版)（中文版累计销量超30000册）', 'Rogers Cadenhead', '55.00', '38.00', '6.9', '人民邮电出版社', '2013-4-1', '1', '410', '781000', '2013-4-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23219731-1_w.jpg', 'book_img/23219731-1_b.jpg', '46');
INSERT INTO `T_Book` VALUES ('7FD7F50B15F74248AA769798909F8653', 'Java网络编程（第3版）——O’Reilly Java系列', '哈诺德', '85.00', '51.00', '6.0', '中国电力出版社', '2005-11-1', '1', '718', '668000', '2005-11-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/9062293-1_w.jpg', 'book_img/9062293-1_b.jpg', '35');
INSERT INTO `T_Book` VALUES ('819FF56E4423462394E6F83882F78975', '学通Java Web的24堂课（配光盘）（软件开发羊皮卷）', '陈丹丹', '79.80', '55.10', '6.9', '清华大学出版社', '2011-6-1', '1', '718', '1488000', '2011-6-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/21118835-1_w_1.jpg', 'book_img/21118835-1_b.jpg', '91');
INSERT INTO `T_Book` VALUES ('81FADA99309342F4978D5C680B0C6E8C', 'Java入门很简单（配光盘）（入门很简单丛书）（打开Java编程之门 Java技术网推荐）', '李世民', '59.80', '41.30', '6.9', '清华大学出版社', '2012-8-1', '1', '459', '745000', '2012-8-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22839309-1_w.jpg', 'book_img/22839309-1_b.jpg', '85');
INSERT INTO `T_Book` VALUES ('89A57D099EA14026A5C3D10CFC10C22C', 'Java 2实用教程（第4版）（21世纪高等学校计算机基础实用规划教材）', '耿祥义', '39.50', '31.60', '8.0', '清华大学出版社', '2012-8-1', '4', '479', '782000', '2012-8-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22844118-1_w.jpg', 'book_img/22844118-1_b.jpg', '73');
INSERT INTO `T_Book` VALUES ('8A5B4042D5B14D6B87A34DABF327387F', 'Java核心技术 卷II：高级特性(第9版·英文版)(上、下册)', '霍斯特曼', '119.00', '82.10', '6.9', '人民邮电出版社', '2013-7-1', '1', '1118', '1370000', '2013-7-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23280478-1_w.jpg', 'book_img/23280478-1_b.jpg', '57');
INSERT INTO `T_Book` VALUES ('8DD0ADF2665B40899E09ED2983DC3F7B', 'jQuery权威指南（被公认的权威的、易学的jQuery实战教程，多次重印，热销中）', '陶国荣', '59.00', '40.70', '6.9', '机械工业出版社', '2011-1-1', '1', '385', '0', '2011-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/21006995-1_w_1.jpg', 'book_img/21006995-1_b.jpg', '75');
INSERT INTO `T_Book` VALUES ('8E16D59BA4C34374A68029AE877613C4', '轻量级Java EE企业应用实战（第3版）：Struts 2＋Spring 3＋Hibernate整合开发(含CD光盘1张)', '李刚', '99.00', '68.30', '6.9', '电子工业出版社', '2012-4-1', '1', '816', '1440000', '2012-4-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22685703-1_w.jpg', 'book_img/22685703-1_b.jpg', '6');
INSERT INTO `T_Book` VALUES ('8F1520F2CED94C679433B9C109E791CB', 'Java从入门到精通（实例版）（附光盘1张）（连续8月Java类全国零售排行前2名，14小时视频，732个经典实例、369项面试真题、616项测试史上最全资源库）', '明日科技', '69.80', '47.60', '6.9', '清华大学出版社', '2012-9-1', '1', '548', '986000', '2012-9-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22862061-1_w.jpg', 'book_img/22862061-1_b.jpg', '49');
INSERT INTO `T_Book` VALUES ('90E423DBE56042838806673DB3E86BD3', '《Spring技术内幕（第2版）》（畅销书全新升级，Spring类图书销量桂冠，从宏观和微观两个角度解析Spring架构设计和实现原理）', '计文柯', '69.00', '47.60', '6.9', '机械工业出版社', '2012-2-1', '2', '399', '0', '2012-2-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22606836-1_w.jpg', 'book_img/22606836-1_b.jpg', '45');
INSERT INTO `T_Book` VALUES ('926B8F31C5D04F61A72F66679A0CCFFD', 'JavaScript编程精解（华章程序员书库）（JavaScript之父高度评价并强力推荐，系统学习JS首选！）', '哈弗贝克', '49.00', '33.80', '6.9', '械工业出版社', '2012-9-1', '1', '162', '0', '2012-9-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22873894-1_w.jpg', 'book_img/22873894-1_b.jpg', '70');
INSERT INTO `T_Book` VALUES ('95AACC68D64D4D67B1E33E9EAC22B885', 'Head First Java（中文版）（JAVA经典畅销书 生动有趣 轻松学好JAVA）', '塞若', '79.00', '47.40', '6.0', '中国电力出版社', '2007-2-1', '1', '689', '983000', '2001-7-2', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/9265169-1_w.jpg', 'book_img/9265169-1_b.jpg', '13');
INSERT INTO `T_Book` VALUES ('97437DAD03FA456AA7D6154614A43B55', 'HTML、CSS、JavaScript网页制作从入门到精通（两万读者的选择，经久不衰的超级畅销书最新升级版！网页制作学习者入门必读经典！）', '刘西杰', '49.00', '32.90', '6.7', '人民邮电出版社', '2012-12-24', '2', '450', '705000', '2012-12-24', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22928649-1_w.jpg', 'book_img/22928649-1_b.jpg', '3');
INSERT INTO `T_Book` VALUES ('9923901FBF124623BC707920D8936BC8', 'JavaScript DOM编程艺术(第2版)', '基思', '49.00', '33.80', '6.9', '人民邮电出版社', '2011-4-1', '1', '286', '443000', '2011-4-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/21049601-1_w_1.jpg', 'book_img/21049601-1_b.jpg', '28');
INSERT INTO `T_Book` VALUES ('99BF63AC12AD48FCB673F1820888964E', 'Java Web开发实战1200例（第Ⅱ卷）（史上最全的“编程实例”类图书，代码分析、实例速查、练习巩固的绝好帮手）', '无', '99.00', '67.40', '6.8', '清华大学出版社', '0', '0', '0', '1746000', '2011-6-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/21110929-1_w_1.jpg', 'book_img/21110929-1_b.jpg', '78');
INSERT INTO `T_Book` VALUES ('9D257176A6934CB79427CEC37E69249F', '疯狂Ajax讲义（第3版）--jQuery/Ext JS/Prototype/DWR企业应用前端开发实战(含CD光盘1张)（畅销书升级版，企业应用前端开发实战指南）', '李刚', '79.00', '54.50', '6.9', '电子工业出版社', '2013-2-1', '1', '624', '997000', '2013-2-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23184673-1_w.jpg', 'book_img/23184673-1_b.jpg', '53');
INSERT INTO `T_Book` VALUES ('9FBD51A7C02D4F5B9862CD2EBBF5CA04', 'Flash ActionScript 3.0全站互动设计', '刘欢 ', '69.80', '48.20', '6.9', '人民邮电出版社', '2012-10-1', '1', '488', '760000', '2012-10-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22886581-1_w.jpg', 'book_img/22886581-1_b.jpg', '96');
INSERT INTO `T_Book` VALUES ('9FF423101836438F874035A48498CF45', 'Java编程思想（英文版·第4版）', '埃克尔 ', '79.00', '54.50', '6.9', '机械工业出版社', '2007-4-1', '1', '1482', '0', '2007-4-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/9288920-1_w.jpg', 'book_img/9288920-1_b.jpg', '31');
INSERT INTO `T_Book` VALUES ('A3D464D1D1344ED5983920B472826730', 'Java Web开发详解：XML+DTD+XML Schema+XSLT+Servlet 3 0+JSP 2 2深入剖析与实例应用(含CD光盘1张)', '孙鑫', '119.00', '61.30', '5.2', '电子工业出版社', '2012-5-1', '1', '889', '1760000', '2012-5-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22788412-1_w.jpg', 'book_img/22788412-1_b.jpg', '60');
INSERT INTO `T_Book` VALUES ('A46A0F48A4F649AE9008B38EA48FAEBA', 'Java编程全能词典(含DVD光盘2张)', '明日科技', '98.00', '65.70', '6.7', '电子工业出版社', '2010-3-1', '1', '486', '496000', '2010-3-1', '32', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20813806-1_w_1.jpg', 'book_img/20813806-1_b.jpg', '90');
INSERT INTO `T_Book` VALUES ('A5A6F27DCD174614850B26633A0B4605', 'JavaScript模式', '斯特凡洛夫', '38.00', '22.80', '6.0', '中国电力出版社', '2012-7-1', '1', '208', '253000', '2012-7-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22819430-1_w.jpg', 'book_img/22819430-1_b.jpg', '61');
INSERT INTO `T_Book` VALUES ('A7220EF174704012830E066FDFAAD4AD', 'Spring 3.0就这么简单（国内原创的Java敏捷开发图书，展现作者Spring原创开源项目ROP开发的全过程，所有项目工程均以Maven组织）', '陈雄华', '59.00', '40.70', '6.9', '人民邮电出版社', '2013-1-1', '1', '380', '530000', '2013-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22938474-1_w.jpg', 'book_img/22938474-1_b.jpg', '77');
INSERT INTO `T_Book` VALUES ('A7EFD99367C9434682A790635D3C5FDF', 'Java Web技术整合应用与项目实战（JSP+Servlet+Struts2+Hibernate+Spring3）', '张志锋', '98.00', '67.60', '6.9', '清华大学出版社', '2013-6-1', '1', '878', '0', '2013-6-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23266270-1_w.jpg', 'book_img/23266270-1_b.jpg', '92');
INSERT INTO `T_Book` VALUES ('A8EF76FD21A645109538614DEA85F3F7', 'Java语言程序设计：基础篇（原书第8版）', '梁勇', '75.00', '51.80', '6.9', '机械工业出版社', '2011-6-1', '1', '586', '0', '2011-6-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/21122188-1_w_1.jpg', 'book_img/21122188-1_b.jpg', '30');
INSERT INTO `T_Book` VALUES ('AD6EA79CCB8240AAAF5B292AD7E5DCAA', 'jQuery Mobile权威指南（根据jQuery Mobile最新版本撰写的权威参考书！全面讲解jQuery Mobile的所有功能、特性、使用方法和开发技巧）', '陶国荣', '59.00', '40.70', '6.9', '机械工业出版社', '2012-8-1', '1', '249', '0', '2012-8-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22847009-1_w.jpg', 'book_img/22847009-1_b.jpg', '38');
INSERT INTO `T_Book` VALUES ('AE0935F13A214436B8599DE285A86220', 'JavaScript基础教程(第8版)(经典JavaScript入门书 涵盖Ajax和jQuery)', 'Tom Negrino　Dori Smith', '69.00', '47.60', '6.9', '人民邮电出版社', '2012-4-1', '1', '392', '694000', '2012-4-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22717349-1_w.jpg', 'book_img/22717349-1_b.jpg', '37');
INSERT INTO `T_Book` VALUES ('AF28ED8F692C4288B32CF411CBDBFC23', '经典Java EE企业应用实战——基于WebLogic/JBoss的JSF+EJB 3+JPA整合开发(含CD光盘1张)', '无', '79.00', '54.50', '6.9', '电子工业出版社', '2010-8-1', '1', '0', '0', '2010-8-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20928547-1_w_1.jpg', 'book_img/20928547-1_b.jpg', '55');
INSERT INTO `T_Book` VALUES ('B329A14DDEF8455F82B3FDF25821D2BB', '名师讲坛——Java Web开发实战经典基础篇（JSP、Servlet、Struts、Ajax）（390项实例及分析，北京魔乐科技培训中心Java Web全部精华）', '李兴华', '69.80', '48.20', '6.9', '清华大学出版社', '2010-8-1', '1', '554', '819000', '2010-8-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20915948-1_w_3.jpg', 'book_img/20915948-1_b.jpg', '22');
INSERT INTO `T_Book` VALUES ('B7A7DA7A94E54054841EED1F70C3027C', '锋利的jQuery(第2版)(畅销书升级版，增加jQuery Mobile和性能优化)', '单东林', '49.00', '33.80', '6.9', '人民邮电出版社', '2012-7-1', '2', '380', '598000', '2012-7-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22786088-1_w.jpg', 'book_img/22786088-1_b.jpg', '10');
INSERT INTO `T_Book` VALUES ('BD1CB005E4A04DCA881DA8689E21D4D0', 'jQuery UI开发指南', 'Eric Sarrion', '39.00', '26.90', '6.9', '人民邮电出版社', '2012-12-1', '1', '212', '286000', '2012-12-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22910975-1_w.jpg', 'book_img/22910975-1_b.jpg', '63');
INSERT INTO `T_Book` VALUES ('C23E6E8A6DB94E27B6E2ABD39DC21AF5', 'JavaScript:The Good Parts(影印版）', '克罗克福特', '28.00', '19.30', '6.9', '东南大学出版社', '2009-1-1', '1', '153', '181000', '2009-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20412979-1_w.jpg', 'book_img/20412979-1_b.jpg', '95');
INSERT INTO `T_Book` VALUES ('C3CF52B3ED2D4187A16754551488D733', 'Java从入门到精通（附光盘）', '魔乐科技', '59.00', '35.40', '6.0', '人民邮电出版社', '2010-4-1', '1', '519', '884000', '2010-4-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20810282-1_w_1.jpg', 'book_img/20810282-1_b.jpg', '29');
INSERT INTO `T_Book` VALUES ('C86D3F6FACB449BEBD940D9307ED4A47', '编写高质量代码(从语法、程序设计和架构、工具和框架、编码风格、编程思想5个方面探讨编写高质量Java代码的技巧、禁忌和最佳实践)', '秦小波', '59.00', '40.70', '6.9', '机械工业出版社', '2012-1-1', '1', '303', '0', '2012-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22579686-1_w.jpg', 'book_img/22579686-1_b.jpg', '84');
INSERT INTO `T_Book` VALUES ('CB0AB3654945411EA69F368D0EA91A00', 'JavaScript语言精粹（修订版）', '道格拉斯·克罗克福德', '49.00', '39.20', '8.0', '电子工业出版社', '2012-9-1', '1', '155', '258000', '2012-9-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22872884-1_w.jpg', 'book_img/22872884-1_b.jpg', '33');
INSERT INTO `T_Book` VALUES ('CD913617EE964D0DBAF20C60076D32FB', '名师讲坛——Java开发实战经典（配光盘）（60小时全真课堂培训，视频超级给力！790项实例及分析，北京魔乐科技培训中心Java全部精华）', '李兴华', '79.80', '55.10', '6.9', '清华大学出版社', '2009-8-1', '1', '831', '1222000', '2012-8-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20637368-1_w_2.jpg', 'book_img/20637368-1_b_2.jpg', '19');
INSERT INTO `T_Book` VALUES ('CE01F15D435A4C51B0AD8202A318DCA7', 'Java编程思想（第4版）', '布鲁斯.艾克尔', '108.00', '74.50', '6.9', '机械工业出版社', '2007-6-1', '1', '880', '0', '2007-6-1', '0', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/9317290-1_w.jpg', 'book_img/9317290-1_b.jpg', '2');
INSERT INTO `T_Book` VALUES ('CF5546769F2842DABB2EF7A00D51F255', 'jQuery开发从入门到精通（配套视频327节，中小实例232个，实战案例7个商品详情手册11部，网页模版83类）（附1DVD）', '袁江', '79.80', '55.10', '6.9', '清华大学出版社', '2013-6-1', '1', '619', '1109000', '2013-6-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23263012-1_w.jpg', 'book_img/23263012-1_b.jpg', '43');
INSERT INTO `T_Book` VALUES ('D0DA36CEE42549FFB299B7C7129761D5', 'Java应用架构设计：模块化模式与OSGi(系统、全面地讲解如何将模块化设计思想引入开发中，涵盖18个有助于实现模块化软件架构的模式)', '克内恩席尔德', '69.00', '47.60', '6.9', '机械工业出版社', '2013-9-1', '1', '251', '0', '2013-9-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23339643-1_w.jpg', 'book_img/23339643-1_b.jpg', '26');
INSERT INTO `T_Book` VALUES ('D0E69F85ACAB4C15BB40966E5AA545F1', 'Java并发编程实战（第16届Jolt大奖提名图书，Java并发编程必读佳作', 'Brian Goetz', '69.00', '47.60', '6.9', '机械工业出版社', '2012-2-1', '1', '290', '0', '2012-2-1', '32', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22606835-1_w.jpg', 'book_img/22606835-1_b.jpg', '15');
INSERT INTO `T_Book` VALUES ('D2ABA8B06C524632846F27C34568F3CE', 'Java 经典实例', '达尔文', '98.00', '67.60', '6.9', '中国电力出版社', '2009-2-1', '1', '784', '805000', '2009-2-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20500255-1_w.jpg', 'book_img/20500255-1_b.jpg', '62');
INSERT INTO `T_Book` VALUES ('D8723405BA054C13B52357B8F6AEEC24', '深入理解Java虚拟机：JVM高级特性与最佳实践（第2版）', '周志明', '79.00', '54.50', '6.9', '机械工业出版社', '2013-6-1', '2', '433', '0', '2013-6-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23259731-1_w.jpg', 'book_img/23259731-1_b.jpg', '14');
INSERT INTO `T_Book` VALUES ('DC36FD53A1514312A0A9ADD53A583886', 'JavaScript异步编程：设计快速响应的网络应用【掌握JavaScript异步编程必杀技，让代码更具响应度！ 】', 'Trevor Burnham ', '32.00', '22.10', '6.9', '人民邮电出版社', '2013-6-1', '1', '118', '98000', '2013-5-23', '32', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23252196-1_w.jpg', 'book_img/23252196-1_b.jpg', '72');
INSERT INTO `T_Book` VALUES ('DCB64DF0084E486EBF173F729A3A630A', 'Java设计模式(第2版)', 'Steven John Metsker', '75.00', '51.80', '6.9', '电子工业出版社', '2012-9-1', '1', '0', '0', '2012-9-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22868759-1_w.jpg', 'book_img/22868759-1_b.jpg', '76');
INSERT INTO `T_Book` VALUES ('DEE7BDC7E0E343149E3C3601D2658171', '疯狂HTML 5/CSS 3/JavaScript讲义(含CD光盘1张)', '李刚', '69.00', '47.60', '6.9', '电子工业出版社', '2012-5-1', '1', '500', '819000', '2012-5-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22783904-1_w.jpg', 'book_img/22783904-1_b.jpg', '21');
INSERT INTO `T_Book` VALUES ('DF4E74EEE89B43229BB8212F0B858C38', '精通Hibernate：Java对象持久化技术详解（第2版）(含光盘1张)', '孙卫琴', '75.00', '51.80', '6.9', '电子工业出版社', '2010-2-1', '1', '695', '1148800', '2010-2-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20773347-1_w_1.jpg', 'book_img/20773347-1_b.jpg', '39');
INSERT INTO `T_Book` VALUES ('E4F184188C8B4C7BB32D4E76603426AC', '疯狂Java讲义（第2版，附光盘）', '李刚', '109.00', '75.20', '6.9', '电子工业出版社', '2012-1-1', '1', '844', '1747000', '2012-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22588603-1_w.jpg', 'book_img/22588603-1_b.jpg', '7');
INSERT INTO `T_Book` VALUES ('EA695342393C4BE48B831FA5E6B0E5C4', '编写可维护的JavaScript《JavaScript高级程序设计》作者Nicholas Zakas最新力作，构建编码风格手册，帮助开发团队从“游击队”走向“正规军”）', 'Nicholas C. Zakas', '55.00', '38.00', '6.9', '人民邮电出版社', '2013-4-1', '1', '227', '400000', '2013-4-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23200995-1_w.jpg', 'book_img/23200995-1_b.jpg', '59');
INSERT INTO `T_Book` VALUES ('F0E34313BF304CCEBF198BD4E05307B8', 'jQuery Cookbook中文版（jQuery之父鼎力推荐，社区数十位专家倾情力作', 'jQuery社区专家组', '69.00', '47.60', '6.9', '人民邮电出版社', '2013-5-1', '1', '425', '573000', '2013-5-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23219358-1_w.jpg', 'book_img/23219358-1_b.jpg', '87');
INSERT INTO `T_Book` VALUES ('F6162799E913423EA5CB57BEC65AB1E9', 'JUnit实战(第2版)', '塔凯文', '79.00', '54.50', '6.9', '人民邮电出版社', '2012-4-1', '1', '442', '640000', '2012-4-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22633574-1_w.jpg', 'book_img/22633574-1_b.jpg', '80');
INSERT INTO `T_Book` VALUES ('F693239BC3B3444C8538ABE7411BB38E', 'Java Web典型模块与项目实战大全（配光盘）', '常建功', '99.50', '68.70', '6.9', '清华大学出版社', '2011-1-1', '1', '922', '1473000', '2011-1-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/20988080-1_w_1.jpg', 'book_img/20988080-1_b.jpg', '41');
INSERT INTO `T_Book` VALUES ('F78C94641DB4475BBA1E72A07DF9B3AE', 'JAVA面向对象编程', '孙卫琴 ', '65.80', '45.40', '6.9', '电子工业出版社', '2006-7-1', '1', '625', '1030400', '2006-7-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/9186890-1_w.jpg', 'book_img/9186890-1_b.jpg', '69');
INSERT INTO `T_Book` VALUES ('FC232CD9B6E6411BBBB1A5B781D2C3C9', 'Java与模式(含盘)（超多实例和习题，详解设计原则与设计模式）', '阎宏', '88.00', '60.70', '6.9', '电子工业出版社', '2002-10-1', '1', '1024', '16704000', '2002-10-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/696673-1_w.jpg', 'book_img/696673-1_b.jpg', '67');
INSERT INTO `T_Book` VALUES ('FEC3740CF30E442A94021911A25EF0D7', 'Spring攻略(第2版)(Spring攻略(第2版))', 'Gary Mak　Josh Long　Daniel Rubio', '128.00', '88.30', '6.9', '人民邮电出版社', '2012-3-1', '1', '938', '1322000', '2012-3-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/22623020-1_w.jpg', 'book_img/22623020-1_b.jpg', '82');
INSERT INTO `T_Book` VALUES ('FFABBED1E5254BC0B2726EC4ED8ACCDA', '深入理解OSGi：Equinox原理、应用与最佳实践（《深入理解Java虚拟机》作者新作！深入讲解OSGi原理和服务，以及Equinox框架的用法和原理）', '周志明', '79.00', '54.50', '6.9', '机械工业出版社', '2013-2-1', '1', '414', '0', '2013-2-1', '16', '胶版纸', '5F79D0D246AD4216AC04E9C5FAB3199E', 'book_img/23179003-1_w.jpg', 'book_img/23179003-1_b.jpg', '47');

-- ----------------------------
-- Table structure for `T_CartItem`
-- ----------------------------
DROP TABLE IF EXISTS `T_CartItem`;
CREATE TABLE `T_CartItem` (
  `cartItemId` char(32) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `bid` char(32) DEFAULT NULL,
  `uid` char(32) DEFAULT NULL,
  `orderBy` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cartItemId`),
  KEY `orderBy` (`orderBy`),
  KEY `FK_T_CartItem_T_User` (`uid`),
  KEY `FK_T_CartItem_T_Book` (`bid`),
  CONSTRAINT `FK_T_CartItem_T_Book` FOREIGN KEY (`bid`) REFERENCES `T_Book` (`bid`),
  CONSTRAINT `FK_T_CartItem_T_User` FOREIGN KEY (`uid`) REFERENCES `T_User` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of T_CartItem
-- ----------------------------

-- ----------------------------
-- Table structure for `T_Category`
-- ----------------------------
DROP TABLE IF EXISTS `T_Category`;
CREATE TABLE `T_Category` (
  `cid` char(32) NOT NULL,
  `cname` varchar(50) DEFAULT NULL,
  `pid` char(32) DEFAULT NULL,
  `desc` varchar(100) DEFAULT NULL,
  `orderBy` int(11) NOT NULL AUTO_INCREMENT,
  `sub_cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `cname` (`cname`),
  KEY `FK_T_Category_T_Category` (`pid`),
  KEY `orderBy` (`orderBy`),
  CONSTRAINT `FK_T_Category_T_Category` FOREIGN KEY (`pid`) REFERENCES `T_Category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of T_Category
-- ----------------------------
INSERT INTO `T_Category` VALUES ('1', '程序设计', null, '程序设计分类', '1', null);
INSERT INTO `T_Category` VALUES ('17A0079A55624D59BADC17F0D21E9732', '222222sss', '22', '222333333', '38', null);
INSERT INTO `T_Category` VALUES ('2', '办公室用书', null, '办公室用书', '2', null);
INSERT INTO `T_Category` VALUES ('22', '程序设计11', null, '程序设计分类', '1', null);
INSERT INTO `T_Category` VALUES ('297e83805ba08cbb015ba08cebd50001', '22', '22', 'aa', '47', null);
INSERT INTO `T_Category` VALUES ('3', '图形 图像 多媒体', null, '图形图像多媒体', '3', null);
INSERT INTO `T_Category` VALUES ('4', '操作系统/系统开发', null, '操作系统/系统开发', '4', null);
INSERT INTO `T_Category` VALUES ('458795C27E7346A8A5F1B942319297E0', '系统开发', '4', '系统开发分类', '29', null);
INSERT INTO `T_Category` VALUES ('4D01FFF0CB94468EA907EF42780668AB', '购买指南 组装指南 维修', '2', '购买指南 组装指南 维修分类', '18', null);
INSERT INTO `T_Category` VALUES ('5', '数据库', null, '数据库', '5', null);
INSERT INTO `T_Category` VALUES ('56AD72718C524147A2485E5F4A95A062', '3DS MAX', '3', '3DS MAX分类', '21', null);
INSERT INTO `T_Category` VALUES ('57DE3C2DDA784B81844029A28217698C', 'Dreamweaver', '3', 'Dreamweaver分类', '24', null);
INSERT INTO `T_Category` VALUES ('5F79D0D246AD4216AC04E9C5FAB3199E', 'Java Javascript', '1', 'Java Javascript分类', '10', null);
INSERT INTO `T_Category` VALUES ('6', '网络与数据通讯', null, '网络与数据通讯!', '6', null);
INSERT INTO `T_Category` VALUES ('65640549B80E40B1981CDEC269BFFCAD', 'Photoshop', '3', 'Photoshop分类', '20', null);
INSERT INTO `T_Category` VALUES ('65830AB237EF428BAE9B7ADC78A8D1F6', 'Unix', '4', 'Unix分类', '28', null);
INSERT INTO `T_Category` VALUES ('757BDAB506A445EC8DEDA4CE04303B9F', '网页设计', '3', '网页设计分类', '22', null);
INSERT INTO `T_Category` VALUES ('84ECE401C2904DBEA560D04A581A66D9', 'HTML XML', '1', 'HTML XML分类', '13', null);
INSERT INTO `T_Category` VALUES ('922E6E2DB04143D39C9DDB26365B3EE8', 'C C++ VC VC++', '1', 'C C++ VC VC++分类', '12', null);
INSERT INTO `T_Category` VALUES ('96F209F79DB242E9B99CC1B98FAB01DB', '数据库理论', '5', '数据库理论分类', '33', null);
INSERT INTO `T_Category` VALUES ('B596ECE0F9BF40288F40A66B35551806', 'Flush', '3', 'Flush分类', '23', null);
INSERT INTO `T_Category` VALUES ('B92ED191DBE647BE8F75721FB231E207', '因特网 电子邮件', '2', '因特网 电子邮件分类', '19', null);
INSERT INTO `T_Category` VALUES ('C3F9FAAF4EA64857ACFAB0D9C8D0E446', 'PHP', '1', 'PHP分类', '14', null);
INSERT INTO `T_Category` VALUES ('C4DD8CA232864B31A367EE135D86382C', '计算机初级入门', '2', '计算机初级入门分类', '17', null);
INSERT INTO `T_Category` VALUES ('C8E274EE5C99499080A98E24F0BD2E03', '.NET', '1', '.NET分类', '15', null);
INSERT INTO `T_Category` VALUES ('D45D96DA359A4FEAB3AB4DCF2157FC06', 'JSP', '1', 'JSP分类', '11', null);
INSERT INTO `T_Category` VALUES ('DCAD0384A6444C048951C7B36C5D96EE', 'Flash', '3', 'Flash分类', '25', null);
INSERT INTO `T_Category` VALUES ('F4FBD087EB054CA1896093F172AC33D9', '数据仓库与数据挖掘', '5', '数据仓库与数据挖掘分类', '30', null);
INSERT INTO `T_Category` VALUES ('F5C091B3967442A2B35EFEFC4EF8746F', '微软Office', '2', '微软Office分类', '16', null);
INSERT INTO `T_Category` VALUES ('FAB7B7F7084F4D57A0808ADC61117683', 'Windows', '4', 'Windows分类', '26', null);

-- ----------------------------
-- Table structure for `T_Order`
-- ----------------------------
DROP TABLE IF EXISTS `T_Order`;
CREATE TABLE `T_Order` (
  `oid` char(32) NOT NULL,
  `ordertime` char(19) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  `uid` char(32) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FK_T_Order_T_User` (`uid`),
  CONSTRAINT `FK_T_Order_T_User` FOREIGN KEY (`uid`) REFERENCES `T_User` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of T_Order
-- ----------------------------
INSERT INTO `T_Order` VALUES ('0230E746637A4D80A86E956A71BE7C56', '2017-04-24 11:28:14', '67.00', '1', '北京市 昌平区 西三旗 金燕龙办公楼1层 ', '55790D9C1A1845738E6D93866A148C7E');
INSERT INTO `T_Order` VALUES ('05DBA68D267F485987F449B9C8272121', '2017-04-24 11:27:08', '33.00', '1', '北京市 昌平区 西三旗 金燕龙办公楼1层 ', '55790D9C1A1845738E6D93866A148C7E');
INSERT INTO `T_Order` VALUES ('12C11DC728F0491387E704E595C9E9D7', '2017-04-24 11:26:09', '74.00', '1', '北京市 昌平区 西三旗 金燕龙办公楼1层 ', '55790D9C1A1845738E6D93866A148C7E');
INSERT INTO `T_Order` VALUES ('297e83805b9e432d015b9e444eab0002', '2017-04-24 12:42:08', '217.00', '1', '北京市 昌平区 西三旗 金燕龙办公楼1层 ', '55790D9C1A1845738E6D93866A148C7E');
INSERT INTO `T_Order` VALUES ('297e83805ba4e74e015ba4e7d4a70002', '2017-04-25 19:38:28', '106.00', '1', '北京市 昌平区 西三旗 金燕龙办公楼1层 ', '55790D9C1A1845738E6D93866A148C7E');
INSERT INTO `T_Order` VALUES ('2C629D97D5E2491EA2AA45068F7D7B40', '2017-04-24 11:27:25', '132.00', '1', '北京市 昌平区 西三旗 金燕龙办公楼1层 ', '55790D9C1A1845738E6D93866A148C7E');
INSERT INTO `T_Order` VALUES ('2CEA16A8A1C34612A1D37FDE756C74B3', '2017-04-24 11:26:42', '95.00', '1', '北京市 昌平区 西三旗 金燕龙办公楼1层 ', '55790D9C1A1845738E6D93866A148C7E');
INSERT INTO `T_Order` VALUES ('5C36F3B9367F443C9797A41069AC2CC9', '2017-04-24 11:26:48', '50.00', '1', '北京市 昌平区 西三旗 金燕龙办公楼1层 ', '55790D9C1A1845738E6D93866A148C7E');
INSERT INTO `T_Order` VALUES ('608CDEE56BE14B2B9AA9C08C06935A3E', '2017-04-24 11:28:32', '267.00', '5', '北京市 昌平区 西三旗 金燕龙办公楼1层 ', '55790D9C1A1845738E6D93866A148C7E');
INSERT INTO `T_Order` VALUES ('8EAA3CED9159497CB9882BBD7EC8D36B', '2017-04-24 11:27:02', '68.00', '1', '北京市 昌平区 西三旗 金燕龙办公楼1层 ', '55790D9C1A1845738E6D93866A148C7E');
INSERT INTO `T_Order` VALUES ('9E9F416DFBD1495C9C10ECD90F3CA879', '2017-04-24 11:26:36', '32.00', '1', '北京市 昌平区 西三旗 金燕龙办公楼1层 ', '55790D9C1A1845738E6D93866A148C7E');
INSERT INTO `T_Order` VALUES ('ACE8963FC1E64AA19B8CACBE8A177E10', '2017-04-24 11:26:55', '75.00', '1', '北京市 昌平区 西三旗 金燕龙办公楼1层 ', '55790D9C1A1845738E6D93866A148C7E');
INSERT INTO `T_Order` VALUES ('BF9EAE0F7E49462FA51ADD80B2AA3FA7', '2017-04-24 11:27:58', '125.00', '1', '北京市 昌平区 西三旗 金燕龙办公楼1层 ', '55790D9C1A1845738E6D93866A148C7E');
INSERT INTO `T_Order` VALUES ('C4BD0DFC286E4AFBADB6F5E564CF8CE6', '2017-04-24 11:27:35', '106.00', '1', '北京市 昌平区 西三旗 金燕龙办公楼1层 ', '55790D9C1A1845738E6D93866A148C7E');
INSERT INTO `T_Order` VALUES ('C8157E0B2CD647FA889D9022508F8EDF', '2017-04-24 11:28:06', '47.00', '1', '北京市 昌平区 西三旗 金燕龙办公楼1层 ', '55790D9C1A1845738E6D93866A148C7E');

-- ----------------------------
-- Table structure for `T_OrderItem`
-- ----------------------------
DROP TABLE IF EXISTS `T_OrderItem`;
CREATE TABLE `T_OrderItem` (
  `orderItemId` char(32) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `subtotal` decimal(8,2) DEFAULT NULL,
  `bid` char(32) DEFAULT NULL,
  `bname` varchar(200) DEFAULT NULL,
  `currPrice` decimal(8,2) DEFAULT NULL,
  `image_b` varchar(100) DEFAULT NULL,
  `oid` char(32) DEFAULT NULL,
  PRIMARY KEY (`orderItemId`),
  KEY `FK_T_OrderItem_T_Order` (`oid`),
  KEY `FKE261E13666796731` (`bid`),
  CONSTRAINT `FKE261E13666796731` FOREIGN KEY (`bid`) REFERENCES `T_Book` (`bid`),
  CONSTRAINT `FK_T_OrderItem_T_Order` FOREIGN KEY (`oid`) REFERENCES `T_Order` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of T_OrderItem
-- ----------------------------
INSERT INTO `T_OrderItem` VALUES ('18E8FE2FC31F4CB790F3E6B8120A3831', '1', '50.00', '5C4A6F0F4A3B4672AD8C5F89BF5D37D2', 'Java从入门到精通（第3版）（附光盘1张）（连续8月Java类全国零售排行前2名，32小时视频，732个经典实例、369项面试真题、616项测试史上最全资源库）', '50.00', 'book_img/22862060-1_b.jpg', '5C36F3B9367F443C9797A41069AC2CC9');
INSERT INTO `T_OrderItem` VALUES ('297e83805b9e432d015b9e444eb00003', '1', '74.00', 'CE01F15D435A4C51B0AD8202A318DCA7', 'Java编程思想（第4版）', '74.50', 'book_img/9317290-1_b.jpg', '297e83805b9e432d015b9e444eab0002');
INSERT INTO `T_OrderItem` VALUES ('297e83805b9e432d015b9e444eb10004', '1', '68.00', '8E16D59BA4C34374A68029AE877613C4', '轻量级Java EE企业应用实战（第3版）：Struts 2＋Spring 3＋Hibernate整合开发(含CD光盘1张)', '68.30', 'book_img/22685703-1_b.jpg', '297e83805b9e432d015b9e444eab0002');
INSERT INTO `T_OrderItem` VALUES ('297e83805b9e432d015b9e444eb10005', '1', '75.00', 'E4F184188C8B4C7BB32D4E76603426AC', '疯狂Java讲义（第2版，附光盘）', '75.20', 'book_img/22588603-1_b.jpg', '297e83805b9e432d015b9e444eab0002');
INSERT INTO `T_OrderItem` VALUES ('297e83805ba4e74e015ba4e7d4a80003', '1', '32.00', '97437DAD03FA456AA7D6154614A43B55', 'HTML、CSS、JavaScript网页制作从入门到精通（两万读者的选择，经久不衰的超级畅销书最新升级版！网页制作学习者入门必读经典！）', '32.90', 'book_img/22928649-1_b.jpg', '297e83805ba4e74e015ba4e7d4a70002');
INSERT INTO `T_OrderItem` VALUES ('297e83805ba4e74e015ba4e7d4a80004', '1', '74.00', 'CE01F15D435A4C51B0AD8202A318DCA7', 'Java编程思想（第4版）', '74.50', 'book_img/9317290-1_b.jpg', '297e83805ba4e74e015ba4e7d4a70002');
INSERT INTO `T_OrderItem` VALUES ('31BBC9BB86354A0DA1EF239AC91244F9', '1', '50.00', '5C4A6F0F4A3B4672AD8C5F89BF5D37D2', 'Java从入门到精通（第3版）（附光盘1张）（连续8月Java类全国零售排行前2名，32小时视频，732个经典实例、369项面试真题、616项测试史上最全资源库）', '50.00', 'book_img/22862060-1_b.jpg', 'BF9EAE0F7E49462FA51ADD80B2AA3FA7');
INSERT INTO `T_OrderItem` VALUES ('447E36C9CFD441138CD491AD3EF88558', '1', '68.00', '5C68141786B84A4CB8929A2415040739', 'JavaScript高级程序设计(第3版)(JavaScript技术名著，国内JavasScript第一书，销量超过8万册)', '68.30', 'book_img/22628333-1_b.jpg', '8EAA3CED9159497CB9882BBD7EC8D36B');
INSERT INTO `T_OrderItem` VALUES ('64456C0334AA4CC1A407F989C2317C02', '1', '95.00', '48BBFBFC07074ADE8CC906A45BE5D9A6', 'JavaScript权威指南（第6版）（淘宝前端团队倾情翻译！经典权威的JavaScript犀牛书！第6版特别涵盖了HTML5和ECMAScript5！）（经典巨著，当当独家首发）', '95.30', 'book_img/22722790-1_b.jpg', '2CEA16A8A1C34612A1D37FDE756C74B3');
INSERT INTO `T_OrderItem` VALUES ('6BE3CCDA3282427FA067AFC6FEA83A39', '1', '74.00', 'CE01F15D435A4C51B0AD8202A318DCA7', 'Java编程思想（第4版）', '74.50', 'book_img/9317290-1_b.jpg', '12C11DC728F0491387E704E595C9E9D7');
INSERT INTO `T_OrderItem` VALUES ('700788D7CA56449C872418C0F0F75418', '1', '68.00', '8E16D59BA4C34374A68029AE877613C4', '轻量级Java EE企业应用实战（第3版）：Struts 2＋Spring 3＋Hibernate整合开发(含CD光盘1张)', '68.30', 'book_img/22685703-1_b.jpg', '608CDEE56BE14B2B9AA9C08C06935A3E');
INSERT INTO `T_OrderItem` VALUES ('7A466AB2178F4D6D8309B104FCCCF47B', '2', '149.00', 'CE01F15D435A4C51B0AD8202A318DCA7', 'Java编程思想（第4版）', '74.50', 'book_img/9317290-1_b.jpg', '608CDEE56BE14B2B9AA9C08C06935A3E');
INSERT INTO `T_OrderItem` VALUES ('83278C6FEBDD4675BF89B3CA0DA49A58', '1', '75.00', 'E4F184188C8B4C7BB32D4E76603426AC', '疯狂Java讲义（第2版，附光盘）', '75.20', 'book_img/22588603-1_b.jpg', 'BF9EAE0F7E49462FA51ADD80B2AA3FA7');
INSERT INTO `T_OrderItem` VALUES ('8F1687744E0D4FEF92200C380251E35B', '1', '74.00', 'CE01F15D435A4C51B0AD8202A318DCA7', 'Java编程思想（第4版）', '74.50', 'book_img/9317290-1_b.jpg', 'C4BD0DFC286E4AFBADB6F5E564CF8CE6');
INSERT INTO `T_OrderItem` VALUES ('982C42230BDE4DB48DA943EA06E4A9EE', '1', '32.00', '97437DAD03FA456AA7D6154614A43B55', 'HTML、CSS、JavaScript网页制作从入门到精通（两万读者的选择，经久不衰的超级畅销书最新升级版！网页制作学习者入门必读经典！）', '32.90', 'book_img/22928649-1_b.jpg', '9E9F416DFBD1495C9C10ECD90F3CA879');
INSERT INTO `T_OrderItem` VALUES ('9B4806AEA52048B5AA6D29D7DC766CFE', '1', '67.00', '0EE8A0AE69154287A378FB110FF2C780', 'Java核心技术：卷Ⅰ基础知识（原书第8版）', '67.60', 'book_img/20285763-1_b.jpg', '0230E746637A4D80A86E956A71BE7C56');
INSERT INTO `T_OrderItem` VALUES ('A00EB2CEECA44322A4A3D08FF8BC2E08', '1', '47.00', '95AACC68D64D4D67B1E33E9EAC22B885', 'Head First Java（中文版）（JAVA经典畅销书 生动有趣 轻松学好JAVA）', '47.40', 'book_img/9265169-1_b.jpg', 'C8157E0B2CD647FA889D9022508F8EDF');
INSERT INTO `T_OrderItem` VALUES ('A5B06297DF9B4BF287A1AC8848078D07', '1', '75.00', 'E4F184188C8B4C7BB32D4E76603426AC', '疯狂Java讲义（第2版，附光盘）', '75.20', 'book_img/22588603-1_b.jpg', 'ACE8963FC1E64AA19B8CACBE8A177E10');
INSERT INTO `T_OrderItem` VALUES ('B8F91EBFA52549BBB3B23E6C9B0D2285', '1', '33.00', 'B7A7DA7A94E54054841EED1F70C3027C', '锋利的jQuery(第2版)(畅销书升级版，增加jQuery Mobile和性能优化)', '33.80', 'book_img/22786088-1_b.jpg', '05DBA68D267F485987F449B9C8272121');
INSERT INTO `T_OrderItem` VALUES ('BE4D9DFC1B524AE5B269B773EE6032CB', '1', '50.00', '5C4A6F0F4A3B4672AD8C5F89BF5D37D2', 'Java从入门到精通（第3版）（附光盘1张）（连续8月Java类全国零售排行前2名，32小时视频，732个经典实例、369项面试真题、616项测试史上最全资源库）', '50.00', 'book_img/22862060-1_b.jpg', '608CDEE56BE14B2B9AA9C08C06935A3E');
INSERT INTO `T_OrderItem` VALUES ('E35ACAB78FBF4CF993C2C2148853D57B', '1', '50.00', '5C4A6F0F4A3B4672AD8C5F89BF5D37D2', 'Java从入门到精通（第3版）（附光盘1张）（连续8月Java类全国零售排行前2名，32小时视频，732个经典实例、369项面试真题、616项测试史上最全资源库）', '50.00', 'book_img/22862060-1_b.jpg', '2C629D97D5E2491EA2AA45068F7D7B40');
INSERT INTO `T_OrderItem` VALUES ('E5582A0FB4274E8ABF335F6C072577F3', '1', '82.00', '3DD187217BF44A99B86DD18A4DC628BA', 'Java核心技术 卷1 基础知识（原书第9版）', '82.10', 'book_img/23362142-1_b.jpg', '2C629D97D5E2491EA2AA45068F7D7B40');
INSERT INTO `T_OrderItem` VALUES ('E8A3F93B84D54426BBD5C8A9F015973B', '1', '32.00', '97437DAD03FA456AA7D6154614A43B55', 'HTML、CSS、JavaScript网页制作从入门到精通（两万读者的选择，经久不衰的超级畅销书最新升级版！网页制作学习者入门必读经典！）', '32.90', 'book_img/22928649-1_b.jpg', 'C4BD0DFC286E4AFBADB6F5E564CF8CE6');

-- ----------------------------
-- Table structure for `T_User`
-- ----------------------------
DROP TABLE IF EXISTS `T_User`;
CREATE TABLE `T_User` (
  `uid` char(32) NOT NULL,
  `loginname` varchar(50) DEFAULT NULL,
  `loginpass` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `activationCode` char(64) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `loginname` (`loginname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of T_User
-- ----------------------------
INSERT INTO `T_User` VALUES ('297e83805ba51e8b015ba51f014c0001', 'Shayne', '12345', '1667899775@qq.com', '0', '152D93B582B04740ABADC1DDF4D4E08A3D794A31FB0B4633B656BAE445554680');
INSERT INTO `T_User` VALUES ('55790D9C1A1845738E6D93866A148C7E', 'shunzi', 'admin', 'shayne@163.com', '1', 'FCF142D04C4A420992FF4E7BAC92C1E58AF905F1A46B4818BB455BD925E52D');
