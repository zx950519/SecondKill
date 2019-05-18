
DROP TABLE IF EXISTS `miaosha_goods`;
CREATE TABLE `miaosha_goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀的商品',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `miaosha_price` decimal(10, 2) DEFAULT 0.00 COMMENT '秒杀价',
  `stock_count` int(11) DEFAULT NULL COMMENT '库存数量',
  `start_date` datetime(0) DEFAULT NULL COMMENT '秒杀开始时间',
  `end_date` datetime(0) DEFAULT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4;


INSERT INTO `miaosha_goods` VALUES (1, 1, 0.01, 9, '2019-05-05 15:18:00', '2020-02-13 14:00:18');
INSERT INTO `miaosha_goods` VALUES (2, 2, 0.01, 9, '2019-05-05 14:00:00', '2020-02-13 14:00:18');
