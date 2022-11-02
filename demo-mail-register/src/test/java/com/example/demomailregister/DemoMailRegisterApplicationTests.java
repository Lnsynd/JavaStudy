package com.example.demomailregister;

import com.example.demomailregister.entity.Account;
import com.example.demomailregister.repo.AccountRepository;
import com.example.demomailregister.service.VerifyService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@SpringBootTest
class DemoMailRegisterApplicationTests {

    @Resource
    AccountRepository repository;
    @Resource
    VerifyService verifyService;


    @Commit
    @Test
    void contextLoads() {
        verifyService.sendVerifyCode("2034215871@qq.com");
    }


    @Test
    void FindAllAccount() {
        System.out.println(repository.findAll());
    }


    @Test
    void AddAccount() {
        Account account = new Account();
        account.setUsername("委屈饿的撒");
        account.setPassword("23455");
        repository.save(account);
        System.out.println(account.getId());
    }

    @Test
    void DelAccount() {
        repository.deleteById(2);
    }

    @Test
    void PageAccount() {
        repository.findAll(PageRequest.of(0, 2)).forEach(System.out::println);
    }

}
