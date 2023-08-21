package com.example.finance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FinanceController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/finance")
    public String financeData(@RequestParam(value = "name", defaultValue = "John Doe") String name) {
        String indexData = "Welcome to the finance system " + name + "!";
        return indexData;
    }

}