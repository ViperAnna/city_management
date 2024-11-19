package com.example.city_management.repository;

import com.example.city_management.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findCarsByOwnerId(Long id);


}

