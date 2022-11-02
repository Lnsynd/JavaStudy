package com.example.demoormmybatismapperpage.mapper;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.demoormmybatismapperpage.DemoOrmMybatisMapperTestPageApplicationTests;
import com.example.demoormmybatismapperpage.entity.User;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UserMapperTest extends DemoOrmMybatisMapperTestPageApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 插入操作
     */
    @Test
    public void testInsert(){
        String salt = IdUtil.simpleUUID();
        User test3 = User.builder()
                .name("test3")
                .password(SecureUtil.md5("123456"+salt))
                .salt(salt)
                .email("test@lqs.com")
                .phoneNumber("18435375345")
                .status(1)
                .createTime(new DateTime())
                .lastLoginTime(new DateTime())
                .lastUpdateTime(new DateTime())
                .build();
        userMapper.insertUseGeneratedKeys(test3);
        System.out.println(test3);
        log.debug("【测试主键回写#testSave3.getId()】= {}", test3.getId());
    }


    /**
     * 增加列表
     */
    @Test
    public void testInsetList(){
        List<User> userList = Lists.newArrayList();
        for (int i = 4; i < 14; i++) {
            String salt = IdUtil.fastSimpleUUID();
            User user = User.builder().name("testSave" + i).password(SecureUtil.md5("123456" + salt)).salt(salt).email("testSave" + i + "@xkcoding.com").phoneNumber("1730000000" + i).status(1).lastLoginTime(new DateTime()).createTime(new DateTime()).lastUpdateTime(new DateTime()).build();
            userList.add(user);
        }
        userMapper.insertList(userList);
        List<Long> ids = userList.stream().map(User::getId).collect(Collectors.toList());
        System.out.println(ids);

    }

    /**
     * 删除
     *
     */
    @Test
    public void testDelete() {
        Long primaryKey = 4L;
        userMapper.deleteByPrimaryKey(primaryKey);
        User user = userMapper.selectByPrimaryKey(primaryKey);
    }


    /***
     * 修改
     */
    @Test
    public void testUpdate(){
        Long primaryKey = 17L;
        User u = userMapper.selectByPrimaryKey(primaryKey);
        u.setName("刘千山");
        userMapper.updateByPrimaryKey(u);
        User user = userMapper.selectByPrimaryKey(primaryKey);
        log.debug("【update】= {}", user);

    }

    @Test
    public void queryOne(){
        Long primaryKey = 16L;
        User user = userMapper.selectByPrimaryKey(primaryKey);
        System.out.println(user);
        log.debug("【user】= {}", user);
    }

    /**
     * 查询全部
     */
    @Test
    public void queryAll(){
        List<User> list = userMapper.selectAll();
        System.out.println(list);
    }

    @Test
    public void queryByPageAndSort(){
        int currentPage = 2;
        int pageSize = 5;
        String orderBy = "id desc"; // 降序

        PageHelper.startPage(currentPage,pageSize,orderBy);
        List<User> list = userMapper.selectAll();
        PageInfo<User> userPageInfo =  new PageInfo<>(list);
        System.out.println(userPageInfo);
        log.debug("【userPageInfo】= {}", userPageInfo);
    }



}
