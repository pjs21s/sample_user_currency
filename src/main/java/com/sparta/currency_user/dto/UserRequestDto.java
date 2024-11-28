package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @NotNull(message = "name을 입력 해 주세요.")
    private String name;

    @NotNull(message = "email을 입력 해 주세요")
    private String email;

    public User toEntity() {
        return new User(
                this.name,
                this.email
        );
    }
}
