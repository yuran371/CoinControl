package com.mergeteam.coincontrol.security.tokenAuth.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

}
