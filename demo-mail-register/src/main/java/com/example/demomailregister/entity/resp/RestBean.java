package com.example.demomailregister.entity.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@ApiModel(description = "响应实体封装类")
@Data
@AllArgsConstructor
public class RestBean<T> {

    @ApiModelProperty("状态码")
    int code;
    @ApiModelProperty("原因")
    String reason;
    @ApiModelProperty("数据")
    T data;

    public RestBean(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }
}
