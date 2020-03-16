package com.example.practice.repository;

import com.example.practice.model.HolidayHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends CrudRepository<HolidayHistory,Integer> {

}
// List<HolidayHistory> findByCountry(String country);