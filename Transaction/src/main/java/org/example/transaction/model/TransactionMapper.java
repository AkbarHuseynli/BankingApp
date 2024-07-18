package org.example.transaction.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "transactionType", source = "transactionType")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "transactionDate", source = "transactionDate")
    TransactionDTO mapToTransactionDTO(Transaction transaction);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "transactionType", source = "transactionType")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "transactionDate", source = "transactionDate")
    Transaction mapToTransaction(TransactionDTO transactionDTO);

}
