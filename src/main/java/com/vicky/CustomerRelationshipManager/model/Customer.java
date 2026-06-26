package com.vicky.CustomerRelationshipManager.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vicky.CustomerRelationshipManager.enums.CustomerType;
import com.vicky.CustomerRelationshipManager.enums.Source;
import com.vicky.CustomerRelationshipManager.enums.CustomerStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // ->  Customer Name
    private String occupation; // ->  Customer Occupation
    private LocalDate dateOfBirth;  // ->  Customer DOB
    private String address;  // ->  Customer Address
    private String phoneNumber; // ->  Customer Phone Number
    private String email;  // ->  Customer Email

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;  // ->  Customer Type -> NEW, REGULAR, VIP, INACTIVE, LEAD

    @Enumerated(EnumType.STRING)
    private Source source; // ->  Customer WEBSITE, PHONE, REFERRAL, SOCIAL_MEDIA, WALK_IN

    private String preferredContact;  // ->  Customer preferred Contact like phone, Whatsapp, email, sms

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;  // ->  Customer ACTIVE, INACTIVE, BLOCKED


    private LocalDate anniversaryDate; // ->  Customer Anniversary
    private LocalDateTime lastContactDate; // ->  Customer last time call update
    private LocalDateTime nextFollowUpDate;  // ->  Customer next when we have to make call

    private Double totalPurchaseAmount = 0.0;
    private Integer totalInteractions = 0;
    private Double creditLimit = 0.0;

    private String notes;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "customer_product_enquiry",  // Join table ka naam
            joinColumns = @JoinColumn(name = "customer_id"),  // Customer ka foreign key
            inverseJoinColumns = @JoinColumn(name = "product_id")  // Product ka foreign key
    )
    private List<Product> enquiredProducts;

    //One Customer get Many Notification
    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Notification> notifications;

    //One Customer can get Many Interaction
    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Interaction> interactions;


    private String createdBy;
    private String updatedBy;



}