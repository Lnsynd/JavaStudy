package com.example.demomailregister.controller;

import com.example.demomailregister.entity.Account;
import com.example.demomailregister.entity.resp.RestBean;
import com.example.demomailregister.repo.AccountRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "用户信息请求")
@RequestMapping("/api/user")
@RestController
public class AccountApiController {

    @Resource
    AccountRepository repository;

    @ApiOperation(value = "用户信息")
    @GetMapping("/info")
    public RestBean<Account> info(){
        SecurityContext context = SecurityContextHolder.getContext();
        Account account = repository.findAccountByUsername(context.getAuthentication().getName());
        account.setPassword("");
        return new RestBean<>(200,"请求成功",account);
    }


}
