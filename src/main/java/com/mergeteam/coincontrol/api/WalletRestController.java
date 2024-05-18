package com.mergeteam.coincontrol.api;

import com.mergeteam.coincontrol.dto.WalletDto;
import com.mergeteam.coincontrol.entity.Wallet;
import com.mergeteam.coincontrol.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/wallet")
@RequiredArgsConstructor
public class WalletRestController {

    private final WalletService walletService;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    Wallet createWallet(@RequestBody WalletDto walletDto) {
        return walletService.createWallet(walletDto);
    }
}

// GET /balance
// POST /transaction
// GET /transactions:
// GET /income
// GET /expense
// DELETE {id}



