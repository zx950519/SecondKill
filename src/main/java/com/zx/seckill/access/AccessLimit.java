package com.zx.seckill.access;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 18:35
 * @description：
 * @modified By：
 * @version: $
 */

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD})
@Retention(RUNTIME)
public @interface AccessLimit {
    int seconds();

    int maxCount();

    boolean needLogin() default true;
}
