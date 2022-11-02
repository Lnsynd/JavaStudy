package com.example.demomailregister.service.Impl;

import com.example.demomailregister.service.VerifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class VerifyServiceImpl implements VerifyService {


    @Resource
    StringRedisTemplate template;

    @Value("${spring.mail.username}")
    String from;

    @Resource
    JavaMailSender sender;

    @Override
    public void sendVerifyCode(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        Random random = new Random();
        int code = random.nextInt(89999) + 10000;
        template.opsForValue().set("verify:code" + email, code + "", 3, TimeUnit.MINUTES);
        message.setSubject("通知:");
        message.setText("您的验证码为:" + code);
        message.setFrom(from);
        sender.send(message);
        log.debug("短信目的邮箱为:{}", email);
    }

    @Override
    public boolean doVerify(String email, String code) {
        System.out.println(email + ":" + code);
        String s = template.opsForValue().get("verify:code" + email);
        System.out.println(s);
        if (s == null) return false;
        if (!s.equals(code)) return false;
        template.delete("verify:code" + email);
        return true;
    }
}
