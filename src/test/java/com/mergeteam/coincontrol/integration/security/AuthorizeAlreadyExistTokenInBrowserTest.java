package com.mergeteam.coincontrol.integration.security;

import com.mergeteam.coincontrol.security.TokenCookieSessionAuthenticationStrategy;
import com.mergeteam.coincontrol.security.tokenAuth.serializers.TokenCookieJwtStringSerializer;
import com.mergeteam.coincontrol.security.tokenAuth.tokens.CookieToken;
import com.mergeteam.coincontrol.security.tokenAuth.tokens.Token;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorizeAlreadyExistTokenInBrowserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenCookieJwtStringSerializer tokenJwtStringSerializer;

    private Cookie cookie;
    private MvcResult mvcResult;
    private String username;

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
        username = "123@mail.ru";
        List<String> authorities = List.of("ROLE_USER");
        Instant createdAt = Instant.now();
        Instant expiresAt = createdAt.plusSeconds(3600); // 1 hour TTL
        Token token = new CookieToken(tokenId, username, authorities, createdAt, expiresAt);

        cookie = new Cookie(TokenCookieSessionAuthenticationStrategy.AUTH_COOKIE_NAME,
                tokenJwtStringSerializer.apply(token));
        cookie.setPath("/");
        cookie.setDomain(null);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge((int) ChronoUnit.SECONDS.between(Instant.now(), token.getExpiresAt()));


        // Для мока
//        when(tokenCookieFactory.apply(any(Authentication.class))).thenReturn(token);
//        when(tokenJwtStringSerializer.apply(any(Token.class))).thenReturn("mockJwtString");
//        when(cookieService.createSecureHttpOnlyTokenCookie(any(String.class), any(String.class), any(Instant.class)))
//                .thenReturn(new Cookie("__Host", "mockJwtString"));
    }

    @Test
    void testOnAuthenticationJson() throws Exception {
//        ResultActions resultActions = mockMvc.perform(post("/api/v1/login")
//                        .cookie(cookie)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                                {
//                                  "email": "123@mail.ru",
//                                  "password": "123"
//                                }
//                                """))
//                .andDo(result -> {
//                    mvcResult = this.mockMvc.perform(get("/api/v1/g"))
//                            .andExpect(status().isOk())
//                            .andExpect(cookie().exists(TokenCookieSessionAuthenticationStrategy.AUTH_COOKIE_NAME))
//                            .andExpect(jsonPath("$.greeting").value("Hello, 123@mail.ru!"))
//                            .andReturn();
//
//                })
//                .andExpect(status().isOk())
//                .andExpect(cookie().exists(TokenCookieSessionAuthenticationStrategy.AUTH_COOKIE_NAME));

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/g")
                        .cookie(cookie))
                .andExpect(status().isOk())
//                .andExpect(cookie().exists(TokenCookieSessionAuthenticationStrategy.AUTH_COOKIE_NAME))
                .andReturn();
        String responseAsString = mvcResult.getResponse()
                .getContentAsString();
        Assertions.assertEquals("{\"greeting\":\"Hello, %s!\"}".formatted(username), responseAsString);
    }


// TODO: нижние два теста сработают только при 302
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
//                            ".security.web.context.RequestAttributeSecurityContextRepository
//                            .SPRING_SECURITY_CONTEXT");
//                    assertNotNull(securityContext, "Security context should not be null");
//                    Authentication auth = securityContext.getAuthentication();
//                    assertNotNull(auth, "Authentication should not be null");
//                    assertTrue(auth.isAuthenticated(), "Authentication should be authenticated");
//                    assertEquals("123@mail.ru", auth.getName(), "Username should be '123@mail.ru'");
//                });
//    }

}
