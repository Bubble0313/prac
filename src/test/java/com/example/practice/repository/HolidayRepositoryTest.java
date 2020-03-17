package com.example.practice.repository;

import com.example.practice.model.HolidayHistory;
import com.example.practice.model.Visitor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HolidayRepositoryTest {

    @Autowired
    HolidayRepository holidayRepository;

    @Autowired
    VisitorRepository visitorRepository;

    @Test
    public void testCanSave(){
        Visitor visitor1 = new Visitor(1, "Amy","Zhang");
        visitorRepository.save(visitor1);
        Visitor visitor2 = new Visitor(2, "Emma","Rela");
        visitorRepository.save(visitor2);
        List<Visitor> list = new ArrayList<>();
        list.add(visitor1);
        list.add(visitor2);
        HolidayHistory holidayHistory = new HolidayHistory(1, "Shenyang", "03132020", list);
        this.holidayRepository.save(holidayHistory);
        Assertions.assertThat(this.holidayRepository.findById(1).get()).isNotNull().isEqualTo(holidayHistory);
    }
}