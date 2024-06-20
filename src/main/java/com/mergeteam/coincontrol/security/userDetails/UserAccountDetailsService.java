package com.mergeteam.coincontrol.security.userDetails;

import com.mergeteam.coincontrol.entity.User;
import com.mergeteam.coincontrol.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class UserAccountDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byEmail = this.userService.findByEmail(username);
        System.out.println();
        return byEmail
                .map(UserAccountDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }

}
