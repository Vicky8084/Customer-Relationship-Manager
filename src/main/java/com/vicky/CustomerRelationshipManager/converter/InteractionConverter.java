package com.vicky.CustomerRelationshipManager.converter;

import com.vicky.CustomerRelationshipManager.dto.InterationRequestDto;
import com.vicky.CustomerRelationshipManager.model.Interaction;

public class InteractionConverter {
    public static Interaction convertInteractionRequestDtoIntoInteraction(InterationRequestDto interationRequestDto){
        Interaction interaction=new Interaction();
        interaction.setInteractionType(interationRequestDto.getInteractionType());
        interaction.setDirection(interationRequestDto.getDirection());
        interaction.setInteractionDate(interationRequestDto.getInteractionDate());
        interaction.setDuration(interationRequestDto.getDuration());
        interaction.setSummery(interationRequestDto.getSummery());
        interaction.setEnquiryType(interationRequestDto.getEnquiryType());
        interaction.setFollowUpRequired(interationRequestDto.isFollowUpRequired());
        interaction.setFollowUpDate(interationRequestDto.getFollowUpDate());
        interaction.setFollowUpAction(interationRequestDto.getFollowUpAction());
        interaction.setResolution(interationRequestDto.getResolution());
        interaction.setCustomerMood(interationRequestDto.getCustomerMood());
        interaction.setInteractionStatus(interationRequestDto.getInterationStatus());
        interaction.setCreatedBy(interationRequestDto.getCreatedBy());
        return interaction;
    }
}

/*
private InteractionType interactionType;
    private InteractionDirection direction;
    private LocalDateTime interactionDate;
    private Integer duration;
    private String summery;
    private EnquiryType enquiryType;
    private boolean followUpRequired;
    private LocalDateTime followUpDate;
    private FollowUpAction followUpAction;
    private String resolution;
    private String customerMood;
    private InteractionStatus interationStatus;
    private String createdBy;
 */
