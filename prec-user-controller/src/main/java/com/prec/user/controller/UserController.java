package com.prec.user.controller;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.prec.pojo.PrUser;
import com.prec.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.JsonResult;

import javax.rmi.CORBA.Util;

/**
 * @author 张业祥
 */
@Service
@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public JSONObject Register(@RequestBody PrUser prUser){
        userService.register(prUser);
        return JsonResult.resultSuccess("注册成功",null);
    }

}
