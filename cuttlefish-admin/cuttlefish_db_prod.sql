/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : cuttlefish_db_prod

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2021-09-01 07:09:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_content
-- ----------------------------
DROP TABLE IF EXISTS `t_content`;
CREATE TABLE `t_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(4000) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL COMMENT '10000:段子',
  `author_id` varchar(32) DEFAULT NULL,
  `title` varchar(32) DEFAULT NULL,
  `content_type` int(11) DEFAULT NULL COMMENT '0-文字  1-图片  2-音频  3-视频  4-图文混合',
  `audit_status` int(11) DEFAULT NULL COMMENT '0-待审核(用户发表内容后的初始状态)  1-审核中   2-审核通过  3-审核拒绝',
  `key_words` varchar(32) DEFAULT NULL,
  `content_from` int(11) DEFAULT NULL COMMENT '0-原创  1-引用',
  `from_desc` varchar(128) DEFAULT NULL COMMENT '引用来源，注明作者或来源地',
  `high_quality` tinyint(1) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `valid_status` int(5) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content_cover_url` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8 COMMENT='0-文字  1-图片  2-音频  3-视频  4-图文混合 5-H5内容';

-- ----------------------------
-- Records of t_content
-- ----------------------------
INSERT INTO `t_content` VALUES ('1', '<p>\r\n	<strong>“你可以笑一个吗？”</strong></span>\r\n</p>\r\n	<p>\r\n	<strong>“为什么啊？”</strong></span>\r\n</p>\r\n	<p>\r\n	<strong>“因为我的咖啡忘加糖了。”</strong></span> \r\n</p>\r\n<p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', null, '0', '0', '1', '2019-12-17 15:47:21', '2019-12-17 15:47:25', 'https://ossweb-img.qq.com/images/lol/web201310/skin/big84000.jpg');
INSERT INTO `t_content` VALUES ('2', '<p>\r\n	<strong>“我可以称呼你为您吗？”</strong>\r\n</p>\r\n	<p>\r\n	<strong>“这样我就可以把你放在心上。”</span></strong></span>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', null, '0', null, '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', null);
INSERT INTO `t_content` VALUES ('3', '<p>\r\n	<strong>“你是年少的欢喜”</strong>\r\n</p>\r\n		<p>\r\n	<strong>“这句话反过来念也是真的。”</strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('4', '<p>\r\n	<strong>“喜欢猫还是狗？”</strong>\r\n</p>\r\n		<p>\r\n	<strong>“猫。”</strong>\r\n</p>		<p>\r\n<strong>“喵～”</strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('5', '<p>\r\n	<strong>“你是年少的欢喜”</strong>\r\n</p>\r\n	<p>\r\n<strong>“这句话反过来念也是真的。”</strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('6', '<p>\r\n	<strong>“你看起来好像一个老婆婆啊。”</strong>\r\n</p>\r\n	<p>\r\n<strong>“？？？”</strong>\r\n</p>	<p>\r\n<strong>“不好意思，多说了一个婆字。”</strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('7', '<p>\r\n	<strong>“我是迷人的男人，你是迷人。”</strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('8', '<p>\r\n	<strong>“我想你说土味情话给你听。”</strong>\r\n</p>\r\n<p>\r\n	<strong>“不想听。”</strong>\r\n</p>		<p>\r\n<strong>“那就看前三个字好了。”</strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('9', '<p>\r\n	<strong>“我怀疑你是碳酸饮料。”</strong>\r\n</p>\r\n<p>\r\n	<strong>“为什么？”</strong>\r\n</p>		<p>\r\n<strong>“因为一遇见你，我就开心地冒泡泡。”</strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('10', '<p>\r\n	<strong>“你永远也看不到我寂寞的样子”</strong>\r\n</p>\r\n<p>\r\n	<strong>“为什么？”</strong>\r\n</p>		<p>\r\n<strong>“因为只有你不在我身边的时候，我才是最寂寞的”</strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('11', '<p>\r\n	<strong>“你累不累啊?”</strong>\r\n</p>\r\n<p>\r\n	<strong>“不累。”</strong>\r\n</p>		<p>\r\n<strong>“可是你都在我脑里跑了一天了！”</strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('12', '<p>\r\n	<strong>“你猜我什么星座”</strong>\r\n</p>\r\n<p>\r\n	<strong> “白羊?” </strong>\r\n</p>		<p>\r\n<strong>“错，我是为你量身定做”</strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('13', '<p>\r\n	<strong>“你的脸上有点东西”</strong>\r\n</p>\r\n<p>\r\n	<strong> “有什么?” </strong>\r\n</p>		<p>\r\n<strong>“有点漂亮。”</strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('14', '<p>\r\n	<strong>“和你聊天就像考试，事后想想总是觉没有发挥好。”</strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('15', '<p>\r\n	<strong>“立刻有=like you”</strong>\r\n</p>\r\n<p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('16', '<p>\r\n	<strong>“n 55lw !n paau !”</strong>\r\n</p>\r\n		<p>\r\n<strong> “什么意思?” </strong>\r\n</p>		<p>\r\n<strong>“你把手机倒过来看看。”</strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('17', '<p>\r\n	<strong>“我们来玩木头人不许动吧”</strong>\r\n</p>\r\n		<p>\r\n<strong> “好!” </strong>\r\n</p>		<p>\r\n<strong>“我输了，心动了。”</strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('18', '<p>\r\n	<strong>“你的手划了一道口子啊。”</strong>\r\n</p>\r\n		<p>\r\n<strong> “恩” </strong>\r\n</p>		<p>\r\n<strong>“那我也划一下。”</strong>\r\n</p>		<p>\r\n<strong>“为什么？”</strong>\r\n</p>		<p>\r\n<strong>“这样，我们就是两口子了。”</strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('19', '<p>\r\n	<strong>“我发现我预知未来。”</strong>\r\n</p>\r\n<p>\r\n	<strong> “这么厉害？” </strong>\r\n</p>		<p>\r\n<strong>“我预感明天我还是会喜欢你。”</strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('20', '<p>\r\n	<strong>“那个男的好帅啊。”</strong>\r\n</p>\r\n<p>\r\n	<strong> “那又怎样，他女朋友有我的好看吗？” </strong>\r\n</p>', '30000', '1', '那些土得掉渣的情话', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('100', '<p>\r\n	<strong>我由南向北去</strong>\r\n</p>\r\n<p>\r\n	<strong> 你从东往西走</strong>\r\n</p>		<p>\r\n<strong>十字路口有了一段你我美丽的相遇</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('101', '<p>\r\n	<strong>愿余生有你相伴</strong>\r\n</p>\r\n<p>\r\n	<strong> 早晨一份早餐 </strong>\r\n</p>		<p>\r\n<strong>夜半一声晚安</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('102', '<p>\r\n	<strong>我的心像一本书</strong>\r\n</p>\r\n<p>\r\n	<strong>每一行 </strong>\r\n</p>		<p>\r\n<strong>都明明白白写着个你</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('103', '<p>\r\n	<strong>这么些天</strong>\r\n</p>\r\n<p>\r\n	<strong> 你知不知道 </strong>\r\n</p>		<p>\r\n<strong>我 度日如年</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('104', '<p>\r\n	<strong>如果爱是口中的鱼</strong>\r\n</p>\r\n<p>\r\n	<strong> 那你就是喉中的刺 </strong>\r\n</p>		<p>\r\n<strong>让我拔不出也咽不下</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('105', '<p>\r\n	<strong>在岁月织就的场景里走得匆匆忙忙</strong>\r\n</p>\r\n<p>\r\n	<strong> 能停下来与你邂逅一场 </strong>\r\n</p>		<p>\r\n<strong>也不算徒劳奔忙</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('106', '<p>\r\n	<strong>你若成仙我陪你成仙</strong>\r\n</p>\r\n<p>\r\n	<strong> 你若成佛我伴你成佛</strong>\r\n</p>		<p>\r\n<strong>你若投胎 我不答应</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('107', '<p>\r\n	<strong>不管今天是41℃的晴天</strong>\r\n</p>\r\n<p>\r\n	<strong> 还是零下14℃的雪天 </strong>\r\n</p>		<p>\r\n<strong>我还是喜欢你</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('108', '<p>\r\n	<strong>我为你夺了一座城</strong>\r\n</p>\r\n<p>\r\n	<strong> 还未让你知道就被抢了 </strong>\r\n</p>		<p>\r\n<strong>难过的是因为夺不回来了</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('109', '<p>\r\n	<strong>你的眼睛宛如天空般澄澈</strong>\r\n</p>\r\n<p>\r\n	<strong> 你的笑容如同蜜糖般甜美 </strong>\r\n</p>		<p>\r\n<strong>我期待与你，在温柔的风中相遇</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('110', '<p>\r\n	<strong>我的心本是一潭死水</strong>\r\n</p>\r\n<p>\r\n	<strong> 你的出现 </strong>\r\n</p>		<p>\r\n<strong>泛起涟漪</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('111', '<p>\r\n	<strong>我还是想你</strong>\r\n</p>\r\n<p>\r\n	<strong>像昼夜交替 </strong>\r\n</p>		<p>\r\n<strong>无有间断</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('112', '<p>\r\n	<strong>最贵的电话费</strong>\r\n</p>\r\n<p>\r\n	<strong> 不是长途加漫游 </strong>\r\n</p>		<p>\r\n<strong>而是对方一直在通话中</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('113', '<p>\r\n	<strong>生活里让人难受的事太多了</strong>\r\n</p>\r\n<p>\r\n	<strong> 我就不记着了，做一条金鱼 </strong>\r\n</p>		<p>\r\n<strong>我记着你就好</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('114', '<p>\r\n	<strong>下雨天的车站</strong>\r\n</p>\r\n<p>\r\n	<strong> 没等到车等到你 </strong>\r\n</p>		<p>\r\n<strong>心情也会无以伦比的美丽</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('115', '<p>\r\n	<strong>只想看你</strong>\r\n</p>\r\n<p>\r\n	<strong> 怎么看 </strong>\r\n</p>		<p>\r\n<strong>都看不够</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('116', '<p>\r\n	<strong>外面下雨了</strong>\r\n</p>\r\n<p>\r\n	<strong> 你不在我身边 </strong>\r\n</p>		<p>\r\n<strong>风也是你，雨也是你</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('117', '<p>\r\n	<strong>愿你</strong>\r\n</p>\r\n<p>\r\n	<strong> 初心不忘 </strong>\r\n</p>		<p>\r\n<strong>前途无量</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('118', '<p>\r\n	<strong>我爱你</strong>\r\n</p>\r\n<p>\r\n	<strong> 这件事 </strong>\r\n</p>		<p>\r\n<strong>会是一辈子</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('119', '<p>\r\n	<strong>我所希翼的感情，不过就是</strong>\r\n</p>\r\n<p>\r\n	<strong> 心上人是眼前人，枕边人是身边人 </strong>\r\n</p>		<p>\r\n<strong>最后却变成了，眼前人和枕边人都变成离人</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');
INSERT INTO `t_content` VALUES ('120', '<p>\r\n	<strong>我是个贪婪的人</strong>\r\n</p>\r\n<p>\r\n	<strong> 想要你全部的温柔 </strong>\r\n</p>		<p>\r\n<strong>和他们所有的羡慕</strong>\r\n</p>', '10000', '1', '暖心到哭的三行情书', '0', '2', '', '0', '', '0', '0', '1', '2019-12-17 15:56:05', '2019-12-17 15:56:08', '');

-- ----------------------------
-- Table structure for t_content_category
-- ----------------------------
DROP TABLE IF EXISTS `t_content_category`;
CREATE TABLE `t_content_category` (
  `id` int(11) NOT NULL,
  `category_name` varchar(32) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '默认为0',
  `depth` int(11) DEFAULT NULL COMMENT '一级分类为1，逐渐递增',
  `category_status` int(11) DEFAULT NULL COMMENT '0-禁用   1-启用',
  `priority` int(11) DEFAULT NULL COMMENT '数值越大，同级显示越靠前',
  `program_url` varchar(512) DEFAULT NULL,
  `program_image_url` varchar(512) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容分类表';

-- ----------------------------
-- Records of t_content_category
-- ----------------------------
INSERT INTO `t_content_category` VALUES ('10000', '三行情书', '0', '1', '1', '1', null, 'https://image.weilanwl.com/color2.0/plugin/sylb2244.jpg', null, null);
INSERT INTO `t_content_category` VALUES ('20000', '内涵段子', '0', '1', '1', '2', null, 'https://image.weilanwl.com/color2.0/plugin/wdh2236.jpg', null, null);
INSERT INTO `t_content_category` VALUES ('30000', '土味情话', '0', '1', '1', '3', '', 'https://image.weilanwl.com/color2.0/plugin/qpct2148.jpg', null, null);

-- ----------------------------
-- Table structure for t_content_thumb_up
-- ----------------------------
DROP TABLE IF EXISTS `t_content_thumb_up`;
CREATE TABLE `t_content_thumb_up` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content_id` int(11) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `thumb_status` int(11) DEFAULT NULL COMMENT '0-取消赞获踩  1-获赞/获踩数据',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_content_thumb_up
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `password` varchar(32) DEFAULT NULL,
  `qq_id` varchar(128) DEFAULT NULL,
  `wechat_id` varchar(128) DEFAULT NULL,
  `sina_id` varchar(128) DEFAULT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `avatar_img_url` varchar(1000) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL COMMENT '0-未知  1-男   2-女',
  `age` int(11) DEFAULT NULL,
  `phone` varbinary(20) DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  `vocation` varchar(30) DEFAULT NULL,
  `interest` varchar(50) DEFAULT NULL,
  `user_status` int(11) DEFAULT NULL COMMENT '管理员：1-启用  0-禁用',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `ip_address` varchar(24) DEFAULT NULL,
  `signature` varchar(32) DEFAULT NULL,
  `client_type` int(11) DEFAULT NULL COMMENT '1-安卓   2-IOS',
  `token` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '', null, 'omOGQ4uaX0s-ve7BnrTimaQeCZO8', null, 'thank', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKeY4HjTSJWialnpS3ou2tuYCkIbnIE5W4owcFSbn5aT0y0oEerFM4udLrePRZ45o2BwB5zPtZJiavw/132', '1', null, '', null, null, null, null, '2020-01-11 12:48:05', '2020-02-13 15:49:31', '127.0.0.1', null, null, 'eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJ3ZWNoYXQiLCJzdWIiOiJvbU9HUTR1YVgwcy12ZTdCbnJUaW1hUWVDWk84IiwiZXhwIjoxNTgyMTg0OTcxLCJpYXQiOjE1ODE1ODAxNzEsImp0aSI6IndlY2hhdCJ9.tyAWF1ZrkueRNeFyJ53-yfyckoOtbK0K2KPckJdlyZA');

-- ----------------------------
-- Table structure for t_view_record
-- ----------------------------
DROP TABLE IF EXISTS `t_view_record`;
CREATE TABLE `t_view_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) DEFAULT NULL,
  `content_id` int(11) DEFAULT NULL COMMENT '0-内容    1-用户',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_view_record
-- ----------------------------
INSERT INTO `t_view_record` VALUES ('1', '1', '13', '2020-01-11 12:49:00', null, '127.0.0.1');
INSERT INTO `t_view_record` VALUES ('2', null, '100', '2021-08-21 21:33:53', null, '192.168.100.1');
