package com.mergeteam.coincontrol.security.tokenAuth.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "deactivated_token", schema = "coin_repository", catalog = "coin")
public class DeactivatedToken {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "keep_until")
    private LocalDateTime keepUntil;
}
