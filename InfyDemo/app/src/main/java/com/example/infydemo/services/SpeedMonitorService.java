package com.example.infydemo.services;

import com.example.infydemo.model.Rental;

public interface SpeedMonitorService {
    void checkSpeed(Rental rental, double currentSpeed);
}

