package com.example.demoormmybatisplus.activeRecord;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import com.example.demoormmybatisplus.DemoOrmMybatisPlusApplicationTests;
import com.example.demoormmybatisplus.entity.Role;
import org.junit.jupiter.api.Test;

import java.util.List;


@Slf4j
public class ActiveRecordTest extends DemoOrmMybatisPlusApplicationTests {


    @Test
    public void testInsert() {
        Role role = new Role();
        role.setName("刘千山");
        Assert.assertTrue(role.insert());
        log.debug("[role]:{}",role);
    }


    @Test
    public void testUpdate(){
        new Role().setId(2L).setName("一个拉").updateById();
        new Role().update(new UpdateWrapper<Role>().lambda().set(Role::getName,"阿弥诺").eq(Role::getId,1));
    }


    @Test
    public void testDelete(){
        new Role().setId(2L).deleteById();
        new Role().delete(new QueryWrapper<Role>().lambda().eq(Role::getName,"刘千山"));
    }

    @Test
    public void testSelect(){
        Role role = new Role().selectOne(new QueryWrapper<Role>().lambda().eq(Role::getId,1));
        log.debug("role:{}",role);
        List<Role> roles = new Role().selectAll();
        log.debug("roleList:{}",roles);
    }
}
