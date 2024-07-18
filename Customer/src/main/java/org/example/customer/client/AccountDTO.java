package org.example.customer.client;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountDTO(
        Long id,
        BigDecimal balance,
        LocalDateTime creationDate
){

}
