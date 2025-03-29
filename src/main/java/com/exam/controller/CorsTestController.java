package com.exam.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cors-test")
@CrossOrigin("*") // This annotation is important
public class CorsTestController {

    @GetMapping("/status")
    public Map<String, Object> testCors() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "ok");
        response.put("message", "CORS is working!");
        return response;
    }
} 