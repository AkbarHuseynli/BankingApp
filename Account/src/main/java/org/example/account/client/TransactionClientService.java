package org.example.account.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "transaction-service", path = "/v1/transactions")
public interface TransactionClientService {

    Logger logger = LoggerFactory.getLogger(TransactionClientService.class);

    @PostMapping("/add")
    ResponseEntity<TransactionDTO> saveTransaction(@RequestBody TransactionDTO transactionDTO);

//    @CircuitBreaker(name="getTransactionById", fallbackMethod = "getTransactionFallBack")
    @GetMapping("/get")
    ResponseEntity<TransactionDTO> getById(@RequestParam String transactionId);

//    default ResponseEntity<TransactionDTO> getTransactionFallBack(String transactionId, Exception exception){
//        logger.info("Could not get transaction by id : " + transactionId);
//        return ResponseEntity
//                .ok(new TransactionDTO
//                        ("default-transaction","INITIAL",new BigDecimal(11), LocalDateTime.now()));
//
//    }

}

