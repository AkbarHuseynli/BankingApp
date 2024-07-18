package org.example.account.model;

import org.example.account.client.TransactionDTO;
import org.jilt.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record AccountDTO(
        Long id,
        BigDecimal balance,
        LocalDateTime creationDate,
        Set<TransactionDTO> transactions
){
}
