package com.example.demomailregister.service.Impl;

import com.example.demomailregister.entity.Account;
import com.example.demomailregister.repo.AccountRepository;
import com.example.demomailregister.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    AccountRepository repository;

    @Override
    public void createAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        account.setPassword(encoder.encode(password));
        repository.save(account);
    }
}
