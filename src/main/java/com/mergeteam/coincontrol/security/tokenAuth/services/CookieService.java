package com.mergeteam.coincontrol.security.tokenAuth.services;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class CookieService {
    private static final int REFRESH_COOKIE_LIVE_TERM_SECONDS = Duration.ofDays(14L).toMillisPart();

    public Cookie createSecureHttpOnlyTokenCookie(
            String cookieName, String cookieValue, Instant expiry)
    {
        var cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");
        cookie.setDomain(null);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge((int) ChronoUnit.SECONDS.between(Instant.now(), expiry));
        return cookie;
    }

}
