package com.mergeteam.coincontrol.service;

import com.mergeteam.coincontrol.entity.Wallet;
import com.mergeteam.coincontrol.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

//    public Wallet createWallet() {
//    }
//
//    public void deleteWallet(UUID walletId) {
//        Wallet getWallet = getWallet()
//
//    }
//
//    public Wallet getAllTransactions(UUID walletId, UUID userId) {
//        Wallet wallet = getWallet(walletId, userId);
//        return wallet
//    }

    public BigDecimal getBalance(UUID walletId, UUID userId) {
        Wallet wallet = getWallet(walletId, userId);
        return wallet.getBalance();
    }

    private Wallet getWallet(UUID walletId, UUID userId) {
        return walletRepository.getWalletByUserIdAndId(walletId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found for user"));
    }
}
