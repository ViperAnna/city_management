package com.example.city_management.service.Impl;

import com.example.city_management.dto.CarDTO;
import com.example.city_management.entity.Car;
import com.example.city_management.entity.Person;
import com.example.city_management.exception.CarNotFoundException;
import com.example.city_management.exception.PersonNotFoundException;
import com.example.city_management.mapper.CarMapper;
import com.example.city_management.mapper.PersonMapper;
import com.example.city_management.repository.CarRepository;
import com.example.city_management.repository.PersonRepository;
import com.example.city_management.service.CarService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final PersonRepository personRepository;
    private final CarRepository carRepository;


    public boolean hasCarByPersonId(Long id) {
        List<Car> cars = (List<Car>) carRepository.findAll();
        return cars.stream()
                .anyMatch(car -> car.getOwner().getId().equals(id));
    }

    @Override
    @Transactional
    public CarDTO createCar(@NonNull CarDTO carDetails) {
        Car car = new Car();
        car.setBrand(carDetails.getBrand());
        car.setOwner(PersonMapper.INSTANCE.toEntity(carDetails.getOwner()));
        return CarMapper.INSTANCE.toDto(carRepository.save(car));
    }

    @Override
    public CarDTO getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found with id: " + id));
        return CarMapper.INSTANCE.toDto(car);
    }

    @Override
    public List<CarDTO> getAllCars() {
        List<Car> cars = (List<Car>) carRepository.findAll();
        return cars.stream()
                .map(CarMapper.INSTANCE::toDto)
                .toList();
    }

    public List<CarDTO> getAllCarsByOwner(Long id) {
        List<Car> car = carRepository.findCarsByOwnerId(id);
        return car.stream()
                .map(CarMapper.INSTANCE::toDto)
                .toList();
    }

    @Override
    @Transactional
    public CarDTO updateCar(Long id, @NonNull CarDTO carDetails) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found with id: " + id));
        if (carDetails.getBrand() != null) {
            car.setBrand(carDetails.getBrand());
        }

        if (carDetails.getOwner() != null && carDetails.getOwner().getId() != null) {
            Long ownerId = carDetails.getOwner().getId();
            Person person = personRepository.findById(ownerId)
                    .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + id));
            car.setOwner(person);
        }
        return CarMapper.INSTANCE.toDto(car);
    }

    @Override
    @Transactional
    public void deleteCarById(Long id) {
        carRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + id));
        carRepository.deleteById(id);
    }
}
