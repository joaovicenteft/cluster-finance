package com.example.finance.controller;

import com.example.finance.model.DataEntry;
import com.example.finance.model.EntityFinance;
import com.example.finance.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    private final Map<Long, List<String>> financeMap = new HashMap<>();
    private final List<DataEntry> financeList = new ArrayList<>();
    private long idCounter = 1;

    @GetMapping
    public ResponseEntity<List<EntityFinance>> getAllFinance() {
        List<EntityFinance> entityFinance = new ArrayList<>();

        entityFinance = financeService.findAll(financeList, entityFinance);

        return ResponseEntity.ok(entityFinance);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<List<EntityFinance>> getFinanceById(@PathVariable Long id) {
        List<EntityFinance> entityFinance = new ArrayList<>();
        entityFinance = financeService.findSpecificId(financeList, id, entityFinance);

        if (entityFinance != null) {
            return ResponseEntity.ok(entityFinance);
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<EntityFinance> createFinance(@RequestBody EntityFinance entityfinance) {
        EntityFinance entityFinance = financeService.postData(financeList, entityfinance, idCounter);
        idCounter++;
        return ResponseEntity.ok(entityfinance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityFinance> updateFinance(@PathVariable Long id, @RequestBody EntityFinance entityfinance) {

        EntityFinance entityFinance;
        entityFinance = financeService.putData(financeList, id, entityfinance);

        if (entityFinance != null) {
            return ResponseEntity.ok(entityFinance);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinance(@PathVariable Long id) {

        if (financeService.deleteData(financeList, id) == 1)
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }
}