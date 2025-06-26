package com.nicky.gestionCuentasBancariasSena.domain.models.gateways;

import com.nicky.gestionCuentasBancariasSena.domain.models.TransactionDomain;

import java.util.List;

public interface TransactionGateway {
    List<TransactionDomain> getAllTransactions(Long userId);
    TransactionDomain getTransactionById(Long transactionId);
    TransactionDomain createTransaction(Long userId, TransactionDomain transaction);
    TransactionDomain updateTransaction(Long transactionId, TransactionDomain newTransaction);
    String deleteTransaction(Long transactionId);
}
