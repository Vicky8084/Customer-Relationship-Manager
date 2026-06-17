package com.vicky.CustomerRelationshipManager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private LocalDate expiry;
    private String companyName;
    private String description;

    @ManyToMany(mappedBy = "enquiredProducts")
    @JsonBackReference
    private List<Customer> enquiredByCustomers;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<Interaction> interactions;


    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
