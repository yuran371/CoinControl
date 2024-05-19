package com.mergeteam.coincontrol.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateUserDto {

    String email;
    String name;
    String avatarPath;

}
