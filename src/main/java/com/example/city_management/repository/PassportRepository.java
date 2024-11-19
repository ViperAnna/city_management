package com.example.city_management.repository;

import com.example.city_management.dto.PassportDTO;
import com.example.city_management.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassportRepository extends CrudRepository<Passport, Long> {
    @Query(value = "SELECT * FROM passports WHERE id IN (SELECT id FROM persons WHERE gender = 'Male' AND name LIKE :firstLetter%)", nativeQuery = true)
    List<PassportDTO> getPassportsOfMalesByFirstLetter(@Param("firstLetter") String firstLetter);
}
