package com.zx.seckill.result;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 15:44
 * @description：返回数据的封装类
 * @modified By：
 * @version: $
 */

@Data
public class Result<T> {
    @Getter
    @Setter
    private int code;       // 返回码
    private String msg;     // 返回消息
    private T data;         // 返回数据

    // 默认构造函数，设置为成功返回
    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(CodeMsg codeMsg) {
        if (codeMsg == null) {
            return;
        }
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    /**
     * 成功时候的调用
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * 失败时候的调用
     */
    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<T>(codeMsg);
    }
}
