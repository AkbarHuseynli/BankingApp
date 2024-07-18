package org.example.transaction.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.example.transaction.model.TransactionDTO;
import org.example.transaction.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/add")
    public ResponseEntity<TransactionDTO> saveTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        return transactionService.saveTransaction(transactionDTO);
    }
    
   @GetMapping("/get")
   public ResponseEntity<TransactionDTO> getById(@NotEmpty @RequestParam String transactionId) {
       return transactionService.getById(transactionId);
   }

}
