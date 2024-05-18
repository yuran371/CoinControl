package com.mergeteam.coincontrol.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "user", catalog = "coin", schema = "coin_repository")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @UuidGenerator
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "avatar_path")
    private String avatarPath;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Wallet> wallets = new ArrayList<>();

}
