package com.example.demoormjdbctemplate.dao;

import com.example.demoormjdbctemplate.dao.Base.BaseDao;
import com.example.demoormjdbctemplate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao extends BaseDao<User,Long> {


    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    /**
     *  插入操作
     * @param user
     * @return
     */
    public Integer insert(User user){
        return super.insert(user,true);
    }


    /**
     * 删除操作
     * @param id
     * @return
     */
    public Integer delete(Long id){
        return super.deleteById(id);
    }


    /**
     *  修改
     * @param user
     * @param id
     * @return
     */
    public Integer updateById(User user,Long id){
        return super.updateById(user,id,true);
    }


    /***
     *  根据id查询
     * @param id
     * @return
     */
    public User selectById(Long id){
        return super.findById(id);
    }


    /**
     *  获得用户列表
     * @param user
     * @return
     */
    public List<User> selectUserList(User user){
        return super.findByExample(user);
    }


}
