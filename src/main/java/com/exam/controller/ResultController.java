package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Result;
import com.exam.service.impl.ResultServiceImpl;

@RestController
@RequestMapping("/result")
@CrossOrigin("*")
public class ResultController {

	@Autowired
	ResultServiceImpl service;

	@PostMapping("")
	public ResponseEntity<Result> addResult(@RequestBody Result result) {

		Result bhu = service.AddResult(result);
		return ResponseEntity.ok(bhu);

	}

}
