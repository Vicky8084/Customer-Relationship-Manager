package com.vicky.CustomerRelationshipManager.controller;
import com.vicky.CustomerRelationshipManager.dto.ProductRequestDto;
import com.vicky.CustomerRelationshipManager.model.Product;
import com.vicky.CustomerRelationshipManager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping("/save")
    public ResponseEntity saveProduct(@RequestBody ProductRequestDto productRequestDto){
        Product product=productService.saveProduct(productRequestDto);
        return new ResponseEntity(product, HttpStatus.CREATED);
    }
}
