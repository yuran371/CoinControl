package com.mergeteam.coincontrol.integration.security.tokenAuth.factories;

import com.mergeteam.coincontrol.CoinControlApplication;
import com.mergeteam.coincontrol.security.tokenAuth.entities.Role;
import com.mergeteam.coincontrol.security.tokenAuth.factories.TokenCookieFactory;
import com.mergeteam.coincontrol.security.tokenAuth.tokens.Token;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@SpringBootTest(classes = CoinControlApplication.class)
@ExtendWith(MockitoExtension.class)
class TokenCookieFactoryTest {

    @Autowired
    private TokenCookieFactory tokenCookieFactory;

    @Test
    void testApply() {
        String username = "testuser";
        String password = "testpassword";
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(Role.ROLE_USER.getRoleName()));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password, authorities);

        // Set the token TTL for testing
        tokenCookieFactory.setTokenTtl(Duration.ofHours(1));

        // Apply the token factory
        Token token = tokenCookieFactory.apply(authentication);

        // Verify the token properties
        assertThat(token).isNotNull();
        assertThat(token.getId()).isNotNull();
        assertThat(token.getSubject()).isEqualTo(username);
        assertThat(token.getAuthorities()).containsExactly(Role.ROLE_USER.getRoleName());
        assertThat(token.getAuthorities()).doesNotContain(Role.ROLE_BANNED.getRoleName());
        assertThat(token.getCreatedAt()).isBeforeOrEqualTo(token.getExpiresAt());
        assertThat(token.getExpiresAt()).isAfter(token.getCreatedAt());
        assertThat(token.getExpiresAt()).isBeforeOrEqualTo(token.getCreatedAt().plus(Duration.ofHours(1)));
    }
}