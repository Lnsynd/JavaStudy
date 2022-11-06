package com.lqs.demospringsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.lqs.demospringsecurity.domain.ResponseResult;
import com.lqs.demospringsecurity.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult<Object> responseResult = new ResponseResult<>(HttpStatus.FORBIDDEN.value(), "用户权限不足!");
        String authInfo = JSON.toJSONString(responseResult);
        WebUtils.renderString(response,authInfo);
    }
}
