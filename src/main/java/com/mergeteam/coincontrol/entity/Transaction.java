package com.mergeteam.coincontrol.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance
@DiscriminatorColumn(name = "transaction_type")
public abstract class Transaction implements BaseEntity<UUID> {
    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID id;
    @Column(name = "wallet_id") //TODO Add @ManyToOne
    private UUID walletId;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "date")
    private OffsetDateTime date;
}
