package com.example.demo.factory;

import com.example.demo.cars.*;

/**
 * Concrete Factory for Petrol Cars.
 * Produces Petrol Sedan and Petrol SUV.
 */
public class PetrolCarFactory implements CarFactory {
    @Override
    public Car createSedan() {
        return new PetrolSedan();
    }
    @Override
    public Car createSUV() {
        return new PetrolSUV();
    }
}
