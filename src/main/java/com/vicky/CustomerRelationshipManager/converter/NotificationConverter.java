package com.vicky.CustomerRelationshipManager.converter;

import com.vicky.CustomerRelationshipManager.dto.NotificationRequestDto;
import com.vicky.CustomerRelationshipManager.model.Notification;

public class NotificationConverter {
    public static Notification convertNotificationRequestDtoIntoNotification(NotificationRequestDto notificationRequestDto){
        Notification notification=new Notification();
        notification.setNotificationType(notificationRequestDto.getNotificationType());
        notification.setScheduledDate(notificationRequestDto.getScheduledDate());
        notification.setChannel(notificationRequestDto.getChannel());
        notification.setSubject(notificationRequestDto.getSubject());
        notification.setMessage(notificationRequestDto.getMessage());
        notification.setStatus(notificationRequestDto.getStatus());
        notification.setSentAt(notificationRequestDto.getSentAt());
        notification.setDeliveryStatus(notificationRequestDto.getDeliveryStatus());
        notification.setFailureReson(notificationRequestDto.getFailureReson());
        notification.setRetryCount(notificationRequestDto.getRetryCount());
        notification.setCreatedBy(notificationRequestDto.getCreatedBy());
        return notification;
    }
}

/*
private NotificationType NotificationType;
    private LocalDateTime scheduledDate;
    private Channel channel;
    private String subject;
    private String message;
    private NotificationStatus status;
    private String sentAt;
    private DeliveryStatus deliveryStatus;
    private String failureReson;
    private String retryCount;
    private String createdBy;
 */
