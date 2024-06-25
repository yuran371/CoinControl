package com.mergeteam.coincontrol.integration.security.tokenAuth.services;

import com.mergeteam.coincontrol.CoinControlApplication;
import com.mergeteam.coincontrol.security.TokenCookieSessionAuthenticationStrategy;
import com.mergeteam.coincontrol.security.tokenAuth.factories.TokenCookieFactory;
import com.mergeteam.coincontrol.security.tokenAuth.serializers.TokenCookieJwtStringSerializer;
import com.mergeteam.coincontrol.security.tokenAuth.services.CookieService;
import com.mergeteam.coincontrol.security.tokenAuth.tokens.CookieToken;
import com.mergeteam.coincontrol.security.tokenAuth.tokens.Token;
import com.mergeteam.coincontrol.security.tokenAuth.utils.RequestAuthenticationConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import jakarta.servlet.http.Cookie;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ActiveProfiles("test")
//@SpringBootTest(classes = CoinControlApplication.class)
//@ExtendWith(MockitoExtension.class)
@WebMvcTest(CoinControlApplication.class)
//@SpringBootTest
@AutoConfigureMockMvc
public class CookieServiceMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenCookieFactory tokenCookieFactory;

    @MockBean
    private TokenCookieJwtStringSerializer tokenJwtStringSerializer;

    @MockBean
    private CookieService cookieService;

    @MockBean
    private RequestAuthenticationConverter requestAuthenticationConverter;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private TokenCookieSessionAuthenticationStrategy tokenCookieSessionAuthenticationStrategy;
    @BeforeEach
    void setUp() throws Exception {
        // Mock the behavior of TokenCookieFactory
        UUID tokenId = UUID.randomUUID();
        String username = "testuser";
        List<String> authorities = List.of("ROLE_USER");
        Instant createdAt = Instant.now();
        Instant expiresAt = createdAt.plusSeconds(3600); // 1 hour TTL
        Token token = new CookieToken(tokenId, username, authorities, createdAt, expiresAt);

        when(tokenCookieFactory.apply(any(Authentication.class))).thenReturn(token);
        when(tokenJwtStringSerializer.apply(any(Token.class))).thenReturn("mockJwtString");
        when(cookieService.createSecureHttpOnlyTokenCookie(any(String.class), any(String.class), any(Instant.class)))
                .thenReturn(new Cookie("mockCookieName", "mockCookieValue"));
    }

    @Test
    void testOnAuthentication() throws Exception {
        // Create an authentication object
        Authentication authentication = new UsernamePasswordAuthenticationToken("user", "password");

        // Perform a post request to trigger the onAuthentication method
        mockMvc.perform(post("/login").principal(authentication))
                .andExpect(status().isOk())
                .andExpect(cookie().exists(TokenCookieSessionAuthenticationStrategy.AUTH_COOKIE_NAME));
    }
}