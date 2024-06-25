//package com.mergeteam.coincontrol.security.tokenAuth.filters;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//  TODO: реализовать редирект в случае успешной аутентификации кастомного логина
//public class CustomRedirectAfterAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    @Override
//    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
//        return "/api/v1/login".equals(request.getRequestURI()) && "POST".equalsIgnoreCase(request.getMethod());
//    }
//}
