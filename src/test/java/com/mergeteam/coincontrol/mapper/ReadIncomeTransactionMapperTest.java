package com.mergeteam.coincontrol.mapper;

import com.mergeteam.coincontrol.dto.ReadExpenseTransactionDto;
import com.mergeteam.coincontrol.dto.ReadIncomeTransactionDto;
import com.mergeteam.coincontrol.entity.ExpenseTransaction;
import com.mergeteam.coincontrol.entity.IncomeTransaction;
import com.mergeteam.coincontrol.entity.Wallet;
import com.mergeteam.coincontrol.entity.enums.ExpenseCategory;
import com.mergeteam.coincontrol.entity.enums.IncomeCategory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ReadIncomeTransactionMapperTest {

    @Test
    void dtoToEntity() {
        IncomeTransaction expenseTransaction = IncomeTransaction.builder()
                .date(OffsetDateTime.now())
                .amount(BigDecimal.valueOf(1022.3422))
                .walletId(Wallet.builder().name("nobyWallet").build())
                .category(IncomeCategory.HOBBY).build();
        ReadIncomeTransactionDto readExpenseTransactionDto = ReadIncomeTransactionMapper.INSTANCE.entityToDto(expenseTransaction);
        assertAll(() -> assertThat(expenseTransaction.getCategory()).isEqualTo(readExpenseTransactionDto.getCategory()),
                  () -> assertThat(expenseTransaction.getAmount()).isEqualTo(readExpenseTransactionDto.getAmount()),
                  () -> assertThat(expenseTransaction.getDate()).isEqualTo(readExpenseTransactionDto.getDate()),
                  () -> assertThat(expenseTransaction.getWalletId().getName()).isEqualTo(readExpenseTransactionDto.getWalletName()));
    }

}
