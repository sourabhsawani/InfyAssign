package com.example.infydemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.infydemo.model.Car;
import com.example.infydemo.model.Customer;
import com.example.infydemo.model.Rental;
import com.example.infydemo.services.NotificationService;
import com.example.infydemo.services.SpeedMonitorService;
import com.example.infydemo.services.SpeedTracker;
import com.example.infydemo.services.impls.AwsNotificationService;
import com.example.infydemo.services.impls.FirebaseNotificationService;
import com.example.infydemo.services.impls.RentalServiceImpl;
import com.example.infydemo.services.impls.SpeedMonitorServiceImpl;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startMonitoring();
    }

    private void startMonitoring() {
        // Create Notification Services
        NotificationService firebaseNotificationService = new FirebaseNotificationService();
        NotificationService awsNotificationService = new AwsNotificationService();

        // Setup Services
        final SpeedMonitorService speedMonitorService = new SpeedMonitorServiceImpl(firebaseNotificationService, awsNotificationService);
        RentalServiceImpl rentalService = new RentalServiceImpl();

        // create a rental object
        createTestingRental(rentalService);
        final Rental rental = rentalService.getRental("1");

        SpeedTracker speedTracker = new SpeedTracker() {
            @Override
            public void trackSpeed(double currentSpeed) {
                speedMonitorService.checkSpeed(rental, currentSpeed);
            }
        };

        // Simulate Speed Tracking
        speedTracker.trackSpeed(72.0); // This should Not trigger a notification
        speedTracker.trackSpeed(92.0); // This should trigger a notification
    }
}