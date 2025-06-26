package com.nicky.gestionCuentasBancariasSena.infrastructure.entryPoints;

import com.nicky.gestionCuentasBancariasSena.domain.usecases.TransactionUseCase;
import com.nicky.gestionCuentasBancariasSena.infrastructure.entryPoints.dto.request.TransactionRequestDTO;
import com.nicky.gestionCuentasBancariasSena.infrastructure.entryPoints.dto.response.TransactionResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionUseCase transactionUseCase;

    @GetMapping("/{userId}")
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions(@PathVariable Long userId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        transactionUseCase.getAllTransactions(userId).stream()
                                .map(TransactionResponseDTO::fromDomain)
                                .toList()
                );
    }

    @GetMapping("/transactions/{transactionId}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable Long transactionId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        TransactionResponseDTO.fromDomain(transactionUseCase.getTransactionById(transactionId))
                );
    }

    @PostMapping("/{userId}")
    public ResponseEntity<TransactionResponseDTO> createTransaction(@PathVariable Long userId, @RequestBody TransactionRequestDTO transaction){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TransactionResponseDTO.fromDomain(transactionUseCase.createTransaction(userId, transaction.toDomain())));
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionResponseDTO> updateTransaction(@PathVariable Long transactionId, @RequestBody TransactionRequestDTO transaction){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TransactionResponseDTO.fromDomain(transactionUseCase.updateTransaction(transactionId, transaction.toDomain())));
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long transactionId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionUseCase.deleteTransaction(transactionId));
    }
}
