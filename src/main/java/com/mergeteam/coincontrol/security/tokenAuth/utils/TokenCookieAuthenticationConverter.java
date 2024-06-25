package com.mergeteam.coincontrol.security.tokenAuth.utils;

import com.mergeteam.coincontrol.security.TokenCookieSessionAuthenticationStrategy;
import com.mergeteam.coincontrol.security.tokenAuth.deserializers.TokenCookieJweStringDeserializer;
import com.mergeteam.coincontrol.security.tokenAuth.tokens.CookieToken;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class TokenCookieAuthenticationConverter implements AuthenticationConverter {

    private final TokenCookieJweStringDeserializer tokenCookieStringDeserializer;

    @Override
    public Authentication convert(HttpServletRequest request) {
        if (request.getCookies() != null) {
            return Stream.of(request.getCookies())
                    .filter(cookie -> cookie.getName().equals(TokenCookieSessionAuthenticationStrategy.AUTH_COOKIE_NAME))
                    .findFirst()
                    .map(cookie -> {
                        CookieToken token = this.tokenCookieStringDeserializer.deserialize(cookie.getValue());
                        return new PreAuthenticatedAuthenticationToken(token, cookie.getValue());
                    })
                    .orElse(null);
        }
        return null;
    }
}


