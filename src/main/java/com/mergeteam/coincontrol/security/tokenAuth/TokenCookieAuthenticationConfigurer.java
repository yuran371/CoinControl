package com.mergeteam.coincontrol.security.tokenAuth;

import com.mergeteam.coincontrol.security.TokenCookieSessionAuthenticationStrategy;
import com.mergeteam.coincontrol.security.tokenAuth.filters.LoginFilter;
import com.mergeteam.coincontrol.security.tokenAuth.utils.TokenCookieAuthenticationConverter;
import com.mergeteam.coincontrol.security.userDetails.TokenAccountDetails;
import com.mergeteam.coincontrol.security.userDetails.TokenAuthenticationUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.mergeteam.coincontrol.security.TokenCookieSessionAuthenticationStrategy.AUTH_COOKIE_NAME;

@RequiredArgsConstructor
@Component
public class TokenCookieAuthenticationConfigurer
        extends AbstractHttpConfigurer<TokenCookieAuthenticationConfigurer, HttpSecurity> {

    private final LoginFilter loginFilter;
    private final JdbcTemplate jdbcTemplate;
    private final TokenAuthenticationUserDetailsService tokenAuthenticationUserDetailsService;
    private final TokenCookieAuthenticationConverter tokenCookieAuthenticationConverter;

    @Override
    public void init(HttpSecurity builder) throws Exception {
        builder.logout(logout -> logout.logoutUrl("/api/v1/logout")
                .addLogoutHandler(
                        new CookieClearingLogoutHandler(AUTH_COOKIE_NAME))
                .addLogoutHandler((request, response, authentication) -> {
                    if (authentication != null &&
                            authentication.getPrincipal() instanceof TokenAccountDetails user) {
                        this.jdbcTemplate.update("insert into coin_repository.deactivated_token (id, keep_until) values (?, ?)",
                                user.token()
                                        .getId(),
                                Date.from(user.token().getExpiresAt())
//                                user.token().getExpiresAt()
                        );

                        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    }
                }));
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        AuthenticationFilter cookieAuthenticationFilter = new AuthenticationFilter(
                builder.getSharedObject(AuthenticationManager.class), tokenCookieAuthenticationConverter);
        cookieAuthenticationFilter.setSuccessHandler((request, response, authentication) -> {
        });
        cookieAuthenticationFilter.setFailureHandler(
                new AuthenticationEntryPointFailureHandler(
                        new Http403ForbiddenEntryPoint()
                )
        );

        PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
        authenticationProvider.setPreAuthenticatedUserDetailsService(tokenAuthenticationUserDetailsService);

        builder.addFilterAfter(cookieAuthenticationFilter, CsrfFilter.class)
                .addFilterAfter(loginFilter, ExceptionTranslationFilter.class)    // TODO: вернуть
                .authenticationProvider(authenticationProvider);

    }

}
