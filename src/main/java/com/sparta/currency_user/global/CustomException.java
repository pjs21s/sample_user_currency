package com.sparta.currency_user.global;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCodeEnum errorCode;

    public CustomException(ErrorCodeEnum errorCode) {
        super(errorCode.getMessage()); // 부모 클래스의 메시지로 설정
        this.errorCode = errorCode;
    }
}
