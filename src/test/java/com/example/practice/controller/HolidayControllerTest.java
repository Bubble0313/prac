package com.example.practice.controller;

import com.example.practice.PracticeApplication;
import com.example.practice.model.HolidayHistory;
import com.example.practice.model.Visitor;
import com.example.practice.repository.HolidayRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = PracticeApplication.class)
//class HolidayControllerTest {
//    @Autowired
//    private HolidayRepository holidayRepository;
//    @Autowired
//    private HolidayController holidayController = new HolidayController();
//
//    @Test
//    public void testNotNull(){
//        Assertions.assertThat(this.holidayController).isNotNull();
//    }
//
//    @Test
//    public void testSave(){
//        HolidayHistory holidayHistory1 = new HolidayHistory(1, "13032020", "Shenyang", Collections.emptyList());
//        Assertions.assertThat(this.holidayController.saveHistory(holidayHistory1).getBody()).isNotNull().isNotEmpty().hasSize(1);
//    }
//}




@RunWith(MockitoJUnitRunner.class)
class HolidayControllerTest {

    @Mock
    private HolidayRepository holidayRepository;

    @InjectMocks
    private HolidayController holidayController = new HolidayController();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNotNull(){
        Assertions.assertThat(this.holidayController).isNotNull();
    }

    @Test
    public void testFindAll(){
        HolidayHistory holidayHistory1 = new HolidayHistory(1, "13032020", "Shenyang", Collections.emptyList());
        when(holidayRepository.findAll()).thenReturn(Collections.singletonList(holidayHistory1));
        ResponseEntity<List<HolidayHistory>> allFromHistory = holidayController.findAllHistory();
        Assertions.assertThat(allFromHistory.getBody()).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(allFromHistory.getBody()).isNotNull().isNotEmpty().hasSize(1).isEqualTo(Collections.singletonList(holidayHistory1));
        Mockito.verify(this.holidayRepository).findAll();
    }

    @Test
    public void testFindNull(){
        when(holidayRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<List<HolidayHistory>> allHistory = holidayController.findAllHistory();
        Assertions.assertThat(allHistory.getBody()).isNotNull().isEmpty();
        Mockito.verify(this.holidayRepository).findAll();
    }

    @Test
    public void testSaveSuccess(){
        HolidayHistory holidayHistory1 = new HolidayHistory(1, "13032020", "Shenyang", Collections.emptyList());
        when(holidayRepository.save(any(HolidayHistory.class))).thenReturn(holidayHistory1);
        ResponseEntity<String> message = holidayController.saveHistory(holidayHistory1);
        Assertions.assertThat(message.getBody()).isNotNull().isEqualTo("Successfully saved holiday history!");
    }

    @Test
    public void testSaveFailure(){
        HolidayHistory holidayHistory = new HolidayHistory();
        when(holidayRepository.save(any(HolidayHistory.class))).thenReturn(holidayHistory);
        ResponseEntity<String> message = holidayController.saveHistory(holidayHistory);
        Assertions.assertThat(message.getBody()).isNotNull().isEqualTo("Successfully saved holiday history!");
    }

}