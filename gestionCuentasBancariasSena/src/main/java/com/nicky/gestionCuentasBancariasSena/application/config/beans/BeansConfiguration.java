package com.nicky.gestionCuentasBancariasSena.application.config.beans;

import com.nicky.gestionCuentasBancariasSena.domain.models.gateways.TransactionGateway;
import com.nicky.gestionCuentasBancariasSena.domain.models.gateways.UserGateway;
import com.nicky.gestionCuentasBancariasSena.domain.usecases.TransactionUseCase;
import com.nicky.gestionCuentasBancariasSena.domain.usecases.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public UserUseCase userUseCase(UserGateway userGateway){
        return new UserUseCase(userGateway);
    }

    @Bean
    public TransactionUseCase transactionUseCase(TransactionGateway transactionGateway){
        return new TransactionUseCase(transactionGateway);
    }
}
