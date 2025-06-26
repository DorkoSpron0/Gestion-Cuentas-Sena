package com.nicky.gestionCuentasBancariasSena.domain.usecases;

import com.nicky.gestionCuentasBancariasSena.domain.models.TransactionDomain;
import com.nicky.gestionCuentasBancariasSena.domain.models.gateways.TransactionGateway;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionUseCase {

    private final TransactionGateway transactionGateway;

    public TransactionUseCase(TransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    public List<TransactionDomain> getAllTransactions(Long userId) {
        return transactionGateway.getAllTransactions(userId);
    }

    public TransactionDomain getTransactionById(Long transactionId){
        return transactionGateway.getTransactionById(transactionId);
    }

    public TransactionDomain createTransaction(Long userId, TransactionDomain transaction) {
        if(transaction.amount() <= 0 || transaction.amountType() == null || transaction.description() == null || transaction.type() == null){
            throw new IllegalArgumentException("Invalid credentials, check the information");
        }

        TransactionDomain transactionToSave = new TransactionDomain(
                transaction.id() != null ? transaction.id() : null,
                LocalDateTime.now(),
                transaction.amount(),
                transaction.description(),
                transaction.amountType(),
                transaction.type(),
                transaction.user() != null ? transaction.user() : null
        );

        return transactionGateway.createTransaction(userId, transactionToSave);
    }

    public TransactionDomain updateTransaction(Long transactionId, TransactionDomain newTransaction) {
        if(transactionId == null || newTransaction.amount() <= 0 || newTransaction.amountType() == null || newTransaction.description() == null || newTransaction.type() == null){
            throw new IllegalArgumentException("Invalid credentials, check the information");
        }

        TransactionDomain transactionToSave = new TransactionDomain(
                transactionId,
                LocalDateTime.now(),
                newTransaction.amount(),
                newTransaction.description(),
                newTransaction.amountType(),
                newTransaction.type(),
                newTransaction.user() != null ? newTransaction.user() : null
        );


        return transactionGateway.updateTransaction(transactionId, transactionToSave);
    }

    public String deleteTransaction(Long transactionId) {
        return transactionGateway.deleteTransaction(transactionId);
    }
}
