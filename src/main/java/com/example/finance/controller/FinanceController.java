package com.example.finance.controller;

import com.example.finance.model.EntityFinance;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/finance")
public class FinanceController {

    private final Map<Long, String> financeMap = new HashMap<>();
    private long idCounter = 1;

    @GetMapping
    public ResponseEntity<List<EntityFinance>> getAllFinance() {
        List<EntityFinance> entityFinance = new ArrayList<>();

        for (Map.Entry<Long, String> entry : financeMap.entrySet()) {
            entityFinance.add(new EntityFinance(entry.getKey(), entry.getValue()));
        }
        return ResponseEntity.ok(entityFinance);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EntityFinance> getFinanceById(@PathVariable Long id) {
        String message = financeMap.get(id);

        if (message != null) {
            return ResponseEntity.ok(new EntityFinance(id,message));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EntityFinance> createFinance(@RequestBody EntityFinance entityfinance) {
        financeMap.put(idCounter, entityfinance.getInitialMoney());
        entityfinance.setId(idCounter);
        idCounter++;
        return ResponseEntity.ok(entityfinance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityFinance> updateFinance(@PathVariable Long id, @RequestBody EntityFinance entityfinance) {
        if (financeMap.containsKey(id)) {
            financeMap.put(id, entityfinance.getInitialMoney());
            return ResponseEntity.ok(new EntityFinance(id, entityfinance.getInitialMoney()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinance(@PathVariable Long id) {
        if (financeMap.remove(id) != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}