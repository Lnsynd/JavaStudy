package com.lqs.demospringsecurity.service.impl;

import com.lqs.demospringsecurity.domain.LoginUser;
import com.lqs.demospringsecurity.domain.ResponseResult;
import com.lqs.demospringsecurity.domain.User;
import com.lqs.demospringsecurity.service.LoginService;
import com.lqs.demospringsecurity.utils.JwtUtils;
import com.lqs.demospringsecurity.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        //使用Provider auth方法验证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (Objects.isNull(authentication)) {
            throw new RuntimeException("用户名不存在");
        }

        // 生成jwt传给前端
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtils.createJWT(userId);
        Map<String, String> map = new HashMap();
        map.put("token", jwt);

        // 用户信息存入redis
        redisCache.setCacheObject("login:" + userId, loginUser);

        return new ResponseResult<>(200, "登陆成功", map);
    }


    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        redisCache.deleteObject("login:"+userId);

        return new ResponseResult(200,"退出成功！");
    }
}
