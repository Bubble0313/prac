package com.example.practice.controller;

import com.example.practice.model.HolidayHistory;
import com.example.practice.model.Visitor;
import com.example.practice.repository.HolidayRepository;
import com.example.practice.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class HolidayController {

    @Autowired
    HolidayRepository holidayRepository;

    @Autowired
    VisitorRepository visitorRepository;

    public void setUp() {
        Visitor visitor1 = new Visitor(1, "Amy","Zhang");
        visitorRepository.save(visitor1);
        Visitor visitor2 = new Visitor(2, "Emma","Rela");
        visitorRepository.save(visitor2);
        Visitor visitor3 = new Visitor(3, "Anna","Marie");
        visitorRepository.save(visitor3);
        Visitor visitor4 = new Visitor(4, "Steve","Cousins");
        visitorRepository.save(visitor4);
        Visitor visitor5 = new Visitor(5, "Lily","Wang");
        visitorRepository.save(visitor5);
        Visitor visitor6 = new Visitor(6, "Rose","Tung");
        visitorRepository.save(visitor6);

        List<Visitor> list1 = new ArrayList<>();
        list1.add(visitor1);
        list1.add(visitor2);
        HolidayHistory holidayHistory1 = new HolidayHistory(1, "13032020", "Shenyang", list1);
        this.holidayRepository.save(holidayHistory1);
        List<Visitor> list2 = new ArrayList<>();
        list2.add(visitor3);
        HolidayHistory holidayHistory2 = new HolidayHistory(2, "12102019", "London", list2);
        this.holidayRepository.save(holidayHistory2);
        List<Visitor> list3 = new ArrayList<>();
        list3.add(visitor4);
        list3.add(visitor5);
        list3.add(visitor6);
        HolidayHistory holidayHistory3 = new HolidayHistory(3, "13042018", "Birmingham", list3);
        this.holidayRepository.save(holidayHistory3);
    }

    @GetMapping("/HolidayHistory")
    public Iterable<HolidayHistory> findAllFromHistory() {
        setUp();
        Iterable<HolidayHistory> h1 = holidayRepository.findAll();
        return h1;
    }

    @PostMapping("/HolidayHistory")
    public Iterable<HolidayHistory> saveIntoHistory(@Valid @RequestBody HolidayHistory holidayHistory) {
        setUp();
        this.holidayRepository.save(holidayHistory);
        return this.holidayRepository.findAll();
    }

}