package com.vicky.CustomerRelationshipManager.dto;

import com.vicky.CustomerRelationshipManager.enums.Channel;
import com.vicky.CustomerRelationshipManager.enums.DeliveryStatus;
import com.vicky.CustomerRelationshipManager.enums.NotificationStatus;
import com.vicky.CustomerRelationshipManager.enums.NotificationType;
import lombok.Data;
import java.time.LocalDateTime;


@Data
public class NotificationRequestDto {
    private NotificationType NotificationType;
    private LocalDateTime scheduledDate;
    private Channel channel;
    private String subject;
    private String message;
    private NotificationStatus status;
    private LocalDateTime sentAt;
    private DeliveryStatus deliveryStatus;
    private String failureReson;
    private Integer retryCount;
    private String createdBy;
}
