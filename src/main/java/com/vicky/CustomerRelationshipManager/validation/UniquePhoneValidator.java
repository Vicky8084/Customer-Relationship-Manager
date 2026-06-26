package com.vicky.CustomerRelationshipManager.validation;

import com.vicky.CustomerRelationshipManager.dbRepository.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniquePhoneValidator implements ConstraintValidator<UniquePhone, String> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void initialize(UniquePhone constraintAnnotation) {
        // Initialization code if needed
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        // If phone is null or empty, skip validation (let @NotBlank handle it)
        if (phone == null || phone.isEmpty()) {
            return true;
        }

        // Check if phone number already exists in database
        return !customerRepository.existsByPhoneNumber(phone);
    }
}