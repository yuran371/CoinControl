package com.mergeteam.coincontrol.repository;

import com.mergeteam.coincontrol.api.sort.TransactionSort;
import com.mergeteam.coincontrol.entity.ExpenseTransaction;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class ExpenseTransactionRepositoryIT extends IntegrationBaseClass {

    private final ExpenseTransactionRepository expenseTransactionRepository;

    @Test
    void findAllByWalletsId_requestWithOrder_returnPageWithTransactions() {
        List<UUID> list = Stream.of("74dd2764-c0cc-4195-a906-b8edd7804c03", "55646589-a2c7-456e-bed4-7c8b3d45e22c")
                .map(UUID::fromString).toList();
        PageRequest pageRequest = PageRequest.of(0, 10, TransactionSort.AMOUNT_DESC.getSortValue());
        Page<ExpenseTransaction> allByWalletsId = expenseTransactionRepository.findAllByWalletsId(list, pageRequest);
        assertThat(allByWalletsId).isNotEmpty();
        assertThat(allByWalletsId).hasSize(2);
        assertThat(allByWalletsId).satisfiesExactly(
                expense1 -> assertThat(expense1.getId().toString())
                        .isEqualTo("09331bd1-c8cb-4bf5-931b-fdb4cfe21d24"),
                expense2 -> assertThat(expense2.getId().toString())
                        .isEqualTo("4c3e97a5-4bc1-4c58-bf83-7b4229d69603"));
    }
}
