
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `delivery_addr_id` bigint(20) DEFAULT NULL COMMENT '收货地址ID',
  `goods_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '冗余过来的商品名称',
  `goods_count` int(11) DEFAULT NULL COMMENT '商品数量',
  `goods_price` decimal(10, 2) DEFAULT 0.00 COMMENT '商品单价',
  `order_channel` tinyint(4) DEFAULT 0 COMMENT '1pc, 2android, 3ios',
  `status` tinyint(4) DEFAULT 0 COMMENT '订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成',
  `create_date` datetime(0) DEFAULT NULL COMMENT '订单的创建时间',
  `pay_date` datetime(0) DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4;
