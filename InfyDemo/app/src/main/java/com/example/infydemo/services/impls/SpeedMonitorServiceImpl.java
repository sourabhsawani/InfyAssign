package com.example.infydemo.services.impls;

import android.util.Log;

import com.example.infydemo.exceptions.UnableToSendNotificationException;
import com.example.infydemo.model.Notification;
import com.example.infydemo.model.Rental;
import com.example.infydemo.services.NotificationService;
import com.example.infydemo.services.SpeedMonitorService;

public class SpeedMonitorServiceImpl implements SpeedMonitorService {
    final String TAG = "SpeedMonitorService";
    NotificationService primaryNotificationService; // primary notification channel is Firebase
    NotificationService secondaryNotificationService; // secondary notification channel is AWS

    public SpeedMonitorServiceImpl(NotificationService primaryNotificationService, NotificationService secondaryNotificationService) {
        this.primaryNotificationService = primaryNotificationService;
        this.secondaryNotificationService = secondaryNotificationService;
    }

    @Override
    public void checkSpeed(Rental rental, double currentSpeed) {
        if (currentSpeed > rental.getMaxSpeed()) {
            String message = "Speed limit exceeded! Current speed: " + currentSpeed;
            Notification notification = new Notification();
            notification.setRentalId(rental.getRentalId());
            notification.setMessage(message);

            try {
                // try to send notification by Firebase
                primaryNotificationService.sendNotification(notification);
            } catch (UnableToSendNotificationException e) {
                // here secondary service is AWS
                retryWithSecondaryService(notification);
            }
        }
    }

    private void retryWithSecondaryService(Notification notification) {
        try {
            // try to send notification by AWS
            secondaryNotificationService.sendNotification(notification);
        } catch (UnableToSendNotificationException e) {
            Log.d(TAG, "Unable to send notification Both Channels failed");
        }
    }
}