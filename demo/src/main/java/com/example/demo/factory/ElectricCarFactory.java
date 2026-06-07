package com.example.demo.factory;

import com.example.demo.cars.*;

/**
 * Concrete Factory for Electric Cars.
 * Produces Electric Sedan and Electric SUV.
 */
public class ElectricCarFactory implements CarFactory {
    @Override
    public Car createSedan() {
        return new ElectricSedan();
    }
    @Override
    public Car createSUV() {
        return new ElectricSUV();
    }
}
