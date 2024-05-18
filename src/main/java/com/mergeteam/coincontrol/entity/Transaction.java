package com.mergeteam.coincontrol.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(catalog = "coin", schema = "coin_repository", name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance
@DiscriminatorColumn(name = "transaction_type")
public abstract class Transaction implements BaseEntity<UUID> {
    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet walletId;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "date")
    private OffsetDateTime date;
}
