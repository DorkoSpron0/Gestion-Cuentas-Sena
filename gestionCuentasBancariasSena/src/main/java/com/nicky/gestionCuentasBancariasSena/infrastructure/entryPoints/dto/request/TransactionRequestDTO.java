package com.nicky.gestionCuentasBancariasSena.infrastructure.entryPoints.dto.request;

import com.nicky.gestionCuentasBancariasSena.domain.models.AmountType;
import com.nicky.gestionCuentasBancariasSena.domain.models.TransactionDomain;
import com.nicky.gestionCuentasBancariasSena.domain.models.TransactionType;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionRequestDTO {
    private Long amount;
    private String description;
    private AmountType amountType;
    private TransactionType type;

    public TransactionDomain toDomain(){
        return new TransactionDomain(
                null,
                null,
                getAmount(),
                getDescription(),
                getAmountType(),
                getType(),
                null
        );
    }
}
