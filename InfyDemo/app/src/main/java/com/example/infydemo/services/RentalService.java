package com.example.infydemo.services;


import com.example.infydemo.model.Rental;

public interface RentalService {
    void setMaxSpeed(String rentalId, double maxSpeed);

    Rental getRental(String rentalId);
}