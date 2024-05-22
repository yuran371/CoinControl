package com.mergeteam.coincontrol.integration.repository;

import com.mergeteam.coincontrol.entity.Wallet;
import com.mergeteam.coincontrol.repository.WalletRepository;
import com.mergeteam.coincontrol.integration.IntegrationBaseClass;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class WalletRepositoryIT extends IntegrationBaseClass {

    private final WalletRepository walletRepository;

    @Test
    void findAllByUserId_haveWallets_returnList() {
        UUID uuid = UUID.fromString("e0ee31f6-6c9b-4987-8fba-246b059b3bee");
        List<Wallet> allByUserId = walletRepository.findAllByUserId(uuid);
        assertThat(allByUserId).isNotEmpty();
        assertThat(allByUserId).hasSize(2);
        assertThat(allByUserId).satisfiesExactly(
                wallet1 -> assertThat(wallet1.getId().toString())
                        .isEqualTo("74dd2764-c0cc-4195-a906-b8edd7804c03"),
                wallet2 -> assertThat(wallet2.getId().toString())
                        .isEqualTo("55646589-a2c7-456e-bed4-7c8b3d45e22c"));
    }
}
