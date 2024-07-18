package org.example.account.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.example.account.client.TransactionDTO;
import org.example.account.model.AccountDTO;
import org.example.account.model.AccountRequest;
import org.example.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<AccountDTO> depositMoney(@PathVariable Long accountId, @RequestBody AccountRequest accountRequest) {
       return accountService.depositMoney(accountId, accountRequest);
    }
    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO accountDTO) {
       return accountService.createAccount(accountDTO);
    }

    @GetMapping("/get{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id){
        return accountService.getAccount(id);
    }

    @GetMapping("/getTransactions")
    public ResponseEntity<Set<TransactionDTO>> getAllTransactions(Long accountId){
        return accountService.getAllTransactions(accountId);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<AccountDTO>> getAllAccounts(){
        return accountService.getAllAccounts();
    }


}
