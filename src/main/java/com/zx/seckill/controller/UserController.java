package com.zx.seckill.controller;

/**
 * @author ：Alitria
 * @date ：Created in 2019/5/13 18:48
 * @description：
 * @modified By：
 * @version: $
 */

import com.zx.seckill.domain.SeckillUser;
import com.zx.seckill.redis.RedisService;
import com.zx.seckill.result.Result;
import com.zx.seckill.service.SeckillUserService;
import com.zx.seckill.vo.LoginVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author ShallowAn
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @RequestMapping("/info")
    @ResponseBody
    public Result<SeckillUser> doLogin(SeckillUser seckillUser) {
        return Result.success(seckillUser);
    }
}
