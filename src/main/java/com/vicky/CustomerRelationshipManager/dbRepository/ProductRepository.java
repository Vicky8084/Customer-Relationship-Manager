package com.vicky.CustomerRelationshipManager.dbRepository;

import com.vicky.CustomerRelationshipManager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    List<Product> findByCompanyName(String companyName);
    List<Product> findByNameContainingAndCompanyNameContainingAndPriceBetween(String name, String companyName, int minPrice,int maxPrice);
    List<Product> findByPriceBetween(int minPrice,int maxPrice);
}
