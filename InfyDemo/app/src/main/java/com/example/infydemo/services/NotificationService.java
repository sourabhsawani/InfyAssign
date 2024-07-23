package com.example.infydemo.services;

import com.example.infydemo.exceptions.UnableToSendNotificationException;
import com.example.infydemo.model.Notification;

public interface NotificationService {
    void sendNotification(Notification notification) throws UnableToSendNotificationException;
}