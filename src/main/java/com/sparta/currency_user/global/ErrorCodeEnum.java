package com.sparta.currency_user.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCodeEnum {
    INVALID_INPUT_VALUE("ERR001", "요청값의 형식이 맞지 않습니다.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("ERR500", "서버에 문제가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND("ERR404", "해당 유저가 존재하지 않습니다", HttpStatus.NOT_FOUND),
    EXCHANGE_CLAIM_NOT_FOUND("ERR404", "해당 환전 요청이 존재하지 않습니다", HttpStatus.NOT_FOUND),
    CURRENCY_NOT_FOUND("ERR404", "해당 통화가 존재하지 않습니다", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

}
