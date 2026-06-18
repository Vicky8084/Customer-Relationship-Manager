package com.vicky.CustomerRelationshipManager.service;

import org.springframework.stereotype.Service;

@Service
public class InteractionService {
}
/*
Create Interaction	POST /interaction/save	New interaction
Get All	GET /interaction/getAll	All interactions
Get by ID	GET /interaction/getById/{id}	Single interaction
Customer Interactions	GET /interaction/customer/{customerId}	Customer specific
Product Interactions	GET /interaction/product/{productId}	Product specific
Today's Follow-ups	GET /interaction/today-followups	Today's reminders
Update Follow-up	PUT /interaction/follow-up/{id}	Update follow-up
 */
