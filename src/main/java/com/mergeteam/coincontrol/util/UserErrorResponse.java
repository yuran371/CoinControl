package com.mergeteam.coincontrol.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class UserErrorResponse {

    private String message;
    private Long timestamp;

}
