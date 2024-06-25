package com.mergeteam.coincontrol.integration.security;

import com.mergeteam.coincontrol.security.TokenCookieSessionAuthenticationStrategy;
import com.mergeteam.coincontrol.security.tokenAuth.deserializers.TokenCookieJweStringDeserializer;
import com.mergeteam.coincontrol.security.tokenAuth.tokens.CookieToken;
import com.mergeteam.coincontrol.security.tokenAuth.tokens.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenCookieSessionAuthenticationStrategyTest {

    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    private TokenCookieFactory tokenCookieFactory;
//
//    @MockBean
//    private TokenCookieJwtStringSerializer tokenJwtStringSerializer;
//
//    @MockBean
//    private CookieService cookieService;

    @BeforeEach
    void setUp() {
        UUID tokenId = UUID.randomUUID();
        String username = "123@mail.ru";
        List<String> authorities = List.of("ROLE_USER");
        Instant createdAt = Instant.now();
        Instant expiresAt = createdAt.plusSeconds(3600); // 1 hour TTL
        Token token = new CookieToken(tokenId, username, authorities, createdAt, expiresAt);

        // Для мока
//        when(tokenCookieFactory.apply(any(Authentication.class))).thenReturn(token);
//        when(tokenJwtStringSerializer.apply(any(Token.class))).thenReturn("mockJwtString");
//        when(cookieService.createSecureHttpOnlyTokenCookie(any(String.class), any(String.class), any(Instant.class)))
//                .thenReturn(new Cookie("__Host", "mockJwtString"));
    }

    @Test
    void testOnAuthenticationJson() throws Exception {
        mockMvc.perform(post("/api/v1/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "123@mail.ru",
                                  "password": "123"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(cookie().exists(TokenCookieSessionAuthenticationStrategy.AUTH_COOKIE_NAME))
                .andExpect(cookie().exists(TokenCookieSessionAuthenticationStrategy.AUTH_COOKIE_NAME));
    }



// TODO: нижние два теста  сработают только при 302
//    @Test
//    public void testOnAuthentication2() throws Exception {
//        MockHttpServletRequestBuilder requestBuilder = post("/api/v1/login")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("""
//                                {
//                                  "email": "123@mail.ru",
//                                  "password": "123"
//                                }
//                                """);
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().isFound()) // Expecting 302 status code for redirect
//                .andExpect(redirectedUrl("/")); // Expecting redirection to the home page
//    }
//    @Test
//    public void testOnAuthentication3() throws Exception {
//        MockHttpServletRequestBuilder requestBuilder = post("/login")
//                .param("username", "123@mail.ru")
//                .param("password", "123");
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().isFound())
//                .andExpect(redirectedUrl("/"))
//                .andDo(result -> {
//                    MockHttpServletRequest request = result.getRequest();// Retrieve existing session
//                    assertNotNull(request);
//                    SecurityContext securityContext = (SecurityContext) request.getAttribute("org.springframework" +
//                            ".security.web.context.RequestAttributeSecurityContextRepository.SPRING_SECURITY_CONTEXT");
//                    assertNotNull(securityContext, "Security context should not be null");
//                    Authentication auth = securityContext.getAuthentication();
//                    assertNotNull(auth, "Authentication should not be null");
//                    assertTrue(auth.isAuthenticated(), "Authentication should be authenticated");
//                    assertEquals("123@mail.ru", auth.getName(), "Username should be '123@mail.ru'");
//                });
//    }

}
