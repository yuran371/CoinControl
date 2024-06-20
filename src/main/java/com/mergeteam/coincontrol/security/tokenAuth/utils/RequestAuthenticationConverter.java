package com.mergeteam.coincontrol.security.tokenAuth.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mergeteam.coincontrol.security.tokenAuth.exceptions.BadJsonBodyException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class RequestAuthenticationConverter {

    private static final String USERNAME = "email";
    private static final String PASSWORD = "password";
    private final ObjectMapper objectMapper;

    public UsernamePasswordAuthenticationToken requestRestToAuthenticationToken(HttpServletRequest request)
            throws IOException, BadJsonBodyException {
        JsonNode jsonContainer = this.objectMapper.readTree(request.getReader());
        String username = jsonContainer.get(USERNAME)
                .asText();
        String password = jsonContainer.get(PASSWORD)
                .asText();
        if (username != null && password != null) {
            return new UsernamePasswordAuthenticationToken(username, password);
        } else {
            throw new BadJsonBodyException("properties username and password are invalids");
        }
    }

}
