package org.example.customer.client;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@FeignClient(name = "account-service", path = "/v1/account")
public interface AccountClientService {

    Logger logger = LoggerFactory.getLogger(AccountClientService.class);

    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO accountDTO);

    @GetMapping("/get{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id);

}

