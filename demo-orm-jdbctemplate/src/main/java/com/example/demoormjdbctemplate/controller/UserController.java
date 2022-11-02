package com.example.demoormjdbctemplate.controller;

import cn.hutool.core.lang.Dict;
import com.example.demoormjdbctemplate.entity.User;
import com.example.demoormjdbctemplate.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * @param user
     * @return 返回用户信息
     */
    @GetMapping("/user")
    public Dict getUserList(User user) {
        List<User> userList = userService.getUserList(user);
        return Dict.create().set("code", 200)
                .set("msg", "成功")
                .set("data", userList);
    }


    /**
     * @param id
     * @return 返回单个用户
     */
    @GetMapping("/user/{id}")
    public Dict getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return Dict.create().set("code", 200).set("msg", "成功").set("data", user);
    }

    /**
     * 增加用户
     *
     * @param user
     * @return
     */
    @PostMapping("/user")
    public Dict save(@RequestBody User user) {
        Boolean save = userService.save(user);
        return Dict.create().set("code", save ? 200 : 500)
                .set("msg", save ? "成功" : "失败")
                .set("data", save ? user : null);
    }

    @DeleteMapping("/user/{id}")
    public Dict delete(@PathVariable Long id) {
        Boolean delete = userService.delete(id);
        return Dict.create().set("code", delete ? 200 : 500)
                .set("msg", delete ? "成功" : "失败");
    }

    @PutMapping("/user/{id}")
    public Dict update(@RequestBody User user, @PathVariable Long id) {
        Boolean update = userService.update(user, id);
        return Dict.create().set("code", update ? 200 : 500).set("msg", update ? "成功" : "失败").set("data", update ? user : null);
    }


}
