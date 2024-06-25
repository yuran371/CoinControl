package com.mergeteam.coincontrol.security.tokenAuth.services;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class CookieService {

    public Cookie createSecureHttpOnlyTokenCookie(
            String cookieName, String cookieValue, Instant expiry)
    {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");
        cookie.setDomain(null);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge((int) ChronoUnit.SECONDS.between(Instant.now(), expiry));
        return cookie;
    }

}
