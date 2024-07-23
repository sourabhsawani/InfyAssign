package com.example.infydemo.services.impls;

import com.example.infydemo.model.Rental;
import com.example.infydemo.services.RentalService;

import java.util.HashMap;
import java.util.Map;

public class RentalServiceImpl implements RentalService {
    private final Map<String, Rental> rentalMap = new HashMap<>();

    @Override
    public void setMaxSpeed(String rentalId, double maxSpeed) {
        Rental rental = rentalMap.get(rentalId);
        if (rental != null) {
            rental.setMaxSpeed(maxSpeed);
        }
    }

    @Override
    public Rental getRental(String rentalId) {
        return rentalMap.get(rentalId);
    }
}