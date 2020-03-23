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
<<<<<<< HEAD
import java.util.List;
=======
import javax.validation.constraints.Null;
import java.util.*;
>>>>>>> 8bfc8caf12bc48082c103876a21cdca98220de83

@RestController
public class HolidayController {

    @Autowired
    private HolidayRepository holidayRepository;

    //first level
<<<<<<< HEAD

    @PostMapping("/holidayhistory")
    public ResponseEntity<String> saveHistory(@Valid @RequestBody HolidayHistory holidayHistory) {
        try{
        this.holidayRepository.save(holidayHistory);
        return new ResponseEntity<>("Successfully saved holiday history!", HttpStatus.OK);}
        catch (Exception e)
        {
            return new ResponseEntity<>("Not valid input!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/holidayhistory")
    public ResponseEntity<List<HolidayHistory>> findAllHistory() {
        List<HolidayHistory> holidayHistoryList = Lists.newArrayList(this.holidayRepository.findAll());
        return new ResponseEntity<>(holidayHistoryList, HttpStatus.OK);
    }

    @GetMapping("/holidayhistory/{historyId}")
    public ResponseEntity<HolidayHistory> findHistoryById(@PathVariable Integer historyId) {
        if (historyId <= Lists.newArrayList(this.holidayRepository.findAll()).size()){
            HolidayHistory holidayHistory= this.holidayRepository.findById(historyId).get();
            return new ResponseEntity<>(holidayHistory, HttpStatus.OK);}
        else {
            throw new NullPointerException("No such history!");
=======
    @GetMapping("/holidayhistory/allhistory")
    public ResponseEntity<List<HolidayHistory>> findAllHistory() {
        List<HolidayHistory> holidayHistoryList = Lists.newArrayList(this.holidayRepository.findAll());
        return new ResponseEntity<>(holidayHistoryList, HttpStatus.OK);
    }

    @GetMapping("/holidayhistory/{historyId}")
    public ResponseEntity<HolidayHistory> findHistoryById(@PathVariable Integer historyId) {
        if (historyId <= Lists.newArrayList(this.holidayRepository.findAll()).size()){
            HolidayHistory holidayHistory= this.holidayRepository.findById(historyId).get();
            return new ResponseEntity<>(holidayHistory, HttpStatus.OK);}
        else {
            throw new NullPointerException("No such history");
        }
    }

    @PostMapping("/holidayhistory/savehistory")
    public ResponseEntity<String> saveHistory(@Valid @RequestBody HolidayHistory holidayHistory) {
        try{
        this.holidayRepository.save(holidayHistory);
        return new ResponseEntity<>("Successfully saved holiday history!", HttpStatus.OK);}
        catch (Exception e)
        {
            return new ResponseEntity<>("Not valid input", HttpStatus.BAD_REQUEST);
>>>>>>> 8bfc8caf12bc48082c103876a21cdca98220de83
        }
    }

    //second level
<<<<<<< HEAD

    @PostMapping("/holidayhistory/{historyId}/visitor")
    public ResponseEntity<String> saveVisitorByHistoryId(@PathVariable Integer historyId , @Valid @RequestBody Visitor visitor) {
        try{
        this.holidayRepository.findById(historyId).map(holidayHistory -> {
            List<Visitor> l = holidayHistory.getVisitorList();
            l.add(visitor);
            holidayHistory.setVisitorList(l);
            return this.holidayRepository.save(holidayHistory);
        });
        return new ResponseEntity<>("Successfully saved the visitor!", HttpStatus.OK);}
        catch(Exception e)
        {
            return new ResponseEntity<>("Not valid input!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/holidayhistory/{historyId}/visitor")
    public ResponseEntity<List<Visitor>> findAllVisitorByHistoryId(@PathVariable Integer historyId) {
        if (historyId <= Lists.newArrayList(this.holidayRepository.findAll()).size()){
            List<Visitor> visitorList= this.holidayRepository.findById(historyId).get().getVisitorList();
            return new ResponseEntity<>(visitorList, HttpStatus.OK);}
        else {
            throw new NullPointerException("No such history!");
        }
    }

    @GetMapping("/holidayhistory/{historyId}/visitor/{order}")
    public ResponseEntity<Visitor> findVisitorByHistoryIdAndOrder(@PathVariable Integer historyId, @PathVariable Integer order) {
        if (historyId <= Lists.newArrayList(this.holidayRepository.findAll()).size()){
            List<Visitor> list = this.holidayRepository.findById(historyId).get().getVisitorList();
            if(order <= list.size()){
                Visitor visitor = list.get(order-1);
                return new ResponseEntity<>(visitor, HttpStatus.OK);
            }
            else{
                throw new NullPointerException("No such visitors!");
            }
        }
        else{
            throw new NullPointerException("No such history!");
        }
=======
    @GetMapping("/holidayhistory/{historyId}/allvisitor")
    public ResponseEntity<List<Visitor>> findAllVisitorByHistoryId(@PathVariable Integer historyId) {
        if (historyId <= Lists.newArrayList(this.holidayRepository.findAll()).size()){
        List<Visitor> visitorList= this.holidayRepository.findById(historyId).get().getVisitorList();
        return new ResponseEntity<>(visitorList, HttpStatus.OK);}
        else {
            throw new NullPointerException("No such history");
        }
    }

    @GetMapping("/holidayhistory/{historyId}/visitor/{order}")
    public ResponseEntity<Visitor> findVisitorByHistoryIdAndOrder(@PathVariable Integer historyId, @PathVariable Integer order) {
        Visitor visitor= this.holidayRepository.findById(historyId).get().getVisitorList().get(order-1);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }

    @PostMapping("/holidayhistory/{historyId}/savevisitor")
    public ResponseEntity<String> saveVisitorByHistoryId(@PathVariable Integer historyId , @Valid @RequestBody Visitor visitor) {
        this.holidayRepository.findById(historyId).map(holidayHistory -> {
            List<Visitor> l = holidayHistory.getVisitorList();
            l.add(visitor);
            holidayHistory.setVisitorList(l);
            return this.holidayRepository.save(holidayHistory);
        });
        return new ResponseEntity<>("Successfully saved the visitor!", HttpStatus.OK);
>>>>>>> 8bfc8caf12bc48082c103876a21cdca98220de83
    }
}