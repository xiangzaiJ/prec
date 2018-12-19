package com.prec.user.service;

import com.prec.pojo.PrUser;

public interface UserService {
    void register(PrUser user);

    PrUser login(String username,String password);
}
