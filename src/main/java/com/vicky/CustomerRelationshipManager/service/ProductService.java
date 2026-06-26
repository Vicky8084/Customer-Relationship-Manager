package com.vicky.CustomerRelationshipManager.service;
import com.vicky.CustomerRelationshipManager.converter.ProductConverter;
import com.vicky.CustomerRelationshipManager.dto.ProductRequestDto;
import com.vicky.CustomerRelationshipManager.model.Product;
import com.vicky.CustomerRelationshipManager.dbRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(ProductRequestDto productRequestDto) {
        Product product = ProductConverter.convertProductRequestDtoIntoProduct(productRequestDto);
        return productRepository.save(product);
    }

    public Product findProductByName(String name) {
        Optional<Product> productOptional = productRepository.findByName(name);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product is not found with name :- " + name);
        }
        return productOptional.get();
    }

    public Product findProductById(long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product not found with id :- " + id);
        }
        return productOptional.get();
    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public List<Product> findProductByCompanyName(String companyName) {
        List<Product> productList = productRepository.findByCompanyName(companyName);
        if (productList.isEmpty()) {
            throw new RuntimeException("Product Not found with Company name :- " + companyName);
        }
        return productList;
    }

    public Product updateProductByName(String name,ProductRequestDto productRequestDto){
        Product product=findProductByName(name);
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        product.setExpiry(productRequestDto.getExpiry());
        product.setCompanyName(productRequestDto.getCompanyName());
        product.setDescription(productRequestDto.getDescription());
        return productRepository.save(product);
    }


    public Product updateProductById(long id, ProductRequestDto productRequestDto) {
        Product product = findProductById(id);
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setQuantity(productRequestDto.getQuantity());
        product.setExpiry(productRequestDto.getExpiry());
        product.setCompanyName(productRequestDto.getCompanyName());
        product.setDescription(productRequestDto.getDescription());
        return productRepository.save(product);
    }

    public void deleteProductById(long id) {
        Product product = findProductById(id);
        productRepository.delete(product);
    }

    //Flexible Search API - Search products using any combination of optional filters (name, category, price range), returns all matching products}
    public List<Product> searchProduct(String name, String companyName, int minPrice,int maxPrice){
        return productRepository.findByNameContainingAndCompanyNameContainingAndPriceBetween(name,companyName,minPrice,maxPrice);

    }

    //This one is also a Flexible Search - search product between renge
    public List<Product> searchProductInPriceRange(int minPrice,int maxPrice){
        return productRepository.findByPriceBetween(minPrice,maxPrice);
    }
}

