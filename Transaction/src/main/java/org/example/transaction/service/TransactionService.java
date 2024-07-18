package org.example.transaction.service;

import jakarta.transaction.Transactional;
import org.example.transaction.exception.TransactionNotFoundException;
import org.example.transaction.model.Transaction;
import org.example.transaction.model.TransactionDTO;
import org.example.transaction.model.TransactionMapper;
import org.example.transaction.model.TransactionType;
import org.example.transaction.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    public ResponseEntity<TransactionDTO> saveTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.mapToTransaction(transactionDTO);
        transaction.setTransactionType(TransactionType.INITIAL);
        transaction.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionMapper.mapToTransactionDTO(transactionRepository.save(transaction)));

    }
    public ResponseEntity<TransactionDTO> getById(String transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(()-> new TransactionNotFoundException("transaction couldnt be found"));
        return ResponseEntity.status(HttpStatus.OK)
                .body(transactionMapper.mapToTransactionDTO(transaction));
    }

}
