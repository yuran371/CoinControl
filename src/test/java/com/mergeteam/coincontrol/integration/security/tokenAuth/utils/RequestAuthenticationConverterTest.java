package com.mergeteam.coincontrol.integration.security.tokenAuth.utils;

import com.mergeteam.coincontrol.CoinControlApplication;
import com.mergeteam.coincontrol.security.tokenAuth.exceptions.BadJsonBodyException;
import com.mergeteam.coincontrol.security.tokenAuth.utils.RequestAuthenticationConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

//@RequiredArgsConstructor
//@IT
@ActiveProfiles("test")
@SpringBootTest(classes = CoinControlApplication.class)
@ExtendWith(MockitoExtension.class)
class RequestAuthenticationConverterTest {

    @Autowired
    private RequestAuthenticationConverter requestAuthenticationConverter;

    @Test
    @Rollback
    void testParse() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "");     //If you use new
        // MockHttpServletRequest(), it currently creates an HTTP method as an empty String (""). This is an invalid
        // HTTP method and is rejected by Spring Security. You can resolve this by replacing it with new
        // MockHttpServletRequest("GET", ""). See SPR_16851 for an issue that requests improving this.
        request.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String jsonBody = """
                {
                    "email": "123@mail.ru",
                    "password": "123"
                }
                """;
        request.setContent(jsonBody.getBytes());

        UsernamePasswordAuthenticationToken authenticationToken =
                requestAuthenticationConverter.requestRestToAuthenticationToken(request);

        Object actualPrincipal = authenticationToken.getPrincipal();
        Object actualCredentials = authenticationToken.getCredentials();
        assertThat(actualPrincipal).isEqualTo("123@mail.ru");
        assertThat(actualCredentials).isEqualTo("123");
        assertNotEquals("wrong@mail.ru", actualPrincipal);
        assertNotEquals("wrongPassword", actualCredentials);
    }
    @Test
    @Rollback
    void testParse_InvalidParameters_ThrowBadJsonBodyException() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "");
        request.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String invalidJsonBody = """
                {
                    "password": "123"
                }
                """;
        request.setContent(invalidJsonBody.getBytes());
        Assertions.assertThrows(BadJsonBodyException.class, () -> requestAuthenticationConverter.requestRestToAuthenticationToken(request));
    }

}
