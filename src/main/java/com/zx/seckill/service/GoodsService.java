package com.zx.seckill.service;

import java.util.List;

import com.zx.seckill.domain.MiaoshaGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.seckill.dao.GoodsDao;
import com.zx.seckill.vo.GoodsVo;

/**
 * @Description:商品提供的服务
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */

@Service
public class GoodsService {
	
	@Autowired
	GoodsDao goodsDao;		// 导入商品可能存在的操作
	// 获取商品集合
	public List<GoodsVo> listGoodsVo(){
		return goodsDao.listGoodsVo();
	}
	// 根据商品id获取指定商品
	public GoodsVo getGoodsVoByGoodsId(long goodsId) {
		return goodsDao.getGoodsVoByGoodsId(goodsId);
	}
	// 减少某商品的库存
	public boolean reduceStock(GoodsVo goods) {
		MiaoshaGoods g = new MiaoshaGoods();
		g.setGoodsId(goods.getId());
		int ret = goodsDao.reduceStock(g);
		return ret > 0;		// 如果无法减少库存，返回false
	}
	// 重置商品的库存(用于初始化)
	public void resetStock(List<GoodsVo> goodsList) {
		for(GoodsVo goods : goodsList ) {
			MiaoshaGoods g = new MiaoshaGoods();
			g.setGoodsId(goods.getId());
			g.setStockCount(goods.getStockCount());
			goodsDao.resetStock(g);
		}
	}
	
	
	
}
