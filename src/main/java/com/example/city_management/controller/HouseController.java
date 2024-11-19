package com.example.city_management.controller;


import com.example.city_management.dto.HouseDTO;
import com.example.city_management.service.Impl.HouseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/houses")
public class HouseController {

    private final HouseServiceImpl houseServiceImpl;

    @PostMapping
    public ResponseEntity<HouseDTO> createHouse(@RequestBody HouseDTO houseDTO) {
        HouseDTO createPerson = houseServiceImpl.createHouse(houseDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createPerson);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseDTO> getHouseById(@PathVariable Long id) {
        HouseDTO house = houseServiceImpl.getHouseById(id);
        return ResponseEntity
                .ok(house);
    }

    @GetMapping
    public ResponseEntity<List<HouseDTO>> getAllCars() {
        List<HouseDTO> cars = houseServiceImpl.getAllHouses();
        return ResponseEntity
                .ok(cars);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<List<HouseDTO>> getAllHouseByPersonId(@PathVariable Long id) {
//    }

    @PutMapping("{id}")
    public ResponseEntity<HouseDTO> updateHouse(@PathVariable Long id, @RequestBody HouseDTO houseDetails) {
        HouseDTO house = houseServiceImpl.updateHouse(id, houseDetails);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(house);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HouseDTO> deleteCarById(@PathVariable Long id) {
        houseServiceImpl.deleteHouseById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
