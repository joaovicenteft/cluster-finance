package com.example.finance.controller;

import com.example.finance.model.EntityFinance;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/finance")
public class FinanceController {

    private final Map<Long, Double> financeMap = new HashMap<>();
    private long idCounter = 1;

    @GetMapping
    public ResponseEntity<List<EntityFinance>> getAllFinance() {
        List<EntityFinance> entityFinance = new ArrayList<>();

        for (Map.Entry<Long, Double> entry : financeMap.entrySet()) {
            entityFinance.add(new EntityFinance(entry.getKey(), entry.getValue()));
        }
        return ResponseEntity.ok(entityFinance);
    }

    @GetMapping("/finance")
    public String financeData(@RequestParam(value = "name", defaultValue = "John Doe") String name) {
        String indexData = "Welcome to the finance system " + name + "!";
        return indexData;
    }

}