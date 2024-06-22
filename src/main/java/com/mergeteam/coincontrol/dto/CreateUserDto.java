package com.mergeteam.coincontrol.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {

    @Email
    String email;
    String password;
    String avatarPath;
    @NotNull
    String name;
//    @NotNull
//    Role role;

}
