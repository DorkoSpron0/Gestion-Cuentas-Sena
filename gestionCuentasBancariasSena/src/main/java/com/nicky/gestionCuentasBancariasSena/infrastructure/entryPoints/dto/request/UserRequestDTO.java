package com.nicky.gestionCuentasBancariasSena.infrastructure.entryPoints.dto.request;

import com.nicky.gestionCuentasBancariasSena.domain.models.UserDomain;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserRequestDTO {

    private String email;
    private String password;

    public UserDomain toDomain(){
        return new UserDomain(
                null,
                getEmail(),
                getPassword(),
                null
        );
    }
}
