package com.zx.seckill.controller;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 18:44
 * @description：
 * @modified By：
 * @version: $
 */

import com.zx.seckill.redis.RedisService;
import com.zx.seckill.result.Result;
import com.zx.seckill.service.SeckillUserService;
import com.zx.seckill.vo.LoginVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private RedisService redisSerice;

    @Autowired
    private SeckillUserService seckillUserService;

    @ApiOperation("获取登录界面接口")
    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @ApiOperation("登录接口")
    @ApiImplicitParam(name = "loginVO", value = "登录实体", required = true, dataType = "LoginVO")
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVO loginVO) {
        log.info("【用户登录】" + loginVO.toString());

        //登录
        String token = seckillUserService.login(response, loginVO);
        return Result.success(token);
    }
}
