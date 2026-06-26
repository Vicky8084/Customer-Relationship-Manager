package com.vicky.CustomerRelationshipManager.service;
import com.vicky.CustomerRelationshipManager.converter.CustomerConverter;
import com.vicky.CustomerRelationshipManager.document.CustomerDocument;
import com.vicky.CustomerRelationshipManager.dto.CustomerRequestDto;
import com.vicky.CustomerRelationshipManager.elasticConverter.CustomerElasticConverter;
import com.vicky.CustomerRelationshipManager.elasticRepository.CustomerElasticRepository;
import com.vicky.CustomerRelationshipManager.model.Customer;
import com.vicky.CustomerRelationshipManager.dbRepository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerElasticRepository customerElasticRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           CustomerElasticRepository customerElasticRepository){
        this.customerRepository=customerRepository;
        this.customerElasticRepository=customerElasticRepository;
    }

    public Customer saveCustomer(CustomerRequestDto customerRequestDto){
        Customer customer=CustomerConverter.convertCustomerRequestDtoIntoCustomer(customerRequestDto);
        Customer savedCustomer=customerRepository.save(customer);
        CustomerDocument customerDocument=CustomerElasticConverter.convertCustomerIntoCustomerDocument(savedCustomer);
        customerElasticRepository.save(customerDocument);
        return savedCustomer;
    }

    public Customer findCustomerById(Long id){
        Optional<Customer> customer= customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new RuntimeException("Customer not found with id :- "+id);
        }
        return customer.get();
    }

    public List<Customer> findALlCustomer(){
        return customerRepository.findAll();
    }

    public Customer findCustomerByEmail(String email){
        Optional<Customer> ExistingCustomer=customerRepository.findByEmail(email);
        if(ExistingCustomer.isEmpty()){
            throw new RuntimeException("Customer not found with Email :- "+email);
        }
        return ExistingCustomer.get();
    }

    public Customer findCustomerByName( String name){
        Optional<Customer> customerOptional =customerRepository.findByName(name);
        if(customerOptional.isEmpty()){
            throw new RuntimeException("Customer not found with name :- "+name);
        }
        return customerOptional.get();
    }

    public Customer findCustomerByPhoneNumber(String phoneNumber){
        Optional<Customer> customerOptional=customerRepository.findByPhoneNumber(phoneNumber);
        if (customerOptional.isEmpty()){
            throw new RuntimeException("Customer Not Found with Phone Number :- "+phoneNumber);
        }
        return customerOptional.get();
    }

    public Customer updateCustomerByEmail(String email,CustomerRequestDto customerRequestDto){
        Customer customer=findCustomerByEmail(email);
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

    public void deleteCustomerById(long id){
        Customer customer=findCustomerById(id);
        customerRepository.delete(customer);
    }

    //deleteByPhone
    public void deleteByPhone(String phoneNumber){
        Customer customer=findCustomerByPhoneNumber(phoneNumber);
        customerRepository.delete(customer);
    }
    //deleteByEmail
    public void deleteByEmail(String email){
        Customer customer=findCustomerByEmail(email);
        customerRepository.delete(customer);
    }

    public void deleteByName(String name){
        Customer customer=findCustomerByName(name);
        customerRepository.delete(customer);
    }

    public List<CustomerDocument> searchByName(String name){
        return customerElasticRepository.findByName(name);
    }



}
