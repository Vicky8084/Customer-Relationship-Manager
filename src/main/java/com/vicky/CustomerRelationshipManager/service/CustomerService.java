package com.vicky.CustomerRelationshipManager.service;
import com.vicky.CustomerRelationshipManager.converter.CustomerConverter;
import com.vicky.CustomerRelationshipManager.document.CustomerDocument;
import com.vicky.CustomerRelationshipManager.dto.CustomerRequestDto;
import com.vicky.CustomerRelationshipManager.elasticConverter.CustomerElasticConverter;
import com.vicky.CustomerRelationshipManager.elasticRepository.CustomerElasticRepository;
import com.vicky.CustomerRelationshipManager.model.Customer;
import com.vicky.CustomerRelationshipManager.dbRepository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerElasticRepository customerElasticRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           CustomerElasticRepository customerElasticRepository){
        this.customerElasticRepository=customerElasticRepository;
        this.customerRepository=customerRepository;
    }

    public Customer saveCustomer(CustomerRequestDto customerRequestDto){
        log.info("Customer Service called : {}",customerRequestDto);
        Customer customer = CustomerConverter.convertCustomerRequestDtoIntoCustomer(customerRequestDto);
        log.info("Converted CustomerDto into Customer : {}",customer);
        Customer savedCustomer =customerRepository.save(customer);
        log.info("Customer saved into DB successfully : {}",savedCustomer);
        CustomerDocument customerDocument=CustomerElasticConverter.convertCustomerIntoCustomerDocument(savedCustomer);
        log.info("Converted customer into CustomerDocument : {}",customerDocument);
        customerElasticRepository.save(customerDocument);
        log.info("CustomerDocument saved into ElasticSearch successfully : {}",savedCustomer);
        return savedCustomer;
    }

    public Customer findById(Long id){
        log.info("findCustomerById method called with id :{}",id);
        Optional<Customer> customerOptional =customerRepository.findById(id);
        log.info("Get Customer with id in the form of CustomerOptional: {}",customerOptional);
        if(customerOptional.isEmpty()){
            throw new RuntimeException("Customer not found with id :- "+id);
        }
        Customer customer=customerOptional.get();
        log.info("Customer Optional Converted into Customer : {}",customer);
        return customer;
    }

    public List<Customer> findAllCustomer(){
        Iterable<CustomerDocument> customerDocumentList =  customerElasticRepository.findAll();
        List<Long> ids=new ArrayList<>();

        for(CustomerDocument customerDocument : customerDocumentList){
            ids.add(Long.parseLong(customerDocument.getId()));
        }

        return customerRepository.findAllById(ids);
    }


    public List<Customer> findByName(String name){
        log.info("findCustomerByName method is called with name : {}",name);
        List<CustomerDocument> customerDocumentList=customerElasticRepository.findByName(name);
        if (customerDocumentList.isEmpty()) {
            throw new RuntimeException("No customers found with name: " + name);
        }
        List<Long> ids=new ArrayList<>();
        for(CustomerDocument customerDocument : customerDocumentList){
            ids.add(Long.parseLong(customerDocument.getId()));
        }
        return customerRepository.findAllById(ids);
    }

    public Customer findByEmail(String email){
        log.info("findCustomerByEmail method is called with email : {}",email);
        CustomerDocument customerDocument=customerElasticRepository.findByEmail(email);
        Optional<Customer> customer=customerRepository.findById(Long.parseLong(customerDocument.getId()));
        if(customer.isEmpty()){
            throw new RuntimeException("Customer Not found woth email : "+email);
        }
        return customer.get();
    }

}
