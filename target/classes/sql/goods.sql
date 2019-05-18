
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `goods_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '商品的图片',
  `goods_detail` longtext CHARACTER SET utf8mb4 COLLATE utf8_general_ci  COMMENT '商品的详情介绍',
  `goods_price` decimal(10, 2) DEFAULT 0.00 COMMENT '商品单价',
  `goods_stock` int(11) DEFAULT 0 COMMENT '商品库存，-1表示没有限制',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4;

INSERT INTO `goods` VALUES (1, 'iPhone X', 'Apple iPhone X (A1865) 64GB 深空灰色 移动联通电信4G手机', '/img/iphonex.png', 'Apple iPhone X (A1865) 64GB 深空灰色 移动联通电信4G手机', 8388.00, 8888);
INSERT INTO `goods` VALUES (2, 'MacBook Pro', 'Apple MacBook Pro 15.4英寸笔记本电脑 银色', '/img/macbookpro.png', 'i7处理器，大容量固态硬盘，外设接口丰富，配备绚丽的retina显示屏，强大而专业！选购AppleCare Protection Plan，获得长达3年来自Apple的额外硬件服务选项。购买勾选：保障服务、原厂保3年。', 13599.00, 6666);

