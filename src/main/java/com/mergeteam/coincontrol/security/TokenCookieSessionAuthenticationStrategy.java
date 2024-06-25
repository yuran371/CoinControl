package com.mergeteam.coincontrol.security;

import com.mergeteam.coincontrol.security.tokenAuth.factories.TokenCookieFactory;
import com.mergeteam.coincontrol.security.tokenAuth.serializers.TokenCookieJwtStringSerializer;
import com.mergeteam.coincontrol.security.tokenAuth.services.CookieService;
import com.mergeteam.coincontrol.security.tokenAuth.tokens.Token;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenCookieSessionAuthenticationStrategy implements SessionAuthenticationStrategy {

    public static final String AUTH_COOKIE_NAME = "__Host";

    private final TokenCookieFactory tokenCookieFactory;
    private final TokenCookieJwtStringSerializer tokenJwtStringSerializer;
    private final CookieService cookieService;

    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request,
                                 HttpServletResponse response) throws SessionAuthenticationException {
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            Token token = this.tokenCookieFactory.apply(authentication);
            String tokenJwtString = this.tokenJwtStringSerializer.apply(token);
            Cookie cookie = cookieService.createSecureHttpOnlyTokenCookie(AUTH_COOKIE_NAME, tokenJwtString,
                    token.getExpiresAt());
            log.info("JWT: "+tokenJwtString);
            response.addCookie(cookie);
            System.out.println();
        }
    }
}
