package com.mergeteam.coincontrol.security;

import com.mergeteam.coincontrol.security.tokenAuth.TokenCookieAuthenticationConfigurer;
import com.mergeteam.coincontrol.security.tokenAuth.filters.GetCsrfTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {        // implements WebMvcConfigurer

//    private final CustomEncoder customEncoder;
    //    private final AuthProviderImpl authProvider;  // реализация через собственный провайдер
//    private final UserAccountDetailsService userAccountDetailsService;

//    private AuthenticationEntryPoint authenticationEntryPoint;
//    private DataSource dataSource;

//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {    // реализация через
//    собственный провайдер
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject
//        (AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(authProvider);
////        authenticationManagerBuilder.userDetailsService(userAccountDetailsService);
//        return authenticationManagerBuilder.build();
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {  // реализация через собственный
//    провайдер
//        http
//                .csrf(AbstractHttpConfigurer::disable)
////                .userDetailsService(userAccountDetailsService)
//                .authenticationManager(authManager(http))
//                .authorizeHttpRequests(
//                        req -> req.anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
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

    @Bean     //JWT
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            TokenCookieAuthenticationConfigurer tokenCookieAuthenticationConfigurer
//            TokenCookieSessionAuthenticationStrategy tokenCookieSessionAuthenticationStrategy
    ) throws Exception {

        http.with(tokenCookieAuthenticationConfigurer, Customizer.withDefaults());

        http
                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(AbstractHttpConfigurer::disable)
//                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
//                .addFilterAfter(new GetCsrfTokenFilter(), ExceptionTranslationFilter.class)
                .authorizeHttpRequests(authorizeHttpReq -> {
                            authorizeHttpReq

                                    .requestMatchers("/api/v1/login", "/v3/api-docs/**", "/swagger-ui/**")
                                    .permitAll()
//                                .requestMatchers("/admin/manager.html", "/manager")
//                                .hasRole("MANAGER")
//                                .requestMatchers("/error", "index.html").permitAll()
//                                .anyRequest().permitAll())
//                            authorizeHttpReq
                                    .anyRequest()
                                    .authenticated();
                        }
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                        .sessionAuthenticationStrategy(tokenCookieSessionAuthenticationStrategy)
                        )
//                .csrf(csrf -> csrf.csrfTokenRepository(new CookieCsrfTokenRepository())
//                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
//                        .sessionAuthenticationStrategy((authentication, request, response) -> {
//                        }))
        ;
        return http.build();
    }

//    @Bean
//    public JdbcUserDetailsManager user(PasswordEncoder encoder) {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        return jdbcUserDetailsManager;
//    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User
//                .withUsername("user")
//                .password("$2a$12$eGuQ1cQdWG3y2KrZgoj9Zeh19BKGyOOknsq1Af6p11Jhum5D1Ubyy")
//                .roles("USER_ROLE")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(8);
//    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring().requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**",
//                                                     "/api/v1/transactions/**");
//    }
}
