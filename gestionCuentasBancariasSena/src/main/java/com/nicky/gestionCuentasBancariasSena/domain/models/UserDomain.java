package com.nicky.gestionCuentasBancariasSena.domain.models;

import java.util.List;

public record UserDomain(
        Long id,
        String email,
        String password,
        List<TransactionDomain> transactions) {
}
