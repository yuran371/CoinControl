package com.mergeteam.coincontrol.dto;

import com.mergeteam.coincontrol.security.tokenAuth.entities.Role;
import com.mergeteam.coincontrol.security.tokenAuth.entities.UserRole;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
@Builder
public class ReadUserRolesDto {

    Role role;

}
