package com.example.demoormmybatisplus.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demoormmybatisplus.entity.User;
import com.example.demoormmybatisplus.mapper.UserMapper;
import com.example.demoormmybatisplus.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
