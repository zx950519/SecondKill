package com.zx.seckill.util;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 16:15
 * @description：Cookie辅助工具
 * @modified By：
 * @version: $
 */

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {

    // 获取Cookie值
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        // 判空
        if (cookies == null || cookies.length < 1) {
            return null;
        }
        // 查找Cookie
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
