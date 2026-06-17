package com.vicky.CustomerRelationshipManager.service;

import com.vicky.CustomerRelationshipManager.controller.ProductController;
import com.vicky.CustomerRelationshipManager.converter.ProductConverter;
import com.vicky.CustomerRelationshipManager.dto.ProductRequestDto;
import com.vicky.CustomerRelationshipManager.model.Product;
import com.vicky.CustomerRelationshipManager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public Product saveProduct(ProductRequestDto productRequestDto){
        Product product= ProductConverter.convertProductRequestDtoIntoProduct(productRequestDto);
        return productRepository.save(product);
    }


}
