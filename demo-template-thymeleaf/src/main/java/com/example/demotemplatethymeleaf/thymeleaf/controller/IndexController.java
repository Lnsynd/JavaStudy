package com.example.demotemplatethymeleaf.thymeleaf.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.demotemplatethymeleaf.thymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 主页
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-10-10 10:12
 */
@Controller
public class IndexController {


    @GetMapping(value = {"","/"})
    public ModelAndView index(HttpServletRequest request){
        ModelAndView mv =  new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");

        if(ObjectUtil.isNull(user)){
            mv.setViewName("redirect:/user/login");
        }else{
            mv.setViewName("page/index");
            mv.addObject(user);
        }
        return mv;
    }
}
