package com.vicky.CustomerRelationshipManager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vicky.CustomerRelationshipManager.enums.*;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Interaction {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private InteractionType interactionType; //Which type of interation -> Call,email,meeting,whatsapp
    @Enumerated(EnumType.STRING)
    private InteractionDirection direction; // Incoming thi ya Outgoing
    private LocalDateTime interactionDate; // nteraction kab hui
    private Integer duration; // almost kitne der tk baat huee
    private String summery; //kya baat huee
    @Enumerated(EnumType.STRING)
    private EnquiryType enquiryType;  //kis type ki enquiry thi
    private boolean followUpRequired;  //Kya follow-up chahiye
    private LocalDateTime followUpDate; //kb chahiye
    @Enumerated(EnumType.STRING)
    private FollowUpAction followUpAction; //Follow-up mein kya karna hai (CALL_AGAIN, SEND_QUOTE, MEETING)
    private String resolution; //  Agar problem thi toh kaise solve hui
    private String customerMood; //  Customer ka mood kaisa tha (HAPPY, NEUTRAL, ANGRY, URGENT)
    @Enumerated(EnumType.STRING)
    private InteractionStatus interactionStatus; //Customer ka mood kaisa tha (HAPPY, NEUTRAL, ANGRY, URGENT)
    private String createdBy;  // kisne interaction kiya tha


    @ManyToOne
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JsonBackReference
    private Product product;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
