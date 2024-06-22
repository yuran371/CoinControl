package com.mergeteam.coincontrol.security.tokenAuth.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mergeteam.coincontrol.security.tokenAuth.exceptions.BadJsonBodyException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RequestAuthenticationConverter {

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private final ObjectMapper objectMapper;

    public UsernamePasswordAuthenticationToken requestRestToAuthenticationToken(HttpServletRequest request)
            throws IOException, BadJsonBodyException {
        JsonNode jsonContainer = this.objectMapper.readTree(request.getReader());
        JsonNode jsonNodeEmail = jsonContainer.get(EMAIL);
        JsonNode jsonNodePassword = jsonContainer.get(PASSWORD);
        if (jsonNodeEmail != null && jsonNodePassword != null) {
            return new UsernamePasswordAuthenticationToken(jsonNodeEmail.asText(), jsonNodePassword.asText());
        } else {
            throw new BadJsonBodyException("properties username and password are invalids");
        }
    }

}
