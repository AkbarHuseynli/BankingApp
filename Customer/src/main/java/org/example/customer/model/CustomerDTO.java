package org.example.customer.model;

import org.example.customer.client.AccountDTO;
import org.jilt.Builder;
import java.util.Set;

@Builder
public record CustomerDTO(
        Long id,
        String name,
        String surname,
        Set<AccountDTO> accounts
) {
}
