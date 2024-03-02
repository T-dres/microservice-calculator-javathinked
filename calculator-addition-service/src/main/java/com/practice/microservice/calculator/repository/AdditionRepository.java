package com.practice.microservice.calculator.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.practice.microservice.calculator.service.model.Result;

@Repository
public interface AdditionRepository extends JpaRepository<Result, Long> {

    List<Result> findByOperation(String operation);
}
