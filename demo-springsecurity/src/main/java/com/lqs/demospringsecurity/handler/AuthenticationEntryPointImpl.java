package com.lqs.demospringsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.lqs.demospringsecurity.domain.ResponseResult;
import com.lqs.demospringsecurity.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult<Object> responseResult = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), "登录认证失败！请重新登陆!");
        String authInfo = JSON.toJSONString(responseResult);
        WebUtils.renderString(response,authInfo);
    }

}
