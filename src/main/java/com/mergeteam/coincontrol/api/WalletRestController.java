//package com.mergeteam.coincontrol.api;
//
//import com.mergeteam.coincontrol.entity.Wallet;
//import com.mergeteam.coincontrol.service.WalletService;
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.RequiredArgsConstructor;
//import org.springdoc.core.annotations.ParameterObject;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("api/v1/wallet")
//@RequiredArgsConstructor
//public class WalletRestController {
//
//    private final WalletService walletService;
//    @PostMapping("/{walletId}/{userId}")
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(summary = "Create wallet with a given ID")
//    Wallet createWallet(@PathVariable UUID walletId, @PathVariable UUID userId) {
//        return walletService.createWallet();
//    }
//
//    @DeleteMapping("/{walletID}")
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(summary = "Delete wallet with a given ID")
//    void deleteWallet(@PathVariable UUID walletID) {
//      walletService.deleteWallet(walletID);
//    }
//
//    @GetMapping(value = "/{walletID}/transactions")
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(summary = "Get all transactions associated with a wallet with a given ID")
//    Wallet getTransactions(
//            @AuthenticationPrincipal Jwt jwt,
//            @PathVariable long walletID,
//            @ParameterObject Pageable pageable,
//            @RequestParam(required = false) Map<String, String> params
//    ) {
//        return walletService.getAllTransactions();
//    }
//
//    @GetMapping(value = "/{walletID}/balance")
//    @ResponseStatus(HttpStatus.OK)
//    @Operation(summary = "Get all transactions associated with a wallet with a given ID")
//    Wallet getBalance(
//            @AuthenticationPrincipal Jwt jwt,
//            @PathVariable long walletID,
//            @ParameterObject Pageable pageable,
//            @RequestParam(required = false) Map<String, String> params
//    ) {
//        return walletService.getBalance();
//    }
//
//
//    // GET /balance
//
//    // POST /transaction
//
//    // GET /transactions:
//
//
//    //GET /income
//
//    //GET /expense
//
//    // DELETE {id}
//}
//
//
