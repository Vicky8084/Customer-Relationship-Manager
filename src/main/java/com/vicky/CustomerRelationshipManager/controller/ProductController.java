package com.vicky.CustomerRelationshipManager.controller;
import com.vicky.CustomerRelationshipManager.dto.ProductRequestDto;
import com.vicky.CustomerRelationshipManager.model.Product;
import com.vicky.CustomerRelationshipManager.service.ProductService;
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
    public ResponseEntity<Product> saveProduct(@RequestBody ProductRequestDto productRequestDto){
        Product product=productService.saveProduct(productRequestDto);
        return new ResponseEntity(product, HttpStatus.CREATED);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<Product> findProductByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProductByName(name));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> findAllProduct(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAllProduct());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable long id){
        return ResponseEntity.ok().body(productService.findProductById(id));
    }

    @GetMapping("/getByCompanyName/{companyName}")
    public ResponseEntity<List<Product>> findProductByCompanyName(@PathVariable String companyName){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProductByCompanyName(companyName));
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto){
        return ResponseEntity.ok().body(productService.updateProductById(id,productRequestDto));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Product> deleteProductByid(@PathVariable long id){
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam(required = false) String name ,
                                                       @RequestParam(required = false) String companyName,
                                                       @RequestParam(required = false) Integer minPrice,
                                                       @RequestParam(required = false) Integer maxPrice ){
        List<Product> productList=productService.searchProduct(name,companyName,minPrice,maxPrice);
        return ResponseEntity.ok().body(productList);
    }

    @GetMapping("/priceRange/minPrice/{minPrice}/maxPrice/{maxPrice}")
    public ResponseEntity<List<Product>> searchProductInPriceRange(@PathVariable Integer minPrice,
                                                                   @PathVariable Integer maxPrice){
        return ResponseEntity.ok().body(productService.searchProductInPriceRange(minPrice,maxPrice));
    }

    @PutMapping("/updateByName/{name}")
    public ResponseEntity<Product> updateProductbyName(@PathVariable String name,@RequestBody ProductRequestDto productRequestDto){
        return ResponseEntity.ok().body(productService.updateProductByName(name,productRequestDto));
    }
}
