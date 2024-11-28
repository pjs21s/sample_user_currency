package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.UserRequestDto;
import com.sparta.currency_user.dto.UserResponseDto;
import com.sparta.currency_user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createUser(
            @Validated @RequestBody UserRequestDto userRequestDto,
            BindingResult bindingResult) {
        ResponseEntity<?> errorMap = getResponseEntity(bindingResult);
        if (errorMap != null) return errorMap;

        return ResponseEntity.ok().body(userService.save(userRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().body("정상적으로 삭제되었습니다.");
    }


    private ResponseEntity<?> getResponseEntity(BindingResult bindingResult) {
        ResponseEntity<?> errorMap = CurrencyController.getResponseEntity(bindingResult);
        return errorMap;
    }
}
