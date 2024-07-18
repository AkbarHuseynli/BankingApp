package org.example.account.client;

import org.jilt.Builder;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TransactionDTO(
    String id,
    String transactionType,
    BigDecimal amount,
    LocalDateTime transactionDate
    ){
}
