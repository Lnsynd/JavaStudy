package com.lqs.demospringsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@MapperScan("com.lqs.demospringsecurity.mapper")
public class DemoSpringsecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringsecurityApplication.class, args);
    }

}
