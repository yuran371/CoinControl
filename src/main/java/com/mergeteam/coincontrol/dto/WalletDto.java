package com.mergeteam.coincontrol.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletDto(UUID id, String name, BigDecimal balance) {
}
