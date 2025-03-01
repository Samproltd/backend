package com.exam.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.email.dto.MailRequest;
import com.exam.email.dto.MailResponse;
import com.exam.service.impl.EmailService;

@RestController
@CrossOrigin("*")
public class SpringBootEmailFreemarkerApplication {

	@Autowired
	private EmailService service;

	@PostMapping("/sendingEmail")
	public MailResponse sendEmail(@RequestBody MailRequest request) {
		System.out.println("-----------------------------");
		System.out.println(request.getName());
		System.out.println(request.getSubject());
		System.out.println("-----------------------------");
		request.setFrom("bhushan0242@gmail.com");
		Map<String, Object> model = new HashMap<>();
		model.put("Name", request.getName());
		model.put("location", "Pune, Maharastra ");
		
		return service.sendEmail(request, model);

	}

}
