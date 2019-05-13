package com.zx.seckill.util;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 16:19
 * @description：MD5辅助工具
 * @modified By：
 * @version: $
 */

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    // 核心方法，依据输入获取对应md5值
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }
    // 盐
    private static final String salt = "1a2b3c4d";
    // 根据输入信息与盐进行加密
    public static String inputPassToFormPass(String inputPass) {
        // 构造加密串儿
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        // 调用加密方法进行加密
        return md5(str);
    }
    // 根据上次加密结果和盐进行二次加密
    public static String FormPassToDBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }
    // 依次调用inputPassToFormPass()->FormPassToDBPass()进行二次加密
    public static String inputPassToDBPass(String input, String saltDb) {
        String formpass = inputPassToFormPass(input);
        String dbPass = FormPassToDBPass(formpass, saltDb);
        return dbPass;
    }
}
