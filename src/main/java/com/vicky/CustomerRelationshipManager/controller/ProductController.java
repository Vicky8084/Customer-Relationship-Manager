package com.vicky.CustomerRelationshipManager.controller;
import com.vicky.CustomerRelationshipManager.dto.ProductRequestDto;
import com.vicky.CustomerRelationshipManager.model.Product;
import com.vicky.CustomerRelationshipManager.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody ProductRequestDto productRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(productService.saveProduct(productRequestDto));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> findALl(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<List<Product>> findByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByName(name.trim()));
    }

    @GetMapping("/getByCompanyName/{companyName}")
    public ResponseEntity<List<Product>> findByCompanyName(@PathVariable String companyName){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByCompanyName(companyName.trim()));
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<Product> updateById(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateById(id,productRequestDto));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam(required = false) String name,
                                                        @RequestParam(required = false) String companyName){
        //return ResponseEntity.status(HttpStatus.OK).body(productService.searchProducts(name.trim(),companyName.trim()));
        return ResponseEntity.ok(productService.searchProducts
                (name != null ? name.trim() : null,
                companyName != null ? companyName.trim() : null));
    }

}
