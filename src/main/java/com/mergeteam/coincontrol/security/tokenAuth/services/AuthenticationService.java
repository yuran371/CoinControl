package com.mergeteam.coincontrol.security.tokenAuth.services;

import com.mergeteam.coincontrol.security.tokenAuth.dtos.AuthenticationRequest;
import com.mergeteam.coincontrol.security.tokenAuth.dtos.AuthenticationResponse;
import com.mergeteam.coincontrol.security.tokenAuth.dtos.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
    public AuthenticationResponse register(RegisterRequest request);

    public AuthenticationResponse authenticate(AuthenticationRequest request);

    public void jwtToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
