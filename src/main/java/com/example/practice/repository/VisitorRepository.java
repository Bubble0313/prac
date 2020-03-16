package com.example.practice.repository;

import com.example.practice.model.Visitor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorRepository extends CrudRepository<Visitor,Integer> {

}
