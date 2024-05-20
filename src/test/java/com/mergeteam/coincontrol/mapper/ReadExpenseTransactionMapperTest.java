package com.mergeteam.coincontrol.mapper;

import com.mergeteam.coincontrol.dto.ReadExpenseTransactionDto;
import com.mergeteam.coincontrol.entity.ExpenseTransaction;
import com.mergeteam.coincontrol.entity.Wallet;
import com.mergeteam.coincontrol.entity.enums.ExpenseCategory;
import com.mergeteam.coincontrol.utils.TestTags;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Tag(TestTags.UNIT)
class ReadExpenseTransactionMapperTest {

    @Spy
    ReadExpenseTransactionMapper readExpenseTransactionMapper = Mappers.getMapper(ReadExpenseTransactionMapper.class);

    @Test
    void dtoToEntity() {
        ExpenseTransaction expenseTransaction = ExpenseTransaction.builder()
                .date(OffsetDateTime.now())
                .amount(BigDecimal.valueOf(1000.0))
                .walletId(Wallet.builder().name("myWallet").build())
                .category(ExpenseCategory.CAFE).build();
        ReadExpenseTransactionDto readExpenseTransactionDto = readExpenseTransactionMapper.entityToDto(expenseTransaction);
        assertAll(() -> assertThat(expenseTransaction.getCategory()).isEqualTo(readExpenseTransactionDto.getCategory()),
                  () -> assertThat(expenseTransaction.getAmount()).isEqualTo(readExpenseTransactionDto.getAmount()),
                  () -> assertThat(expenseTransaction.getDate()).isEqualTo(readExpenseTransactionDto.getDate()),
                  () -> assertThat(expenseTransaction.getWalletId().getName()).isEqualTo(readExpenseTransactionDto.getWalletName()));
    }
}
