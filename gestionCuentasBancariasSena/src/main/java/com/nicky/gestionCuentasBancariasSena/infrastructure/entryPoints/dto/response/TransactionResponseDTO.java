package com.nicky.gestionCuentasBancariasSena.infrastructure.entryPoints.dto.response;

import com.nicky.gestionCuentasBancariasSena.domain.models.AmountType;
import com.nicky.gestionCuentasBancariasSena.domain.models.TransactionDomain;
import com.nicky.gestionCuentasBancariasSena.domain.models.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionResponseDTO {
    private Long id;
    private LocalDateTime dateTime;
    private Long amount;
    private String description;
    private AmountType amountType;
    private TransactionType type;

    public static TransactionResponseDTO fromDomain(TransactionDomain domain){
        TransactionResponseDTO.TransactionResponseDTOBuilder builder = TransactionResponseDTO.builder()
                .id(domain.id())
                .dateTime(domain.dateTime())
                .amount(domain.amount())
                .description(domain.description())
                .amountType(domain.amountType())
                .type(domain.type());

        return builder.build();
    }

    public TransactionDomain toDomain(){
        return new TransactionDomain(
                getId(),
                getDateTime(),
                getAmount(),
                getDescription(),
                getAmountType(),
                getType(),
                null
        );
    }
}
