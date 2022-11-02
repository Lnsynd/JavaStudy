package com.example.demomailregister.service;

public interface VerifyService {

    void sendVerifyCode(String email);
    boolean doVerify(String mail,String code);
}
