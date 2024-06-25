package com.mergeteam.coincontrol.integration.security.tokenAuth.services;

import com.mergeteam.coincontrol.CoinControlApplication;
import com.mergeteam.coincontrol.security.tokenAuth.services.CookieService;
import com.mergeteam.coincontrol.security.tokenAuth.tokens.CookieToken;
import com.mergeteam.coincontrol.security.tokenAuth.tokens.Token;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(classes = CoinControlApplication.class)
@ExtendWith(MockitoExtension.class)
public class CookieServiceTest {

    @Autowired
    private CookieService cookieService;

    private static final String AUTH_COOKIE_NAME = "__Host-auth-token";

    private static final String tokenJwtString =
            "eyJraWQiOiJkZTM5N2U2NS04NjRjLTRjYTctOThlYi1mY2I5MTRiYTg3NzMiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiZGlyIn0." +
                    ".pIqlKq4Q6X0PSkwA.bVqpHww0pan2EFng8P5u4npMvGfyKFcI" +
                    "-bpAor8aAF9E_EbDnZPZCUaRdQbdw54h0unHtxnmaaaV6fm3vYAUd1igZNGe461V7VMuK40y5HNana3tmUfVfKh030atg8xP_mUTwZJZJS-fuQ4xFwsaAykYVxeKzVLNj1CLgkU.DLtv4vJlXlVg7djpXvltlg";

    @Test
    void testCreateSecureHttpOnlyTokenCookie() {
        UUID tokenId = UUID.randomUUID();
        String username = "testuser";
        List<String> authorities = List.of("ROLE_USER");
        Instant createdAt = Instant.now();
        Instant expiresAt = createdAt.plusSeconds(36000); // 1 hour TTL
        Token token = new CookieToken(tokenId, username, authorities, createdAt, expiresAt);
        Cookie cookie = cookieService.createSecureHttpOnlyTokenCookie(AUTH_COOKIE_NAME, tokenJwtString, token.getExpiresAt());

        assertThat(cookie).isNotNull();
        assertThat(cookie.getName()).isEqualTo(AUTH_COOKIE_NAME);
    }

}
