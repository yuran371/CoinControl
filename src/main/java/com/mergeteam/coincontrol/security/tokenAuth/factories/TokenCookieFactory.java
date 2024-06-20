package com.mergeteam.coincontrol.security.tokenAuth.factories;

import com.mergeteam.coincontrol.security.tokenAuth.tokens.CookieToken;
import com.mergeteam.coincontrol.security.tokenAuth.tokens.Token;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import static java.time.Instant.now;

@Setter
@Component
public class TokenCookieFactory implements Function<Authentication, Token> {

    private Duration tokenTtl = Duration.ofDays(1);

    @Override
    public Token apply(Authentication authentication) {
        List<String> authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        Instant createdAt = now();
        Instant expiresAt = now().plus(this.tokenTtl);
        return new CookieToken(UUID.randomUUID(), authentication.getName(), authorities, createdAt, expiresAt);
    }
}
