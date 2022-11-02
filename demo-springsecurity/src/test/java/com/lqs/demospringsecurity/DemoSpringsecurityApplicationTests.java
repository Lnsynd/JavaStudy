package com.lqs.demospringsecurity;

import com.lqs.demospringsecurity.domain.User;
import com.lqs.demospringsecurity.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoSpringsecurityApplicationTests {

    @Autowired
    UserMapper userMapper;


    @Test
    void contextLoads() {
        List<User> list = userMapper.selectList(null);
        System.out.println(list);
    }

}
