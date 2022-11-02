package com.example.demoormmybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demoormmybatisplus.mapper")
public class DemoOrmMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoOrmMybatisPlusApplication.class, args);
    }

}
