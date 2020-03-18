package com.example.practice.controller;

import com.example.practice.model.HolidayHistory;
import com.example.practice.model.Visitor;
import com.example.practice.repository.HolidayRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class HolidayController {

    @Autowired
    private HolidayRepository holidayRepository;

    @GetMapping("/holidayhistory")
    public ResponseEntity<List<HolidayHistory>> findAllHistory() {
        List<HolidayHistory> holidayHistoryList = Lists.newArrayList(this.holidayRepository.findAll());
        return new ResponseEntity<>(holidayHistoryList, HttpStatus.OK);
    }

    @PostMapping("/holidayhistory")
    public ResponseEntity<String> saveHistory(@Valid @RequestBody HolidayHistory holidayHistory) {
        this.holidayRepository.save(holidayHistory);
        return new ResponseEntity<>("Successfully saved holiday history!", HttpStatus.OK);
    }

    @GetMapping("/holidayhistory/{historyId}")
    public ResponseEntity<List<Visitor>> findAllVisitor(@PathVariable Integer historyId) {
        List<Visitor> visitorList= this.holidayRepository.findById(historyId).get().getVisitorList();
        return new ResponseEntity<>(visitorList, HttpStatus.OK);
    }

    @PostMapping("/holidayhistory/{historyId}")
    public ResponseEntity<String> saveVisitor(@PathVariable Integer historyId , @Valid @RequestBody Visitor visitor) {
        this.holidayRepository.findById(historyId).map(holidayHistory -> {
            List<Visitor> l = holidayHistory.getVisitorList();
            l.add(visitor);
            holidayHistory.setVisitorList(l);
            return this.holidayRepository.save(holidayHistory);
        });
        return new ResponseEntity<>("Successfully saved the visitor!", HttpStatus.OK);
    }

}