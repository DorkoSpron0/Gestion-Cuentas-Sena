package com.nicky.gestionCuentasBancariasSena.infrastructure.drivenAdapters.postgresqlJpa.repositories;

import com.nicky.gestionCuentasBancariasSena.infrastructure.drivenAdapters.postgresqlJpa.dbo.TransactionDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionDBO, Long> {
}
