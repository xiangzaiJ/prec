package com.prec.user.controller;

import com.alibaba.dubbo.config.annotation.Service;
import com.prec.pojo.PrUser;
import com.prec.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/Register")
    public void Register(@RequestBody PrUser prUser){
        userService.Register(prUser);
    }

}
