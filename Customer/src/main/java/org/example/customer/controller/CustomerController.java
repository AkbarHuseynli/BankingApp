package org.example.customer.controller;
import org.example.customer.client.AccountDTO;
import org.example.customer.model.CustomerDTO;
import org.example.customer.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @PostMapping("/createAccount/{customerId}")
    public ResponseEntity<CustomerDTO> createAccount(@PathVariable Long customerId, @RequestBody AccountDTO accountDTO) {
        return customerService.createAccount(customerId, accountDTO);
    }

    @GetMapping("/getCustomer/{id}")
    public ResponseEntity<CustomerDTO> getByCustomerId(@PathVariable Long id){
        return customerService.getByCustomerId(id);
    }

    @GetMapping("/getAllAccounts/{customerId}")
    public ResponseEntity<Set<AccountDTO>> getAllAccounts(@PathVariable Long customerId){
        return customerService.getAllAccounts(customerId);
    }


}
