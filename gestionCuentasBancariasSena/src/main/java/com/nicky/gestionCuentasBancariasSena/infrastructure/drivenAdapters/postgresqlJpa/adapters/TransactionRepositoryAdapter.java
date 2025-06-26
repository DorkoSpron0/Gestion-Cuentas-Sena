package com.nicky.gestionCuentasBancariasSena.infrastructure.drivenAdapters.postgresqlJpa.adapters;

import com.nicky.gestionCuentasBancariasSena.domain.models.TransactionDomain;
import com.nicky.gestionCuentasBancariasSena.domain.models.gateways.TransactionGateway;
import com.nicky.gestionCuentasBancariasSena.infrastructure.drivenAdapters.postgresqlJpa.dbo.TransactionDBO;
import com.nicky.gestionCuentasBancariasSena.infrastructure.drivenAdapters.postgresqlJpa.dbo.UserDBO;
import com.nicky.gestionCuentasBancariasSena.infrastructure.drivenAdapters.postgresqlJpa.repositories.TransactionRepository;
import com.nicky.gestionCuentasBancariasSena.infrastructure.drivenAdapters.postgresqlJpa.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor

@Repository
public class TransactionRepositoryAdapter implements TransactionGateway {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Override
    public List<TransactionDomain> getAllTransactions(Long userId) {
        return transactionRepository.findAll().stream()
                .filter(transactionDBO -> transactionDBO.getUser().getId().equals(userId))
                .map(TransactionDBO::toDomain)
                .toList();
    }

    @Override
    public TransactionDomain getTransactionById(Long transactionId){
        return transactionRepository.findById(transactionId)
                .map(TransactionDBO::toDomain)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transacion Not found"));
    }

    @Override
    public TransactionDomain createTransaction(Long userId, TransactionDomain transaction) {
        UserDBO userFounded = findUserOrThrow(userId);

        TransactionDBO transactionMapped = TransactionDBO.fromDomain(transaction);
        transactionMapped.setUser(userFounded);

        TransactionDBO saved = transactionRepository.save(transactionMapped);

        return saved.toDomain();
    }

    @Override
    public TransactionDomain updateTransaction(Long transactionId, TransactionDomain newTransaction) {
        TransactionDBO transactionFounded = findTransactionOrThrow(transactionId);

        TransactionDBO transactionMapped = TransactionDBO.fromDomain(newTransaction);

        TransactionDBO transactionToUpdate = TransactionDBO.builder()
                .id(transactionFounded.getId())
                .dateTime(transactionMapped.getDateTime())
                .amount(transactionMapped.getAmount())
                .description(transactionMapped.getDescription())
                .amountType(transactionMapped.getAmountType())
                .type(transactionMapped.getType())
                .user(transactionFounded.getUser())
                .build();

        TransactionDBO transactionUpdated = transactionRepository.save(transactionToUpdate);

        return transactionUpdated.toDomain();
    }

    @Override
    public String deleteTransaction(Long transactionId) {
        TransactionDBO transactionFounded = findTransactionOrThrow(transactionId);

        transactionRepository.delete(transactionFounded);
        return "Transaction with id " + transactionId + " removed successfully";
    }

    private UserDBO findUserOrThrow(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private TransactionDBO findTransactionOrThrow(Long transactionId){
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
    }
}
