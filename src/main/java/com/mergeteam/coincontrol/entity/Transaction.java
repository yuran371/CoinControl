package com.mergeteam.coincontrol.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
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
    @Column(name = "wallet_id")
    private UUID walletId;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "date")
    private Object date;
}
