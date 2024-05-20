package com.mergeteam.coincontrol.service;

import com.mergeteam.coincontrol.entity.Wallet;
import com.mergeteam.coincontrol.repository.ExpenseTransactionRepository;
import com.mergeteam.coincontrol.repository.IncomeTransactionRepository;
import com.mergeteam.coincontrol.repository.WalletRepository;
import com.mergeteam.coincontrol.utils.TestTags;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@Tag(TestTags.UNIT)
class TransactionServiceTest {

    @Mock
    private ExpenseTransactionRepository expenseTransactionRepository;
    @Mock
    private IncomeTransactionRepository incomeTransactionRepository;
    @Mock
    private WalletRepository walletRepository;
    @InjectMocks
    private TransactionService transactionService;

    @Test
    void readAllWalletsExpensesByUserId() {
//        List<Wallet> wallets = List.of(Wallet.builder().id(UUID.randomUUID()).build(),
//                                       Wallet.builder().id(UUID.randomUUID()).build());
//        doReturn(wallets).when(walletRepository).findAllByUserId(any());
//        doReturn(Page.empty()).when(expenseTransactionRepository).findAllByWalletsId(any(), any());
//        transactionService.readAllWalletsExpensesByUserId(UUID.randomUUID(),
//                                                                Pageable.unpaged());
    }

    @Test
    void readAllWalletsIncomesByUserId() {
    }
}