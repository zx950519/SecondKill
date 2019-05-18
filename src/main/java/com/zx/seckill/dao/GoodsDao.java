package com.zx.seckill.dao;

import java.util.List;

import com.zx.seckill.domain.MiaoshaGoods;
import com.zx.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Description:商品Dao
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */

@Mapper
public interface GoodsDao {
	
	@Select("select g.*, mg.stock_count, mg.start_date, mg.end_date, mg.miaosha_price "+
			"from miaosha_goods mg left join goods g "+
			"on mg.goods_id = g.id")
	public List<GoodsVo> listGoodsVo();     // 列出所有商品

	@Select("select g.*,mg.stock_count, mg.start_date, mg.end_date, mg.miaosha_price "+
			"from miaosha_goods mg left join goods g on mg.goods_id = g.id "+
			"where g.id = #{goodsId}")
	public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);     // 列出指定id的商品

	@Update("update miaosha_goods " +
			"set stock_count = stock_count - 1 " +
			"where goods_id = #{goodsId} and stock_count > 0")
	public int reduceStock(MiaoshaGoods g);     // 减少商品库存

	@Update("update miaosha_goods " +
			"set stock_count = #{stockCount} " +
			"where goods_id = #{goodsId}")
	public int resetStock(MiaoshaGoods g);     // 重置商品库存

}
