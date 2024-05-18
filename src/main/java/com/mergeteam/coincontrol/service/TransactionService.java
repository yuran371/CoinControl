package com.mergeteam.coincontrol.service;

import com.mergeteam.coincontrol.dto.ReadExpenseTransactionDto;
import com.mergeteam.coincontrol.repository.ExpenseTransactionRepository;
import com.mergeteam.coincontrol.repository.IncomeTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

    private final ExpenseTransactionRepository expenseTransactionRepository;
    private final IncomeTransactionRepository incomeTransactionRepository;

    public Page<ReadExpenseTransactionDto> readAllExpenseByUserId(UUID userID, Pageable pageable) {
        return null;
    }
}
