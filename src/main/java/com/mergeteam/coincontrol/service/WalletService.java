package com.mergeteam.coincontrol.service;

import com.mergeteam.coincontrol.dto.WalletDto;
import com.mergeteam.coincontrol.entity.ExpenseTransaction;
import com.mergeteam.coincontrol.entity.User;
import com.mergeteam.coincontrol.entity.Wallet;
import com.mergeteam.coincontrol.repository.ExpenseTransactionRepository;
import com.mergeteam.coincontrol.repository.IncomeTransactionRepository;
import com.mergeteam.coincontrol.repository.UserRepository;
import com.mergeteam.coincontrol.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    private final UserRepository userRepository;

    private final ExpenseTransactionRepository expenseTransactionRepository;

    private final IncomeTransactionRepository incomeTransactionRepository;

    public Wallet createWallet(WalletDto walletDto, UserDetails user) {
        Optional<User> needUser = userRepository.findByEmail(user.getUsername());
        return walletRepository.save(Wallet.builder()
                .user(needUser.get())
                .balance(walletDto.balance())
                .build());
    }

    public Optional<Wallet> deleteWalletByWalletId(UUID walletId) {
        Optional<Wallet> getWallet = getWallet(walletId);
        walletRepository.deleteById(walletId);
        return getWallet;
    }

    public BigDecimal getBalance(UUID walletId) {
        Optional<Wallet> wallet = getWallet(walletId);
        return wallet.map(Wallet::getBalance).orElseThrow();
    }

    private Optional<Wallet> getWallet(UUID walletId) {
        return walletRepository.findById(walletId);
    }
}
