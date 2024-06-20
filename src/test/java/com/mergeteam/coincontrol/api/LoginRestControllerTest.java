package com.mergeteam.coincontrol.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mergeteam.coincontrol.security.TokenCookieSessionAuthenticationStrategy;
import com.mergeteam.coincontrol.security.tokenAuth.dtos.AuthenticationRequest;
import com.mergeteam.coincontrol.security.tokenAuth.serializers.TokenCookieJwtStringSerializer;
import com.mergeteam.coincontrol.security.tokenAuth.utils.RequestAuthenticationConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;


@WebMvcTest(LoginRestController.class)
//@ExtendWith(MockitoExtension.class)
//@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class LoginRestControllerTest {

        @Autowired
    private MockMvc mockMvc;
//    @Mock
    @MockBean
    private TokenCookieJwtStringSerializer tokenCookieJwtStringSerializer;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private RequestAuthenticationConverter requestAuthenticationConverter;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private TokenCookieSessionAuthenticationStrategy tokenCookieSessionAuthenticationStrategy;

    @Test
    void shouldSetJwtCookieOnLogin() throws Exception {
        AuthenticationRequest authRequest = new AuthenticationRequest("123@mail.ru", "123");
        String expectedJwtToken = "mockJwtToken";
        given(tokenCookieJwtStringSerializer.apply(any())).willReturn(expectedJwtToken);
        mockMvc.perform(post("/api/v1/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isOk())
                .andExpect(cookie().exists(TokenCookieSessionAuthenticationStrategy.AUTH_COOKIE_NAME))
                .andExpect(cookie().value(TokenCookieSessionAuthenticationStrategy.AUTH_COOKIE_NAME, expectedJwtToken));
    }

}