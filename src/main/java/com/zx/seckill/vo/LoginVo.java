package com.zx.seckill.vo;

import javax.validation.constraints.NotNull;

import com.zx.seckill.validator.IsMobile;
import org.hibernate.validator.constraints.Length;

/**
 * @Description:登录页面(表示层)的VO对象
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */

public class LoginVo {
	
	@NotNull
	@IsMobile
	private String mobile;		// 校验手机号码并判空
	
	@NotNull
	@Length(min=32)
	private String password;	// 校验密码长度并判空
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginVo [mobile=" + mobile + ", password=" + password + "]";
	}
}
