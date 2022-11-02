package com.lqs.exception.controller;

import com.lqs.exception.constant.Status;
import com.lqs.exception.exception.JsonException;
import com.lqs.exception.exception.PageException;
import com.lqs.exception.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {


    @GetMapping("/json")
    @ResponseBody
    public ApiResponse JsonException(){
        throw new JsonException(Status.UNKNOWN_ERROR);
    }


    @GetMapping("/page")
    public ModelAndView PageException(){
        throw new PageException(Status.UNKNOWN_ERROR);
    }
}
