package com.vicky.CustomerRelationshipManager.dto;

import com.vicky.CustomerRelationshipManager.enums.CustomerType;
import com.vicky.CustomerRelationshipManager.enums.Source;
import com.vicky.CustomerRelationshipManager.enums.CustomerStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CustomerRequestDto {
    @NotNull
    private String name;  // ->  Customer Name
    private String occupation; // ->  Customer Occupation
    @NotNull
    private LocalDate dateOfBirth;  // ->  Customer DOB
    @NotNull
    private String address;  // ->  Customer Address
    @NotNull
    private String phoneNumber; // ->  Customer Phone Number
    @NotNull
    @Email
    private String email;
    @NotNull
    private CustomerType customerType;  // ->  Customer Type -> NEW, REGULAR, VIP, INACTIVE, LEAD
    @NotNull
    private Source source; // ->  Customer WEBSITE, PHONE, REFERRAL, SOCIAL_MEDIA, WALK_IN
    @NotNull
    private String preferredContact;  // ->  Customer preferred Contact like phone, Whatsapp, email, sms
    @NotNull
    private CustomerStatus status;  // ->  Customer ACTIVE, INACTIVE, BLOCKED

    private LocalDate anniversaryDate; // ->  Customer Anniversary
    @NotNull
    private LocalDateTime lastContactDate; // ->  Customer last time call update
    @NotNull
    private LocalDateTime nextFollowUpDate;  // ->  Customer next when we have to make call
    @NotNull
    private Double totalPurchaseAmount = 0.0;
    @NotNull
    private Integer totalInteractions = 0;
    @NotNull
    private Double creditLimit = 0.0;
    @NotNull
    private String notes;
    @NotNull
    private String createdBy;
    @NotNull
    private String updatedBy;
}
