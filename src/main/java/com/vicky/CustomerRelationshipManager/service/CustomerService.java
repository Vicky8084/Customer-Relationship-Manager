package com.vicky.CustomerRelationshipManager.service;
import com.vicky.CustomerRelationshipManager.converter.CustomerConverter;
import com.vicky.CustomerRelationshipManager.dto.CustomerRequestDto;
import com.vicky.CustomerRelationshipManager.model.Customer;
import com.vicky.CustomerRelationshipManager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    public Customer saveCustomer(CustomerRequestDto customerRequestDto){
        Customer customer= CustomerConverter.convertCustomerRequestDtoIntoCustomer(customerRequestDto);
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id){
        Optional<Customer> customer= customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new RuntimeException("Customer not found with id :- "+id);
        }
        return customer.get();
    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public Customer updateCustomerByEmail(String email,CustomerRequestDto customerRequestDto){
        Optional<Customer> ExistingCustomer=customerRepository.findByEmail(email);
        if(ExistingCustomer.isEmpty()){
            throw new RuntimeException("Customer not found with Email :- "+email);
        }
        Customer customer=ExistingCustomer.get();
        customer.setOccupation(customerRequestDto.getOccupation());
        customer.setDateOfBirth(customerRequestDto.getDateOfBirth());
        customer.setAddress(customerRequestDto.getAddress());
        customer.setPhoneNumber(customerRequestDto.getPhoneNumber());
        customer.setEmail(customerRequestDto.getEmail());
        customer.setCustomerType(customerRequestDto.getCustomerType());
        customer.setSource(customerRequestDto.getSource());
        customer.setPreferredContact(customerRequestDto.getPreferredContact());
        customer.setStatus(customerRequestDto.getStatus());
        customer.setAnniversaryDate(customerRequestDto.getAnniversaryDate());
        customer.setLastContactDate(customerRequestDto.getLastContactDate());
        customer.setNextFollowUpDate(customerRequestDto.getNextFollowUpDate());
        customer.setTotalPurchaseAmount(customerRequestDto.getTotalPurchaseAmount());
        customer.setTotalInteractions(customerRequestDto.getTotalInteractions());
        customer.setCreditLimit(customerRequestDto.getCreditLimit());
        customer.setNotes(customerRequestDto.getNotes());
        customer.setUpdatedBy("admin");
        return customerRepository.save(customer);
    }

    public Customer findCustomerByName( String name){
        Optional<Customer> customerOptional =customerRepository.findByName(name);
        if(customerOptional.isEmpty()){
            throw new RuntimeException("Customer not found with name :- "+name);
        }
        Customer customer=customerOptional.get();
        return customer;
    }

    public Customer findCustomerByPhoneNumber(String phoneNumber){
        Optional<Customer> customerOptional=customerRepository.findByPhone(phoneNumber);
        if (customerOptional.isEmpty()){
            throw new RuntimeException("Customer Not Found with Phone Number :- "+phoneNumber);
        }
        return customerOptional.get();
    }
}
