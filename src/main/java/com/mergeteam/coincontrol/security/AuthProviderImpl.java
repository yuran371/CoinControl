//package com.mergeteam.coincontrol.security;
//
//import com.mergeteam.coincontrol.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//
//@Component
//@RequiredArgsConstructor
//public class AuthProviderImpl implements AuthenticationProvider {
//
//    private final UserAccountDetailsService userAccountDetailsService;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
////        String password = authentication.getCredentials()
////                .toString();
//        String password = passwordEncoder.encode(authentication.getCredentials()
//                .toString());
//        UserDetails userDetails = userAccountDetailsService.loadUserByUsername(username);
//        if (!password.equals(userDetails.getPassword())) {
//            throw new BadCredentialsException("Incorrect password");
//        }
//        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {  // показывает для какого объекта Authentication он работает
//        return true;
//    }
//
//}
