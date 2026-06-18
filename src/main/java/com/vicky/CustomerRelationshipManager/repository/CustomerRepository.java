package com.vicky.CustomerRelationshipManager.repository;

import com.vicky.CustomerRelationshipManager.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByName(String name);
    Optional<Customer> findByPhoneNumber(String phoneNumber);
    List<Customer> findByNameContainingAndPhoneNumberContainingAndEmailContainingAndOccupationContaining(String name,String phoneNumber,String email,String Occupation);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
