package com.mergeteam.coincontrol.security.tokenAuth.tokens;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public abstract class Token {

    protected final UUID id;
    protected final String subject;
    protected final List<String> authorities;
    protected final Instant createdAt;
    protected final Instant expiresAt;

}
