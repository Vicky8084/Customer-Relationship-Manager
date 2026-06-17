package com.vicky.CustomerRelationshipManager.dto;
import com.vicky.CustomerRelationshipManager.enums.*;
import lombok.Data;
import java.time.LocalDateTime;


@Data
public class InterationRequestDto {
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
}
