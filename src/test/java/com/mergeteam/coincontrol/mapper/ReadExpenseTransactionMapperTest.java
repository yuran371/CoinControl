package com.mergeteam.coincontrol.mapper;

import com.mergeteam.coincontrol.dto.ReadExpenseTransactionDto;
import com.mergeteam.coincontrol.entity.ExpenseTransaction;
import com.mergeteam.coincontrol.entity.Wallet;
import com.mergeteam.coincontrol.entity.enums.ExpenseCategory;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReadExpenseTransactionMapperTest {

    @Test
    void dtoToEntity() {
        ExpenseTransaction expenseTransaction = ExpenseTransaction.builder()
                .date(OffsetDateTime.now())
                .amount(BigDecimal.valueOf(1000.0))
                .walletId(Wallet.builder().name("myWallet").build())
                .category(ExpenseCategory.CAFE).build();
        ReadExpenseTransactionDto readExpenseTransactionDto = ReadExpenseTransactionMapperImpl.INSTANCE.entityToDto(expenseTransaction);
        assertAll(() -> assertThat(expenseTransaction.getCategory()).isEqualTo(readExpenseTransactionDto.getCategory()),
                  () -> assertThat(expenseTransaction.getAmount()).isEqualTo(readExpenseTransactionDto.getAmount()),
                  () -> assertThat(expenseTransaction.getDate()).isEqualTo(readExpenseTransactionDto.getDate()),
                  () -> assertThat(expenseTransaction.getWalletId().getName()).isEqualTo(readExpenseTransactionDto.getWalletName()));
    }
}
