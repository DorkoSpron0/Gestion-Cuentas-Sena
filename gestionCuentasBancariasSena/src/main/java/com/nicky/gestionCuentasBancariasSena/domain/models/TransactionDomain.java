package com.nicky.gestionCuentasBancariasSena.domain.models;

import java.time.LocalDateTime;

public record TransactionDomain(
        Long id,
        LocalDateTime dateTime,
        Long amount,
        String description,
        AmountType amountType,
        TransactionType type,
        UserDomain user) {
}
