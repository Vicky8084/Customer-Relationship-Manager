package com.vicky.CustomerRelationshipManager.controller;
import com.vicky.CustomerRelationshipManager.dto.CustomerRequestDto;
import com.vicky.CustomerRelationshipManager.model.Customer;
import com.vicky.CustomerRelationshipManager.service.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping("/save")
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto){
        log.info("saveCustomer API Called with DTO : {}",customerRequestDto);
        Customer customer=customerService.saveCustomer(customerRequestDto);
        log.info("Successfully save Customer : {}",customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id){
        log.info("findById API called with ID : {}",id);
        Customer customer=customerService.findById(id);
        log.info("Customer found : {}",customer);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<List<Customer>> findCustomersByName(@PathVariable String name){
        log.info("getByName API is called with name : {}",name);
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findByName(name));
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<Customer> findCustomerByEmail(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findByEmail(email));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> findAllCustomer(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllCustomer());
    }
}
