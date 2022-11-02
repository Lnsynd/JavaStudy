package com.lqs.exception.exception;

import com.lqs.exception.constant.Status;
import lombok.Getter;

@Getter
public class JsonException extends BaseException{
    public JsonException(Status status) {
        super(status);
    }

    public JsonException(Integer code, String message) {
        super(code, message);
    }
}
