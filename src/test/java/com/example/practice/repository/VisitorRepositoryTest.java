package com.example.practice.repository;

import com.example.practice.model.Visitor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VisitorRepositoryTest {

    @Autowired
    private VisitorRepository visitorRepository;

    @Test
    public void testCanSave(){
        Visitor visitor = new Visitor(1, "Amy","Zhang");
        this.visitorRepository.save(visitor);
        Assertions.assertThat(visitorRepository.findById(1).get().toString()).isNotNull().isEqualTo(visitor.toString());
    }

    @Test
    public void testCanFindById(){
        testCanSave();
    }
}