package org.example.customer.service;

import jakarta.transaction.Transactional;
import org.example.customer.client.AccountClientService;
import org.example.customer.client.AccountDTO;
import org.example.customer.exception.CustomerNotFoundException;
import org.example.customer.model.Customer;
import org.example.customer.model.CustomerBuilder;
import org.example.customer.model.CustomerDTO;
import org.example.customer.model.CustomerDTOBuilder;
import org.example.customer.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountClientService accountClientService;

    public CustomerService(CustomerRepository customerRepository, AccountClientService accountClientService) {
        this.customerRepository = customerRepository;
        this.accountClientService = accountClientService;
    }

    private Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException("Customer could not be found by id:" + id));

    }

    public ResponseEntity<CustomerDTO> createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerBuilder.customer()
                .name(customerDTO.name())
                .surname(customerDTO.surname()).build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapToCustomerDTO(customerRepository.save(customer)));
    }

    public ResponseEntity<CustomerDTO> getByCustomerId(Long id) {
        Customer customer = findCustomerById(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(mapToCustomerDTO(customer));
    }


    public ResponseEntity<CustomerDTO> createAccount(Long customerId, AccountDTO accountDTO) {
        Customer customer = findCustomerById(customerId);

        customer.getAccounts()
                .add(accountClientService.createAccount(accountDTO).getBody().id());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapToCustomerDTO(customerRepository.save(customer)));


    }

    private CustomerDTO mapToCustomerDTO(Customer customer) {
        Set<AccountDTO> accountDTOSet = mapToAccountDTO(customer);
        return CustomerDTOBuilder.customerDTO()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .accounts(accountDTOSet)
                .build();

    }

    private Set<AccountDTO> mapToAccountDTO(Customer customer) {
        return customer.getAccounts()
                .stream()
                .map(accountClientService::getAccount)
                .map(ResponseEntity::getBody)
                .collect(Collectors.toSet());
    }

    public ResponseEntity<Set<AccountDTO>> getAllAccounts(Long customerId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(findCustomerById(customerId)
                .getAccounts()
                .stream()
                .map(accountClientService::getAccount)
                .map(ResponseEntity::getBody)
                .collect(Collectors.toSet()));
    }
}
