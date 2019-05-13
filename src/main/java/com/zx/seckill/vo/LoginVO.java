package com.zx.seckill.vo;

import lombok.Data;
import com.zx.seckill.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 16:49
 * @description：登录VO
 * @modified By：
 * @version: $
 */

@Data
public class LoginVO {
    @NotNull
    @IsMobile
    private String mobile;      // 自动校验

    @NotNull
    @Length(min = 32)
    private String password;    // 自动校验

    @Override
    public String toString() {
        return "LoginVO{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
