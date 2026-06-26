package com.vicky.CustomerRelationshipManager.converter;

import com.vicky.CustomerRelationshipManager.dto.ProductRequestDto;
import com.vicky.CustomerRelationshipManager.model.Product;

public class ProductConverter {
    public static Product convertProductRequestDtoIntoProduct(ProductRequestDto productRequestDto){
        Product product=new Product();
        product.setName(productRequestDto.getName().trim());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        product.setExpiry(productRequestDto.getExpiry());
        product.setCompanyName(productRequestDto.getCompanyName().trim());
        product.setDescription(productRequestDto.getDescription().trim());
        return product;
    }
}
/*
private String name;
    private double price;
    private int quantity;
    private LocalDate expiry;
    private String companyName;
    private String description;
 */
