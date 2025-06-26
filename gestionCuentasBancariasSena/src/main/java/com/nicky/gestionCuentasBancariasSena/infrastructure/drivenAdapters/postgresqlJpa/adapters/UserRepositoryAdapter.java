package com.nicky.gestionCuentasBancariasSena.infrastructure.drivenAdapters.postgresqlJpa.adapters;

import com.nicky.gestionCuentasBancariasSena.domain.models.UserDomain;
import com.nicky.gestionCuentasBancariasSena.domain.models.gateways.UserGateway;
import com.nicky.gestionCuentasBancariasSena.infrastructure.drivenAdapters.postgresqlJpa.dbo.UserDBO;
import com.nicky.gestionCuentasBancariasSena.infrastructure.drivenAdapters.postgresqlJpa.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor

@Repository
public class UserRepositoryAdapter implements UserGateway {

    private final UserRepository userRepository;

    @Override
    public UserDomain registerUser(UserDomain user) {
        return userRepository.save(UserDBO.fromDomain(user)).toDomain();
    }

    @Override
    public UserDomain loginUser(UserDomain user) {
        UserDBO userFounded = userRepository.findByEmail(user.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if(!userFounded.getPassword().equals(user.password())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }

        return userFounded.toDomain();
    }
}
