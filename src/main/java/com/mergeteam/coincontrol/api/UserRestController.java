package com.mergeteam.coincontrol.api;

import com.mergeteam.coincontrol.dto.ReadUserDto;
import com.mergeteam.coincontrol.dto.UpdateUserDto;
import com.mergeteam.coincontrol.dto.WalletDto;
import com.mergeteam.coincontrol.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserRestController {

    private UserService userService;

    @GetMapping("g")
    public ResponseEntity<Greeting> getGreeting(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new Greeting("Hello, %s!".formatted(user.getUsername())));
    }

    public record Greeting(String greeting) {
    }

    @GetMapping("/{id}/wallets")
    public Page<WalletDto> getAllWalletsByUserId(@PathVariable UUID id, Pageable pageable) {
        return userService.getWalletsForUser(id, pageable);
    }

    //    @GetMapping("/showInfo")
//    @GetMapping("/{id}")
//    public UserAccountDetails getUser(@PathVariable UUID id) {
//        Authentication authentication = SecurityContextHolder.getContext()
//                .getAuthentication();
//        UserAccountDetails principal = (UserAccountDetails) authentication.getPrincipal();
//        if (!(principal instanceof UserAccountDetails)) {
//            throw new IllegalStateException("Principal is not of type UserAccountDetails");
//        }
//        System.out.println(principal);
//        return principal;
//    }

//    @GetMapping(value = "/userInfo")
//    public String currentUserName(Authentication authentication) {
//        UserAccountDetails principal = (UserAccountDetails) authentication.getPrincipal();
//        return principal.getPassword();
//    }

    @PutMapping("/{id}")
    public ReadUserDto updateUser(@PathVariable UUID id, @Validated UpdateUserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteById(id);
    }

//    @PostMapping("/id")
//    public void saveUser(@Validated CreateUserDto createUserDto) {
//        userService.save(createUserDto);
//    }

}
