package com.example.demo.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory Producer (Factory of Factories).
 * Decides which factory (Electric or Petrol) to return based on input.
 */
public class FactoryProducer {
    private static final Logger logger = LoggerFactory.getLogger(FactoryProducer.class);

    /**
     * Returns the correct CarFactory based on fuel type.
     * @param fuelType (electric, petrol)
     * @return CarFactory (ElectricCarFactory or PetrolCarFactory)
     */
    public static CarFactory getFactory(String fuelType) {
        if (fuelType == null || fuelType.isBlank()) {
            logger.warn("Fuel type is missing or empty.");
            throw new IllegalArgumentException("Fuel type cannot be empty.");
        }

        switch (fuelType.trim().toUpperCase()) {
            case "ELECTRIC":
                return new ElectricCarFactory();
            case "PETROL":
                return new PetrolCarFactory();
            default:
                logger.error("Unknown fuel type requested: {}", fuelType);
                throw new IllegalArgumentException("Unsupported fuel type: " + fuelType);
        }
    }
}
