package org.example.account.service;

import jakarta.transaction.Transactional;
import org.example.account.client.TransactionClientService;
import org.example.account.client.TransactionDTO;
import org.example.account.client.TransactionDTOBuilder;
import org.example.account.exception.AccountNotFoundException;
import org.example.account.model.*;
import org.example.account.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionClientService transactionService;
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    public AccountService(AccountRepository accountRepository, TransactionClientService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    public ResponseEntity<AccountDTO> depositMoney(Long accountId, AccountRequest accountRequest) {
        Account account = findAccountById(accountId);
        account.setBalance(account.getBalance().add(accountRequest.initialCredit()));

        if (accountRequest.initialCredit().compareTo(BigDecimal.ZERO) > 0) {
            TransactionDTO transactionDTO = TransactionDTOBuilder
                    .transactionDTO()
                    .amount(accountRequest.initialCredit()).build();
            account
                    .getTransactions()
                    .add(transactionService
                            .saveTransaction(transactionDTO)
                            .getBody().id());

        }

        accountRepository.save(account);
        logger.info("Account is created: " + account);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(mapToAccountDTO(account));

    }
    public ResponseEntity<AccountDTO> createAccount(AccountDTO accountDTO) {

        Account account = AccountBuilder.account()
                .balance(new BigDecimal(0))
                .creationDate(LocalDateTime.now())
                .build();

        accountRepository.save(account);

        logger.info("Account is created: " + account);

        return ResponseEntity.status(HttpStatus.OK)
                .body(mapToAccountDTO(account));

    }

    public ResponseEntity<AccountDTO> getAccount(Long id) {
        Account account = findAccountById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(mapToAccountDTO(account));

    }

    private Account findAccountById(Long id) {
        return accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account could not be found!"));
    }

    private AccountDTO mapToAccountDTO(Account account) {
        Set<TransactionDTO> transactionDTOSet = mapToTransactionDTO(account);
        return AccountDTOBuilder.accountDTO()
                .id(account.getId())
                .balance(account.getBalance())
                .creationDate(account.getCreationDate())
                .transactions(transactionDTOSet)
                .build();
    }
    private Set<TransactionDTO> mapToTransactionDTO(Account account) {
        return account.getTransactions()
                .stream()
                .map(transactionService::getById)
                .map(ResponseEntity::getBody)
                .collect(Collectors.toSet());
    }

    public ResponseEntity<Set<TransactionDTO>> getAllTransactions(Long accountId) {
        Account account = findAccountById(accountId);
        return ResponseEntity.status(HttpStatus.FOUND).body(mapToTransactionDTO(account));
    }

    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(
                        accountRepository.findAll()
                                .stream().map(this::mapToAccountDTO)
                                .toList()
                );

    }
}
