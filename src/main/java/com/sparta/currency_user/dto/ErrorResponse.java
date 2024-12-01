package com.sparta.currency_user.dto;

import com.sparta.currency_user.global.ErrorCodeEnum;
import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorResponse {
    private final String errorCode;
    private final String errorMessage;

    // 일반적인 에러
    private ErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static ErrorResponse of(ErrorCodeEnum errorCodeEnum) {
        return new ErrorResponse(errorCodeEnum.getCode(), errorCodeEnum.getMessage());
    }
}
