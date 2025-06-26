package com.nicky.gestionCuentasBancariasSena.infrastructure.entryPoints.dto.response;

import com.nicky.gestionCuentasBancariasSena.domain.models.TransactionDomain;
import com.nicky.gestionCuentasBancariasSena.domain.models.UserDomain;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserResponseDTO {

    private Long id;
    private String email;
    private String password;
    private List<TransactionResponseDTO> transactions;

    public static UserResponseDTO fromDomain(UserDomain domain){
        UserResponseDTO.UserResponseDTOBuilder builder = UserResponseDTO.builder()
                .id(domain.id())
                .email(domain.email())
                .password(domain.password());

        if(domain.transactions() != null){
            builder.transactions(
                    domain.transactions().stream()
                            .map(transactionDomain ->
                                    TransactionResponseDTO.builder()
                                            .id(transactionDomain.id())
                                            .dateTime(transactionDomain.dateTime())
                                            .amount(transactionDomain.amount())
                                            .description(transactionDomain.description())
                                            .amountType(transactionDomain.amountType())
                                            .type(transactionDomain.type())
                                            .build()
                            ).toList()
            );
        }

        return builder.build();
    }
}
