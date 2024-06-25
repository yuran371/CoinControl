package com.mergeteam.coincontrol.security;

import com.mergeteam.coincontrol.security.tokenAuth.TokenCookieAuthenticationConfigurer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {        // implements WebMvcConfigurer

    @Bean     //JWT
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            TokenCookieAuthenticationConfigurer tokenCookieAuthenticationConfigurer
    ) throws Exception {


        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
//                .formLogin(Customizer.withDefaults())
//                .addFilterAfter(new GetCsrfTokenFilter(), ExceptionTranslationFilter.class)
                .authorizeHttpRequests(authorizeHttpReq -> {
                    authorizeHttpReq
                                    .requestMatchers("/api/v1/login", "/v3/api-docs/**", "/swagger-ui/**", "/api/v1/getCookieTest")
                                    .permitAll()
//                                .anyRequest().permitAll())
//                            authorizeHttpReq
                                    .anyRequest()
                                    .authenticated();
                        }
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .csrf(csrf -> csrf.csrfTokenRepository(new CookieCsrfTokenRepository())
//                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
//                        .sessionAuthenticationStrategy((authentication, request, response) -> {
//                        }))
        ;
        http.with(tokenCookieAuthenticationConfigurer, Customizer.withDefaults());
        return http.build();
    }

    //    public AuthenticationManager authManager(HttpSecurity http) throws Exception {    // реализация через
//    собственный провайдер
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject
//        (AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(authProvider);
////        authenticationManagerBuilder.userDetailsService(userAccountDetailsService);
//        return authenticationManagerBuilder.build();
//    }

//    @Bean     // последний до JWT реализации
//    public SecurityFilterChain filterChain(
//            HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(AbstractHttpConfigurer::disable)
////                .addFilterAfter(new GetCsrfTokenFilter(), ExceptionTranslationFilter.class)
//                .authorizeHttpRequests(authorizeHttpReq ->
//                        authorizeHttpReq
//                                .requestMatchers("/login", "/api/v1/login", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
//
////                                .requestMatchers("/error", "index.html").permitAll()
////                                .anyRequest().permitAll())
//                                .anyRequest().authenticated())
////                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////                .csrf(csrf -> csrf.csrfTokenRepository(new CookieCsrfTokenRepository())
////                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
////                        .sessionAuthenticationStrategy((authentication, request, response) -> {}))
//                        ;
//        return http.build();
//    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowCredentials(true);
//    }

}
