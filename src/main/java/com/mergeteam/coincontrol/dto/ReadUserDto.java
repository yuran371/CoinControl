package com.mergeteam.coincontrol.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ReadUserDto {

    UUID id;
    String email;
    String name;
    String avatarPath;
//    Role role;

}
