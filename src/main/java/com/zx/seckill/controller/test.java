package com.zx.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/11 14:25
 * @description：测试用Controller
 * @modified By：
 * @version: $
 */

@Controller
public class test {
    @RequestMapping(value = "/test/signin", method = {RequestMethod.GET})
    @ResponseBody
    public String userSigninByPost() throws IOException {
        return "Hello World";
    }
}
