package com.vicky.CustomerRelationshipManager.controller;
import com.vicky.CustomerRelationshipManager.dto.CustomerRequestDto;
import com.vicky.CustomerRelationshipManager.model.Customer;
import com.vicky.CustomerRelationshipManager.service.CustomerService;
import jakarta.validation.Valid;
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
    public ResponseEntity saveCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto){
        Customer customer=customerService.saveCustomer(customerRequestDto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity findCustomerById(@PathVariable Long id){
        Customer customer=customerService.findCustomerById(id);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> findAllCustomer(){
        List<Customer> customers = customerService.findALlCustomer();
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

    @GetMapping("getByEmail/{email}")
    public ResponseEntity<Customer> findCustomerByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(customerService.findCustomerByEmail(email));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable long id){
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomer(@RequestParam(required = false) String name,
                                                         @RequestParam(required = false)String phoneNumber,
                                                         @RequestParam(required = false)String email,
                                                         @RequestParam(required = false)String occupation){
        return ResponseEntity.ok().body(customerService.searchCustomer(name,phoneNumber,email,occupation));
    }

    @DeleteMapping("/deleteByPhone/{phoneNumber}")
    public ResponseEntity<Customer> deleteByPhoneNumber(String phoneNUmber){
        customerService.deleteByPhone(phoneNUmber);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity<Customer> deleteByName(String name){
        customerService.deleteByPhone(name);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteByEmail/{email}")
    public ResponseEntity<Customer> deleteByEmail(String email){
        customerService.deleteByPhone(email);
        return ResponseEntity.noContent().build();
    }


}
