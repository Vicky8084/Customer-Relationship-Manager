package com.vicky.CustomerRelationshipManager.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniquePhoneValidator.class)
public @interface UniquePhone {
    String message() default "Phone number already exists in the system";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}