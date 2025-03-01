package com.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Result;
import com.exam.repo.ResultRepository;
import com.exam.service.ResultService;
@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	ResultRepository repository;

	@Override
	public Result AddResult(Result result) {
		return repository.save(result);

	}

}
