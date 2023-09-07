package com.example.finance.controller;

import com.example.finance.model.DataEntry;
import com.example.finance.model.EntityFinance;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.*;

@RestController
@RequestMapping("/finance")
public class FinanceController {

    private final Map<Long, List<String>> financeMap = new HashMap<>();
    private final List<DataEntry> financeList = new ArrayList<>();
    private long idCounter = 1;

    @GetMapping
    public ResponseEntity<List<EntityFinance>> getAllFinance() {
        List<EntityFinance> entityFinance = new ArrayList<>();

        for (DataEntry entry : financeList) {
            System.out.println("oiii" + Long.parseLong(entry.getValues(0)));
            entityFinance.add(new EntityFinance(Long.parseLong(entry.getValues(0)), Integer.parseInt(entry.getValues(1))));
        }

        return ResponseEntity.ok(entityFinance);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EntityFinance> getFinanceById(@PathVariable Long id) {
        //List<String> message = financeMap.get(id);
        String id_financeList = financeList.get(id.intValue()).getValues(0);
        String timeMonth = financeList.get((id.intValue())).getValues(1);

        if (timeMonth != null) {
            return ResponseEntity.ok(new EntityFinance(id, Integer.parseInt(timeMonth)));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EntityFinance> createFinance(@RequestBody EntityFinance entityfinance) {
        //List<String> values = Arrays.asList(entityfinance.getValues(), entityfinance.getTimeMonth());
        //financeMap.put(idCounter, values);
        financeList.add(new DataEntry(String.valueOf(idCounter), String.valueOf(entityfinance.getTimeMonth())));
        entityfinance.setId(idCounter);
        idCounter++;
        return ResponseEntity.ok(entityfinance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityFinance> updateFinance(@PathVariable Long id, @RequestBody EntityFinance entityfinance) {
        if (financeList.get(id.intValue()) != null) {
            financeList.get(id.intValue()).putValues(id.intValue(), String.valueOf(entityfinance.getTimeMonth()));
            return ResponseEntity.ok(new EntityFinance(id, entityfinance.getTimeMonth()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinance(@PathVariable Long id) {

        if (financeList.get(id.intValue()) != null)  {
            financeList.get(id.intValue()).deleteValues(id.intValue());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}