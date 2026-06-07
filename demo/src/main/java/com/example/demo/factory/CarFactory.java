package com.example.demo.factory;

import com.example.demo.cars.Car;

/**
 * Abstract Factory interface.
 * Declares methods to create a family of related products (Sedan, SUV).
 */
public interface CarFactory {
    Car createSedan();
    Car createSUV();
}