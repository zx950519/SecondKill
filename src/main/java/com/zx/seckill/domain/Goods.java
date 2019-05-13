package com.zx.seckill.domain;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/12 20:40
 * @description：商品对应的实体类
 * @modified By：
 * @version: $
 */
import lombok.Data;

@Data
public class Goods {

    private Long id;                // id
    private String goodsName;       // 名称
    private String goodsTitle;      // 标签
    private String goodsImg;        // 图片
    private String goodsDetail;     // 细节
    private Double goodsPrice;      // 价格
    private Integer goodsStock;     // 储量

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                ", goodsDetail='" + goodsDetail + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsStock=" + goodsStock +
                '}';
    }
}
