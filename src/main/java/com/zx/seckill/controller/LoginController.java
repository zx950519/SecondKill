package com.zx.seckill.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.zx.seckill.redis.RedisService;
import com.zx.seckill.result.Result;
import com.zx.seckill.service.MiaoshaUserService;
import com.zx.seckill.vo.LoginVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:登陆控制器
 * @Author:Alitria
 * @CreateDate:2019/05/17
 * @UpdateUser:Alitria
 * @UpdateDate:2019/05/17
 * @UpdateRemark:
 * @Version:
 */

@Controller
@RequestMapping("/login")
public class LoginController {

	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	MiaoshaUserService userService;
	@Autowired
	RedisService redisService;
	
    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }
    // Stable版登陆
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
    	log.info(loginVo.toString());
    	// 登录
    	String token = userService.login(response, loginVo);
    	return Result.success(token);
    }
    // 测试版登陆
    @PostMapping("/login")
    @ResponseBody
    public Result<String> doLoginByVue(HttpServletResponse response,
                                       @RequestParam(value = "mobile") String mobile,
                                       @RequestParam(value = "password") String password) {
        String token = userService.loginByVue(response, mobile, password);
        return Result.success(token);
    }
    @RequestMapping(value="/test", method= RequestMethod.GET)
    @ResponseBody
    public Map<String, List> getMap(Model model) {
        System.out.println("已进入");
        List<Integer> data = new ArrayList<>();
        for (int i=0; i<5; i++) {
            data.add(i);
        }
        Map<String, List> res = new HashMap<>();
        String user = "user_";
        for (int i=0; i<10; i++) {
            res.put(user+i, data);
        }
        return res;
    }
}
