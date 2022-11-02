package com.example.demotemplatethymeleaf.thymeleaf.controller;

import com.example.demotemplatethymeleaf.thymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户页面
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-10-10 10:11
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public ModelAndView login(User user,HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.addObject(user);
        request.getSession().setAttribute("user",user);
        mv.setViewName("page/index");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("page/login");
    }
}
