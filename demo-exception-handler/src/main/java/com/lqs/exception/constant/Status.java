package com.lqs.exception.constant;


import lombok.Data;
import lombok.Getter;

/**
 * 状态码
 * */
@Getter
public enum Status {
    OK(200,"成功"),
    UNKNOWN_ERROR(500,"服务器错误");

    private Integer code;
    private String message;

    Status(Integer code,String message){
        this.code=code;
        this.message =  message;
    }
}
