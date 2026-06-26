package com.vicky.CustomerRelationshipManager.elasticConverter;

import com.vicky.CustomerRelationshipManager.document.CustomerDocument;
import com.vicky.CustomerRelationshipManager.model.Customer;

public class CustomerElasticConverter{
    public static Customer convertCustomerDocumentIntoCustomer(CustomerDocument customerDocument){
        Customer customer=new Customer();
        customer.setName(customerDocument.getName());
        customer.setOccupation(customerDocument.getOccupation());
        customer.setAddress(customerDocument.getAddress());
        customer.setPhoneNumber(customerDocument.getPhoneNumber());
        customer.setEmail(customerDocument.getEmail());
        return customer;
    }

    public static CustomerDocument convertCustomerIntoCustomerDocument(Customer customer){
        CustomerDocument customerDocument=new CustomerDocument();
        customerDocument.setId(String.valueOf(customer.getId()));
        customerDocument.setName(customer.getName());
        customerDocument.setAddress(customer.getAddress());
        customerDocument.setOccupation(customer.getOccupation());
        customerDocument.setPhoneNumber(customer.getPhoneNumber());
        customerDocument.setEmail(customer.getEmail());
        return customerDocument;
    }
}