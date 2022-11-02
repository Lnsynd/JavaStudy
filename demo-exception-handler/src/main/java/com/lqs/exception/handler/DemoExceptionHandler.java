package com.lqs.exception.handler;

import com.lqs.exception.exception.JsonException;
import com.lqs.exception.exception.PageException;
import com.lqs.exception.response.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DemoExceptionHandler {
    private static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(JsonException.class)
    @ResponseBody
    public ApiResponse JsonException(JsonException exception){
        return ApiResponse.ofException(exception);
    }


    @ExceptionHandler(PageException.class)
    public ModelAndView PageException(PageException exception){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message",exception.getMessage());
        mv.setViewName(DEFAULT_ERROR_VIEW);
        return mv;
    }
}
