package com.mergeteam.coincontrol.api;

import com.mergeteam.coincontrol.security.tokenAuth.dtos.AuthenticationRequest;
import com.mergeteam.coincontrol.security.tokenAuth.dtos.AuthenticationResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Auth", description = "Auth management APIs")
public class LoginRestController {

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        // The actual authentication logic is handled by the LoginFilter
        // So this endpoint only exists to provide a mapping for the LoginFilter
        // So in theory we don't need to return anything
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        // Implement logout logic if necessary
        // For example, you might clear the JWT cookie or invalidate the session
        return ResponseEntity.noContent()
                .build();
    }
}
