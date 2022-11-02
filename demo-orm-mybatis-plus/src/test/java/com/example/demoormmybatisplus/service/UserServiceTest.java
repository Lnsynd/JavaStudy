package com.example.demoormmybatisplus.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.demoormmybatisplus.DemoOrmMybatisPlusApplication;
import com.example.demoormmybatisplus.DemoOrmMybatisPlusApplicationTests;
import com.example.demoormmybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends DemoOrmMybatisPlusApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testInsert(){
        String salt = IdUtil.fastSimpleUUID();
        User u = User.builder()
                .name("lqs")
                .password(SecureUtil.md5("12345"+salt))
                .salt(salt)
                .email("12@qq.com")
                .phoneNumber("123456789")
                .status(1)
                .createTime(new DateTime())
                .lastLoginTime(new DateTime())
                .lastUpdateTime(new DateTime())
                .build();
        userService.save(u);
    }

}
