package com.zx.seckill.util;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 16:27
 * @description：校验工具
 * @modified By：
 * @version: $
 */

import org.springframework.util.StringUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

    // 手机号的模式串儿
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }

    public static void main(String[] args) {
        System.out.println(isMobile("15573313526"));
    }
}
