package com.example.practice.controller;


import com.example.practice.model.HolidayHistory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HolidayControllerITTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindSaveWithMockMVC() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/holidayhistory")
                .content(String.valueOf(new HolidayHistory(1, "13032020", "Shenyang", Collections.emptyList())))
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
