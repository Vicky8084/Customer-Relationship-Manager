package com.vicky.CustomerRelationshipManager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vicky.CustomerRelationshipManager.enums.Channel;
import com.vicky.CustomerRelationshipManager.enums.DeliveryStatus;
import com.vicky.CustomerRelationshipManager.enums.NotificationStatus;
import com.vicky.CustomerRelationshipManager.enums.NotificationType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;  //notification for what occasion
    private LocalDateTime scheduledDate; // when we have to sent notification
    @Enumerated(EnumType.STRING)
    private Channel channel;  // how we have to sent notification
    private String subject; // Title for notification
    private String message; // Actual message what we need to send to the customer
    @Enumerated(EnumType.STRING)
    private NotificationStatus status; // Current status of notification
    private LocalDateTime sentAt; // when notification was sent
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus; //has notification delivered or not
    private String failureReson;  // Reson to notification was fail (doesn't send)
    private Integer retryCount; // how many times try to send notification
    private String createdBy;  // who has sent notification to the customer or who has scheduled that notification will be sent on that particular date or time

    //One Customer can get many notification
    @ManyToOne()
    @JsonBackReference
    private Customer customer;


    @CreationTimestamp
    private LocalDateTime createdAt; // when the notification was created
    @UpdateTimestamp
    private LocalDateTime updatedAt; // when the notification was updated

}


