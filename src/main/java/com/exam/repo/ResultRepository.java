package com.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.Result;

public interface ResultRepository extends JpaRepository<Result,Long> {

}
