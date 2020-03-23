package com.example.practice.controller;

//import com.example.practice.Handler.NotFoundAdvice;
import com.example.practice.model.HolidayHistory;
import com.example.practice.model.Visitor;
import com.example.practice.repository.HolidayRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

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
    public void testSaveValidHistory(){
        HolidayHistory holidayHistory1 = new HolidayHistory(1, "13032020", "Shenyang", Collections.emptyList());
        //when(holidayRepository.save(any(HolidayHistory.class))).thenReturn(holidayHistory1);
        ResponseEntity<String> message = holidayController.saveHistory(holidayHistory1);
        Assertions.assertThat(message.getBody()).isNotNull().isEqualTo("Successfully saved holiday history!");
    }

    @Test
    public void testSaveInvalidHistory(){
        HolidayHistory holidayHistory = new HolidayHistory();
        holidayHistory.setId(null);
        holidayHistory.setDate(null);
        holidayHistory.setDestination(null);
        holidayHistory.setVisitorList(null);
        //HolidayHistory holidayHistory1 = null;
        //when(holidayRepository.save(any(HolidayHistory.class))).thenReturn(holidayHistory);
        when(holidayRepository.save(holidayHistory)).thenThrow(new IllegalArgumentException());
        ResponseEntity<String> message = holidayController.saveHistory(holidayHistory);
        Assertions.assertThat(message.getBody()).isNotNull().isEqualTo("Not valid input!");
    }

    @Test
    public void testFindAllHistoryNotEmpty(){
        HolidayHistory holidayHistory1 = new HolidayHistory(1, "13032020", "Shenyang", Collections.emptyList());
        when(holidayRepository.findAll()).thenReturn(Collections.singletonList(holidayHistory1));
        ResponseEntity<List<HolidayHistory>> allFromHistory = holidayController.findAllHistory();
        Assertions.assertThat(allFromHistory.getBody()).isNotNull().isNotEmpty().hasSize(1).isEqualTo(Collections.singletonList(holidayHistory1));
    }

    @Test
    public void testFindAllHistoryEmpty(){
        when(holidayRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<List<HolidayHistory>> allHistory = holidayController.findAllHistory();
        Assertions.assertThat(allHistory.getBody()).isNotNull().isEmpty();
    }

    @Test
    public void testFindHistoryByValidId(){
        Visitor visitor1 = new Visitor();
        visitor1.setVid(1);
        visitor1.setFirstName("Amy");
        visitor1.setLastName("Zhang");
        List<Visitor> list1 = new ArrayList<>();
        list1.add(visitor1);
        HolidayHistory holidayHistory1 = new HolidayHistory(1, "13032020", "Shenyang", list1);
        when(holidayRepository.findAll()).thenReturn(Collections.singletonList(holidayHistory1));
        when(holidayRepository.findById(1)).thenReturn(Optional.of(holidayHistory1));
        ResponseEntity<HolidayHistory> all = holidayController.findHistoryById(1);
        Assertions.assertThat(all.getBody()).isNotNull().isEqualTo(holidayHistory1);
    }

    @Test
    public void testFindHistoryByInvalidId() {
        Visitor visitor1 = new Visitor(1, "Amy","Zhang");
        List<Visitor> list1 = new ArrayList<>();
        list1.add(visitor1);
        HolidayHistory holidayHistory1 = new HolidayHistory(1, "13032020", "Shenyang", list1);
        when(holidayRepository.findAll()).thenReturn(Collections.singletonList(holidayHistory1));
       // when(holidayRepository.findById(2)).thenThrow(new NullPointerException("No such history!"));
        Assertions.assertThatNullPointerException().isThrownBy(()->holidayController.findHistoryById(2));
        Exception exception = assertThrows(NullPointerException.class, ()->{holidayController.findHistoryById(2);});
        String expectedMsg = "No such history!";
        String actualMsg = exception.getMessage();
        org.junit.jupiter.api.Assertions.assertTrue(actualMsg.contains(expectedMsg));
    }

    @Test
    public void testSaveValidVisitorByHistoryId(){
        Visitor visitor1 = new Visitor(1, "Amy","Zhang");
        HolidayHistory holidayHistory1 = new HolidayHistory(1, "13032020", "Shenyang", Collections.emptyList());
        ResponseEntity<String> message = holidayController.saveVisitorByHistoryId(1,visitor1);
        Assertions.assertThat(message.getBody()).isNotNull().isEqualTo("Successfully saved the visitor!");
    }

    @Test
    public void testSaveInValidVisitorByHistoryId(){
        Visitor visitor1 = new Visitor(1, "Amy",null);
        HolidayHistory holidayHistory1 = new HolidayHistory(1, "13032020", "Shenyang", Collections.emptyList());
        when(this.holidayRepository.findById(1).map(holidayHistory -> {
            List<Visitor> l = holidayHistory.getVisitorList();
            l.add(visitor1);
            holidayHistory.setVisitorList(l);
            return this.holidayRepository.save(holidayHistory);
        })).thenThrow(new IllegalArgumentException());
        ResponseEntity<String> message = holidayController.saveVisitorByHistoryId(1,visitor1);
        Assertions.assertThat(message.getBody()).isNotNull().isEqualTo("Not valid input!");
    }

    @Test
    public void testFindAllVisitorByValidHistoryId(){
        Visitor visitor1 = new Visitor(1, "Amy","Zhang");
        List<Visitor> list1 = new ArrayList<>();
        list1.add(visitor1);
        HolidayHistory holidayHistory1 = new HolidayHistory(1, "13032020", "Shenyang", list1);
        when(holidayRepository.findAll()).thenReturn(Collections.singletonList(holidayHistory1));
        when(holidayRepository.findById(1)).thenReturn(Optional.of(holidayHistory1));
        ResponseEntity<List<Visitor>> all = holidayController.findAllVisitorByHistoryId(1);
        Assertions.assertThat(all.getBody()).isNotNull().isNotEmpty().hasSize(1).isEqualTo(holidayHistory1.getVisitorList());
    }

    @Test
    public void testFindAllVisitorByInvalidHistoryId(){
        Visitor visitor1 = new Visitor(1, "Amy","Zhang");
        List<Visitor> list1 = new ArrayList<>();
        list1.add(visitor1);
        HolidayHistory holidayHistory1 = new HolidayHistory(1, "13032020", "Shenyang", list1);
        when(holidayRepository.findAll()).thenReturn(Collections.singletonList(holidayHistory1));
        //when(holidayRepository.findById(2)).thenThrow(new NullPointerException("No such history!"));
       // ResponseEntity<List<Visitor>> all = holidayController.findAllVisitorByHistoryId(2);
        Assertions.assertThatNullPointerException().isThrownBy(()->holidayController.findAllVisitorByHistoryId(2));
    }


    @Test
    public void testfindVisitorByValidHistoryIdAndValidOrder()
    {
        Visitor visitor1 = new Visitor(1, "Amy","Zhang");
        List<Visitor> list1 = new ArrayList<>();
        list1.add(visitor1);
        HolidayHistory holidayHistory1 = new HolidayHistory(1, "13032020", "Shenyang", list1);
        when(holidayRepository.findAll()).thenReturn(Collections.singletonList(holidayHistory1));
        when(holidayRepository.findById(1)).thenReturn(Optional.of(holidayHistory1));
        ResponseEntity<Visitor> message = holidayController.findVisitorByHistoryIdAndOrder(1,1);
        Assertions.assertThat(message.getBody()).isNotNull().isEqualTo(visitor1);
    }


    @Test
    public void testfindVisitorByValidHistoryIdAndInvalidOrder()
    {
        Visitor visitor1 = new Visitor(1, "Amy","Zhang");
        List<Visitor> list1 = new ArrayList<>();
        list1.add(visitor1);
        HolidayHistory holidayHistory1 = new HolidayHistory(1, "13032020", "Shenyang", list1);
        when(holidayRepository.findAll()).thenReturn(Collections.singletonList(holidayHistory1));
        when(holidayRepository.findById(1)).thenReturn(Optional.of(holidayHistory1));
        Assertions.assertThatNullPointerException().isThrownBy(()->holidayController.findVisitorByHistoryIdAndOrder(1,2));
    }

    @Test
    public void testfindVisitorByInValidHistoryIdAndOrder()
    {
        Visitor visitor1 = new Visitor(1, "Amy","Zhang");
        List<Visitor> list1 = new ArrayList<>();
        list1.add(visitor1);
        HolidayHistory holidayHistory1 = new HolidayHistory(1, "13032020", "Shenyang", list1);
        when(holidayRepository.findAll()).thenReturn(Collections.singletonList(holidayHistory1));
        Assertions.assertThatNullPointerException().isThrownBy(()->holidayController.findVisitorByHistoryIdAndOrder(2,1));
    }

}