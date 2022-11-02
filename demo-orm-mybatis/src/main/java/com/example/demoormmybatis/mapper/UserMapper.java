package com.example.demoormmybatis.mapper;

import com.example.demoormmybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    int save(@Param("user") User user);

    int deleteById(@Param("id") Long id);

    @Select("SELECT * FROM orm_user WHERE id = #{id}")
    User selectUserById(@Param("id")Long id);

    @Select("Select * from orm_user")
    List<User> selectAllUser();
}
