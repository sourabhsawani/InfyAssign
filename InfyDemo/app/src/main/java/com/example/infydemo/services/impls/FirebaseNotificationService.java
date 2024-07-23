package com.example.infydemo.services.impls;

import android.util.Log;

import com.example.infydemo.exceptions.UnableToSendNotificationException;
import com.example.infydemo.model.Notification;
import com.example.infydemo.services.NotificationService;

public class FirebaseNotificationService implements NotificationService {
    final String TAG = "FirebaseNotificationService";

    @Override
    public void sendNotification(Notification notification) throws UnableToSendNotificationException {
        // Comment: Call to Firebase API to send notification
        Log.d(TAG, "Sending notification via Firebase: " + notification.getMessage());
    }
}