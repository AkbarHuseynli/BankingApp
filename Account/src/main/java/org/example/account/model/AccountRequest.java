package org.example.account.model;

import jakarta.validation.constraints.Min;
import java.math.BigDecimal;

public record AccountRequest(

        @Min(0)
        BigDecimal initialCredit
)
{

}
