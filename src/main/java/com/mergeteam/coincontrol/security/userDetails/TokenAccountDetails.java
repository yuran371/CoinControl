package com.mergeteam.coincontrol.security.userDetails;

import com.mergeteam.coincontrol.security.tokenAuth.tokens.Token;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class TokenAccountDetails extends User {
    private final Token token;

    public TokenAccountDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Token token) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.token = token;
    }

    public TokenAccountDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Token token) {
        super(username, password, authorities);
        this.token = token;
    }

    public Token token() {  // TODO: понять
        return token;
    }

}
