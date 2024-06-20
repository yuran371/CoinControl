package com.mergeteam.coincontrol.security.tokenAuth.entities;

import com.mergeteam.coincontrol.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "user_role", schema = "coin_repository", catalog = "coin")
public class UserRole implements GrantedAuthority {

    @Id
    @Column(name = "id")
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_email")
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return role.getRoleName();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserRole userRole)) {
            return false;
        }

        if (!user.equals(userRole.user)) {
            return false;
        }
        return getRole() == userRole.getRole();
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

}
