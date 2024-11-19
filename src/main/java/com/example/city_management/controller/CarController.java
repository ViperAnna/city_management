package com.example.city_management.controller;

import com.example.city_management.dto.CarDTO;
import com.example.city_management.service.Impl.CarServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarServiceImpl carServiceImpl;

    @PostMapping
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDetails) {
        CarDTO car = carServiceImpl.createCar(carDetails);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(car);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        CarDTO car = carServiceImpl.getCarById(id);
        return ResponseEntity
                .ok(car);
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> cars = carServiceImpl.getAllCars();
        return ResponseEntity
                .ok(cars);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<List<CarDTO>> getAllCarsByPerson(@PathVariable Long id) {
        List<CarDTO> carsByPerson = carServiceImpl.getAllCarsByOwner(id);
        return ResponseEntity
                .ok(carsByPerson);
    }

    @PutMapping("{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @RequestBody CarDTO carDetails) {
        CarDTO updateCar = carServiceImpl.updateCar(id, carDetails);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updateCar);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CarDTO> deleteCarById(@PathVariable Long id) {
        carServiceImpl.deleteCarById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
