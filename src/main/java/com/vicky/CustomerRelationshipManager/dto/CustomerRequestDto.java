package com.vicky.CustomerRelationshipManager.dto;

import com.vicky.CustomerRelationshipManager.enums.CustomerType;
import com.vicky.CustomerRelationshipManager.enums.Source;
import com.vicky.CustomerRelationshipManager.enums.CustomerStatus;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CustomerRequestDto {
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
}
