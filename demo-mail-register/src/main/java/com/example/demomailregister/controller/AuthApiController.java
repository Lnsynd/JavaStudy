package com.example.demomailregister.controller;

import com.example.demomailregister.entity.resp.RestBean;
import com.example.demomailregister.service.AccountService;
import com.example.demomailregister.service.VerifyService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@Api(tags = "用户验证",description = "用户登陆成功，失败，发送验证码")
@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    @Resource
    VerifyService verifyService;

    @Resource
    AccountService accountService;

    @ApiOperation(value = "发送验证码")
    @ApiResponses({
            @ApiResponse(code = 200,message = "邮件发送成功"),
            @ApiResponse(code = 500,message = "邮件发送失败")
    })
    @GetMapping("/verify-code")
    public RestBean<Void> verify(@ApiParam("邮箱地址")@RequestParam("email") String email) {
        try {
            verifyService.sendVerifyCode(email);
            return new RestBean<>(200, "邮件发送成功!");
        } catch (Exception e) {
            return new RestBean<>(500, "邮件发送失败!");
        }
    }

    @ApiOperation(value = "注册")
    @ApiResponses({
            @ApiResponse(code = 200,message = "注册成功"),
            @ApiResponse(code = 403,message = "注册失败")
    })
    @PostMapping("/register")
    public RestBean<Void> register(@ApiParam("用户名")@RequestParam String username,
                                   @ApiParam("密码")@RequestParam String password,
                                   @ApiParam("邮箱地址")@RequestParam String email,
                                   @ApiParam("验证码")@RequestParam String verify) {
        if (verifyService.doVerify(email, verify)) {
            accountService.createAccount(username, password);
            return new RestBean<>(200, "注册成功");
//            System.out.println("注册成功");
        } else {
            return new RestBean<>(403, "注册失败");
//            System.out.println("注册失败");
        }
    }

    @ApiOperation(value = "登出成功")
    @GetMapping("/logout-success")
    public RestBean<Void> logoutSuccess() {
        return new RestBean<>(200, "退出成功");
    }

    @ApiOperation(value = "登录成功")
    @PostMapping("/login-success")
    public RestBean<Void> loginSuccess() {
        return new RestBean<>(200, "登录成功");
    }

    @ApiOperation(value = "登录失败")
    @PostMapping("/login-failure")
    public RestBean<Void> loginFailure() {
        return new RestBean<>(304, "登录失败，用户名或密码错误");
    }


    @ApiIgnore
    @ApiOperation(value = "进入失败")
    @RequestMapping("/access-deny")
    public RestBean<Void> accessDeny() {
        return new RestBean<>(401, "进入失败");
    }
}
