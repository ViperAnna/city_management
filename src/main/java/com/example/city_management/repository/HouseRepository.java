package com.example.city_management.repository;

import com.example.city_management.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HouseRepository extends CrudRepository<House, Long> {
}
