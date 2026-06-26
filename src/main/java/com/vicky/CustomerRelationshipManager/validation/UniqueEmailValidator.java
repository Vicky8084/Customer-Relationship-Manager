package com.vicky.CustomerRelationshipManager.validation;

import com.vicky.CustomerRelationshipManager.dbRepository.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        // Initialization code if needed
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        // If email is null or empty, skip validation (let @NotBlank handle it)
        if (email == null || email.isEmpty()) {
            return true;
        }

        // Check if email already exists in database
        return !customerRepository.existsByEmail(email);
    }
}