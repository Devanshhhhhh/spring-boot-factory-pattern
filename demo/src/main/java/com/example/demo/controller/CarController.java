package com.example.demo.controller;

import com.example.demo.cars.Car;
import com.example.demo.factory.CarFactory;
import com.example.demo.factory.FactoryProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for handling Car creation requests.
 * Demonstrates the Abstract Factory pattern via HTTP endpoints.
 */
@RestController
@RequestMapping("/cars")
public class CarController {

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    /**
     * Default endpoint for /cars.
     * Provides usage instructions to the user.
     */
    @GetMapping
    public ResponseEntity<String> home() {
        return ResponseEntity.ok(
                "Usage: /cars/{fuelType}/{carType} \n" +
                        "Fuel types: electric, petrol \n" +
                        "Car types: sedan, suv"
        );
    }

    /**
     * Endpoint to create a car.
     * Example: /cars/electric/sedan → "Created: Electric Sedan"
     *
     * @param fuelType type of fuel ("electric", "petrol")
     * @param carType type of car ("sedan", "suv")
     * @return ResponseEntity with car type or error message
     */
    @GetMapping("/{fuelType}/{carType}")
    public ResponseEntity<String> getCar(@PathVariable String fuelType, @PathVariable String carType) {
        try {
            // Get appropriate factory based on fuel type
            CarFactory factory = FactoryProducer.getFactory(fuelType);

            Car car;
            // Validate and create appropriate car type
            if (carType == null || carType.isBlank()) {
                return ResponseEntity.badRequest().body("Car type cannot be empty.");
            }

            switch (carType.trim().toLowerCase()) {
                case "sedan":
                    car = factory.createSedan();
                    break;
                case "suv":
                    car = factory.createSUV();
                    break;
                default:
                    logger.warn("Invalid car type requested: {}", carType);
                    return ResponseEntity.badRequest().body("Invalid car type: " + carType +
                            ". Use 'sedan' or 'suv'.");
            }

            // Success response
            return ResponseEntity.ok("Created: " + car.getType());

        } catch (IllegalArgumentException ex) {
            // Handle bad input gracefully
            logger.error("Error while creating car: {}", ex.getMessage());
            return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
        } catch (Exception ex) {
            // Handle unexpected runtime errors
            logger.error("Unexpected error: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body("Something went wrong. Please try again later.");
        }
    }
}
