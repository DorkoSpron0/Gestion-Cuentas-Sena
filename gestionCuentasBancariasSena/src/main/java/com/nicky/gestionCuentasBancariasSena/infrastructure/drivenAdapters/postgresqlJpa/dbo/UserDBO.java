package com.nicky.gestionCuentasBancariasSena.infrastructure.drivenAdapters.postgresqlJpa.dbo;

import com.nicky.gestionCuentasBancariasSena.domain.models.TransactionDomain;
import com.nicky.gestionCuentasBancariasSena.domain.models.UserDomain;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "user_TABLE")
public class UserDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TransactionDBO> transactions;

    public static UserDBO fromDomain(UserDomain domain){
        UserDBO.UserDBOBuilder builder = UserDBO.builder()
                .id(domain.id())
                .email(domain.email())
                .password(domain.password());

        if(domain.transactions() != null) {
            builder.transactions(
                    domain.transactions().stream()
                            .map(transactionDomain ->
                                    TransactionDBO.builder()
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

    public UserDomain toDomain(){
        return new UserDomain(
                id,
                email,
                password,
                transactions != null ? transactions.stream()
                        .map(transactionDBO -> new TransactionDomain(
                                transactionDBO.getId(),
                                transactionDBO.getDateTime(),
                                transactionDBO.getAmount(),
                                transactionDBO.getDescription(),
                                transactionDBO.getAmountType(),
                                transactionDBO.getType(),
                                transactionDBO.getUser() != null ? new UserDomain(
                                        transactionDBO.getUser().getId(),
                                        transactionDBO.getUser().getEmail(),
                                        transactionDBO.getUser().getPassword(),
                                        null
                                ) : null
                        )).toList() : null
        );
    }
}
