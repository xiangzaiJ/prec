package com.prec.user.service.impl;

import com.prec.mapper.PrUserMapper;
import com.prec.pojo.PrUser;
import com.prec.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    @Autowired
    private PrUserMapper prUserMapper;
    @Override
    public void Register(PrUser user) {

        prUserMapper.insert(user);
    }
}
