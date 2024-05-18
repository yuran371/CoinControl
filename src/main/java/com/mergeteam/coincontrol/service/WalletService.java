package com.mergeteam.coincontrol.service;

import com.mergeteam.coincontrol.dto.WalletDto;
import com.mergeteam.coincontrol.entity.Wallet;
import com.mergeteam.coincontrol.repository.ExpenseTransactionRepository;
import com.mergeteam.coincontrol.repository.IncomeTransactionRepository;
import com.mergeteam.coincontrol.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    private final ExpenseTransactionRepository expenseTransactionRepository;

    private final IncomeTransactionRepository incomeTransactionRepository;

    public Wallet createWallet(WalletDto walletDto) {
        return walletRepository.save(Wallet.builder()
                .id(walletDto.id())
                .user(walletRepository.findUserByWalletId(walletDto.id()))
                .balance(walletDto.balance())
                .build());
    }

//    public void deleteWallet(UUID walletId) {
//        Wallet getWallet = getWallet(walletId, userId);
//        walletRepository.delete(getWallet);
//    }
//
//    public Page<ExpenseTransaction> getExpenseTransactionsForWallet(UUID walletId, Pageable pageable) {
//        return expenseTransactionRepository.findByWalletId(walletId, pageable);
//    }
//
//    public BigDecimal getBalance(UUID walletId, UUID userId) {
//        Wallet wallet = getWallet(walletId, userId);
//        return wallet.getBalance();
//    }
//
//    private Wallet getWallet(UUID walletId, UUID userId) {
//        return walletRepository.getWalletByUserIdAndId(walletId, userId)
//                .orElseThrow(() -> new IllegalArgumentException("Wallet not found for user"));
//    }
}
