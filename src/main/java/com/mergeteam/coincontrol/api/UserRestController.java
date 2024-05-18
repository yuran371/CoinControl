package com.mergeteam.coincontrol.api;

import com.mergeteam.coincontrol.dto.CreateUserDto;
import com.mergeteam.coincontrol.dto.ReadUserDto;
import com.mergeteam.coincontrol.dto.UpdateUserDto;
import com.mergeteam.coincontrol.dto.WalletDto;
import com.mergeteam.coincontrol.entity.ExpenseTransaction;
import com.mergeteam.coincontrol.entity.User;
import com.mergeteam.coincontrol.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserRestController {
    private UserService userService;

    @GetMapping("/{id}/wallets")
    public Page<WalletDto> getAllWalletsByUserId(@PathVariable UUID id, Pageable pageable) {
        return userService.getWalletsForUser(id, pageable);
    }

    @PutMapping("/{id}")
    public ReadUserDto updateUser(@PathVariable UUID id, UpdateUserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteById(id);
    }

    @PostMapping("/{id}")
    public ReadUserDto saveUser(CreateUserDto createUserDto) {
        return userService.save(createUserDto);
    }



//    @GetMapping("/{id}/wallets")




}
