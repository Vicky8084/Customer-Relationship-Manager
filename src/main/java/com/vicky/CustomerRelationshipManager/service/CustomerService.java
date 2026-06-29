package com.vicky.CustomerRelationshipManager.service;
import com.vicky.CustomerRelationshipManager.converter.CustomerConverter;
import com.vicky.CustomerRelationshipManager.document.CustomerDocument;
import com.vicky.CustomerRelationshipManager.dto.CustomerRequestDto;
import com.vicky.CustomerRelationshipManager.elasticConverter.CustomerElasticConverter;
import com.vicky.CustomerRelationshipManager.elasticRepository.CustomerElasticRepository;
import com.vicky.CustomerRelationshipManager.model.Customer;
import com.vicky.CustomerRelationshipManager.dbRepository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
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

    public Customer updateById(Long id, CustomerRequestDto customerRequestDto){
        Customer customer=findById(id);
        customer.setOccupation(customerRequestDto.getOccupation().trim());
        customer.setDateOfBirth(customerRequestDto.getDateOfBirth());
        customer.setAddress(customerRequestDto.getAddress().trim());
        customer.setPhoneNumber(customerRequestDto.getPhoneNumber());
        customer.setEmail(customerRequestDto.getEmail().trim());
        customer.setCustomerType(customerRequestDto.getCustomerType());
        customer.setSource(customerRequestDto.getSource());
        customer.setPreferredContact(customerRequestDto.getPreferredContact().trim());
        customer.setStatus(customerRequestDto.getStatus());
        customer.setAnniversaryDate(customerRequestDto.getAnniversaryDate());
        customer.setLastContactDate(customerRequestDto.getLastContactDate());
        customer.setNextFollowUpDate(customerRequestDto.getNextFollowUpDate());
        customer.setTotalPurchaseAmount(customerRequestDto.getTotalPurchaseAmount());
        customer.setTotalInteractions(customerRequestDto.getTotalInteractions());
        customer.setCreditLimit(customerRequestDto.getCreditLimit());
        customer.setNotes(customerRequestDto.getNotes().trim());
        customer.setCreatedBy(customerRequestDto.getCreatedBy().trim());
        customer.setUpdatedBy(customerRequestDto.getUpdatedBy().trim());
        customer.setName(customerRequestDto.getName().trim());
        customerRepository.save(customer);
        return customer;
    }

    public Customer updateByEmail(String email,CustomerRequestDto customerRequestDto){
        Customer customer=findByEmail(email);
        customer.setOccupation(customerRequestDto.getOccupation().trim());
        customer.setDateOfBirth(customerRequestDto.getDateOfBirth());
        customer.setAddress(customerRequestDto.getAddress().trim());
        customer.setPhoneNumber(customerRequestDto.getPhoneNumber());
        customer.setEmail(customerRequestDto.getEmail().trim());
        customer.setCustomerType(customerRequestDto.getCustomerType());
        customer.setSource(customerRequestDto.getSource());
        customer.setPreferredContact(customerRequestDto.getPreferredContact().trim());
        customer.setStatus(customerRequestDto.getStatus());
        customer.setAnniversaryDate(customerRequestDto.getAnniversaryDate());
        customer.setLastContactDate(customerRequestDto.getLastContactDate());
        customer.setNextFollowUpDate(customerRequestDto.getNextFollowUpDate());
        customer.setTotalPurchaseAmount(customerRequestDto.getTotalPurchaseAmount());
        customer.setTotalInteractions(customerRequestDto.getTotalInteractions());
        customer.setCreditLimit(customerRequestDto.getCreditLimit());
        customer.setNotes(customerRequestDto.getNotes().trim());
        customer.setCreatedBy(customerRequestDto.getCreatedBy().trim());
        customer.setUpdatedBy(customerRequestDto.getUpdatedBy().trim());
        customer.setName(customerRequestDto.getName().trim());
        customerRepository.save(customer);
        return customer;
    }

    public void deleteById(Long id){
        Customer customer=findById(id);
        customerRepository.delete(customer);
    }

    public void deleteByEmail(String email){
        Customer customer=findByEmail(email);
        customerRepository.delete(customer);
    }



    public List<Customer> flexibleSearch(String name, String email, String phoneNumber){
        HashSet<Long> ids=new HashSet<>();
        if(name!=null){
            List<CustomerDocument> customerDocuments=customerElasticRepository.findByName(name);
            for (CustomerDocument customerDocument : customerDocuments){
                ids.add(Long.parseLong(customerDocument.getId()));
            }
        }
        if(email!=null){
            CustomerDocument customerDocument=customerElasticRepository.findByEmail(email);
            ids.add(Long.parseLong(customerDocument.getId()));
        }
        if(phoneNumber!=null){
            CustomerDocument customerDocument=customerElasticRepository.findByPhoneNumber(phoneNumber);
            ids.add(Long.parseLong(customerDocument.getId()));
        }
        if (ids.isEmpty()){
            return new ArrayList<>();
        }
        return customerRepository.findAllById(ids);
    }



    public List<Customer> findByOccupation(String occupation){
        List<CustomerDocument> customerDocumentList=customerElasticRepository.findByOccupation(occupation);
        if(customerDocumentList.isEmpty()){
            throw new RuntimeException("Customers not found with Occupation :- "+occupation);
        }
        ArrayList<Long> ids=new ArrayList<>();
        for(CustomerDocument customerDocument : customerDocumentList){
            ids.add(Long.parseLong(customerDocument.getId()));
        }
        return customerRepository.findAllById(ids);
    }

}
