package com.mergeteam.coincontrol.service;

import com.mergeteam.coincontrol.dto.ReadExpenseTransactionDto;
import com.mergeteam.coincontrol.entity.Wallet;
import com.mergeteam.coincontrol.repository.ExpenseTransactionRepository;
import com.mergeteam.coincontrol.repository.IncomeTransactionRepository;
import com.mergeteam.coincontrol.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

    private final ExpenseTransactionRepository expenseTransactionRepository;
    private final IncomeTransactionRepository incomeTransactionRepository;
    private final WalletRepository walletRepository;

    public Page<ReadExpenseTransactionDto> readAllWalletsExpenseByUserId(UUID userId, Pageable pageable) {
        List<UUID> allWallets = walletRepository.findAllByUserId(userId).stream().map(Wallet::getId)
                .toList();
        if (allWallets.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return null;
    }
}
