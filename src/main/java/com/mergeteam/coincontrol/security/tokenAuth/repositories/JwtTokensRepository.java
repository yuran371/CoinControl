package com.mergeteam.coincontrol.security.tokenAuth.repositories;

import com.mergeteam.coincontrol.security.tokenAuth.entities.DeactivatedToken;
import com.mergeteam.coincontrol.security.tokenAuth.tokens.Token;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface JwtTokensRepository extends JpaRepository<DeactivatedToken, UUID>{

    @Query("select count(d) > 0 from DeactivatedToken d where d.id = :uuid")
    public boolean checkIfTokenDeactivated(@Param("uuid") UUID uuid);

}
