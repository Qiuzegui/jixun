# Host: localhost  (Version: 5.5.22)
# Date: 2019-08-08 23:22:13
# Generator: MySQL-Front 5.3  (Build 4.9)

/*!40101 SET NAMES utf8 */;

#
# Source for table "cart_item"
#

DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `product_sku_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `product_user_id` bigint(20) DEFAULT NULL COMMENT '商品卖主',
  `quantity` int(11) DEFAULT NULL COMMENT '购买数量',
  `price` decimal(10,2) DEFAULT NULL COMMENT '添加到购物车的价格',
  `sp1` varchar(200) DEFAULT NULL COMMENT '销售属性1',
  `sp2` varchar(200) DEFAULT NULL COMMENT '销售属性2',
  `sp3` varchar(200) DEFAULT NULL COMMENT '销售属性3',
  `product_pic` varchar(1000) DEFAULT NULL COMMENT '商品主图',
  `product_name` varchar(500) DEFAULT NULL COMMENT '商品名称',
  `product_sub_title` varchar(500) DEFAULT NULL COMMENT '商品副标题（卖点）',
  `member_nickname` varchar(500) DEFAULT NULL COMMENT '会员昵称',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_status` int(1) DEFAULT '0' COMMENT '是否删除',
  `product_category_id` bigint(20) DEFAULT NULL COMMENT '商品分类',
  `product_brand` varchar(200) DEFAULT NULL,
  `product_sn` varchar(200) DEFAULT NULL,
  `product_attr` varchar(500) DEFAULT NULL COMMENT '商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='购物车表';

#
# Data for table "cart_item"
#

INSERT INTO `cart_item` VALUES (2,5,0,2,1,1,3999.00,'string','string','string','string','string','string','TOYOU','2019-03-31 15:45:30','2019-03-31 15:36:22',0,0,'string','string','string');

#
# Source for table "index_advertise"
#

DROP TABLE IF EXISTS `index_advertise`;
CREATE TABLE `index_advertise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '轮播位置：0->PC首页轮播；1->app首页轮播',
  `pic` varchar(500) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '上下线状态：0->下线；1->上线',
  `click_count` int(11) DEFAULT NULL COMMENT '点击数',
  `order_count` int(11) DEFAULT NULL COMMENT '下单数',
  `url` varchar(500) DEFAULT NULL COMMENT '链接地址',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='首页轮播广告表';

#
# Data for table "index_advertise"
#

INSERT INTO `index_advertise` VALUES (1,'广告图1',1,'http://localhost:9999/advertiset-one.jpg',NULL,NULL,1,0,0,NULL,NULL,0),(2,'广告图2',1,'http://localhost:9999/advertiset-two.png',NULL,NULL,1,0,0,NULL,NULL,0),(3,'广告图3',1,'http://localhost:9999/advertiset-three.jpg',NULL,NULL,1,0,0,NULL,NULL,0);

#
# Source for table "order"
#

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `member_id` bigint(20) NOT NULL,
  `coupon_id` bigint(20) DEFAULT NULL,
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `create_time` datetime DEFAULT NULL COMMENT '提交时间',
  `member_username` varchar(64) DEFAULT NULL COMMENT '用户帐号',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '应付金额（实际支付金额）',
  `freight_amount` decimal(10,2) DEFAULT NULL COMMENT '运费金额',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付方式：0->未支付；1->支付宝；2->微信',
  `source_type` int(1) DEFAULT NULL COMMENT '订单来源：0->PC订单；1->app订单',
  `status` int(1) DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
  `order_type` int(1) DEFAULT NULL COMMENT '订单类型：0->正常订单；1->秒杀订单',
  `auto_confirm_day` int(11) DEFAULT NULL COMMENT '自动确认时间（天）',
  `receiver_name` varchar(100) NOT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(32) NOT NULL COMMENT '收货人电话',
  `receiver_post_code` varchar(32) DEFAULT NULL COMMENT '收货人邮编',
  `receiver_province` varchar(32) DEFAULT NULL COMMENT '省份/直辖市',
  `receiver_city` varchar(32) DEFAULT NULL COMMENT '城市',
  `receiver_region` varchar(32) DEFAULT NULL COMMENT '区',
  `receiver_detail_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `note` varchar(500) DEFAULT NULL COMMENT '订单备注',
  `confirm_status` int(1) DEFAULT NULL COMMENT '确认收货状态：0->未确认；1->已确认',
  `delete_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除状态：0->未删除；1->已删除',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receive_time` datetime DEFAULT NULL COMMENT '确认收货时间',
  `comment_time` datetime DEFAULT NULL COMMENT '评价时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='订单表';

#
# Data for table "order"
#

INSERT INTO `order` VALUES (5,2,NULL,'2019041201012019041288527823','2019-04-12 20:44:01','13750010415',0.00,0.00,NULL,1,1,0,0,NULL,'邱泽桂','13750010415','518040','广东省','深圳市','福田区','竹子林',NULL,0,0,NULL,NULL,NULL,NULL,NULL),(6,2,NULL,'2019041201012019041288527824','2019-04-13 20:44:01','13750010415',0.00,0.00,NULL,1,1,0,0,NULL,'邱泽桂','13750010415','518040','广东省','深圳市',NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL);

#
# Source for table "order_item"
#

DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `product_userid` bigint(20) DEFAULT NULL COMMENT '商品卖主',
  `product_id` bigint(20) DEFAULT NULL,
  `product_pic` varchar(500) DEFAULT NULL,
  `product_name` varchar(200) DEFAULT NULL,
  `product_sn` varchar(64) DEFAULT NULL,
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '销售价格',
  `product_quantity` int(11) DEFAULT NULL COMMENT '购买数量',
  `product_category_id` bigint(20) DEFAULT NULL COMMENT '商品分类id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='订单中所包含的商品';

#
# Data for table "order_item"
#

INSERT INTO `order_item` VALUES (4,5,'2019041201012019041288527823',1,1,'string','string',NULL,3999.00,1,NULL);

#
# Source for table "order_operate_history"
#

DROP TABLE IF EXISTS `order_operate_history`;
CREATE TABLE `order_operate_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `operate_man` varchar(100) DEFAULT NULL COMMENT '操作人：用户；系统；后台管理员',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `order_status` int(1) DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='订单操作历史记录';

#
# Data for table "order_operate_history"
#


#
# Source for table "order_return_apply"
#

DROP TABLE IF EXISTS `order_return_apply`;
CREATE TABLE `order_return_apply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `company_address_id` bigint(20) DEFAULT NULL COMMENT '收货地址表id',
  `product_id` bigint(20) DEFAULT NULL COMMENT '退货商品id',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `create_time` datetime DEFAULT NULL COMMENT '申请时间',
  `member_username` varchar(64) DEFAULT NULL COMMENT '会员用户名',
  `return_amount` decimal(10,2) DEFAULT NULL COMMENT '退款金额',
  `return_name` varchar(100) DEFAULT NULL COMMENT '退货人姓名',
  `return_phone` varchar(100) DEFAULT NULL COMMENT '退货人电话',
  `status` int(1) DEFAULT NULL COMMENT '申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝',
  `handle_time` datetime DEFAULT NULL COMMENT '处理时间',
  `product_pic` varchar(500) DEFAULT NULL COMMENT '商品图片',
  `product_name` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `product_brand` varchar(200) DEFAULT NULL COMMENT '商品品牌',
  `product_attr` varchar(500) DEFAULT NULL COMMENT '商品销售属性：颜色：红色；尺码：xl;',
  `product_count` int(11) DEFAULT NULL COMMENT '退货数量',
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '商品单价',
  `product_real_price` decimal(10,2) DEFAULT NULL COMMENT '商品实际支付单价',
  `reason` varchar(200) DEFAULT NULL COMMENT '原因',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `proof_pics` varchar(1000) DEFAULT NULL COMMENT '凭证图片，以逗号隔开',
  `handle_note` varchar(500) DEFAULT NULL COMMENT '处理备注',
  `handle_man` varchar(100) DEFAULT NULL COMMENT '处理人员',
  `receive_man` varchar(100) DEFAULT NULL COMMENT '收货人',
  `receive_time` datetime DEFAULT NULL COMMENT '收货时间',
  `receive_note` varchar(500) DEFAULT NULL COMMENT '收货备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='订单退货申请';

#
# Data for table "order_return_apply"
#


#
# Source for table "order_return_reason"
#

DROP TABLE IF EXISTS `order_return_reason`;
CREATE TABLE `order_return_reason` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '退货类型',
  `sort` int(11) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '状态：0->不启用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='退货原因表';

#
# Data for table "order_return_reason"
#

INSERT INTO `order_return_reason` VALUES (1,'质量问题',1,0,'2018-10-17 10:00:45'),(2,'尺码太大',1,1,'2018-10-17 10:01:03'),(3,'颜色不喜欢',1,1,'2018-10-17 10:01:13'),(4,'7天无理由退货',1,1,'2018-10-17 10:01:47'),(5,'价格问题',1,0,'2018-10-17 10:01:57'),(12,'发票问题',0,1,'2018-10-19 16:28:36'),(13,'其他问题',0,1,'2018-10-19 16:28:51'),(14,'物流问题',0,1,'2018-10-19 16:29:01'),(15,'售后问题',0,1,'2018-10-19 16:29:11');

#
# Source for table "product"
#

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '卖家id',
  `proWX` varchar(50) DEFAULT NULL COMMENT '该产品卖家联系方式',
  `product_category_id` bigint(20) DEFAULT NULL,
  `name` varchar(64) NOT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL COMMENT '发布时间',
  `delete_status` int(1) DEFAULT NULL COMMENT '删除状态：0->未删除；1->已删除',
  `publish_status` int(1) DEFAULT NULL COMMENT '上架状态：0->下架；1->上架',
  `new_status` int(1) DEFAULT NULL COMMENT '新品状态:0->不是新品；1->新品',
  `recommand_status` int(1) DEFAULT NULL COMMENT '推荐状态；0->不推荐；1->推荐',
  `verify_status` int(1) DEFAULT NULL COMMENT '审核状态：0->未审核；1->审核通过',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `price` decimal(10,2) DEFAULT NULL,
  `sub_title` varchar(255) DEFAULT NULL COMMENT '副标题',
  `description` varchar(400) DEFAULT '' COMMENT '商品描述',
  `original_price` decimal(10,2) DEFAULT NULL COMMENT '市场价',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `stock_lock` int(11) DEFAULT NULL COMMENT '已被预订的数量(待付款状态)',
  `unit` varchar(16) DEFAULT NULL COMMENT '单位',
  `service_ids` varchar(64) DEFAULT NULL COMMENT '以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮',
  `keywords` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `album_pics` varchar(768) DEFAULT NULL COMMENT '画册图片，连产品图片限制为5张，以逗号分割',
  `detail_title` varchar(255) DEFAULT NULL,
  `detail_desc` text,
  `brand_name` varchar(255) DEFAULT NULL COMMENT '品牌名称',
  `product_category_name` varchar(255) DEFAULT NULL COMMENT '商品分类名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='商品信息';

#
# Data for table "product"
#

INSERT INTO `product` VALUES (2,1,'13750010415',13,'你几点能到记得记得你发福建地方几家地方级机房几点见覅啊发发发法','http://232l5h5507.iok.la/1.jpg','2019-04-12 19:44:01',0,1,1,1,1,NULL,3998.00,'小米出品','小米9，是小米公司旗下一款智能手机，内部代号是战斗天使，采用：高通骁龙855旗舰平台，最高配备12GB内存+256GB存储；搭载索尼三摄全焦段镜头1200万人像镜头， [1-3]  4800万像素主摄，1600万微距+超广角；搭载6.39英寸三星AMOLED水滴屏。',3999.00,30,24,NULL,'3',NULL,NULL,'http://ji-xun-mall.oss-cn-shenzhen.aliyuncs.com/productimg/15567840555921373.png?Expires=1588320208&OSSAccessKeyId=LTAIjFPIHC7TOqzO&Signature=mi4nB0tSuQKcxlzw28trQABmlDU%3D,http://ji-xun-mall.oss-cn-shenzhen.aliyuncs.com/productimg/1556784207705363.png?Expires=1588320208&OSSAccessKeyId=LTAIjFPIHC7TOqzO&Signature=cCp9UzI%2BEACRzlgmar5tu%2F53qJc%3D',NULL,NULL,'小米','手机通讯'),(5,2,'13750010415',21,'修改测试4','http://ji-xun-mall.oss-cn-shenzhen.aliyuncs.com/productimg/15567910946511498.png?Expires=1588327095&OSSAccessKeyId=LTAIjFPIHC7TOqzO&Signature=hFu3wO0enP04hFlytSUtAb%2F9dog%3D','2019-05-02 17:58:15',0,1,1,1,1,NULL,199.00,NULL,'13750010415',NULL,1,0,NULL,NULL,NULL,NULL,'http://ji-xun-mall.oss-cn-shenzhen.aliyuncs.com/productimg/1556791094651995.png?Expires=1588327095&OSSAccessKeyId=LTAIjFPIHC7TOqzO&Signature=Brv8qyujhu2MwM%2BmPyJZxnoa6cY%3D,http://ji-xun-mall.oss-cn-shenzhen.aliyuncs.com/productimg/1556791094651697.png?Expires=1588327095&OSSAccessKeyId=LTAIjFPIHC7TOqzO&Signature=XFzUMlS0ya5cesMsakl3Lgyvg4w%3D',NULL,NULL,NULL,NULL),(7,2,'13750010415',13,'你几点能到记得记得你发福建地方几家地方级机房几点见覅啊发发发法','http://232l5h5507.iok.la/1.jpg','2019-04-12 19:44:01',0,1,1,1,1,NULL,4999.00,'小米出品','小米9，是小米公司旗下一款智能手机，内部代号是战斗天使，采用：高通骁龙855旗舰平台，最高配备12GB内存+256GB存储；搭载索尼三摄全焦段镜头1200万人像镜头， [1-3]  4800万像素主摄，1600万微距+超广角；搭载6.39英寸三星AMOLED水滴屏。',3999.00,30,24,NULL,'3',NULL,NULL,'http://ji-xun-mall.oss-cn-shenzhen.aliyuncs.com/productimg/15567840555921373.png?Expires=1588320208&OSSAccessKeyId=LTAIjFPIHC7TOqzO&Signature=mi4nB0tSuQKcxlzw28trQABmlDU%3D,http://ji-xun-mall.oss-cn-shenzhen.aliyuncs.com/productimg/1556784207705363.png?Expires=1588320208&OSSAccessKeyId=LTAIjFPIHC7TOqzO&Signature=cCp9UzI%2BEACRzlgmar5tu%2F53qJc%3D',NULL,NULL,'小米','手机通讯'),(9,2,'13750010415',6,'发布搜索测试','http://ji-xun-mall.oss-cn-shenzhen.aliyuncs.com/productimg/1557468175053731.png?Expires=1589004176&OSSAccessKeyId=LTAIjFPIHC7TOqzO&Signature=3%2BadeyFwMraKfA15Tog2OKiWUN0%3D','2019-05-10 14:02:56',0,1,1,1,1,NULL,111.00,NULL,'测试测试四测试',NULL,11,0,NULL,NULL,NULL,NULL,'http://ji-xun-mall.oss-cn-shenzhen.aliyuncs.com/productimg/15574681750531313.png?Expires=1589004176&OSSAccessKeyId=LTAIjFPIHC7TOqzO&Signature=Xi6OkKBmqS%2B%2BHS8n9UiRDhr7ri8%3D',NULL,NULL,NULL,NULL);

#
# Source for table "product_category"
#

DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级分类的编号：0表示一级分类',
  `name` varchar(64) DEFAULT NULL,
  `level` int(1) DEFAULT NULL COMMENT '分类级别：0->1级；1->2级',
  `product_count` int(11) DEFAULT NULL,
  `product_unit` varchar(64) DEFAULT NULL,
  `nav_status` int(1) DEFAULT NULL COMMENT '是否显示在导航栏：0->不显示；1->显示',
  `show_status` int(1) DEFAULT NULL COMMENT '显示状态：0->不显示；1->显示',
  `sort` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `keywords` varchar(255) DEFAULT NULL,
  `description` text COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='产品分类';

#
# Data for table "product_category"
#

INSERT INTO `product_category` VALUES (1,0,'服装',0,100,'件',1,1,1,NULL,'服装','服装分类'),(2,0,'手机数码',0,100,'件',1,1,1,NULL,'手机数码','手机数码'),(3,0,'家用电器',0,100,'件',1,1,1,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/subject_cate_jiadian.png','家用电器','家用电器'),(4,0,'家具家装',0,100,'件',1,1,1,NULL,'家具家装','家具家装'),(5,0,'汽车用品',0,100,'件',1,1,1,NULL,'汽车用品','汽车用品'),(6,1,'外套',1,100,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_waitao.png','外套','外套'),(7,1,'T恤',1,100,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_tshirt.png','T恤','T恤'),(8,1,'休闲裤',1,100,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_xiuxianku.png','休闲裤','休闲裤'),(9,1,'牛仔裤',1,100,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_niuzaiku.png','牛仔裤','牛仔裤'),(10,1,'衬衫',1,100,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_chenshan.png','衬衫','衬衫分类'),(11,12,'家电子分类1',1,1,'string',0,1,0,'string','string','string'),(12,12,'家电子分类2',1,1,'string',0,1,0,'string','string','string'),(13,2,'手机通讯',1,0,'件',1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_shouji.png','手机通讯','手机通讯'),(14,1,'男鞋',1,0,NULL,0,0,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_xie.png',NULL,NULL),(15,2,'手机配件',1,0,NULL,1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_peijian.png','手机配件','手机配件'),(16,2,'摄影摄像',1,0,NULL,1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_sheying.png',NULL,NULL),(17,2,'影音娱乐',1,0,NULL,1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_yule.png',NULL,NULL),(18,2,'数码配件',1,0,NULL,1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_yule.png',NULL,NULL),(19,2,'智能设备',1,0,NULL,1,1,0,'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/product_cate_zhineng.png',NULL,NULL),(20,3,'电视',1,0,NULL,1,1,0,NULL,NULL,NULL),(21,3,'空调',1,0,NULL,1,1,0,NULL,NULL,NULL),(22,3,'洗衣机',1,0,NULL,1,1,0,NULL,NULL,NULL),(23,3,'冰箱',1,0,NULL,1,1,0,NULL,NULL,NULL),(24,3,'厨卫大电',1,0,NULL,1,1,0,NULL,NULL,NULL),(25,3,'厨房小电',1,0,NULL,0,0,0,NULL,NULL,NULL),(26,3,'生活电器',1,0,NULL,0,0,0,NULL,NULL,NULL),(27,3,'个护健康',1,0,NULL,0,0,0,NULL,NULL,NULL),(28,4,'厨房卫浴',1,0,NULL,1,1,0,NULL,NULL,NULL),(29,4,'灯饰照明',1,0,NULL,1,1,0,NULL,NULL,NULL),(30,4,'五金工具',1,0,NULL,1,1,0,NULL,NULL,NULL),(31,4,'卧室家具',1,0,NULL,1,1,0,NULL,NULL,NULL),(32,4,'客厅家具',1,0,NULL,1,1,0,NULL,NULL,NULL),(33,5,'全新整车',1,0,NULL,1,1,0,NULL,NULL,NULL),(34,5,'车载电器',1,0,NULL,1,1,0,NULL,NULL,NULL),(35,5,'维修保养',1,0,NULL,1,1,0,NULL,NULL,NULL),(36,5,'汽车装饰',1,0,NULL,1,1,0,NULL,NULL,NULL);

#
# Source for table "test_order"
#

DROP TABLE IF EXISTS `test_order`;
CREATE TABLE `test_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `member_id` bigint(20) NOT NULL,
  `coupon_id` bigint(20) DEFAULT NULL,
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `create_time` datetime DEFAULT NULL COMMENT '提交时间',
  `member_username` varchar(64) DEFAULT NULL COMMENT '用户帐号',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '应付金额（实际支付金额）',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付方式：0->未支付；1->支付宝；2->微信',
  `status` int(1) DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
  `auto_confirm_day` int(11) DEFAULT NULL COMMENT '自动确认时间（天）',
  `confirm_status` int(1) DEFAULT NULL COMMENT '确认收货状态：0->未确认；1->已确认',
  `delete_status` int(1) NOT NULL DEFAULT '0' COMMENT '删除状态：0->未删除；1->已删除',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `receive_time` datetime DEFAULT NULL COMMENT '确认收货时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='订单表';

#
# Data for table "test_order"
#


#
# Source for table "user_address"
#

DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '收货人名称',
  `phone_number` varchar(64) DEFAULT NULL,
  `default_status` int(1) DEFAULT NULL COMMENT '是否为默认',
  `post_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `province` varchar(100) DEFAULT NULL COMMENT '省份/直辖市',
  `city` varchar(100) DEFAULT NULL COMMENT '城市',
  `region` varchar(100) DEFAULT NULL COMMENT '区',
  `detail_address` varchar(128) DEFAULT NULL COMMENT '详细地址(街道)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='会员收货地址表';

#
# Data for table "user_address"
#

INSERT INTO `user_address` VALUES (1,1,'邱泽桂','13750010415',NULL,'518040','广东省','深圳市','福田区','竹子林'),(2,1,'张三','13750010415',NULL,'510000','广东省','广州市','海珠区','新港中路397号'),(3,1,'张三',NULL,NULL,'510000','广东省','广州市','海珠区','新港中路397号'),(4,2,'张三',NULL,NULL,'510000','广东省','广州市','海珠区','新港中路397号');

#
# Source for table "user_admin"
#

DROP TABLE IF EXISTS `user_admin`;
CREATE TABLE `user_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台用户表';

#
# Data for table "user_admin"
#


#
# Source for table "user_admin_login_log"
#

DROP TABLE IF EXISTS `user_admin_login_log`;
CREATE TABLE `user_admin_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `user_agent` varchar(100) DEFAULT NULL COMMENT '浏览器登录类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台用户登录日志表';

#
# Data for table "user_admin_login_log"
#


#
# Source for table "user_admin_role"
#

DROP TABLE IF EXISTS `user_admin_role`;
CREATE TABLE `user_admin_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `admin_count` int(11) DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台用户角色表';

#
# Data for table "user_admin_role"
#


#
# Source for table "user_member"
#

DROP TABLE IF EXISTS `user_member`;
CREATE TABLE `user_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_level_id` bigint(20) DEFAULT NULL,
  `openid` varchar(255) DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(64) DEFAULT NULL COMMENT '手机号码',
  `status` int(1) DEFAULT NULL COMMENT '帐号启用状态:0->禁用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `gender` int(1) DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `city` varchar(64) DEFAULT NULL COMMENT '所做城市',
  `job` varchar(100) DEFAULT NULL COMMENT '职业',
  `personalized_signature` varchar(200) DEFAULT NULL COMMENT '个性签名',
  `source_type` int(1) DEFAULT NULL COMMENT '用户来源',
  `integration` int(11) DEFAULT NULL COMMENT '积分',
  `growth` int(11) DEFAULT NULL COMMENT '成长值',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_phone` (`phone`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='会员表';

#
# Data for table "user_member"
#

INSERT INTO `user_member` VALUES (1,0,NULL,'13750010415','$2a$10$BG1AIPeJsG4WGe3DXC/7m./2DlvTri6rilS9TIjLFiiPKuiHSPV6K','TOYOU','13750010415',1,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,NULL,'oCZmI07VGfnMHn8JM0gySR0vKBcc',NULL,NULL,'Q.zg',NULL,NULL,NULL,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLoJTSB95niaeiaia6nFZeibIH10IwKQQjapepEtgHvuMeA4n6u7gz6Lnw3xWVEXuicVc2x7Mzw3G4gbog/132',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

#
# Source for table "user_member_level"
#

DROP TABLE IF EXISTS `user_member_level`;
CREATE TABLE `user_member_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `growth_point` int(11) DEFAULT NULL,
  `default_status` int(1) DEFAULT NULL COMMENT '是否为默认等级：0->不是；1->是',
  `free_freight_point` decimal(10,2) DEFAULT NULL COMMENT '免运费标准',
  `comment_growth_point` int(11) DEFAULT NULL COMMENT '每次评价获取的成长值',
  `priviledge_free_freight` int(1) DEFAULT NULL COMMENT '是否有免邮特权',
  `priviledge_sign_in` int(1) DEFAULT NULL COMMENT '是否有签到特权',
  `priviledge_comment` int(1) DEFAULT NULL COMMENT '是否有评论获奖励特权',
  `priviledge_promotion` int(1) DEFAULT NULL COMMENT '是否有专享活动特权',
  `priviledge_member_price` int(1) DEFAULT NULL COMMENT '是否有会员价格特权',
  `priviledge_birthday` int(1) DEFAULT NULL COMMENT '是否有生日特权',
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='会员等级表';

#
# Data for table "user_member_level"
#


#
# Source for table "user_member_login_log"
#

DROP TABLE IF EXISTS `user_member_login_log`;
CREATE TABLE `user_member_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  `login_type` int(1) DEFAULT NULL COMMENT '登录类型：0->PC；1->android;2->ios;3->小程序',
  `province` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='会员登录记录';

#
# Data for table "user_member_login_log"
#

