package com.vicky.CustomerRelationshipManager.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductRequestDto {
    private String name;
    private double price;
    private int quantity;
    private LocalDate expiry;
    private String companyName;
    private String description;
}
