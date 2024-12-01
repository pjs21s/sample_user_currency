package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @NotNull(message = "name 값은 필수입니다.")
    private String name;

    @NotBlank(message = "Email must not be blank")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "올바른 이메일 형식이 아닙니다"
    )
    private String email;

    public User toEntity() {
        return new User(
                this.name,
                this.email
        );
    }
}
