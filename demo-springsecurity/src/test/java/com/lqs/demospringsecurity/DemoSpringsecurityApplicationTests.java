package com.lqs.demospringsecurity;

import com.lqs.demospringsecurity.domain.User;
import com.lqs.demospringsecurity.mapper.MenuMapper;
import com.lqs.demospringsecurity.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class DemoSpringsecurityApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        List<User> list = userMapper.selectList(null);
        System.out.println(list);
    }

    @Test
    void test() {
        String pswd = passwordEncoder.encode("123456");
        System.out.println(pswd);
    }


    @Autowired
    MenuMapper mapper;

    @Test
    void testMenuMapper(){
        List<String> strings = mapper.selectPermsByUserId(1l);
        System.out.println(strings);
    }

}
