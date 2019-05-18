package com.zx.seckill.validator;
import  javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.zx.seckill.util.ValidatorUtil;

/**
 * @Description:手机校验器接口实现类
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */

public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

	private boolean required = false;       // 手机号是否必须
	
	public void initialize(IsMobile constraintAnnotation) {
		required = constraintAnnotation.required();
	}
    // 判断是否合法
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(required) {      // 手机号必须填写
			return ValidatorUtil.isMobile(value);
		}else {     // 手机号可以为空
			if(StringUtils.isEmpty(value)) {
				return true;
			}else {
				return ValidatorUtil.isMobile(value);
			}
		}
	}
}
