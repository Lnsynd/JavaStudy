package com.example.demoormjdbctemplate.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.demoormjdbctemplate.constant.Const;
import com.example.demoormjdbctemplate.dao.UserDao;
import com.example.demoormjdbctemplate.entity.User;
import com.example.demoormjdbctemplate.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    /**
     * 增加用户
     *
     * @param user
     * @return
     */
    @Override
    public Boolean save(User user) {
        String rawPass = user.getPassword();
        String salt = IdUtil.simpleUUID();
        String pass = SecureUtil.md5(rawPass + Const.SALT_PREFIX + salt);
        user.setPassword(pass);
        user.setSalt(salt);
        return userDao.insert(user) > 0;
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Boolean delete(Long id) {
        return userDao.delete(id) > 0;
    }

    /**
     *  修改
     * @param user
     * @param id
     * @return
     */
    @Override
    public Boolean update(User user, Long id) {
        User exist = getUser(id);
        if (StrUtil.isNotBlank(user.getPassword())) {
            String rawPass = user.getPassword();
            String salt = IdUtil.simpleUUID();
            String pass = SecureUtil.md5(rawPass + Const.SALT_PREFIX + salt);
            user.setPassword(pass);
            user.setSalt(salt);
        }
        BeanUtil.copyProperties(user, exist, CopyOptions.create().setIgnoreNullValue(true));
        exist.setLastUpdateTime(new DateTime());
        return userDao.updateById(user, id) > 0;
    }

    /**
     * @param id
     * @return 得到单个用户
     */
    @Override
    public User getUser(Long id) {
        return userDao.findById(id);
    }

    /**
     * @param user
     * @return 得到用户列表
     */
    @Override
    public List<User> getUserList(User user) {
        return userDao.findByExample(user);
    }
}
