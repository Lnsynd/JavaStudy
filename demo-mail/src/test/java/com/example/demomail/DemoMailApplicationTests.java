package com.example.demomail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.Message;

@SpringBootTest
class DemoMailApplicationTests {

    @Autowired
    JavaMailSender javaMailSender;

    @Test
    void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Title");
        message.setText("Hello");
        message.setFrom("18435375345@163.com");
        message.setTo("2034215871@qq.com");
        javaMailSender.send(message);
    }

}
