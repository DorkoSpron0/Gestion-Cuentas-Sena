package com.nicky.gestionCuentasBancariasSena.domain.usecases;

import com.nicky.gestionCuentasBancariasSena.domain.models.UserDomain;
import com.nicky.gestionCuentasBancariasSena.domain.models.gateways.UserGateway;

public class UserUseCase {

    private final UserGateway userGateway;

    public UserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public UserDomain registerUser(UserDomain user) {
        if(user.email() == null || user.password() == null){
            throw new IllegalArgumentException("Invalid credentials, check the information");
        }
        return userGateway.registerUser(user);
    }

    public UserDomain loginUser(UserDomain user) {
        return userGateway.loginUser(user);
    }
}
