package com.mergeteam.coincontrol.integration.repository;

import com.mergeteam.coincontrol.api.sort.TransactionSort;
import com.mergeteam.coincontrol.entity.IncomeTransaction;
import com.mergeteam.coincontrol.repository.IncomeTransactionRepository;
import com.mergeteam.coincontrol.integration.IntegrationBaseClass;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@RequiredArgsConstructor
class IncomeTransactionRepositoryIT extends IntegrationBaseClass {

    private final IncomeTransactionRepository incomeTransactionRepository;

    @Test
    void findAllByWalletsId_requestWithAmountAscOrder_returnRightPageWithSortTransactions() {
        List<UUID> list = Stream.of("74dd2764-c0cc-4195-a906-b8edd7804c03", "55646589-a2c7-456e-bed4-7c8b3d45e22c")
                .map(UUID::fromString).toList();
        PageRequest pageRequest = PageRequest.of(0, 10, TransactionSort.AMOUNT_ASC.getSortValue());
        Page<IncomeTransaction> allByWalletsId = incomeTransactionRepository.findAllByWalletsId(list, pageRequest);
        assertAll(() -> assertThat(allByWalletsId).isNotEmpty(),
                  () -> assertThat(allByWalletsId).hasSize(2),
                  () -> assertThat(allByWalletsId).satisfiesExactly(
                          incomeTransaction1 -> assertThat(incomeTransaction1.getId())
                                  .hasToString("2378202d-4c06-4688-916c-0e09ae845d09"),
                          incomeTransaction2 -> assertThat(incomeTransaction2.getId())
                                  .hasToString("aa3fa2e3-0b19-43f0-9540-39cb49c76698")));
    }

    @Test
    void findAllByWalletsId_requestWithAmountDescOrder_returnRightPageWithSortTransactions() {
        List<UUID> list = Stream.of("74dd2764-c0cc-4195-a906-b8edd7804c03", "55646589-a2c7-456e-bed4-7c8b3d45e22c")
                .map(UUID::fromString).toList();
        PageRequest pageRequest = PageRequest.of(0, 10, TransactionSort.AMOUNT_DESC.getSortValue());
        Page<IncomeTransaction> allByWalletsId = incomeTransactionRepository.findAllByWalletsId(list, pageRequest);
        assertAll(() -> assertThat(allByWalletsId).isNotEmpty(),
                  () -> assertThat(allByWalletsId).hasSize(2),
                  () -> assertThat(allByWalletsId).satisfiesExactly(
                          incomeTransaction1 -> assertThat(incomeTransaction1.getId())
                                  .hasToString("aa3fa2e3-0b19-43f0-9540-39cb49c76698"),
                          incomeTransaction2 -> assertThat(incomeTransaction2.getId())
                                  .hasToString("2378202d-4c06-4688-916c-0e09ae845d09")));
    }

    @Test
    void findAllByWalletsId_requestWithDateAscOrder_returnRightPageWithSortTransactions() {
        List<UUID> list = Stream.of("74dd2764-c0cc-4195-a906-b8edd7804c03", "55646589-a2c7-456e-bed4-7c8b3d45e22c")
                .map(UUID::fromString).toList();
        PageRequest pageRequest = PageRequest.of(0, 10, TransactionSort.DATE_ASC.getSortValue());
        Page<IncomeTransaction> allByWalletsId = incomeTransactionRepository.findAllByWalletsId(list, pageRequest);
        assertAll(() -> assertThat(allByWalletsId).isNotEmpty(),
                  () -> assertThat(allByWalletsId).hasSize(2),
                  () -> assertThat(allByWalletsId).satisfiesExactly(
                          incomeTransaction1 -> assertThat(incomeTransaction1.getId())
                                  .hasToString("aa3fa2e3-0b19-43f0-9540-39cb49c76698"),
                          incomeTransaction2 -> assertThat(incomeTransaction2.getId())
                                  .hasToString("2378202d-4c06-4688-916c-0e09ae845d09")));
    }

    @Test
    void findAllByWalletsId_requestWithDateDescOrder_returnRightPageWithSortTransactions() {
        List<UUID> list = Stream.of("74dd2764-c0cc-4195-a906-b8edd7804c03", "55646589-a2c7-456e-bed4-7c8b3d45e22c")
                .map(UUID::fromString).toList();
        PageRequest pageRequest = PageRequest.of(0, 10, TransactionSort.DATE_DESC.getSortValue());
        Page<IncomeTransaction> allByWalletsId = incomeTransactionRepository.findAllByWalletsId(list, pageRequest);
        assertAll(() -> assertThat(allByWalletsId).isNotEmpty(),
                  () -> assertThat(allByWalletsId).hasSize(2),
                  () -> assertThat(allByWalletsId).satisfiesExactly(
                          incomeTransaction1 -> assertThat(incomeTransaction1.getId())
                                  .hasToString("2378202d-4c06-4688-916c-0e09ae845d09"),
                          incomeTransaction2 -> assertThat(incomeTransaction2.getId())
                                  .hasToString("aa3fa2e3-0b19-43f0-9540-39cb49c76698")));
    }

    @Test
    void findAllByWalletsId_requestWithNameAscOrder_returnRightPageWithSortTransactions() {
        List<UUID> list = Stream.of("74dd2764-c0cc-4195-a906-b8edd7804c03", "55646589-a2c7-456e-bed4-7c8b3d45e22c")
                .map(UUID::fromString).toList();
        PageRequest pageRequest = PageRequest.of(0, 10, TransactionSort.NAME_ASC.getSortValue());
        Page<IncomeTransaction> allByWalletsId = incomeTransactionRepository.findAllByWalletsId(list, pageRequest);
        assertAll(() -> assertThat(allByWalletsId).isNotEmpty(),
                  () -> assertThat(allByWalletsId).hasSize(2),
                  () -> assertThat(allByWalletsId).satisfiesExactly(
                          incomeTransaction1 -> assertThat(incomeTransaction1.getId())
                                  .hasToString("2378202d-4c06-4688-916c-0e09ae845d09"),
                          incomeTransaction2 -> assertThat(incomeTransaction2.getId())
                                  .hasToString("aa3fa2e3-0b19-43f0-9540-39cb49c76698")));
    }

    @Test
    void findAllByWalletsId_requestWithNameDescOrder_returnRightPageWithSortTransactions() {
        List<UUID> list = Stream.of("74dd2764-c0cc-4195-a906-b8edd7804c03", "55646589-a2c7-456e-bed4-7c8b3d45e22c")
                .map(UUID::fromString).toList();
        PageRequest pageRequest = PageRequest.of(0, 10, TransactionSort.NAME_DESC.getSortValue());
        Page<IncomeTransaction> allByWalletsId = incomeTransactionRepository.findAllByWalletsId(list, pageRequest);
        assertAll(() -> assertThat(allByWalletsId).isNotEmpty(),
                  () -> assertThat(allByWalletsId).hasSize(2),
                  () -> assertThat(allByWalletsId).satisfiesExactly(
                          incomeTransaction1 -> assertThat(incomeTransaction1.getId())
                                  .hasToString("aa3fa2e3-0b19-43f0-9540-39cb49c76698"),
                          incomeTransaction2 -> assertThat(incomeTransaction2.getId())
                                  .hasToString( "2378202d-4c06-4688-916c-0e09ae845d09")));
    }

    @Test
    void findAllByWalletsId_requestWithCategoryAscOrder_returnRightPageWithSortTransactions() {
        List<UUID> list = Stream.of("74dd2764-c0cc-4195-a906-b8edd7804c03", "55646589-a2c7-456e-bed4-7c8b3d45e22c")
                .map(UUID::fromString).toList();
        PageRequest pageRequest = PageRequest.of(0, 10, TransactionSort.CATEGORY_ASC.getSortValue());
        Page<IncomeTransaction> allByWalletsId = incomeTransactionRepository.findAllByWalletsId(list, pageRequest);
        assertAll(() -> assertThat(allByWalletsId).isNotEmpty(),
                  () -> assertThat(allByWalletsId).hasSize(2),
                  () -> assertThat(allByWalletsId).satisfiesExactly(
                          incomeTransaction1 -> assertThat(incomeTransaction1.getId())
                                  .hasToString("aa3fa2e3-0b19-43f0-9540-39cb49c76698"),
                          incomeTransaction2 -> assertThat(incomeTransaction2.getId())
                                  .hasToString( "2378202d-4c06-4688-916c-0e09ae845d09")));
    }

    @Test
    void findAllByWalletsId_requestWithCategoryDescOrder_returnRightPageWithSortTransactions() {
        List<UUID> list = Stream.of("74dd2764-c0cc-4195-a906-b8edd7804c03", "55646589-a2c7-456e-bed4-7c8b3d45e22c")
                .map(UUID::fromString).toList();
        PageRequest pageRequest = PageRequest.of(0, 10, TransactionSort.CATEGORY_DESC.getSortValue());
        Page<IncomeTransaction> allByWalletsId = incomeTransactionRepository.findAllByWalletsId(list, pageRequest);
        assertAll(() -> assertThat(allByWalletsId).isNotEmpty(),
                  () -> assertThat(allByWalletsId).hasSize(2),
                  () -> assertThat(allByWalletsId).satisfiesExactly(
                          incomeTransaction1 -> assertThat(incomeTransaction1.getId())
                                  .hasToString("2378202d-4c06-4688-916c-0e09ae845d09"),
                          incomeTransaction2 -> assertThat(incomeTransaction2.getId())
                                  .hasToString(  "aa3fa2e3-0b19-43f0-9540-39cb49c76698")));
    }
}
