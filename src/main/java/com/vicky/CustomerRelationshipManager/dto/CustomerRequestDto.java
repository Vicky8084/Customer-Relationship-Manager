package com.vicky.CustomerRelationshipManager.dto;

import com.vicky.CustomerRelationshipManager.enums.CustomerType;
import com.vicky.CustomerRelationshipManager.enums.Source;
import com.vicky.CustomerRelationshipManager.enums.CustomerStatus;
import com.vicky.CustomerRelationshipManager.validation.UniqueEmail;
import com.vicky.CustomerRelationshipManager.validation.UniquePhone;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data

public class CustomerRequestDto {

    @NotBlank(message = "Customer name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Size(max = 100, message = "Occupation cannot exceed 100 characters")
    private String occupation;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Address is required")
    @Size(max = 500, message = "Address cannot exceed 500 characters")
    private String address;

    // ✅ Phone validation with unique check
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    @UniquePhone  // ✅ Custom validation
    private String phoneNumber;

    // ✅ Email validation with unique check
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @UniqueEmail  // ✅ Custom validation
    private String email;

    @NotNull(message = "Customer type is required")
    private CustomerType customerType;

    @NotNull(message = "Source is required")
    private Source source;

    @NotBlank(message = "Preferred contact method is required")
    private String preferredContact;

    @NotNull(message = "Status is required")
    private CustomerStatus status;

    private LocalDate anniversaryDate;

    @NotNull(message = "Last contact date is required")
    @PastOrPresent(message = "Last contact date cannot be in future")
    private LocalDateTime lastContactDate;

    @NotNull(message = "Next follow-up date is required")
    @Future(message = "Next follow-up date must be in future")
    private LocalDateTime nextFollowUpDate;

    @NotNull(message = "Total purchase amount is required")
    @PositiveOrZero(message = "Total purchase amount cannot be negative")
    private Double totalPurchaseAmount = 0.0;

    @NotNull(message = "Total interactions is required")
    @PositiveOrZero(message = "Total interactions cannot be negative")
    private Integer totalInteractions = 0;

    @NotNull(message = "Credit limit is required")
    @PositiveOrZero(message = "Credit limit cannot be negative")
    private Double creditLimit = 0.0;

    @Size(max = 1000, message = "Notes cannot exceed 1000 characters")
    private String notes;

    @NotBlank(message = "Created by is required")
    private String createdBy;

    @NotBlank(message = "Updated by is required")
    private String updatedBy;
}