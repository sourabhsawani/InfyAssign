package com.example.infydemo.services.impls;

import android.util.Log;

import com.example.infydemo.exceptions.UnableToSendNotificationException;
import com.example.infydemo.model.Notification;
import com.example.infydemo.services.NotificationService;

public class AwsNotificationService implements NotificationService {
    final String TAG = "AwsNotificationService";

    @Override
    public void sendNotification(Notification notification) throws UnableToSendNotificationException {
        // Comment: Call to AWS API to send notification
        Log.d(TAG, "Sending notification via AWS: " + notification.getMessage());
    }
}