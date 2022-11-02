package com.lqs.exception.response;


import com.lqs.exception.constant.Status;
import com.lqs.exception.exception.BaseException;
import lombok.Data;

import javax.servlet.http.PushBuilder;

/***
 * 服务器返回的API
 */

@Data
public class ApiResponse {

    private Integer code;
    private String message;
    private Object data;

    public ApiResponse() {
    }

    public ApiResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 返回全参
    public static ApiResponse of(Integer code, String message, Object data){
        return new ApiResponse(code,message,data);
    }

    // 返回带有状态码和数据的
    public static ApiResponse ofStatus(Status status, Object data){
        return of(status.getCode(), status.getMessage(),data);
    }

    //返回不带数据的
    public static ApiResponse ofStatus(Status status){
        return ofStatus(status,null);
    }

    // 返回自定义信息且成功的
    public static ApiResponse ofSuccess(String message){
        return of(Status.OK.getCode(), message,null);
    }

    // 返回不带数据的错误
    public static<T extends BaseException> ApiResponse ofException(T t){
        return ofException(t,null);
    }

    // 返回带数据的错误
    public static <T extends BaseException> ApiResponse ofException(T t,Object data){
        return of(t.getCode(),t.getMessage(),data);
    }




}
