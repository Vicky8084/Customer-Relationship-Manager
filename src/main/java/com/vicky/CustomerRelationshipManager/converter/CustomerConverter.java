package com.vicky.CustomerRelationshipManager.converter;

import com.vicky.CustomerRelationshipManager.dto.CustomerRequestDto;
import com.vicky.CustomerRelationshipManager.model.Customer;

public class CustomerConverter {
    public static Customer convertCustomerRequestDtoIntoCustomer(CustomerRequestDto customerRequestDto){
        Customer customer=new Customer();
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
        customer.setCreatedBy(customerRequestDto.getCreatedBy());
        customer.setUpdatedBy(customerRequestDto.getUpdatedBy());
        customer.setName(customerRequestDto.getName());
        return customer;
    }
}

/*
private String name;  // ->  Customer Name
    private String occupation; // ->  Customer Occupation
    private LocalDate dateOfBirth;  // ->  Customer DOB
    private String address;  // ->  Customer Address
    private String phoneNumber; // ->  Customer Phone Number
    private String email;
    private CustomerType customerType;  // ->  Customer Type -> NEW, REGULAR, VIP, INACTIVE, LEAD
    private Source source; // ->  Customer WEBSITE, PHONE, REFERRAL, SOCIAL_MEDIA, WALK_IN
    private String preferredContact;  // ->  Customer preferred Contact like phone, Whatsapp, email, sms
    private CustomerStatus status;  // ->  Customer ACTIVE, INACTIVE, BLOCKED
    private LocalDate anniversaryDate; // ->  Customer Anniversary
    private LocalDateTime lastContactDate; // ->  Customer last time call update
    private LocalDateTime nextFollowUpDate;  // ->  Customer next when we have to make call
    private Double totalPurchaseAmount = 0.0;
    private Integer totalInteractions = 0;
    private Double creditLimit = 0.0;
    private String notes;
    private String createdBy;
    private String updatedBy;
 */
