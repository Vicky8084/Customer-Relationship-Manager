package com.vicky.CustomerRelationshipManager.repository;

import com.vicky.CustomerRelationshipManager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
