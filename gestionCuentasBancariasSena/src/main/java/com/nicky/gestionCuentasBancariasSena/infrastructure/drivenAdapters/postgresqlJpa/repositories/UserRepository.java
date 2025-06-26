package com.nicky.gestionCuentasBancariasSena.infrastructure.drivenAdapters.postgresqlJpa.repositories;

import com.nicky.gestionCuentasBancariasSena.infrastructure.drivenAdapters.postgresqlJpa.dbo.UserDBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDBO, Long> {
    Optional<UserDBO> findByEmail(String email);
}
