package com.example.demoormmybatis.mapper;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.demoormmybatis.DemoOrmMybatisApplicationTests;
import com.example.demoormmybatis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class UserMapperTest extends DemoOrmMybatisApplicationTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void selectAllUser(){
        List<User> list = userMapper.selectAllUser();
        System.out.println(list);
    }



}
