package com.vicky.CustomerRelationshipManager.controller;
import com.vicky.CustomerRelationshipManager.document.CustomerDocument;
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
import java.util.Optional;

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
        log.info("Received Dto from the User to Save Customer : {}", customerRequestDto);
        Customer customer=customerService.saveCustomer(customerRequestDto);
        log.info("Customer saved successfully with id: {}", customer.getId());
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id){
        Customer customer=customerService.findCustomerById(id);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> findAllCustomer(){
        List<Customer> customers = customerService.findALlCustomer();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @PutMapping("/updateByEmail/{email}")
    public ResponseEntity<Customer> updateCustomerByEmail(@PathVariable String email,@RequestBody CustomerRequestDto customerRequestDto){
        Customer customer=customerService.updateCustomerByEmail(email,customerRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<Customer> findCustomerByName(@PathVariable String name){
        Customer customer=customerService.findCustomerByName(name.trim());
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping("/getByPhoneNumber/{phoneNumber}")
    public ResponseEntity<Customer> findCustomerByPhoneNumber(@PathVariable String phoneNumber){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomerByPhoneNumber(phoneNumber));
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<Customer> findCustomerByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(customerService.findCustomerByEmail(email));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable long id){
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteByPhone/{phoneNumber}")
    public ResponseEntity<Customer> deleteByPhoneNumber(@PathVariable String phoneNumber){
        customerService.deleteByPhone(phoneNumber);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity<Customer> deleteByName(@PathVariable String name){
        customerService.deleteByPhone(name);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteByEmail/{email}")
    public ResponseEntity<Customer> deleteByEmail(@PathVariable String email){
        customerService.deleteByPhone(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchByName/{name}")
    public ResponseEntity<List<CustomerDocument>> searchByName(@PathVariable String name){
        List<CustomerDocument> customers=customerService.searchByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }


}
