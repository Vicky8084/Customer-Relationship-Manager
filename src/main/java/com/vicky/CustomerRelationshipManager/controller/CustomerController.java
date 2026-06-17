package com.vicky.CustomerRelationshipManager.controller;
import com.vicky.CustomerRelationshipManager.dto.CustomerRequestDto;
import com.vicky.CustomerRelationshipManager.model.Customer;
import com.vicky.CustomerRelationshipManager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping("/save")
    public ResponseEntity saveCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        Customer customer=customerService.saveCustomer(customerRequestDto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getCustomerById(@PathVariable Long id){
        Customer customer=customerService.getCustomerById(id);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> customers = customerService.getAllCustomer();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @PutMapping("/updateByEmail/{email}")
    public ResponseEntity updateCustomerByEmail(@PathVariable String email,@RequestBody CustomerRequestDto customerRequestDto){
        Customer customer=customerService.updateCustomerByEmail(email,customerRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity findCustomerByName(@PathVariable String name){
        Customer customer=customerService.findCustomerByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping("/getByPhoneNumber/{phoneNumber}")
    public ResponseEntity findCustomerByPhoneNumber(@PathVariable String phoneNumber){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomerByPhoneNumber(phoneNumber));
    }
}
