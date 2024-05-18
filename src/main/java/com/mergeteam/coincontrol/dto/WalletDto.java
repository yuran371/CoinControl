package com.mergeteam.coincontrol.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record WalletDto(String name, BigDecimal balance) {
}
