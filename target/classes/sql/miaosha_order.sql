
CREATE TABLE `miaosha_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `order_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4;
