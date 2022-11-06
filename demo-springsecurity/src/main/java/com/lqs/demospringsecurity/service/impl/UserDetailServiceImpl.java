package com.lqs.demospringsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lqs.demospringsecurity.domain.LoginUser;
import com.lqs.demospringsecurity.domain.User;
import com.lqs.demospringsecurity.mapper.MenuMapper;
import com.lqs.demospringsecurity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,s);
        User user = userMapper.selectOne(wrapper);


        List<String> perms = menuMapper.selectPermsByUserId(user.getId());

        if(Objects.isNull(user)){
            throw new RuntimeException("用户名不存在");
        }
        return new LoginUser(user,perms);
    }
}
