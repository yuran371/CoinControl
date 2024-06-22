package com.mergeteam.coincontrol.entity;

import com.mergeteam.coincontrol.security.tokenAuth.entities.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.stereotype.Component;

import java.util.*;


@Entity
@Table(name = "user", catalog = "coin", schema = "coin_repository")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
@ToString(exclude = {"wallets", "roles"})
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

//    @Column(name = "role")
//    @Enumerated(EnumType.STRING)
//    private Role role;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL
            },
            orphanRemoval = true
    )
    private Set<UserRole> roles;

    public void addUserRole(UserRole userRole) {
        roles.add(userRole);
        userRole.setUser(this);
    }

    public void removeUserRole(UserRole userRole) {
        roles.remove(userRole);
        userRole.setUser(null);
    }

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Wallet> wallets = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User student = (User) o;
        return Objects.equals(id, student.id)
                && Objects.equals(email, student.email);
    }

}
