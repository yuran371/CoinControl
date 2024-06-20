//package com.mergeteam.coincontrol.security.tokenAuth.services;
//
//import com.mergeteam.coincontrol.security.tokenAuth.dtos.AuthenticationRequest;
//import com.mergeteam.coincontrol.security.tokenAuth.dtos.AuthenticationResponse;
//import com.mergeteam.coincontrol.security.tokenAuth.dtos.RegisterRequest;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationServiceImpl implements AuthenticationService{
//    @Override
//    public AuthenticationResponse register(RegisterRequest request) {
//        return null;
//    }
//
//    @Override
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        var user = repository.findByEmail(request.getEmail())
//                .orElseThrow();
//        var jwtToken = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(user);
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
//        return AuthenticationResponse.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
//    }
//
//    @Override
//    public void jwtToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//    }
//}
