package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.api.sort.TransactionSort;
import com.mergeteam.coincontrol.entity.ExpenseTransaction;
import com.mergeteam.coincontrol.entity.IncomeTransaction;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class IncomeTransactionRepositoryIT extends IntegrationBaseClass {

    private final IncomeTransactionRepository incomeTransactionRepository;

    @Test
    void findAllByWalletsId_requestWithOrder_returnPageWithTransactions() {
        List<UUID> list = Stream.of("74dd2764-c0cc-4195-a906-b8edd7804c03", "55646589-a2c7-456e-bed4-7c8b3d45e22c")
                .map(UUID::fromString).toList();
        PageRequest pageRequest = PageRequest.of(0, 10, TransactionSort.AMOUNT_ASC.getSortValue());
        Page<IncomeTransaction> allByWalletsId = incomeTransactionRepository.findAllByWalletsId(list, pageRequest);
        assertThat(allByWalletsId).isNotEmpty();
        assertThat(allByWalletsId).hasSize(1);
        assertThat(allByWalletsId).satisfiesExactly(
                expense1 -> assertThat(expense1.getId().toString())
                        .isEqualTo("2378202d-4c06-4688-916c-0e09ae845d09"));
    }
}
