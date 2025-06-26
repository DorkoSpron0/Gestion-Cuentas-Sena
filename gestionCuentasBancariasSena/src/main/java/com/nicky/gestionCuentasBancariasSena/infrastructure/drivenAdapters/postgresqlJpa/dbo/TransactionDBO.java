package com.nicky.gestionCuentasBancariasSena.infrastructure.drivenAdapters.postgresqlJpa.dbo;

import com.nicky.gestionCuentasBancariasSena.domain.models.AmountType;
import com.nicky.gestionCuentasBancariasSena.domain.models.TransactionDomain;
import com.nicky.gestionCuentasBancariasSena.domain.models.TransactionType;
import com.nicky.gestionCuentasBancariasSena.domain.models.UserDomain;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "transaction_TABLE")
public class TransactionDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;
    private Long amount;
    private String description;

    @Enumerated(EnumType.STRING)
    private AmountType amountType;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDBO user;

    public static TransactionDBO fromDomain(TransactionDomain domain){
        TransactionDBO.TransactionDBOBuilder builder = TransactionDBO.builder()
                .id(domain.id())
                .dateTime(domain.dateTime())
                .amount(domain.amount())
                .description(domain.description())
                .amountType(domain.amountType())
                .type(domain.type());

        if(domain.user() != null){
            builder.user(
                    new UserDBO(
                            domain.user().id(),
                            domain.user().email(),
                            domain.user().password(),
                            null
                    )
            );
        }

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
                getUser() != null ? new UserDomain(
                        getUser().getId(),
                        getUser().getEmail(),
                        getUser().getPassword(),
                        null
                ): null
        );
    }
}
