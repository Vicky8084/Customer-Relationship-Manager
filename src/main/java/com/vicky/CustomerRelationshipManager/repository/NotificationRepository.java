package com.vicky.CustomerRelationshipManager.repository;

import com.vicky.CustomerRelationshipManager.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
/*
Create Notification	POST /notification/save	Create new notification
Get All	GET /notification/getAll	All notifications
Get by ID	GET /notification/getById/{id}	Single notification
Customer Notifications	GET /notification/customer/{customerId}	Customer specific
Pending Notifications	GET /notification/pending	Not yet sent
Send Notification	PUT /notification/send/{id}	Send manually
Send Birthday Wishes	POST /notification/send-birthday-wishes	Auto birthday
 */
