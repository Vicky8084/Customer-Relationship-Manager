package com.vicky.CustomerRelationshipManager.converter;

import com.vicky.CustomerRelationshipManager.dto.CustomerRequestDto;
import com.vicky.CustomerRelationshipManager.model.Customer;

public class CustomerConverter {
    public static Customer convertCustomerRequestDtoIntoCustomer(CustomerRequestDto customerRequestDto){
        Customer customer=new Customer();
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
        return customer;
    }
}

