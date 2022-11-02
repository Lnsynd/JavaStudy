package com.lqs.demospringsecurity.service;

import com.lqs.demospringsecurity.domain.ResponseResult;
import com.lqs.demospringsecurity.domain.User;

public interface LoginService {
    ResponseResult login(User user);
}
