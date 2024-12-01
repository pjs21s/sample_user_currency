package com.sparta.currency_user.global.handler;

import com.sparta.currency_user.dto.ErrorResponse;
import com.sparta.currency_user.global.CustomException;
import com.sparta.currency_user.global.ErrorCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // CustomException 처리

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorCodeEnum errorCode = ex.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(errorResponse, errorCode.getHttpStatus());
    }
}
