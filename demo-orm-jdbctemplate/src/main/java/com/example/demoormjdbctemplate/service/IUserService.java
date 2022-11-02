package com.example.demoormjdbctemplate.service;

import com.example.demoormjdbctemplate.entity.User;

import java.util.List;

public interface IUserService {

    Boolean save(User user);

    Boolean delete(Long id);

    Boolean update(User user,Long id);

    User getUser(Long id);

    List<User> getUserList (User user);

}
