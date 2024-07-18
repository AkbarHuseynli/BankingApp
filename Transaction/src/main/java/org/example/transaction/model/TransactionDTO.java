package org.example.transaction.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(
    String id,
    String transactionType,
    BigDecimal amount,
    LocalDateTime transactionDate
    ){
}
