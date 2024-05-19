package com.mergeteam.coincontrol.dto;

import com.mergeteam.coincontrol.entity.enums.Role;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {

    String email;
    String password;
    String avatarPath;
    String name;
    Role role;

}
