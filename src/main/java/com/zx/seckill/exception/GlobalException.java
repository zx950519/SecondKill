package com.zx.seckill.exception;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 17:32
 * @description：
 * @modified By：
 * @version: $
 */

import com.zx.seckill.result.CodeMsg;

public class GlobalException extends RuntimeException{

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.getMsg());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
