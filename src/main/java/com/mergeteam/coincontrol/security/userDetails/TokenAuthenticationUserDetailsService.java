package com.mergeteam.coincontrol.security.userDetails;

import com.mergeteam.coincontrol.repository.UserRepository;
import com.mergeteam.coincontrol.security.tokenAuth.repositories.JwtTokensRepository;
import com.mergeteam.coincontrol.security.tokenAuth.tokens.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@RequiredArgsConstructor
@Service
public class TokenAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final JwtTokensRepository jwtTokensRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken) throws UsernameNotFoundException {
        if (preAuthenticatedAuthenticationToken.getPrincipal() instanceof Token token) {
            return new TokenAccountDetails(token.getSubject(),
                    "nopassword",
                    true,
                    true,
//                    checkIfEnabled(token),  // TODO: реализовать
//                    checkIfExpired(token),  // TODO: реализовать
                    true,
                    true,
                    token.getAuthorities()
                            .stream()
                            .map(SimpleGrantedAuthority::new)
                            .toList(), token);
        }
        throw new UsernameNotFoundException("Principal must be of type Token");
    }

    private boolean checkIfEnabled(Token token) {
        return userRepository.fetchUserRoles(token.getSubject())
                .stream()
                .noneMatch(role -> token.getAuthorities()
                        .contains(role))
                && checkIfLoggedOut(token)  // TODO: returns a boolean value indicating whether the token is logged out.
                ;
    }

    private boolean checkIfExpired(Token token) {
        return token.getExpiresAt()
                .isAfter(Instant.now());
    }

    private boolean checkIfLoggedOut(Token token) {
        return !jwtTokensRepository.checkIfTokenDeactivated(token.getId());
    }

}
