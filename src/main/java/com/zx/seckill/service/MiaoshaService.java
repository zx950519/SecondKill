package com.zx.seckill.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.zx.seckill.domain.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zx.seckill.domain.MiaoshaOrder;
import com.zx.seckill.domain.MiaoshaUser;
import com.zx.seckill.redis.MiaoshaKey;
import com.zx.seckill.redis.RedisService;
import com.zx.seckill.util.MD5Util;
import com.zx.seckill.util.UUIDUtil;
import com.zx.seckill.vo.GoodsVo;

/**
 * @Description:秒杀提供的服务
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */

@SuppressWarnings("restriction")
@Service
public class MiaoshaService {
	
	@Autowired
	GoodsService goodsService;
	@Autowired
	OrderService orderService;
	@Autowired
	RedisService redisService;

	@Transactional		// 具体的秒杀操作，注明为事务
	public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
		//减库存 下订单 写入秒杀订单
		boolean success = goodsService.reduceStock(goods);
		if(success) {
			// 库存减少成功，则创建订单
			return orderService.createOrder(user, goods);
		}else {
			setGoodsOver(goods.getId());	// 更改缓存中的库存状态，设置为卖空
			return null;
		}
	}
	// 获取秒杀结果
	public long getMiaoshaResult(Long userId, long goodsId) {
		MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
		if(order != null) { // 秒杀成功
			return order.getOrderId();
		}else {				// 秒杀失败
			boolean isOver = getGoodsOver(goodsId);
			if(isOver) {
				return -1;
			}else {
				return 0;
			}
		}
	}
	// 更新缓存，设置商品卖空
	private void setGoodsOver(Long goodsId) {
		redisService.set(MiaoshaKey.isGoodsOver, ""+goodsId, true);
	}
	// 判断商品是否卖空
	private boolean getGoodsOver(long goodsId) {
		return redisService.exists(MiaoshaKey.isGoodsOver, ""+goodsId);
	}
	// 重置
	public void reset(List<GoodsVo> goodsList) {
		goodsService.resetStock(goodsList);
		orderService.deleteOrders();
	}
	// 路径检查
	public boolean checkPath(MiaoshaUser user, long goodsId, String path) {
		if(user == null || path == null) {
			return false;
		}
		String pathOld = redisService.get(MiaoshaKey.getMiaoshaPath, ""+user.getId() + "_"+ goodsId, String.class);
		return path.equals(pathOld);
	}
	// 创建秒杀路径
	public String createMiaoshaPath(MiaoshaUser user, long goodsId) {
		if(user == null || goodsId <=0) {
			return null;
		}
		String str = MD5Util.md5(UUIDUtil.uuid()+"123456");
    	redisService.set(MiaoshaKey.getMiaoshaPath, ""+user.getId() + "_"+ goodsId, str);       // 更新秒杀路径
		return str;
	}
	// 创建用于秒杀校验码
	public BufferedImage createVerifyCode(MiaoshaUser user, long goodsId) {
		if(user == null || goodsId <=0) {
			return null;
		}
		int width = 80;
		int height = 32;
		//create the image
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// set the background color
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, width, height);
		// draw the border
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);
		// create a random instance to generate the codes
		Random rdm = new Random();
		// make some confusion
		for (int i = 0; i < 50; i++) {
			int x = rdm.nextInt(width);
			int y = rdm.nextInt(height);
			g.drawOval(x, y, 0, 0);
		}
		// generate a random code
		String verifyCode = generateVerifyCode(rdm);
		g.setColor(new Color(0, 100, 0));
		g.setFont(new Font("Candara", Font.BOLD, 24));
		g.drawString(verifyCode, 8, 24);
		g.dispose();
		//把验证码存到redis中
		int rnd = calc(verifyCode);
		redisService.set(MiaoshaKey.getMiaoshaVerifyCode, user.getId()+","+goodsId, rnd);
		//输出图片	
		return image;
	}
	// 检查校验码
	public boolean checkVerifyCode(MiaoshaUser user, long goodsId, int verifyCode) {
		if(user == null || goodsId <=0) {
			return false;
		}
		// 从Redis中获取验证码
		Integer codeOld = redisService.get(MiaoshaKey.getMiaoshaVerifyCode, user.getId()+","+goodsId, Integer.class);
		System.out.println("校验码为: "+codeOld);
		if(codeOld == null || codeOld - verifyCode != 0 ) {
			return false;
		}
		redisService.delete(MiaoshaKey.getMiaoshaVerifyCode, user.getId()+","+goodsId);     // 删除验证码
		return true;
	}
	// 计算校验码
	private static int calc(String exp) {
		try {
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			return (Integer)engine.eval(exp);
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	private static char[] ops = new char[] {'+', '-', '*'};
	/**
	 * + - * 
	 * */
	// 生成校验码
	private String generateVerifyCode(Random rdm) {
		int num1 = rdm.nextInt(10);
	    int num2 = rdm.nextInt(10);
		int num3 = rdm.nextInt(10);
		char op1 = ops[rdm.nextInt(3)];
		char op2 = ops[rdm.nextInt(3)];
		String exp = ""+ num1 + op1 + num2 + op2 + num3;
		return exp;
	}
}
