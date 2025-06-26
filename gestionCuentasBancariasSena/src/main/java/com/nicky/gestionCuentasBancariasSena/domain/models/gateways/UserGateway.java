package com.nicky.gestionCuentasBancariasSena.domain.models.gateways;

import com.nicky.gestionCuentasBancariasSena.domain.models.UserDomain;

public interface UserGateway {
    UserDomain registerUser(UserDomain user);
    UserDomain loginUser(UserDomain user);
}
